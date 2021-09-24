package com.fandataxidriver.ui.bottomsheetdialog.showproduct;

import com.fandataxidriver.base.MvpPresenter;

public interface ProductDialogIPresenter<V extends ProductDialogIView> extends MvpPresenter<V> {

    void updateStatus(String deliveryId, String status);
}
