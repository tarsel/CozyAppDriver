package com.fandataxidriver.ui.fragment.past;


import com.fandataxidriver.base.MvpPresenter;

public interface PastTripIPresenter<V extends PastTripIView> extends MvpPresenter<V> {

    void getHistory();

}
