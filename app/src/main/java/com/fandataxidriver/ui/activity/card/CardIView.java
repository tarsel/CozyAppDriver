package com.fandataxidriver.ui.activity.card;

import com.fandataxidriver.base.MvpView;
import com.fandataxidriver.data.network.model.Card;

import java.util.List;

public interface CardIView extends MvpView {

    void onSuccess(Object card);

    void onSuccess(List<Card> cards);

    void onError(Throwable e);

    void onSuccessChangeCard(Object card);
}
