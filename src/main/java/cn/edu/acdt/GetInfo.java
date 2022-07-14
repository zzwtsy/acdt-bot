package cn.edu.acdt;

import net.mamoe.mirai.event.SimpleListenerHost;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

import static cn.edu.acdt.Acdt.botSettings;
import static cn.edu.acdt.tools.ToNumberPart.toNumberPart;

/**
 * @author Daydreamer
 */
public class GetInfo extends SimpleListenerHost {
    /**
     * 剩余购电
     */
    String electricityPurchase;
    /**
     * 剩余补助
     */
    String subsidy;
    /**
     * 昨日用电
     */
    String electricityConsumption;

    /**
     * @return 剩余购电电量
     */
    public String getElectricityPurchase() {
        return electricityPurchase;
    }

    /**
     * @return 剩余补助电量
     */
    public String getSubsidy() {
        return subsidy;
    }

    /**
     * 获取电量信息
     */
    public void getInfo() {
        String urlPath = "http://df.acdt.edu.cn/use/record";
        Document document;
        Connection connection;
        try {
            try {
                connection = Jsoup.connect(urlPath).timeout(3000);
            } catch (Exception e) {
                throw new RuntimeException("连接超时");
            }
            connection.header("Cookie", botSettings.getCookie());
            connection.header("User-Agent", "mozilla/5.0 (linux; android 12; m2012k11ac build/skq1.211006.001; wv) applewebkit/537.36 (khtml, like gecko) version/4.0 chrome/86.0.4240.99 xweb/4263 mmwebsdk/20220604 mobile safari/537.36 mmwebid/8194 micromessenger/8.0.24.2180(0x28001879) wechat/arm64 weixin nettype/wifi language/zh_cn abi/arm64");
            document = connection.get();
            Elements select = document.getElementsByClass("item-after");
            //获取剩余购电
            electricityPurchase = toNumberPart(select.get(0).text());
            //获取剩余补助
            subsidy = toNumberPart(select.get(1).text());
            //获取昨日用电
            electricityConsumption = toNumberPart(select.get(2).text());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
