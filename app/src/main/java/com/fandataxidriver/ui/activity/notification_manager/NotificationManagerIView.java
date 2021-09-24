package com.fandataxidriver.ui.activity.notification_manager;

import com.fandataxidriver.base.MvpView;
import com.fandataxidriver.data.network.model.NotificationManager;

import java.util.List;

public interface NotificationManagerIView extends MvpView {

    void onSuccess(List<NotificationManager> managers);

    void onError(Throwable e);

}