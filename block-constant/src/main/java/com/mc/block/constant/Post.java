package com.mc.block.constant;

public class Post {

    public static final int OK                                 = 200; //（OK）- 如果现有资源已被更改
    public static final int CREATED                            = 201; //（created）- 如果新资源被创建
    public static final int ACCEPTED                           = 202; //（accepted）- 已接受处理请求但尚未完成（异步处理）
    public static final int MOVED_PERMANENTLY                  = 301; //（Moved Permanently）- 资源的URI被更新
    public static final int SEE_OTHER                          = 303; //（See Other）- 其他（如，负载均衡）
    public static final int BAD_REQUEST                        = 400; //（bad request）- 指代坏请求
    public static final int NOT_FOUND                          = 404; //（not found）- 资源不存在
    public static final int FOUND                              = 405; //（found）- 资源已经存在
    public static final int NOT_ACCEPTABLE                     = 406; //（not acceptable）- 服务端不支持所需表示
    public static final int CONFLICT                           = 409; //（conflict）- 通用冲突
    public static final int PRECONDITION_FAILED                = 412; //（Precondition Failed）- 前置条件失败（如执行条件更新时的冲突）
    public static final int UNSUPPORTED_MEDIA_TYPE             = 415; //（unsupported media type）- 接受到的表示不受支持
    public static final int INTERNAL_SERVER_ERROR              = 500; //（internal server error）- 通用错误响应
    public static final int SERVICE_UNAVAILABLE                = 503; //（Service Unavailable）- 服务当前无法处理请求

}
