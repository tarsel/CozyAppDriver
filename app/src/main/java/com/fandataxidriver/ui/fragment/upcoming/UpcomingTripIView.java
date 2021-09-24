package com.fandataxidriver.ui.fragment.upcoming;

import com.fandataxidriver.base.MvpView;
import com.fandataxidriver.data.network.model.HistoryList;

import java.util.List;

public interface UpcomingTripIView extends MvpView {

    void onSuccess(List<HistoryList> historyList);
    void onError(Throwable e);
}
