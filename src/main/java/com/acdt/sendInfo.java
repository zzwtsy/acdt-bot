package com.acdt;

import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.event.ListeningStatus;
import net.mamoe.mirai.event.SimpleListenerHost;
import net.mamoe.mirai.event.events.FriendMessageEvent;
import net.mamoe.mirai.event.events.GroupMessageEvent;

public class sendInfo extends SimpleListenerHost {
    String message;
    /**
     * 回复个人消息
     *
     * @param event 机器人收到的好友消息的事件
     * @return 继续监听
     */
    @EventHandler
    private ListeningStatus onEvent(FriendMessageEvent event) {
        message = event.getMessage().contentToString();
        if (message.equals("#电费")) {
            event.getSender().sendMessage("功能还没写");
        }
        if(message.equals("#help")){
            event.getSender().sendMessage("#help：帮助信息\n#电费：发送当前电费余额\n");
        }
        return ListeningStatus.LISTENING;
    }

    /**
     * 回复群消息
     *
     * @param event 机器人收到的群消息的事件
     * @return 继续监听
     */
    @EventHandler
    private ListeningStatus onEvent(GroupMessageEvent event) {
        message = event.getMessage().contentToString();
        if (message.equals("#电费")) {
            event.getGroup().sendMessage("功能还没写");
        }
        if(message.equals("#help")){
            event.getGroup().sendMessage("#help：帮助信息\n#电费：发送当前电费余额\n");
        }
        return ListeningStatus.LISTENING;
    }
}
