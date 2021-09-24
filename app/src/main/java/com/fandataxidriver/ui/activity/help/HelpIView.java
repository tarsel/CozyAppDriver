package com.fandataxidriver.ui.activity.help;

import com.fandataxidriver.base.MvpView;
import com.fandataxidriver.data.network.model.Help;

public interface HelpIView extends MvpView {

    void onSuccess(Help object);

    void onError(Throwable e);
}
