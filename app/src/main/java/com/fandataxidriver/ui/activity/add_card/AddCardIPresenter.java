package com.fandataxidriver.ui.activity.add_card;

import com.fandataxidriver.base.MvpPresenter;

public interface AddCardIPresenter<V extends AddCardIView> extends MvpPresenter<V> {

    void addCard(String stripeToken);
}
