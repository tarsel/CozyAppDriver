package com.fandataxidriver.ui.fragment.incoming_request;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.widget.AppCompatRatingBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.fandataxidriver.BuildConfig;
import com.fandataxidriver.R;
import com.fandataxidriver.base.BaseFragment;
import com.fandataxidriver.common.Utilities;
import com.fandataxidriver.data.network.model.Request_;
import com.fandataxidriver.MvpApplication;
import com.fandataxidriver.ui.adapter.ItemAdapter;
import com.rbrooks.indefinitepagerindicator.IndefinitePagerIndicator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.StringTokenizer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;
import es.dmoral.toasty.Toasty;

public class IncomingRequestFragment extends BaseFragment implements IncomingRequestIView {

    @BindView(R.id.btnReject)
    Button btnReject;
    @BindView(R.id.btnAccept)
    Button btnAccept;
    Unbinder unbinder;
    @BindView(R.id.lblCount)
    TextView lblCount;
    @BindView(R.id.imgUser)
    CircleImageView imgUser;
    @BindView(R.id.lblUserName)
    TextView lblUserName;
    @BindView(R.id.ratingUser)
    AppCompatRatingBar ratingUser;
    @BindView(R.id.lblLocationName)
    TextView lblLocationName;
    @BindView(R.id.lblDrop)
    TextView lblDrop;
    @BindView(R.id.lblScheduleDate)
    TextView lblScheduleDate;

    @BindView(R.id.rv_product)
    RecyclerView recyclerView;
    @BindView(R.id.recyclerview_pager_indicator)
    IndefinitePagerIndicator indicator;

//
//    TextView product_name;
//    @BindView(R.id.reciver_name_txt)
//    TextView reciver_name;
//    @BindView(R.id.reciver_number_txt)
//    TextView reciver_num;
//    @BindView(R.id.instruction_txt)
//    TextView instructions_txt;
//    @BindView(R.id.delivery_adr_txt)
//    TextView delivery_adr;

    ItemAdapter adapter;

