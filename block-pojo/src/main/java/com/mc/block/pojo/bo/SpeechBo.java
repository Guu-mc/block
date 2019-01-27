package com.mc.block.pojo.bo;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class SpeechBo implements Serializable {
    @NotNull
    @Size(min = 1, max = 1024, message = "tex not empty, max 1024")
    private String tex;             //合成的文本，使用UTF-8编码，请注意文本长度必须小于1024字节
    private String spd;             //语速，取值0-9，默认为5中语速
    private String pit;             //音调，取值0-9，默认为5中语调
    private String vol;             //音量，取值0-15，默认为5中音量
    private String per;             //发音人选择, 0为女声，1为男声，3为情感合成-度逍遥，4为情感合成-度丫丫，默认为普通女
    private String aue;             //3为mp3格式(默认)； 4为pcm-16k；5为pcm-8k；6为wav（内容同pcm-16k）; 注意aue=4或者6是语音识别要求的格式，但是音频内容不是语音识别要求的自然人发音，所以识别效果会受影响。

    public String getTex() {
        return tex;
    }

    public SpeechBo setTex(String tex) {
        this.tex = tex;
        return this;
    }

    public String getSpd() {
        return spd;
    }

    public SpeechBo setSpd(String spd) {
        this.spd = spd;
        return this;
    }

    public String getPit() {
        return pit;
    }

    public SpeechBo setPit(String pit) {
        this.pit = pit;
        return this;
    }

    public String getVol() {
        return vol;
    }

    public SpeechBo setVol(String vol) {
        this.vol = vol;
        return this;
    }

    public String getPer() {
        return per;
    }

    public SpeechBo setPer(String per) {
        this.per = per;
        return this;
    }

    public String getAue() {
        return aue;
    }

    public void setAue(String aue) {
        this.aue = aue;
    }
}
