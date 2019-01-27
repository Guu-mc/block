package com.mc.block.commom;

import com.mc.block.pojo.VideoInfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FfmpegUtils {

    /**
     * ffmpeg能解析的格式：（asx，asf，mpg，wmv，3gp，mp4，mov，avi，flv）
     * @param ext
     * @return
     */
    public static boolean checkContentType(String ext) {
        boolean b = false;
        if (ext.equals("avi")) {
            b = true;
        } else if (ext.equals("mpg")) {
            b = true;
        } else if (ext.equals("wmv")) {
            b = true;
        } else if (ext.equals("3gp")) {
            b = true;
        } else if (ext.equals("mov")) {
            b = true;
        } else if (ext.equals("mp4")) {
            b = true;
        } else if (ext.equals("asf")) {
            b = true;
        } else if (ext.equals("asx")) {
            b = true;
        } else if (ext.equals("flv")) {
            b = true;
        }
        return b;
    }

    /**
     *
     * @param timelen 时间格式:"00:00:10.68" 转 int 秒
     * @return
     */
    public static int timelen(String timelen) {
        int min = 0;
        String strs[] = timelen.split(":");
        if (strs[0].compareTo("0") > 0) {
            // 秒
            min += Integer.valueOf(strs[0]) * 60 * 60;
        }
        if (strs[1].compareTo("0") > 0) {
            min += Integer.valueOf(strs[1]) * 60;
        }
        if (strs[2].compareTo("0") > 0) {
            min += Math.round(Float.valueOf(strs[2]));
        }
        return min;
    }

    /**
     * 获取视频信息
     * @param videoPath
     * @return
     * @throws IOException
     */
    public static VideoInfo videoInfo(String videoPath) throws IOException {
        ProcessBuilder builder = new ProcessBuilder();
        builder.command("ffmpeg -i ", videoPath);
        Process p = builder.start();
        //从输入流中读取视频信息
        BufferedReader br = new BufferedReader(new InputStreamReader(p.getErrorStream()));
        StringBuffer stringBuffer = new StringBuffer();
        String line;
        while ((line = br.readLine()) != null) {
            stringBuffer.append(line);
        }
        br.close();

        //从视频信息中解析时长
        String regexDuration = "Duration: (.*?), start: (.*?), bitrate: (\\d*) kb\\/s";
        Pattern pattern = Pattern.compile(regexDuration);
        Matcher m = pattern.matcher(stringBuffer.toString());
        VideoInfo videoInfo = new VideoInfo();
        if (m.find()) {
            videoInfo.setVideoDuration(m.group(1));
            videoInfo.setVideoTimelen(timelen(m.group(1)));
            videoInfo.setStartTime(m.group(2));
            videoInfo.setBitRate(m.group(3) + "kb/s");
        }

        String regexVideo = "Video: (.*?), (.*?), (.*?)[,\\s]";
        pattern = Pattern.compile(regexVideo);
        m = pattern.matcher(stringBuffer.toString());
        if (m.find()) {
            videoInfo.setCodedFormat(m.group(1));
            videoInfo.setVideoFormat(m.group(2));
            videoInfo.setResolvingPower(m.group(3) + "kb/s");
        }
        return videoInfo;
    }

//    ffmpeg -ss 00:01:00 -i video.mp4 -to 00:02:00 -c copy -copyts cut.mp4

    /**
     *
     * @param oldPath 旧文件路径
     * @param newPath 新文件路径
     * @param openingTime 开始时间 如: 00:00:08 开始时间为第八秒
     * @param duration 时长 如: 00:00:10 截取⑩秒时间
     */
    public static void shearVideo(String oldPath, String newPath, String openingTime, String duration){
        CmdUtils.run_cmd(new String[]{"ffmpeg -i ", oldPath, " -ss ", openingTime, " -to ", duration, " -c copy -copyts ", newPath});
    }

    /**
     *  sShearVideo 为使用关键帧技术
     * @param oldPath 旧文件路径
     * @param newPath 新文件路径
     * @param openingTime 开始时间 如: 00:00:08 开始时间为第八秒
     * @param duration 时长 如: 00:00:10 截取⑩秒时间
     */
    public static void sShearVideo(String oldPath, String newPath, String openingTime, String duration){
        CmdUtils.run_cmd(new String[]{"ffmpeg -ss ", openingTime, " -i ", oldPath, " -to ", duration, " -c copy -copyts ", newPath});
    }

//    ffmpeg -i C:\Users\10375\Desktop\learner-demo.m4v -b:v 400k -s 960x540 newfiles/learner-demo.mp4

    /**
     *
     * @param oldPath 旧文件路径
     * @param newPath 新文件路径
     * @param RP 分辨率
     * @param BV 码率 如: 500k
     */
    public static void transcoding(String oldPath, String newPath, String RP, String BV){
        CmdUtils.run_cmd(new String[]{"ffmpeg -i ", oldPath, " -b:v ", BV, " -s ", RP, " ", newPath});
    }
}
