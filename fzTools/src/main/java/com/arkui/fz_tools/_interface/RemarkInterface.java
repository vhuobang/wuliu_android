package com.arkui.fz_tools._interface;

import com.arkui.fz_tools.entity.RemarkEntity;

import java.util.List;

/**
 * Created by 84658 on 2017/9/25.
 */

public interface RemarkInterface {
     void remarkList(List<RemarkEntity> remarkEntities);
    void  noRemark(String message);

}
