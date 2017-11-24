package com.arkui.fz_tools._interface;

import com.arkui.fz_tools.entity.CodeEntity;
import com.arkui.fz_tools.entity.VerPicEntity;

/**
 * Created by 84658 on 2017/11/21.
 */

public interface CodeInterface {
    void loadImageSuccess(VerPicEntity verPicEntity);
    void loadCodeSuccess(CodeEntity codeEntity);
    void onFail(String errorMessage,int type);

}
