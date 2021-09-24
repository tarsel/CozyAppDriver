package com.fandataxidriver.ui.bottomsheetdialog.invoice_flow;

import com.fandataxidriver.base.MvpView;

public interface InvoiceDialogIView extends MvpView {

    void onSuccess(Object object);
    void onError(Throwable e);
}
