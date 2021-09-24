package com.fandataxidriver.ui.activity.summary;


import com.fandataxidriver.base.MvpPresenter;

public interface SummaryIPresenter<V extends SummaryIView> extends MvpPresenter<V> {

    void getSummary();
}
