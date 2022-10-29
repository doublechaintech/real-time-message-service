package com.doublechaintech.manage;

import java.util.UUID;

public class GlobalId {
    public  String genUUID(){
        return UUID.randomUUID().toString();
    }
}
