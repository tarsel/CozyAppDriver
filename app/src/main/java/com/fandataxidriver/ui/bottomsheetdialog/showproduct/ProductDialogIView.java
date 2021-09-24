package com.fandataxidriver.ui.bottomsheetdialog.showproduct;

import com.fandataxidriver.base.MvpView;

public interface ProductDialogIView extends MvpView {

    void onSuccess(Object response);
    void onError(Throwable e);
}
