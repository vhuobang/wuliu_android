package com.arkui.fz_tools._interface;

import com.arkui.fz_tools.entity.InviteFriendEntity;

import java.util.List;

/**
 * Created by 84658 on 2017/8/10.
 */

public interface InviteFriendListInterface {
    void onSuccess(List<InviteFriendEntity> inviteFriendEntities);
    void onFail(String message);
}
