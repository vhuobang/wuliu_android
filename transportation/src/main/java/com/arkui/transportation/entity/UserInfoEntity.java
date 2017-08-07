package com.arkui.transportation.entity;

/**
 * Created by 84658 on 2017/8/7.
 */

public class UserInfoEntity {

    /**
     * password : 78ea5e34f02da496cae4a4b0b8c6787e
     * invitation_code : lJNPDaw
     * salt : wuliu
     * mobile : 18735100046
     * id : 1
     * hx_id : null
     * type : 1
     * register_time : 2017-08-04 11:43:34
     * status : 1
     */

    private String password;
    private String invitation_code;
    private String salt;
    private String mobile;
    private String id;
    private Object hx_id;
    private String type;
    private String register_time;
    private String status;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getInvitation_code() {
        return invitation_code;
    }

    public void setInvitation_code(String invitation_code) {
        this.invitation_code = invitation_code;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Object getHx_id() {
        return hx_id;
    }

    public void setHx_id(Object hx_id) {
        this.hx_id = hx_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRegister_time() {
        return register_time;
    }

    public void setRegister_time(String register_time) {
        this.register_time = register_time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
