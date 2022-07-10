# acdt-bot
宿舍电费监测机器人  
适用于安徽国防科技职业技术学院
# 使用方法
1. 浏览器访问此网址 http://df.acdt.edu.cn/go?openid
2. 点击F12键进入开发者模式,点击home复制Cookie  
![pic01](./image/pic01.png)
3. 复制Cookie
4. 将Cookie[填入此处](https://github.com/zzwtsy/acdt-bot/blob/master/src/main/java/com/acdt/GetInfo.java#L42)  
   示例
````
  String urlPath = "http://df.acdt.edu.cn/use/record";
  String cookie = "JSESSIONID=xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
````