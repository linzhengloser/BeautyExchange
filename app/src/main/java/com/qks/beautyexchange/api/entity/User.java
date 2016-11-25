package com.qks.beautyexchange.api.entity;

/**
 * Created by admin on 2016/3/31.
 */
public class User {

    private String user_id;

    private String weixin_uid;

    private boolean is_forbidden;

    public String getUser_id() {
        return user_id;
    }

    public String getWeixin_uid() {
        return weixin_uid;
    }

    public boolean is_forbidden() {
        return is_forbidden;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setWeixin_uid(String weixin_uid) {
        this.weixin_uid = weixin_uid;
    }

    public void setIs_forbidden(boolean is_forbidden) {
        this.is_forbidden = is_forbidden;
    }

}
