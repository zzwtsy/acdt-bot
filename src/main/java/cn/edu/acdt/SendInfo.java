package cn.edu.acdt;

import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.event.ListeningStatus;
import net.mamoe.mirai.event.SimpleListenerHost;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import org.jetbrains.annotations.NotNull;

import static cn.edu.acdt.Acdt.botSettings;

/**
 * @author Daydreamer
 */
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
    private ListeningStatus onEvent(@NotNull GroupMessageEvent event) {
        message = event.getMessage().contentToString();
        if (message.equals(botSettings.getInfoCommand())) {
            //获取电量信息
            getInfo.getInfo();
            event.getGroup().sendMessage("剩余购电:" + getInfo.electricityPurchase + "度\n剩余补助:" + getInfo.subsidy + "度\n昨日用电:" + getInfo.electricityConsumption + "度");
        }
        if (message.equals(botSettings.getHelpCommand())) {
            event.getGroup().sendMessage(botSettings.getHelpCommand() + ":获取帮助信息\n" + botSettings.getInfoCommand() + "：发送当前电费信息" + "配置文件提示:" + botSettings.getTips());
        }
        return ListeningStatus.LISTENING;
    }
}
