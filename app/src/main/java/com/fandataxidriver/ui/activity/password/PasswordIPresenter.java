package com.fandataxidriver.ui.activity.password;

import com.fandataxidriver.base.MvpPresenter;

import java.util.HashMap;

public interface PasswordIPresenter<V extends PasswordIView> extends MvpPresenter<V> {

    void login(HashMap<String, Object> obj);
    void forgot(HashMap<String, Object> obj);

}
