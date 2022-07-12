package com.acdt;

import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.event.ListeningStatus;
import net.mamoe.mirai.event.SimpleListenerHost;
import net.mamoe.mirai.event.events.GroupMessageEvent;

import static com.acdt.Acdt.botSettings;

public class SendInfo extends SimpleListenerHost {
    GetInfo getInfo = new GetInfo();
    String message;

    /**
     * 回复群消息
     *
     * @param event 机器人收到的群消息的事件
     * @return 继续监听
     */
    @EventHandler
    private ListeningStatus onEvent(GroupMessageEvent event) {
        message = event.getMessage().contentToString();
        if (botSettings.getInfoCommand().equals(message)) {
            //获取电量信息
            new GetInfo().getInfo();
            event.getGroup().sendMessage(getInfo.getInfo());
        }
        if (botSettings.getHelpCommand().equals(message)) {
            event.getGroup().sendMessage("#help：帮助信息\n#电费：发送当前电费信息");
        }
        return ListeningStatus.LISTENING;
    }
}
