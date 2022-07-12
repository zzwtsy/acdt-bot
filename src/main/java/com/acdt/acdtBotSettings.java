package com.acdt;

public class acdtBotSettings {
    public static final acdtBotSettings INSTANCE = new acdtBotSettings();
    //qq群号
    long groupId;
    //机器人qq号
    long botId;
    //检测电费的时间间隔（小时）
    int taskTime;
    //电费预警值
    int threshold;
    String cookie;
    //获取信息的指令
    String infoCommand;
    //获取帮助的指令
    String helpCommand;

    private acdtBotSettings() {
    }

    public int getThreshold() {
        return threshold;
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }

    public String getInfoCommand() {
        return infoCommand;
    }

    public void setInfoCommand(String infoCommand) {
        this.infoCommand = infoCommand;
    }

    public String getHelpCommand() {
        return helpCommand;
    }

    public void setHelpCommand(String helpCommand) {
        this.helpCommand = helpCommand;
    }

    public int getTaskTime() {
        return taskTime;
    }

    public void setTaskTime(int taskTime) {
        this.taskTime = taskTime;
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }

    public long getBotId() {
        return botId;
    }

    public void setBotId(long botId) {
        this.botId = botId;
    }

}
