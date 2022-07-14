package cn.edu.acdt;

import net.mamoe.mirai.Bot;
import net.mamoe.mirai.event.SimpleListenerHost;

import java.util.Objects;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static cn.edu.acdt.Acdt.botSettings;

/**
 * @author Daydreamer
 */
public class TimingTask extends SimpleListenerHost {
    /**
     * 定时监测电费信息是否小余设定的值
     */
    public void sendMessage() {
        Runnable runnable = () -> {
            GetInfo getInfo = new GetInfo();
            getInfo.getInfo();
            float electricityInformation = Float.parseFloat(getInfo.getElectricityPurchase()) + Float.parseFloat(getInfo.getSubsidy());
            if (electricityInformation <= botSettings.getThreshold()) {
                Objects.requireNonNull(Bot.getInstance(botSettings.getBotId()).getGroup(botSettings.getGroupId())).sendMessage("电量剩余" + electricityInformation + "度，请及时充值");
            }
        };
        long time = botSettings.getTaskTime();
        new ScheduledThreadPoolExecutor(1).scheduleAtFixedRate(runnable, time, time, TimeUnit.SECONDS);
    }
}
