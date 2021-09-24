package com.fandataxidriver.ui.bottomsheetdialog.rating;

import com.fandataxidriver.base.MvpPresenter;

import java.util.HashMap;

public interface RatingDialogIPresenter<V extends RatingDialogIView> extends MvpPresenter<V> {

    void rate(HashMap<String, Object> obj, Integer id);
}
