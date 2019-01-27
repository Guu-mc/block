package com.mc.block.constant;

public class Delete {

    public static final int OK                                           = 200; //（OK）- 资源已被删除
    public static final int MOVED_PERMANENTLY                            = 301; //（Moved Permanently）- 资源的URI已更改
    public static final int SEE_OTHER                                    = 303; //（See Other）- 其他，如负载均衡
    public static final int BAD_REQUEST                                  = 400; //（bad request）- 指代坏请求
    public static final int NOT_FOUND                                    = 404; //（not found）- 资源不存在
    public static final int CONFLICT                                     = 409; //（conflict）- 通用冲突
    public static final int INTERNAL_SERVER_ERROR                        = 500; //（internal server error）- 通用错误响应
    public static final int SERVICE_UNAVAILABLE                          = 503; //（Service Unavailable）- 服务端当前无法处理请求
}
