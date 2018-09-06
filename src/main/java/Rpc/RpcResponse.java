/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */
package Rpc;

import java.io.Serializable;

import sun.rmi.rmic.iiop.CompoundType;

/**
 *
 *响应对象，用于封装接收到的消息，Member在通信框架是节点的抽象，这里用来表示来源节点
 *
 */
public class RpcResponse {
    private CompoundType.Member source;
    private Serializable message;

    public RpcResponse() {}

    public RpcResponse(CompoundType.Member source, Serializable message) {
        this.source = source;
        this.message = message;
    }

    public void setSource(CompoundType.Member source) {
        this.source = source;
    }

    public void setMessage(Serializable message) {
        this.message = message;
    }

    public CompoundType.Member getSource() {
        return source;
    }

    public Serializable getMessage() {
        return message;
    }
}
