package com.fandataxidriver.ui.bottomsheetdialog.rating;

import com.fandataxidriver.base.MvpView;
import com.fandataxidriver.data.network.model.Rating;

public interface RatingDialogIView extends MvpView {

    void onSuccess(Rating rating);
    void onError(Throwable e);
}
