package com.fandataxidriver.ui.activity.help;


import com.fandataxidriver.base.MvpPresenter;

public interface HelpIPresenter<V extends HelpIView> extends MvpPresenter<V> {

    void getHelp();
}
