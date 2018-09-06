/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */
package Rpc;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.Serializable;

/**
 * 定义通信消息协议
 *
 * 实现Externalizable接口自定义序列化和反序列化，message用于存放响应消息
 * uuid标识用于关联线程，rpcld用于标记rpc实例，reply表示是否回复
 *
 *
 */
public class RpcMessage implements Externalizable {

    protected Serializable message;

    protected byte[] uuid;

    protected byte[] rpcId;

    protected  boolean reply=false;

    public RpcMessage(Serializable message, byte[] uuid, byte[] rpcId) {
        this.message = message;
        this.uuid = uuid;
        this.rpcId = rpcId;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {

        out.writeBoolean(reply);
        out.writeInt(uuid.length);
        out.write(uuid, 0, uuid.length);
        out.writeInt(rpcId.length);
        out.write(rpcId, 0, rpcId.length);
        out.writeObject(message);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {

        reply=in.readBoolean();

        int length=in.readInt();
        uuid=new byte[length];
        in.readFully(uuid);
        length=in.readInt();
        rpcId=new byte[length];
        in.readFully(rpcId);
        message= (Serializable) in.readObject();
    }
}
