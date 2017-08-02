package com.arkui.fz_tools.entity;

/**
 * Created by hubo on 2016/12/6.
 */

public class OptionName {

    private String name;

    public OptionName(){

    }

    public OptionName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "OptionName{" +
                "name='" + name + '\'' +
                '}';
    }
}
