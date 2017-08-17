package com.arkui.fz_tools._interface;

import com.arkui.fz_tools.entity.WayBillDetailEntity;

/**
 * Created by 84658 on 2017/8/15.
 */

public interface WayBillDetialsInterface {
     void  onSuccess(WayBillDetailEntity wayBillDetailEntity);
     void onFail(String  message);

}
