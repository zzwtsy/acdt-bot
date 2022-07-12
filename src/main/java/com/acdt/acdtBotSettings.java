package com.acdt;

public class acdtBotSettings {
    public static final acdtBotSettings INSTANCE = new acdtBotSettings();
    int groupId;
    int botId;
    int taskTime;
    String prefix;
    String cookie;

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

    private acdtBotSettings() {}

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getBotId() {
        return botId;
    }

    public void setBotId(int botId) {
        this.botId = botId;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }
}
