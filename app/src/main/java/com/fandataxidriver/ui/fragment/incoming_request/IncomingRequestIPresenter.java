package com.fandataxidriver.ui.fragment.incoming_request;

import com.fandataxidriver.base.MvpPresenter;

public interface IncomingRequestIPresenter<V extends IncomingRequestIView> extends MvpPresenter<V> {

    void accept(Integer id);
    void cancel(Integer id);
}
