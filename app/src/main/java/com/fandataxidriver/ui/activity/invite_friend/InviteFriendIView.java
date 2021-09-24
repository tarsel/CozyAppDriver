package com.fandataxidriver.ui.activity.invite_friend;

import com.fandataxidriver.base.MvpView;
import com.fandataxidriver.data.network.model.UserResponse;

public interface InviteFriendIView extends MvpView {

    void onSuccess(UserResponse response);
    void onError(Throwable e);

}
