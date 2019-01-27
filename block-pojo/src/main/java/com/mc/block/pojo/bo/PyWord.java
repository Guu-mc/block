package com.mc.block.pojo.bo;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class PyWord implements Serializable {
    @NotNull
    @Size(min = 1, max = 10, message = "min:1, max:10")
    private String word;

    public String getWord() {
        return word;
    }

    public PyWord setWord(String word) {
        this.word = word;
        return this;
    }
}
