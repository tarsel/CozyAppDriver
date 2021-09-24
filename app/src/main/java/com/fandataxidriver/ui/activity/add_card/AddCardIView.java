package com.fandataxidriver.ui.activity.add_card;

import com.fandataxidriver.base.MvpView;

public interface AddCardIView extends MvpView {

    void onSuccess(Object card);

    void onError(Throwable e);
}
