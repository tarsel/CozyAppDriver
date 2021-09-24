package com.fandataxidriver.ui.bottomsheetdialog.cancel;

import com.fandataxidriver.base.MvpView;
import com.fandataxidriver.data.network.model.CancelResponse;

import java.util.List;

public interface CancelDialogIView extends MvpView {

    void onSuccessCancel(Object object);
    void onError(Throwable e);
    void onSuccess(List<CancelResponse> response);
    void onReasonError(Throwable e);
}
