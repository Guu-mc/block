block-redis-service:
    公用redis缓存
    dubbo:
        qos.port: 22220
        port: 20880
    
    
block-sso-service:
    单点登录
    dubbo:
        qos.port: 22221
        port: 20881
    
    
block-sso-web:
    单点登录
    port: 9000
    
    
block-img-file-web:
    图片文件服务器-图片最大大小（50M）
