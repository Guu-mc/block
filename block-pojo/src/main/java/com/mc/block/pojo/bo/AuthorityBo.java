package com.mc.block.pojo.bo;

import org.springframework.security.core.GrantedAuthority;

public class AuthorityBo implements GrantedAuthority {

    private String authority;
    private String permissionUrl;
    private String method;

    public AuthorityBo(String authority, String permissionUrl, String method){
        this.authority = authority;
        this.permissionUrl = permissionUrl;
        this.method = method;
    }

    @Override
    public String getAuthority() {

        return this.authority;
    }

    public AuthorityBo setAuthority(String authority) {
        this.authority = authority;
        return this;
    }

    public String getPermissionUrl() {
        return permissionUrl;
    }

    public void setPermissionUrl(String permissionUrl) {
        this.permissionUrl = permissionUrl;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
