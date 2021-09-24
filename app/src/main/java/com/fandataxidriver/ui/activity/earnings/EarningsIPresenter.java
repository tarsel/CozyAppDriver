package com.fandataxidriver.ui.activity.earnings;


import com.fandataxidriver.base.MvpPresenter;

public interface EarningsIPresenter<V extends EarningsIView> extends MvpPresenter<V> {

    void getEarnings();
}
