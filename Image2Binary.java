package com.hisense.road.smartroad.event.util;


import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 文件处理
 *
 * @author chenruirui
 */
@Slf4j
public class Image2Binary {
    public static byte[] toByteArray(InputStream in) throws IOException {

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024 * 4];
        int n;
        while ((n = in.read(buffer)) != -1) {
            out.write(buffer, 0, n);
        }
        return out.toByteArray();
    }


    /**
     * 网络文件转换为本地文件
     *
     * @param urlStr 网络文件地址
     * @param bdUrl  本地文件地址
     */
    public static void toBdFile(String urlStr, String bdUrl) throws IOException {
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        DataInputStream in = new DataInputStream(conn.getInputStream());
        byte[] data = toByteArray(in);
        in.close();
        FileOutputStream out = new FileOutputStream(bdUrl);
        out.write(data);
        out.close();
    }
}