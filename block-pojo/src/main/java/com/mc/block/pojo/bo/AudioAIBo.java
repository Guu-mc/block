package com.mc.block.pojo.bo;

import java.io.Serializable;

public class AudioAIBo implements Serializable {
    private Integer devPid;                 //1536:普通话(支持简单的英文识别),1537:普通话(纯中文识别),1737:	英语,1637:粤语,1837:四川话,1936:普通话远场

    public Integer getDevPid() {
        return devPid;
    }

    public AudioAIBo setDevPid(Integer devPid) {
        this.devPid = devPid;
        return this;
    }
}
