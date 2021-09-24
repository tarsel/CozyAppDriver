package com.fandataxidriver.ui.bottomsheetdialog.showproduct;

import com.fandataxidriver.base.BasePresenter;
import com.fandataxidriver.data.network.APIClient;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ProductDialogPresenter<V extends ProductDialogIView> extends BasePresenter<V> implements ProductDialogIPresenter<V> {

    @Override
    public void updateStatus(String deliveryId, String status) {
        getCompositeDisposable().add(APIClient
                .getAPIClient()
                .changeStatus(deliveryId)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getMvpView()::onSuccess, getMvpView()::onError));

    }
}
