package com.hisense.road.smartroad.event.util;

import com.coremedia.iso.IsoFile;

import java.io.File;
import java.io.IOException;

/**
 * @author chenruirui
 */
public class VideoUtil {

    /**
     * 获取视频文件的播放长度(mp4、mov格式)
     *
     * @param videoPath 视频文件路径
     * @return 单位为毫秒
     */
    public static long getMp4Duration(String videoPath) throws IOException {
        IsoFile isoFile = new IsoFile(videoPath);
        return isoFile.getMovieBox().getMovieHeaderBox().getDuration() /
                isoFile.getMovieBox().getMovieHeaderBox().getTimescale();
    }


    /**
     * 得到语音或视频文件时长,单位秒
     *
     * @param filePath 文件路径
     * @return 时长
     */
    public static long getDuration(String filePath) throws IOException {
        String format = getVideoFormat(filePath);
        return getDuration(filePath, format);
    }

    /**
     * 得到语音或视频文件时长,单位秒
     *
     * @param filePath 文件路径
     * @return 时长
     */
    public static long getDuration(String filePath, String format) throws IOException {
        long result = 0;
        if ("wav".equals(format) || "WAV".equals(format)) {
            result = AudioUtil.getDuration(filePath).intValue();
        } else if ("mp3".equals(format) || "MP3".equals(format)) {
            result = AudioUtil.getMp3Duration(filePath).intValue();
        } else if ("m4a".equals(format) || "M4A".equals(format)) {
            result = VideoUtil.getMp4Duration(filePath);
        } else if ("mov".equals(format) || "MOV".equals(format)) {
            result = VideoUtil.getMp4Duration(filePath);
        } else if ("mp4".equals(format) || "MP4".equals(format)) {
            result = VideoUtil.getMp4Duration(filePath);
        }
        return result;
    }

    /**
     * 获取网络视频时长
     *
     * @param url 网络视频地址
     * @return 时长
     */
    public static long getUrlFileDuration(String url) throws Exception {
        return getDuration(getFileByUrl(url));
    }

    /**
     * 得到文件格式
     *
     * @param path 文件路径
     * @return 文件格式
     */
    public static String getVideoFormat(String path) {
        return path.toLowerCase().substring(path.toLowerCase().lastIndexOf(".") + 1);
    }

    /**
     * 通过url获取文件
     *
     * @param url 文件url
     * @return 文件路径
     */
    public static String getFileByUrl(String url) throws IOException {
        //创建临时文件
        File tmpFile = File.createTempFile("temp", url.substring(url.lastIndexOf(".")));
        Image2Binary.toBdFile(url, tmpFile.getCanonicalPath());
        return tmpFile.getCanonicalPath();
    }
}