package com.mc.block.exception;


import com.mc.block.confine.result.TypeOfError;

public class EException extends Exception{

    private TypeOfError type;
    private Exception e;
    private Object data;

    public EException(TypeOfError type){
        this.type = type;
    }
    public EException(TypeOfError type, Exception e){
        this.type = type;
        this.e = e;
    }

    public EException(TypeOfError type, String message){
        super(message);
        this.type = type;
    }

    public EException(TypeOfError type, Exception e, String message){
        super(message);
        this.type = type;
        this.e = e;
    }

    public EException(TypeOfError type, Exception e, Object data){
        this.type = type;
        this.e = e;
        this.data = data;
    }

    public EException(TypeOfError type, String message, Object data){
        super(message);
        this.type = type;
        this.data = data;
    }

    public EException(TypeOfError type, Exception e, String message, Object data){
        super(message);
        this.type = type;
        this.e = e;
        this.data = data;
    }

    public TypeOfError getType() {
        return type;
    }

    public Exception getE() {
        return e;
    }

    public Object getData() {
        return data;
    }
}
