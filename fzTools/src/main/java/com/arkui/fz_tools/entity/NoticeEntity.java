package com.arkui.fz_tools.entity;

/**
 * Created by 84658 on 2017/8/8.
 */

public class NoticeEntity {

    /**
     * created_at : 2017-08-07 16:20:12
     * target_id : 0
     * id : 1
     * type : 1
     * title : 测试
     * content : 好了没有
     * recipient_id : 0
     * status : 2
     * truck_status // 货物状态
     */

    private String created_at;
    private String target_id;
    private String id;
    private String type;
    private String title;
    private String content;
    private String recipient_id;
    private String status;
    private String truck_status;

    public String getTruck_status() {
        return truck_status;
    }

    public void setTruck_status(String truck_status) {
        this.truck_status = truck_status;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getTarget_id() {
        return target_id;
    }

    public void setTarget_id(String target_id) {
        this.target_id = target_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRecipient_id() {
        return recipient_id;
    }

    public void setRecipient_id(String recipient_id) {
        this.recipient_id = recipient_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
