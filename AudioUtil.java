package com.hisense.road.smartroad.event.util;

import lombok.extern.slf4j.Slf4j;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.mp3.MP3AudioHeader;
import org.jaudiotagger.audio.mp3.MP3File;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import java.io.File;

/**
 * @author chenruirui
 */
@Slf4j
public class AudioUtil {

    /**
     * 获取语音文件播放时长(秒) 支持wav 格式
     *
     * @param filePath 语音文件路径
     * @return 语音时长
     */
    public static Float getDuration(String filePath) {
        try {
            File destFile = new File(filePath);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(destFile);
            AudioFormat format = audioInputStream.getFormat();
            long audioFileLength = destFile.length();
            int frameSize = format.getFrameSize();
            float frameRate = format.getFrameRate();
            return (audioFileLength / (frameSize * frameRate));
        } catch (Exception e) {
            log.error("获取语音时长失败" + e.getMessage(), e);
            return 0f;
        }
    }

    /**
     * 获取mp3语音文件播放时长(秒) mp3
     *
     * @param filePath 语音文件路径
     * @return 语音时长
     */
    public static Float getMp3Duration(String filePath) {

        try {
            File mp3File = new File(filePath);
            MP3File f = (MP3File) AudioFileIO.read(mp3File);
            MP3AudioHeader audioHeader = (MP3AudioHeader) f.getAudioHeader();
            return Float.parseFloat(audioHeader.getTrackLength() + "");
        } catch (Exception e) {
            log.error("获取语音时长失败" + e.getMessage(), e);
            return 0f;
        }
    }

    /**
     * 获取mp3语音文件播放时长(秒)
     *
     * @param mp3File mp3文件
     * @return 语音时长
     */
    public static Float getMp3Duration(File mp3File) {

        try {
            MP3File f = (MP3File) AudioFileIO.read(mp3File);
            MP3AudioHeader audioHeader = (MP3AudioHeader) f.getAudioHeader();
            return Float.parseFloat(audioHeader.getTrackLength() + "");
        } catch (Exception e) {
            log.error("获取语音时长失败" + e.getMessage(), e);
            return 0f;
        }
    }

}