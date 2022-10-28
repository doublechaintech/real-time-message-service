package com.doublechaintech.channel;

import com.doublechaintech.manage.Audience;
import com.doublechaintech.manage.ChannelManager;
import org.junit.jupiter.api.Test;

public class ChannelTest {

    @Test
    public void runTest(){
        ChannelManager m=new ChannelManager();
        m.switchChannel("c1", Audience.withName("a1"));
        m.switchChannel("c1", Audience.withName("a2"));
        m.switchChannel("c2", Audience.withName("a3"));
        m.switchChannel("c2", Audience.withName("a4"));
        System.out.println(m.listSubscriptionText());

    }
}
