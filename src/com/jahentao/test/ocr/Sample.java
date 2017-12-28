package com.jahentao.test.ocr;


import com.baidu.aip.ocr.AipOcr;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Sample {
    public static final String APP_ID = "";
    public static final String API_KEY = "";
    public static final String SECRET_KEY = "";

    public static void main(String[] args) {
        // 初始化一个AipOcr
        AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
//        client.setHttpProxy("proxy_host", proxy_port);  // 设置http代理
//        client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理

        // 调用接口
        String path = "resources/test.jpg";
        JSONObject res = client.basicGeneral(path, new HashMap<String, String>()); //通用文字识别接口
//        System.out.println(res.toString(2));
        // 结果输出
        JSONArray words_result = (JSONArray) res.get("words_result");
        Iterator<Object> it = words_result.iterator();
        while (it.hasNext())
        {
            JSONObject next = (JSONObject) it.next();
            String words = (String) next.get("words");
            System.out.println(words);
        }
    }

}