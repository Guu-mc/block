package com.mc.block.constant;

public class Get {

    public static final int OK                              = 200; //OK） - 表示已在响应中发出
    public static final int EMPTY                           = 204; //（无内容） - 资源有空表示
    public static final int PARTIAL_CONTENT                 = 206; //（无内容） - 已经成功处理了部分GET请求
    public static final int MOVED_PERMANENTLY               = 301; //（Moved Permanently） - 资源的URI已被更新
    public static final int SEE_OTHER                       = 303; //（See Other） - 其他（如，负载均衡）
    public static final int NOT_MODIFIED                    = 304; //（not modified）- 资源未更改（缓存）
    public static final int BAD_REQUEST                     = 400; //（bad request）- 指代坏请求（如，参数错误）
    public static final int NOT_FOUND                       = 404; //（not found）- 资源不存在
    public static final int NOT_ACCEPTABLE                  = 406; //（not acceptable）- 服务端不支持所需表示
    public static final int INTERNAL_SERVER_ERROR           = 500; // （internal server error）- 通用错误响应
    public static final int SERVICE_UNAVAILABLE             = 503; //（Service Unavailable）- 服务端当前无法处理请求
}
