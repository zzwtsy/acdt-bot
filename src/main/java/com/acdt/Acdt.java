package com.acdt;

import net.mamoe.mirai.console.plugin.jvm.JavaPlugin;
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescriptionBuilder;
import net.mamoe.mirai.event.GlobalEventChannel;

public final class Acdt extends JavaPlugin {
    @SuppressWarnings("unused")
    public static final Acdt INSTANCE = new Acdt();

    private Acdt() {
        super(new JvmPluginDescriptionBuilder("com.acdt.bot", "0.1.0")
                .name("acdt-bot")
                .info("安徽国防电费提醒机器人")
                .author("Daydreamer")
                .build());
    }

    @Override
    public void onEnable() {
        getLogger().info("acdt-bot loaded :)");
        GlobalEventChannel.INSTANCE.registerListenerHost(new SendInfo());
        new GetInfo().getInfo();
        new TimingTask().sendMessage();
    }
}