package com.fandataxidriver.ui.activity.wallet;

import com.fandataxidriver.base.MvpPresenter;

import java.util.HashMap;

public interface WalletIPresenter<V extends WalletIView> extends MvpPresenter<V> {

    void getWalletData();
    void addMoney(HashMap<String, Object> obj);

    void getProfile();
}
