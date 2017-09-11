package com.xhuihui.app;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import sun.net.www.http.HttpClient;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by lihuiguang on 2017/8/15.
 */
public class map {

    private static final String GAODE_KEY = "8ab59d433b3bafb3591af69fd047a6bb";

    private static final String GAODE_BATCH = "http://restapi.amap.com/v3/batch";

    private static final String GAODE_DISTRICT = "http://restapi.amap.com/v3/config/district";

    public static void main(String[] args) throws IOException {

        getProvins("中华人民共和国");
//        getBatch("中华人民共和国");

    }

    public static JSONArray getProvins(String contry) throws IOException {
        StringBuffer sb = new StringBuffer(GAODE_DISTRICT);
        sb.append("?key=").append(GAODE_KEY)
                .append("&keywords=").append(contry)
                .append("&subdistrict=").append("3")
                .append("&extensions=").append("all");
        URL url =new URL(sb.toString());
        URLConnection connect = url.openConnection();
        BufferedReader br = new BufferedReader(new InputStreamReader(connect.getInputStream()));
        JSONObject zh = JSONObject.fromObject(br.readLine());
        JSONArray districts = zh.optJSONArray("districts");
        return null;
    }


    public static JSONArray getBatch(String contry) throws IOException {
        StringBuffer sb = new StringBuffer(GAODE_BATCH);
        sb.append("?key=").append(GAODE_KEY);

        JSONObject res = new JSONObject();
        JSONArray opts = new JSONArray();
        JSONObject opt = new JSONObject();
        opt.element("url","/v3/config/district?key=8ab59d433b3bafb3591af69fd047a6bb");
        JSONObject opte = new JSONObject();
        opte.element("url","/v3/config/district?key=8ab59d433b3bafb3591af69fdd047a6bb");
        opts.add(opt);
        opts.add(opte);
        opts.add(opt);
        res.element("ops",opts);
//        JSONObject res = JSONObject.fromObject("{\"ops\":[{\"url\":\"/v3/config/district?key=8ab59d433b3bafb3591af69fd047a6bb\"},{\"url\":\"/v3/config/district?key=8ab59d433b3bafb3591af69fd047a6bb&keywords=山东\"},{\"url\":\"/v3/config/district?key=8ab59d433b3bafb3591af69fd047a6bb&keywords=河北\"}]}");

        URL url =new URL(sb.toString());
        URLConnection connect = url.openConnection();
        connect.setDoOutput(true);
        PrintWriter pw = new PrintWriter(connect.getOutputStream());
        pw.write(res.toString());
        pw.flush();
        BufferedReader br = new BufferedReader(new InputStreamReader(connect.getInputStream()));
        System.out.print(br.readLine());
        return null;
    }
}