    private IncomingRequestPresenter presenter = new IncomingRequestPresenter();
    private Context context;
    public static CountDownTimer countDownTimer;
    public static MediaPlayer mPlayer;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_incoming_request;
    }

    @Override
    public View initView(View view) {
        unbinder = ButterKnife.bind(this, view);
        context = getContext();
        presenter.attachView(this);
        mPlayer = MediaPlayer.create(context, R.raw.alert_tone);
        init();
        return view;
    }

    @SuppressLint("SetTextI18n")
    void init() {
        Request_ data = MvpApplication.DATUM;
        if (data != null) {
            lblUserName.setText(String.format("%s %s", data.getUser().getFirstName(),
                    data.getUser().getLastName()));
            ratingUser.setRating(Float.parseFloat(data.getUser().getRating()));
            if (data.getSAddress() != null && !data.getSAddress().isEmpty()
                    || data.getDAddress() != null && !data.getDAddress().isEmpty()) {
                lblLocationName.setText(data.getSAddress());
                lblDrop.setText(data.getDAddress());
            }
            if (data.getUser().getPicture() != null)
                Glide.with(activity()).load(BuildConfig.BASE_IMAGE_URL + data.getUser()
                        .getPicture()).apply(RequestOptions.placeholderOf(R.drawable.ic_user_placeholder)
                        .dontAnimate().error(R.drawable.ic_user_placeholder)).into(imgUser);
            adapter= new ItemAdapter(getContext(),data.getDelivery_detail());
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
            Objects.requireNonNull(indicator).attachToRecyclerView(recyclerView);
            recyclerView.setAdapter(adapter);

         // product_name.setText(data.getDelivery_detail().getItemToDeliver());
//            reciver_name.setText(data.getDelivery_detail().getReceiverName());
//            reciver_num.setText(data.getDelivery_detail().getReceiverMobile());
//            instructions_txt.setText(data.getDelivery_detail().getAnyInstructions());
//            delivery_adr.setText(data.getDelivery_detail().getDeliveryAddress());
        }

        String isScheduled = data.getIsScheduled();
        String scheduledAt = data.getScheduleAt();
        if (isScheduled != null && isScheduled.equalsIgnoreCase("NO")) {
            lblScheduleDate.setVisibility(View.INVISIBLE);
        } else {
            if (scheduledAt != null && isScheduled.equalsIgnoreCase("YES")) {
                StringTokenizer tk = new StringTokenizer(scheduledAt);
                String date = tk.nextToken();
                String time = tk.nextToken();
                @SuppressLint("SimpleDateFormat")
                SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
                @SuppressLint("SimpleDateFormat")
                SimpleDateFormat sdfs = new SimpleDateFormat("hh:mm a");
                Date dt;
                try {
                    dt = sdf.parse(time);
                    lblScheduleDate.setVisibility(View.VISIBLE);
                    lblScheduleDate.setText(getString(R.string.schedule_for) + " " +
                            Utilities.convertDate(scheduledAt) + " " + sdfs.format(dt));
                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
        }
        countDownTimer = new CountDownTimer(MvpApplication.time_to_left * 1000, 1000) {

            public void onTick(long millisUntilFinished) {
                lblCount.setText(String.valueOf(millisUntilFinished / 1000));
                setTvZoomInOutAnimation(lblCount);
            }

            public void onFinish() {
                try {
                    context.sendBroadcast(new Intent("INTENT_FILTER"));
                    mPlayer.stop();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        countDownTimer.start();
    }

    private void setTvZoomInOutAnimation(final TextView textView) {
        final float startSize = 20;
        final float endSize = 13;
        final int animationDuration = 900; // Animation duration in ms

        ValueAnimator animator = ValueAnimator.ofFloat(startSize, endSize);
        animator.setDuration(animationDuration);

        animator.addUpdateListener(valueAnimator -> {
            float animatedValue = (Float) valueAnimator.getAnimatedValue();
            textView.setTextSize(animatedValue);
        });
        //animator.setRepeatCount(ValueAnimator.INFINITE);  // Use this line for infinite animations
        animator.setRepeatCount(2);
        animator.start();
    }


    @OnClick({R.id.btnReject, R.id.btnAccept})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnReject:
                if (MvpApplication.DATUM != null) {
                    Request_ datum = MvpApplication.DATUM;
                    showLoading();
                    presenter.cancel(datum.getId());
                    MvpApplication.time_to_left = 60;
                }
                break;
            case R.id.btnAccept:
                if (MvpApplication.DATUM != null) {
                    Request_ datum = MvpApplication.DATUM;
                    showLoading();
                    presenter.accept(datum.getId());
                    MvpApplication.time_to_left = 60;
                }
                break;
        }
    }

    @Override
    public void onSuccessAccept(Object responseBody) {
        countDownTimer.cancel();
        hideLoading();
        Toast.makeText(getContext(), getString(R.string.ride_accepted), Toast.LENGTH_SHORT).show();
        getContext().sendBroadcast(new Intent("INTENT_FILTER"));
        try {
            getActivity().getSupportFragmentManager().beginTransaction().remove(IncomingRequestFragment.this).commitAllowingStateLoss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onSuccessCancel(Object object) {
        countDownTimer.cancel();
        hideLoading();
        getActivity().getSupportFragmentManager().beginTransaction().remove(IncomingRequestFragment.this).commitAllowingStateLoss();
        Toasty.success(context, getString(R.string.ride_cancelled), Toast.LENGTH_SHORT, true).show();
        context.sendBroadcast(new Intent("INTENT_FILTER"));
    }

    @Override
    public void onError(Throwable e) {
        try {
            hideLoading();
            if (mPlayer.isPlaying()) mPlayer.stop();
            if (e != null)
                onErrorBase(e);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (!mPlayer.isPlaying())
            mPlayer.start();
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mPlayer.isPlaying())
            mPlayer.stop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPlayer.isPlaying())
            mPlayer.stop();
    }
}