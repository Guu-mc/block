package com.mc.block.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baidu.aip.speech.AipSpeech;
import com.baidu.aip.speech.TtsResponse;
import com.mc.block.commom.ClassUtils;
import com.mc.block.confine.result.TypeOfError;
import com.mc.block.constant.BaiduApi;
import com.mc.block.exception.EException;
import com.mc.block.interfaces.IAIService;
import com.mc.block.pojo.FileAttributes;
import com.mc.block.pojo.bo.AudioAIBo;
import com.mc.block.pojo.bo.PyWord;
import com.mc.block.pojo.bo.SpeechBo;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;

@Service(timeout = 18000)
public class AIServiceImpl implements IAIService {
    private static AipSpeech client = new AipSpeech(BaiduApi.APP_ID, BaiduApi.API_KEY, BaiduApi.SECRET_KEY);

    static {
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);
    }

    @Override
    public byte[] speechAI(SpeechBo speechBo) {
        TtsResponse res = client.synthesis(speechBo.getTex(), "zh", 1, ClassUtils.fields(speechBo, true, "tex"));
        return res.getData();
    }

    @Override
    public String audioAI(AudioAIBo audioAIBo, FileAttributes fileAttributes) throws EException {
        HashMap<String, Object> options = null;
        if(audioAIBo.getDevPid() != null){
            options = new HashMap<>();
            options.put("dev_pid", audioAIBo.getDevPid());
        }
        JSONObject asr = client.asr(fileAttributes.getPath(), fileAttributes.getExt(), 16000, options);
        JSONArray result;
        try {
            result = (JSONArray) asr.get("result");
        } catch (RuntimeException e){
            throw new EException(TypeOfError.GET_VULUE_ERROR, (String) asr.get("err_msg"));
        }
        return (String) result.get(0);
    }

    @Override
    public String ece(PyWord pyWord) {
        System.out.println(pyWord.getWord());
        return "def cc():\n" +
                "    print('ok')\n" +
                "cc()";
    }
}
