package com.tvd12.ezyfox.videopoker.client.handler;

import java.util.Map;

import sfs2x.client.entities.User;

public class UserUpdateListener extends BaseEventAdapter {

    @Override
    public void dispatch(User user, Map<String, Object> args) {
        System.out.println("user update listener");
        double money = user.getVariable("1").getDoubleValue();
        double gameMoney = user.getVariable("2").getDoubleValue();
        System.out.println("money = " + money + ", game money = " + gameMoney);
    }
    
}
