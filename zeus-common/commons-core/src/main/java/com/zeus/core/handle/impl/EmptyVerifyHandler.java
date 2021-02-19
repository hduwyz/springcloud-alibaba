package com.zeus.core.handle.impl;

import com.zeus.core.handle.VerifyHandler;

import java.util.List;

public class EmptyVerifyHandler implements VerifyHandler {
    @Override
    public List<Object> verify(List<Object> allObjList) {
        return null;
    }
}
