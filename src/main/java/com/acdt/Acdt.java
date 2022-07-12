package com.acdt;

import com.acdt.tools.JavaConfigHelper;
import net.mamoe.mirai.console.plugin.jvm.JavaPlugin;
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescriptionBuilder;
import net.mamoe.mirai.event.GlobalEventChannel;

import java.io.File;
import java.io.IOException;

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
        getLogger().info("acdt-bot loaded successfully :)");
        File file = new File("config/acdtBotSettings.json");
        if (file.exists()) {
            try {
                // 从本地文件加载配置项到内存
                JavaConfigHelper.getConfigFromFile("acdtBotSettings", acdtBotSettings.class);
                getLogger().info("Config file loaded successfully");
            } catch (IOException e) {
                getLogger().error("Filed to load configuration file");
                getLogger().error(e);
            }
        } else {
            getLogger().info("No config file found, create a new config file");
            acdtBotSettings.INSTANCE.setGroupId(0);
            acdtBotSettings.INSTANCE.setBotId(0);
            acdtBotSettings.INSTANCE.setPrefix("#");
            acdtBotSettings.INSTANCE.setCookie("你的cookie");
            acdtBotSettings.INSTANCE.setTaskTime(3);
            try {
                // 将配置类写入到本地配置文件
//                JavaConfigHelper.setConfigFile("acdtBotSettings", acdtBotSettings.INSTANCE);
                // 创建本地配置文件
                JavaConfigHelper.createConfigFile("acdtBotSettings");
            } catch (IOException e) {
                getLogger().error("Config file create failed");
                getLogger().error(e);
            }
        }
        GlobalEventChannel.INSTANCE.registerListenerHost(new SendInfo());
        new GetInfo().getInfo();
        new TimingTask().sendMessage();
    }
}