package com.fandataxidriver.ui.activity.wallet_detail;

import com.fandataxidriver.base.MvpView;
import com.fandataxidriver.data.network.model.Transaction;

import java.util.ArrayList;

public interface WalletDetailIView extends MvpView {
    void setAdapter(ArrayList<Transaction> myList);
}
