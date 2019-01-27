package com.mc.block.interfaces;

import com.mc.block.exception.EException;
import com.mc.block.pojo.FileAttributes;
import com.mc.block.pojo.bo.AudioAIBo;
import com.mc.block.pojo.bo.PyWord;
import com.mc.block.pojo.bo.SpeechBo;

public interface IAIService {
    byte[] speechAI(SpeechBo speechBo);

    String audioAI(AudioAIBo audioAIBo, FileAttributes fileAttributes) throws EException;

    String ece(PyWord pyWord);
}
