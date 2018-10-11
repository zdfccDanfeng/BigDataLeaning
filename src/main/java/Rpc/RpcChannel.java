///*
// * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
// */
//package Rpc;
//
//
////RPC核心类，是整个RPC的抽象，它实现通信框架的ChannelListener接口，
//// 实现了该接口就能在messageReceived方法中处理接收到的消息。因为所有的消息都会通过此方法，所以它必须要根据key
//// 去处理对应的线程，同时它也要负责调用RpcCallback接口定义的相关的方法，
//// 例如响应请求的replyRequest方法和处理残留的响应leftOver方法，残留响应是指有时我们在接收到第一个响应后就唤起线程。
//
//import java.io.Serializable;
//import java.nio.channels.Channel;
//import java.util.Arrays;
//import java.util.HashMap;
//
//import com.fasterxml.jackson.annotation.ObjectIdGenerators;
//
//import io.netty.channel.ChannelException;
//import sun.rmi.rmic.iiop.CompoundType;
//
//public class RpcChannel implements ChannelListener {
//    private Channel channel;
//    private RpcCallback callback;
//    private byte[] rpcId;
//    private int replyMessageOptions = 0;
//    private HashMap<byte[], RpcCollector> responseMap = new HashMap<byte[], RpcCollector>();
//
//    public RpcChannel(byte[] rpcId, Channel channel, RpcCallback callback) {
//        this.rpcId = rpcId;
//        this.channel = channel;
//        this.callback = callback;
//        channel.addChannelListener(this);
//    }
//
//    public RpcResponse[] send(CompoundType.Member[] destination, Serializable message, int rpcOptions,
//                              int channelOptions, long timeout) throws ChannelException {
//        int sendOptions = channelOptions & ~Channel.SEND_OPTIONS_SYNCHRONIZED_ACK;
//        byte[] key = ObjectIdGenerators.UUIDGenerator.randomUUID(false);
//        RpcCollector collector = new RpcCollector(key, rpcOptions, destination.length);
//        try {
//            synchronized (collector) {
//                if (rpcOptions != RpcResponseType.NO_REPLY) responseMap.put(key, collector);
//                RpcMessage rmsg = new RpcMessage(rpcId, key, message);
//                channel.send(destination, rmsg, sendOptions);
//                if (rpcOptions != RpcResponseType.NO_REPLY) collector.wait(timeout);
//            }
//        } catch (InterruptedException ix) {
//            Thread.currentThread().interrupt();
//        } finally {
//            responseMap.remove(key);
//        }
//        return collector.getResponses();
//    }
//
//    @Override
//    public void messageReceived(Serializable msg, CompoundType.Member sender) {
//        RpcMessage rmsg = (RpcMessage) msg;
//        byte[] key = rmsg.uuid;
//        if (rmsg.reply) {
//            RpcCollector collector = responseMap.get(key);
//            if (collector == null) {
//                callback.leftOver(rmsg.message, sender);
//            } else {
//
//                synchronized (collector) {
//                    if (responseMap.containsKey(key)) {
//                        collector.addResponse(rmsg.message, sender);
//                        if (collector.isComplete()) collector.notifyAll();
//                    } else {
//                        callback.leftOver(rmsg.message, sender);
//                    }
//                }
//            }
//        } else {
//            Serializable reply = callback.replyRequest(rmsg.message, sender);
//            rmsg.reply = true;
//            rmsg.message = reply;
//            try {
//                channel.send(new CompoundType.Member[] {sender}, rmsg,
//                        replyMessageOptions & ~Channel.SEND_OPTIONS_SYNCHRONIZED_ACK);
//            } catch (Exception x) {}
//        }
//    }
//
//    @Override
//    public boolean accept(Serializable msg, CompoundType.Member sender) {
//        if (msg instanceof RpcMessage) {
//            RpcMessage rmsg = (RpcMessage) msg;
//            return Arrays.equals(rmsg.rpcId, rpcId);
//        } else
//            return false;
//    }
//}
