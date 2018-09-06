/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */
package Rpc;

import java.io.Serializable;

import sun.rmi.rmic.iiop.CompoundType;

/**
 *用简单代码实现一个RPC例子，自行选择一个集群通信框架负责底层通信
 *
 *
 *  定义一个RPC接口，这些方法是预留提供给上层具体逻辑处理的入口，
 *  replyRequest方法用于处理响应逻辑，leftOver方法用于残留请求的逻辑处理
 *
 *
 * @author zhangdanfeng01
 * @since 2018/09/06
 */
public interface RpcCallback {

    public Serializable replyRequest(Serializable msg, CompoundType.Member sender);

    public void LeftOver(Serializable msg,CompoundType.Member sender);



}
