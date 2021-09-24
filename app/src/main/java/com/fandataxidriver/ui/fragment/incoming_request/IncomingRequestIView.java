package com.fandataxidriver.ui.fragment.incoming_request;

import com.fandataxidriver.base.MvpView;

public interface IncomingRequestIView extends MvpView {

    void onSuccessAccept(Object responseBody);
    void onSuccessCancel(Object object);
    void onError(Throwable e);
}
