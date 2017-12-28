package com.jahentao.test.ocr;


import com.baidu.aip.ocr.AipOcr;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;

/**
 * 该应用从基础的 Sample改造而来。
 * 用于识别从PDF中转换而来的每页图片中的文字。
 *
 * 采用通用识别(高精度)接口得到第二批结果。感觉差不多，效果不明显。
 */
public class Sample3 {
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

        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("language_type", "CHN_ENG"); //中英文混合
        options.put("detect_direction", "true");//是否检测图像朝向，默认不检测
        options.put("detect_language", "true");//是否检测语言，默认不检测。
        options.put("probability", "false");//是否返回识别结果中每一行的置信度

        // 调用接口
        String directory="/home/jahentao/Documents/第八章的图片/";
        File outputResult = new File(directory+"outputResult.txt");
        FileOutputStream out = null;
        OutputStreamWriter outWriter = null;
        BufferedWriter bufWrite = null;
        try {
            out = new FileOutputStream(outputResult);
            outWriter = new OutputStreamWriter(out, "UTF-8");
            bufWrite = new BufferedWriter(outWriter);
            for(int i=1;i<=53;i++)
            {
                String path = directory+String.format("%04d",i)+".jpg";
                JSONObject res = client.basicAccurateGeneral(path, options); //通用文字识别（高精度版）
                System.out.println(path);
                // 结果输出
                JSONArray words_result = (JSONArray) res.get("words_result");
                Iterator<Object> it = words_result.iterator();
                while (it.hasNext())
                {
                    JSONObject next = (JSONObject) it.next();
                    String words = (String) next.get("words");
                    bufWrite.write(words);
                    bufWrite.newLine();
                }
                System.out.println(path+"...ok");
            }
        } catch (FileNotFoundException e) {
            System.out.println("没有找到文件");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("文件写入出错");
            e.printStackTrace();
        }finally {
            try {
                bufWrite.close();
                outWriter.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}