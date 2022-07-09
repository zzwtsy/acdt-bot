package com.acdt;

import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.event.ListeningStatus;
import net.mamoe.mirai.event.SimpleListenerHost;
import net.mamoe.mirai.event.events.FriendMessageEvent;

public class sendInfo extends SimpleListenerHost {
    @EventHandler
    private ListeningStatus onEvent(FriendMessageEvent event) {
        String s = event.getMessage().contentToString();
        if (s.equals("/电费")) {
            event.getSender().sendMessage("功能还没写");
        }
        return ListeningStatus.LISTENING;
    }
}
