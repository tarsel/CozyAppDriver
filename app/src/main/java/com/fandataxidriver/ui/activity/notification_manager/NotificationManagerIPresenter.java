package com.fandataxidriver.ui.activity.notification_manager;

import com.fandataxidriver.base.MvpPresenter;

public interface NotificationManagerIPresenter<V extends NotificationManagerIView> extends MvpPresenter<V> {
    void getNotificationManager();
}
