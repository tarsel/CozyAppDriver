package com.fandataxidriver.ui.fragment.offline;

import com.fandataxidriver.base.MvpView;

public interface OfflineIView extends MvpView {

    void onSuccess(Object object);
    void onError(Throwable e);
}
