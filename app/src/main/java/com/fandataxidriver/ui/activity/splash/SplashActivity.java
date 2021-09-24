package com.fandataxidriver.ui.activity.splash;

import android.app.AlertDialog;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.fandataxidriver.BuildConfig;
import com.fandataxidriver.R;
import com.fandataxidriver.base.BaseActivity;
import com.fandataxidriver.common.AppConstant;
import com.fandataxidriver.common.SharedHelper;
import com.fandataxidriver.common.Utilities;
import com.fandataxidriver.data.network.model.CheckVersion;
import com.fandataxidriver.ui.activity.main.MainActivity;
import com.fandataxidriver.ui.activity.welcome.WelcomeActivity;
import com.google.firebase.FirebaseApp;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.HashMap;

public class SplashActivity extends BaseActivity implements SplashIView {

    private static final String TAG = "SplashActivity";
    private SplashPresenter presenter;

    @Override
    public int getLayoutId() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        return R.layout.activity_splash;
    }

    @Override
    public void initView() {
        presenter = new SplashPresenter();
        presenter.attachView(this);

        ImageView imageView=findViewById(R.id.gifImage);
        Glide.with(this)
                .load(R.raw.splash_gif)
                .into(imageView);


        FirebaseApp.initializeApp(this);

        deviceToken = SharedHelper.getKeyFCM(this, AppConstant.SharedPref.DEVICE_TOKEN);
        deviceId = SharedHelper.getKeyFCM(this, AppConstant.SharedPref.DEVICE_ID);

        if (TextUtils.isEmpty(deviceToken))
            FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(this, instanceIdResult -> {
                deviceToken = instanceIdResult.getToken();
                SharedHelper.putKeyFCM(SplashActivity.this, AppConstant.SharedPref.DEVICE_TOKEN, deviceToken);
            });

        if (TextUtils.isEmpty(deviceId)) {
            deviceId = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
            SharedHelper.putKeyFCM(this, AppConstant.SharedPref.DEVICE_ID, deviceId);
        }

        Utilities.printV("FCM TOKEN ===> ", deviceToken);
        Utilities.printV("FCM TOKEN ID ===> ", deviceId);

        // we don't need this
        //  checkVersion();
        printHashKey();

        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                checkUserAppInstalled();

            }
        },7000);
    }

    private void checkVersion() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("version", BuildConfig.VERSION_CODE);
        map.put("device_type", BuildConfig.DEVICE_TYPE);
        map.put("sender", "provider");
        presenter.checkVersion(map);

        Utilities.printV("FCM TOKEN ID===>", SharedHelper.getKeyFCM(this, "device_id"));
    }

    private void checkUserAppInstalled() {
        if (Utilities.isPackageExisted(SplashActivity.this, AppConstant.userAppPackageName))
            showWarningAlert(getString(R.string.user_provider_app_warning));
        else redirectToScreen();
    }

    private void showWarningAlert(String message) {
        try {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(SplashActivity.this);
            alertDialogBuilder
                    .setTitle(getResources().getString(R.string.warning))
                    .setMessage(message)
                    .setCancelable(false)
                    .setPositiveButton(getResources().getString(R.string.continue_app),
                            (dialog, id) -> redirectToScreen());
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void verifyAppInstalled() {
        checkUserAppInstalled();
    }

    private void redirectToScreen() {
        if (SharedHelper.getKey(this, AppConstant.SharedPref.LOGGGED_IN).equalsIgnoreCase("true"))
            startActivity(new Intent(activity(), MainActivity.class));
        else
            startActivity(new Intent(activity(), WelcomeActivity.class));
    }

    @Override
    public void onSuccess(Object user) {

    }

    @Override
    public void onSuccess(CheckVersion version) {
        try {
            Utilities.printV("jsonObj===>", version.getForceUpdate() + "");
            if (!version.getForceUpdate()) presenter.handlerCall();
            else showAlertDialog(version.getUrl());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showAlertDialog(String url) {
        AlertDialog.Builder builder = new AlertDialog.Builder(SplashActivity.this);
        builder.setTitle(getString(R.string.app_name));
        builder.setMessage(getString(R.string.update_version_message));
        builder.setPositiveButton("Update", (dialog, which) -> {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                    | Intent.FLAG_ACTIVITY_CLEAR_TOP
                    | Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
        });
        builder.show();
    }

    @Override
    public void onError(Throwable e) {
        hideLoading();
        if (e != null)
            onErrorBase(e);
    }

    @Override
    public void onCheckVersionError(Throwable e) {
        hideLoading();
        presenter.handlerCall();
    }

    public void printHashKey() {
//        try {
//            PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNING_CERTIFICATES);
//            for (Signature signature : info.signatures) {
//                MessageDigest md = MessageDigest.getInstance("SHA");
//                md.update(signature.toByteArray());
//                String hashKey = new String(Base64.encode(md.digest(), 0));
//                Log.i(TAG, "printHashKey() Hash Key: " + hashKey);
//            }
//        } catch (Exception e) {
//            Log.e(TAG, "printHashKey()", e);
//        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        NotificationManager notificationManager = (NotificationManager)
                this.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancelAll();
    }
}
