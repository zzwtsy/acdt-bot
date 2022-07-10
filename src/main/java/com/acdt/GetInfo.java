package com.acdt;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import static com.acdt.tools.ToNumberPart.toNumberPart;

public class GetInfo {
    //剩余购电
    String electricityPurchase;
    //剩余补助
    String subsidy;
    //昨日用电
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
    public String getInfo() {
        String urlPath = "http://df.acdt.edu.cn/use/record";
        String cookie = "Your cookie";
        String message;
        URL url;
        Document document;
        StringBuilder stringBuilder;
        try {
            url = new URL(urlPath);
            URLConnection conn = url.openConnection();
            conn.setRequestProperty("Cookie", cookie);
            conn.setDoInput(true);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            document = Jsoup.parse(String.valueOf(stringBuilder));
            Elements select = document.getElementsByClass("item-after");
            //获取剩余购电
            electricityPurchase = toNumberPart(select.get(0).text());
            //获取剩余补助
            subsidy = toNumberPart(select.get(1).text());
            //获取昨日用电
            electricityConsumption = toNumberPart(select.get(2).text());
            message = "剩余购电:" + electricityPurchase + "度\n剩余补助:" + subsidy + "度\n昨日用电:" + electricityConsumption + "度";
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return message;
    }
}
