package com.doublechaintech.channel;

import com.doublechaintech.manage.Audience;
import com.doublechaintech.manage.ChannelManager;
import org.junit.jupiter.api.Test;

public class ChannelTest {
    
    @Test
    public void runTest(){
        ChannelManager m=new ChannelManager();
        m.listenToChannel("c1", Audience.withName("a1"));
        m.listenToChannel("c1", Audience.withName("a2"));
        m.listenToChannel("c2", Audience.withName("a3"));
        m.listenToChannel("c2", Audience.withName("a4"));
        System.out.println(m.listSubscriptionText());

    }
}
