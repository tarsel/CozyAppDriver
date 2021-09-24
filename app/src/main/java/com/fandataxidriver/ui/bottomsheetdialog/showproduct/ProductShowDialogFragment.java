package com.fandataxidriver.ui.bottomsheetdialog.showproduct;

import android.annotation.SuppressLint;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.fandataxidriver.MvpApplication;
import com.fandataxidriver.R;
import com.fandataxidriver.base.BaseBottomSheetDialogFragment;
import com.fandataxidriver.ui.adapter.ProductAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ProductShowDialogFragment extends BaseBottomSheetDialogFragment  implements ProductAdapter.ClickListener,ProductDialogIView{

    Unbinder unbinder;

    @BindView(R.id.rv_product)
    RecyclerView rv_product;

    ProductAdapter adapter;

    ProductDialogIPresenter presenter;
    public ProductShowDialogFragment() {
    }

    @Override
    public int getLayoutId() {
        return R.layout.dilog_product;
    }

    @Override
    public void initView(View view) {
        unbinder = ButterKnife.bind(this, view);
        presenter= new ProductDialogPresenter();
        presenter.attachView(this);
        init();
    }

    @SuppressLint("SetTextI18n")
    private void init() {
        if (MvpApplication.tripResponse != null) {
          rv_product.setLayoutManager(new LinearLayoutManager(getContext()));
          adapter= new ProductAdapter(getContext(),MvpApplication.tripResponse.getRequests().get(0).getRequest().getDelivery_detail());
          adapter.setClickListener(this::updateStatus);
          rv_product.setAdapter(adapter);
        }
    }

    @OnClick(R.id.iv_close)
    public void onViewClicked() {
        dismissAllowingStateLoss();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void updateStatus(String deliveryStatus, int adapterPosition, String deliveryId) {
           presenter.updateStatus(deliveryId,deliveryStatus);
           showLoading();
    }

    @Override
    public void onSuccess(Object object) {
        dismissAllowingStateLoss();
        adapter.notifyDataSetChanged();
        Toast.makeText(getContext(), "Status Updated Successfully", Toast.LENGTH_SHORT).show();
        Log.d("Product", "onSuccess: "+object.toString());
        hideLoading();
    }


    @Override
    public void onError(Throwable e) {
        hideLoading();
    }

}
