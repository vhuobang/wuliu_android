package com.arkui.fz_tools.entity;

import java.util.List;

/**
 * Created by 任少东 on 2016/07/13 16:39
 */
public class City {

    /**
     * name : 北京
     * sub : [{"name":"东城区"},{"name":"西城区"},{"name":"崇文区"},{"name":"宣武区"},{"name":"朝阳区"},{"name":"海淀区"},{"name":"丰台区"},{"name":"石景山区"},{"name":"房山区"},{"name":"通州区"},{"name":"顺义区"},{"name":"昌平区"},{"name":"大兴区"},{"name":"怀柔区"},{"name":"平谷区"},{"name":"门头沟区"},{"name":"密云县"},{"name":"延庆县"},{"name":"其他"}]
     * type : 0
     */

    private String name;
    private int type;
    /**
     * name : 东城区
     */

    private List<City> sub;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<City> getSub() {
        return sub;
    }

    public void setSub(List<City> sub) {
        this.sub = sub;
    }
}
