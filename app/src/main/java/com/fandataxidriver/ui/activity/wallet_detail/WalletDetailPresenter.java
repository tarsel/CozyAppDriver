package com.fandataxidriver.ui.activity.wallet_detail;

import com.fandataxidriver.base.BasePresenter;
import com.fandataxidriver.data.network.model.Transaction;

import java.util.ArrayList;

public class WalletDetailPresenter<V extends WalletDetailIView> extends BasePresenter<V> implements WalletDetailIPresenter<V> {
    @Override
    public void setAdapter(ArrayList<Transaction> myList) {
        getMvpView().setAdapter(myList);
    }
}
