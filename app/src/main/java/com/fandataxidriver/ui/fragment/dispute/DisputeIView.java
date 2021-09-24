package com.fandataxidriver.ui.fragment.dispute;

import com.fandataxidriver.base.MvpView;
import com.fandataxidriver.data.network.model.DisputeResponse;

import java.util.List;

public interface DisputeIView extends MvpView {

    void onSuccessDispute(List<DisputeResponse> responseList);

    void onSuccess(Object object);

    void onError(Throwable e);
}
