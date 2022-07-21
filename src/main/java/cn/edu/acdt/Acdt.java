package cn.edu.acdt;

import cn.edu.acdt.tools.AcdtBotConfig;
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
    public static AcdtBotConfig botSettings;

    private Acdt() {
        super(new JvmPluginDescriptionBuilder("cn.edu.acdt.bot", "0.1.0")
                .name("acdt-bot")
                .info("安徽国防电费提醒机器人")
                .author("Daydreamer")
                .build());
    }

    @Override
    public void onEnable() {
        getLogger().info("acdt-bot loaded successfully :)");
        File file = new File("config/cn.edu.acdt.bot/acdt-Bot.json");
        if (file.exists()) {
            try {
                // 从本地文件加载配置项到内存
                botSettings = JavaConfigHelper.getConfigFromFile("cn.edu.acdt.bot", "acdt-Bot", AcdtBotConfig.class);
                getLogger().info("Config file loaded successfully");
            } catch (IOException e) {
                getLogger().error("加载配置文件时发生错误");
                getLogger().error("Filed to load configuration file");
                getLogger().error(e);
            }
        } else {
            //初始化配置文件
            getLogger().info("没有找到配置文件,正在生成配置文件");
            getLogger().info("No config file found, create a new config file");
            AcdtBotConfig.INSTANCE.setTips("groupId:你的QQ群号||" +
                    "botId:你的机器人QQ号||" +
                    "threshold:电费预警值（当电费小于此值时机器人发送缴费信息）||" +
                    "taskTime:机器人检测剩余电费时间间隔，单位小时||" +
                    "cookie:网站cookie||" +
                    "helpCommand:获取插件帮助信息指令||" +
                    "infoCommand:获取当前电费信息的指令");
            AcdtBotConfig.INSTANCE.setGroupId(0);
            AcdtBotConfig.INSTANCE.setBotId(0);
            AcdtBotConfig.INSTANCE.setHelpCommand("#help");
            AcdtBotConfig.INSTANCE.setInfoCommand("#电费");
            AcdtBotConfig.INSTANCE.setCookie("你的cookie");
            AcdtBotConfig.INSTANCE.setTaskTime(3);
            AcdtBotConfig.INSTANCE.setThreshold(20);
            try {
                // 创建本地配置文件
                JavaConfigHelper.createConfigFile("cn.edu.acdt.bot", "acdt-Bot");
                // 将配置类写入到本地配置文件
                JavaConfigHelper.setConfigFile("cn.edu.acdt.bot", "acdt-Bot", AcdtBotConfig.INSTANCE);
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