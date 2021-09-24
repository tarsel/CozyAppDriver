package com.fandataxidriver.ui.fragment.upcoming;


import com.fandataxidriver.base.MvpPresenter;

public interface UpcomingTripIPresenter<V extends UpcomingTripIView> extends MvpPresenter<V> {

    void getUpcoming();

}
