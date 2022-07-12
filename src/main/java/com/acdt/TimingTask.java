package com.acdt;

import net.mamoe.mirai.Bot;
import net.mamoe.mirai.event.SimpleListenerHost;

import java.util.Objects;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TimingTask extends SimpleListenerHost {
    /**
     * 每3小时监测一次电费信息是否小余20度
     */
    public void sendMessage() {
        ScheduledExecutorService scheduledExecutorService = new ScheduledThreadPoolExecutor(2);
        Runnable runnable = () -> {
            GetInfo getInfo = new GetInfo();
            getInfo.getInfo();
            float electricityInformation = Float.parseFloat(getInfo.getElectricityPurchase()) + Float.parseFloat(getInfo.getSubsidy());
            if (electricityInformation <= 20) {
                Objects.requireNonNull(Bot.getInstance(acdtBotSettings.INSTANCE.getBotId()).getGroup(acdtBotSettings.INSTANCE.getGroupId())).sendMessage("电量不足20度，请及时充值");
            }
        };
        int time = acdtBotSettings.INSTANCE.getTaskTime();
        scheduledExecutorService.scheduleAtFixedRate(runnable, time, time, TimeUnit.HOURS);
    }
}
