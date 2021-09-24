package com.fandataxidriver.ui.activity.setting;

import com.fandataxidriver.base.MvpPresenter;

public interface SettingsIPresenter<V extends SettingsIView> extends MvpPresenter<V> {
    void changeLanguage(String languageID);
}
