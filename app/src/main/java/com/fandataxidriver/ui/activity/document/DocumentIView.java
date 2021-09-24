package com.fandataxidriver.ui.activity.document;

import com.fandataxidriver.base.MvpView;
import com.fandataxidriver.data.network.model.DriverDocumentResponse;

public interface DocumentIView extends MvpView {

    void onSuccess(DriverDocumentResponse response);

    void onDocumentSuccess(DriverDocumentResponse response);

    void onError(Throwable e);

    void onSuccessLogout(Object object);

}
