package com.fandataxidriver.ui.activity.past_detail;


import com.fandataxidriver.base.MvpPresenter;

public interface PastTripDetailIPresenter<V extends PastTripDetailIView> extends MvpPresenter<V> {

    void getPastTripDetail(String request_id);
}
