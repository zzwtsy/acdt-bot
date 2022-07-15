package cn.edu.acdt;

import cn.edu.acdt.tools.JavaConfigHelper;
import net.mamoe.mirai.console.plugin.jvm.JavaPlugin;
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescriptionBuilder;
import net.mamoe.mirai.event.GlobalEventChannel;

import java.io.File;
import java.io.IOException;

/**
 * @author Daydreamer
 */
public final class Acdt extends JavaPlugin {
    @SuppressWarnings("unused")
    public static final Acdt INSTANCE = new Acdt();
    public static AcdtBotSettings botSettings;

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
        File file = new File("config/com.acdt.bot/acdt-Bot.json");
        if (file.exists()) {
            try {
                // 从本地文件加载配置项到内存
                botSettings = JavaConfigHelper.getConfigFromFile("com.acdt.bot", "acdt-Bot", AcdtBotSettings.class);
                getLogger().info("Config file loaded successfully");
            } catch (IOException e) {
                getLogger().error("加载配置文件时发生错误");
                getLogger().error("Filed to load configuration file");
                getLogger().error(e);
            }
        } else {
            //初始化配置文件
            getLogger().info("No config file found, create a new config file");
            AcdtBotSettings.INSTANCE.setGroupId(0);
            AcdtBotSettings.INSTANCE.setBotId(0);
            AcdtBotSettings.INSTANCE.setHelpCommand("#help");
            AcdtBotSettings.INSTANCE.setInfoCommand("#电费");
            AcdtBotSettings.INSTANCE.setCookie("你的cookie");
            AcdtBotSettings.INSTANCE.setTaskTime(3);
            AcdtBotSettings.INSTANCE.setThreshold(20);
            try {
                // 创建本地配置文件
                JavaConfigHelper.createConfigFile("com.acdt.bot", "acdt-Bot");
                // 将配置类写入到本地配置文件
                JavaConfigHelper.setConfigFile("com.acdt.bot", "acdt-Bot", AcdtBotSettings.INSTANCE);
                getLogger().warning("请修改配置文件后再次启动");
                getLogger().warning("Modify the configuration file and start it again");
                return;
            } catch (IOException e) {
                getLogger().error("生成配置文件时发生错误");
                getLogger().error("Config file create failed");
                getLogger().error(e);
            }
        }
        GlobalEventChannel.INSTANCE.registerListenerHost(new SendInfo());
        new GetInfo().getInfo();
        //定时任务
        new TimingTask().sendMessage();
    }

    @Override
    public void onDisable() {
        getLogger().info("acdt-Bot 插件已关闭");
        getLogger().info("acdt-Bot plugin is stop");
    }
}