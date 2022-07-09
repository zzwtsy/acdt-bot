package com.acdt;

import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.event.ListeningStatus;
import net.mamoe.mirai.event.SimpleListenerHost;
import net.mamoe.mirai.event.events.FriendMessageEvent;
import net.mamoe.mirai.event.events.GroupMessageEvent;

public class sendInfo extends SimpleListenerHost {
    String message;
    @EventHandler
    private ListeningStatus onEvent(FriendMessageEvent event) {
        message = event.getMessage().contentToString();
        if (message.equals("#电费")) {
            event.getSender().sendMessage("功能还没写");
        }
        return ListeningStatus.LISTENING;
    }
    @EventHandler
    private ListeningStatus onEvent(GroupMessageEvent event) {
        message = event.getMessage().contentToString();
        if (message.equals("#电费")) {
            event.getGroup().sendMessage("功能还没写");
        }
        return ListeningStatus.LISTENING;
    }
}
