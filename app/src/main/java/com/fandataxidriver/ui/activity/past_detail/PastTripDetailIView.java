package com.fandataxidriver.ui.activity.past_detail;


import com.fandataxidriver.base.MvpView;
import com.fandataxidriver.data.network.model.HistoryDetail;

public interface PastTripDetailIView extends MvpView {

    void onSuccess(HistoryDetail historyDetail);
    void onError(Throwable e);
}
