package com.fandataxidriver.ui.activity.change_password;

import com.fandataxidriver.base.MvpPresenter;

import java.util.HashMap;

public interface ChangePasswordIPresenter<V extends ChangePasswordIView> extends MvpPresenter<V> {

    void changePassword(HashMap<String, Object> obj);
}
