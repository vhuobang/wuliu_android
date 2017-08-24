package com.arkui.fz_tools.entity;

import java.util.Map;

/**
 * Created by nmliz on 2017/8/22.
 */

public class PublishParameterEntity {
    private Map<String, Object> map;

    public PublishParameterEntity(Map<String, Object> map) {
        this.map = map;
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }


}
