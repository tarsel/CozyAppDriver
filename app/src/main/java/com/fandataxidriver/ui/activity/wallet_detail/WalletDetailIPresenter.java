package com.fandataxidriver.ui.activity.wallet_detail;

import com.fandataxidriver.base.MvpPresenter;
import com.fandataxidriver.data.network.model.Transaction;

import java.util.ArrayList;

public interface WalletDetailIPresenter<V extends WalletDetailIView> extends MvpPresenter<V> {
    void setAdapter(ArrayList<Transaction> myList);
}
