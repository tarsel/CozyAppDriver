package com.fandataxidriver.ui.activity.card;

import com.fandataxidriver.base.MvpPresenter;

public interface CardIPresenter<V extends CardIView> extends MvpPresenter<V> {

    void deleteCard(String cardId);

    void card();

    void changeCard(String cardId);
}
