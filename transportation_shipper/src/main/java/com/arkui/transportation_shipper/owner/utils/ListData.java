package com.arkui.transportation_shipper.owner.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nmliz on 2017/6/29.
 * 给 adapter 添加测试数据
 */

public class ListData {

    public static List<String> getTestData(final String content) {
        List<String> stringList = new ArrayList<>();
        stringList.add(content);
        stringList.add(content);
        stringList.add(content);
        return stringList;
    }

}
