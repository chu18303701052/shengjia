package com.digov.api.config.shiro.entity;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionIdGenerator;

import java.io.Serializable;
import java.util.UUID;


public class CustomSessionIdGenerator implements SessionIdGenerator {
    @Override
    public Serializable generateId(Session session) {
        //可以使用更加复杂的,例如加解密算法等等算法
        return UUID.randomUUID().toString().replace("-", "");
    }

}
