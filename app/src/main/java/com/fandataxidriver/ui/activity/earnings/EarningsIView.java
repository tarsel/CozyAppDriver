package com.fandataxidriver.ui.activity.earnings;


import com.fandataxidriver.base.MvpView;
import com.fandataxidriver.data.network.model.EarningsList;

public interface EarningsIView extends MvpView {

    void onSuccess(EarningsList earningsLists);

    void onError(Throwable e);
}
