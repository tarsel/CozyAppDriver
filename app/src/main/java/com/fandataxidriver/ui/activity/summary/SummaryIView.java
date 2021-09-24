package com.fandataxidriver.ui.activity.summary;


import com.fandataxidriver.base.MvpView;
import com.fandataxidriver.data.network.model.Summary;

public interface SummaryIView extends MvpView {

    void onSuccess(Summary object);

    void onError(Throwable e);
}
