package com.fandataxidriver.ui.fragment.past;


import com.fandataxidriver.base.MvpView;
import com.fandataxidriver.data.network.model.HistoryList;

import java.util.List;

public interface PastTripIView extends MvpView {

    void onSuccess(List<HistoryList> historyList);
    void onError(Throwable e);
}
