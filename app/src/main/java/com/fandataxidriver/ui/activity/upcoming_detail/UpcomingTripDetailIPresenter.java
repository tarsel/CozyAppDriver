package com.fandataxidriver.ui.activity.upcoming_detail;


import com.fandataxidriver.base.MvpPresenter;

public interface UpcomingTripDetailIPresenter<V extends UpcomingTripDetailIView> extends MvpPresenter<V> {

    void getUpcomingDetail(String request_id);

}
