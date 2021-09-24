package com.fandataxidriver.common;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.fandataxidriver.MvpApplication;
import com.fandataxidriver.ui.activity.main.MainActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.fandataxidriver.R;
import com.fandataxidriver.data.network.model.LatLngFireBaseDB;

/**
 * Created by santhosh@appoets.com on 11-10-2017.
 */



public class GPSTracker extends Service {

    String key = "";

    private double endLat = 0;
    private double endLng = 0;
    private double bearing = 0;


    String NOTIFICATION_CHANNEL_ID = "com.example.simpleapp";

    /**
     * Request location updates with the highest possible frequency on gps.
     * Typically, this means one update per second for gps.
     */
    private static final long GPS_TIME = 1000;

    /**
     * For the network provider, which gives locations with less accuracy (less reliable),
     * request updates every 5 seconds.
     */
    private static final long NET_TIME = 5000;

    /**
     * For the filter-time argument we use a "real" value: the predictions are triggered by a timer.
     * Lets say we want 5 updates (estimates) per second = update each 200 millis.
     */
    private static final long FILTER_TIME = 1000;


    private static final String TAG = "GPSTracker";
    private LocationManager locationManager = null;

    String status_update = "Init";
    Context context;
    private FusedLocationProviderClient fusedLocationClient;
    private LocationRequest locationRequest;
    private LocationCallback locationCallback;

    /**
     * Listener used to get updates from KalmanLocationManager (the good old Android LocationListener).
     */
//    private LocationListener mLocationListener = new LocationListener() {
//
//        @Override
//        public void onLocationChanged(Location location) {
//
//
//           Log.e("onLocationChanged", "onLocationChanged"+location);
//
//
//             // GPS location
//
//                if (MvpApplication.DATUM == null) return;
//                MvpApplication.mLastKnownLocation = location;
//                getApplicationContext().sendBroadcast(new Intent("INTENT_FILTER"));
//
//                if (MvpApplication.DATUM.getStatus().equalsIgnoreCase("ACCEPTED")
//                        || MvpApplication.DATUM.getStatus().equalsIgnoreCase("STARTED")
//                        || MvpApplication.DATUM.getStatus().equalsIgnoreCase("ARRIVED")
//                        || MvpApplication.DATUM.getStatus().equalsIgnoreCase("PICKEDUP")) {
//
//                    Log.d(TAG, "onLocationChanged: Lattitude"+location.getLatitude());
//                    Log.d(TAG, "onLocationChanged: longtitude"+location.getLongitude());
//               }
//                saveLocationToFireBaseDB(MvpApplication.mLastKnownLocation.getLatitude(), MvpApplication.mLastKnownLocation.getLongitude());
//
////            }
////
////         //  Network location
////            if (location.getProvider().equals(LocationManager.NETWORK_PROVIDER)) {
////                if (MvpApplication.DATUM == null) return;
////                MvpApplication.mLastKnownLocation = location;
////                getApplicationContext().sendBroadcast(new Intent("INTENT_FILTER"));
////                if (MvpApplication.DATUM.getStatus().equalsIgnoreCase("ACCEPTED")
////                        || MvpApplication.DATUM.getStatus().equalsIgnoreCase("STARTED")
////                        || MvpApplication.DATUM.getStatus().equalsIgnoreCase("ARRIVED")
////                        || MvpApplication.DATUM.getStatus().equalsIgnoreCase("PICKEDUP")) {
////
////
////                    Log.d(TAG, "onLocationChanged: Lattitude"+location.getLatitude());
////                    Log.d(TAG, "onLocationChanged: longtitude"+location.getLongitude());
////               }
////                saveLocationToFireBaseDB(MvpApplication.mLastKnownLocation.getLatitude(), MvpApplication.mLastKnownLocation.getLongitude());
////
////            }
//
//            // If Kalman location and google maps activated the supplied mLocationSource
//
//
//
//
//
//        }


//        @Override
//        public void onStatusChanged(String provider, int status, Bundle extras) {
//            switch (status) {
//                case LocationProvider.OUT_OF_SERVICE:
//                    break;
//                case LocationProvider.TEMPORARILY_UNAVAILABLE:
//                    break;
//                case LocationProvider.AVAILABLE:
//                    break;
//            }
//        }
//
//        @Override
//        public void onProviderEnabled(String provider) {
//
////            Toast.makeText(context, String.format("Provider '%s' enabled", provider), Toast.LENGTH_SHORT).show();
//
//        }
//
//        @Override
//        public void onProviderDisabled(String provider) {
//
////            Toast.makeText(context, String.format("Provider '%s' disabled", provider), Toast.LENGTH_SHORT).show();
//
//        }
//    };
    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand");
        status_update = "Service Starts";

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) {
            startMyOwnForeground();
        } else {

            Intent notificationIntent = new Intent(this, MainActivity.class);

            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                    notificationIntent, 0);

            Notification notification = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle(getString(R.string.app_name))
                    .setContentText(getString(R.string.app_name) + "- Current location sharing")
                    .setContentIntent(pendingIntent).build();

            startForeground(1, notification);
        }

        return START_STICKY;
    }


    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate");
        context = getBaseContext();

        initializeLocationManager();


        buildLocationRequest();
        buildLocationCallBack();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            Log.d(TAG, "onCreate: Not Granted ");
            return;
        }
        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null);


//            try {
//                locationManager.requestLocationUpdates(
//                        LocationManager.NETWORK_PROVIDER, LOCATION_INTERVAL, LOCATION_DISTANCE,
//                        mLocationListener);
//            } catch (SecurityException ex) {
//                Log.i(TAG, "fail to request location update, ignore", ex);
//            } catch (IllegalArgumentException ex) {
//                Log.d(TAG, "network provider does not exist, " + ex.getMessage());
//            }
//
//
//            try {
//                locationManager.requestLocationUpdates(
//                        LocationManager.GPS_PROVIDER, LOCATION_INTERVAL, LOCATION_DISTANCE,
//                        mLocationListener);
//            } catch (SecurityException ex) {
//                 Log.i(TAG, "fail to request location update, ignore", ex);
//            } catch (IllegalArgumentException ex) {
//                Log.d(TAG, "gps provider does not exist " + ex.getMessage());
//            }

//        mKalmanLocationManager.requestLocationUpdates(
//                KalmanLocationManager.UseProvider.GPS_AND_NET, FILTER_TIME, GPS_TIME, NET_TIME, mLocationListener, true);


    }

    private void buildLocationRequest() {
        locationRequest = new LocationRequest(); locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY); locationRequest.setInterval(100);
        locationRequest.setFastestInterval(100); locationRequest.setSmallestDisplacement(1);
    }
    //Build the location callback object and obtain the location results //as demonstrated below:
    private void buildLocationCallBack() {
        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                Location location=locationResult.getLocations().get(0);
                String latitude = String.valueOf(location.getLatitude());
                String longitude = String.valueOf(location.getLongitude());
                Log.d(TAG, "onLocationChanged: Lattitude"+location.getLatitude());
                Log.d(TAG, "onLocationChanged: longtitude"+location.getLongitude());
                if (MvpApplication.DATUM == null) return;
                MvpApplication.mLastKnownLocation = location;
                getApplicationContext().sendBroadcast(new Intent("INTENT_FILTER"));

                if (MvpApplication.DATUM.getStatus().equalsIgnoreCase("ACCEPTED")
                        || MvpApplication.DATUM.getStatus().equalsIgnoreCase("STARTED")
                        || MvpApplication.DATUM.getStatus().equalsIgnoreCase("ARRIVED")
                        || MvpApplication.DATUM.getStatus().equalsIgnoreCase("PICKEDUP")) {

                    Log.d(TAG, "onLocationChanged: Lattitude"+location.getLatitude());
                    Log.d(TAG, "onLocationChanged: longtitude"+location.getLongitude());
                }
                saveLocationToFireBaseDB(MvpApplication.mLastKnownLocation.getLatitude(), MvpApplication.mLastKnownLocation.getLongitude());



            }

            private void saveLocationToFireBaseDB(double lat, double lng) {
                String refPath = "loc_p_" + MvpApplication.DATUM.getProviderId();
                System.out.println("RRR GPSTracker.saveLocationToFireBaseDB :: " + refPath);
                if (!refPath.equals("loc_p_0"))
                    try {
                        DatabaseReference mLocationRef = FirebaseDatabase.getInstance().getReference(refPath);
                        key = key == null ? mLocationRef.push().getKey() : key;

                        if (endLng == 0 || endLat == 0) {
                            endLng = lng;
                            endLat = lat;
                        } else {
                            double startLat = endLat;
                            endLat = lat;
                            double startLng = endLng;
                            endLng = lng;

                            bearing = bearingBetweenLocations(startLat, startLng, endLat, endLng);
                            System.out.println("RRR bearing " + bearing);
                        }

                        mLocationRef.setValue(new LatLngFireBaseDB(lat, lng, bearing));

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
            }


        };
    }


    private void initializeLocationManager() {
        Log.d(TAG, "initializeLocationManager");
        if (fusedLocationClient == null) {
            fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
            Log.d(TAG, "initializeLocationManager Fuesd Client");
        }
//        if (locationManager == null) {
//            locationManager = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
//        }
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {

        status_update = "Service Task destroyed onTaskRemoved";


    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void startMyOwnForeground() {
        String channelName = "Current location sharing";
        @SuppressLint("InlinedApi") NotificationChannel chan = new NotificationChannel(NOTIFICATION_CHANNEL_ID, channelName, NotificationManager.IMPORTANCE_NONE);
        chan.setLightColor(Color.BLUE);
        chan.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        assert manager != null;
        manager.createNotificationChannel(chan);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID);
        Notification notification = notificationBuilder.setOngoing(true)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(getString(R.string.app_name)+ "- Current location sharing")
                .setPriority(NotificationManager.IMPORTANCE_MAX)
                .setCategory(Notification.CATEGORY_SERVICE)
                .setOngoing(true)
                .setAutoCancel(false)
                .setContentIntent(PendingIntent.getActivity(this, 0, new Intent(), 0))
                .build();
        notification.flags = Notification.FLAG_ONGOING_EVENT;

        startForeground(2, notification);
    }

    private double bearingBetweenLocations(double... points) {

        double PI = 3.14159;
        double lat1 = points[0] * PI / 180;
        double long1 = points[1] * PI / 180;
        double lat2 = points[2] * PI / 180;
        double long2 = points[3] * PI / 180;
        double dLon = (long2 - long1);
        double y = Math.sin(dLon) * Math.cos(lat2);
        double x = Math.cos(lat1) * Math.sin(lat2) - Math.sin(lat1)
                * Math.cos(lat2) * Math.cos(dLon);

        double brng = Math.atan2(y, x);

        brng = Math.toDegrees(brng);
        brng = (brng + 360) % 360;

        return brng;
    }









    private static final int LOCATION_INTERVAL = 1000 * 10 * 1; // 1 minute
    private static final float LOCATION_DISTANCE = 10; // 10 meters






//    @Override
//    public void onCreate() {
//        Log.d(TAG, "onCreate");
//        context = getBaseContext();
//        initializeLocationManager();
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            Log.i(TAG, "location provider requires ACCESS_FINE_LOCATION | ACCESS_COARSE_LOCATION");
//            return;
//        }
//
//
//        try {
//            locationManager.requestLocationUpdates(
//                    LocationManager.NETWORK_PROVIDER, LOCATION_INTERVAL, LOCATION_DISTANCE,
//                    mLocationListeners[1]);
//        } catch (SecurityException ex) {
//            Log.i(TAG, "fail to request location update, ignore", ex);
//        } catch (IllegalArgumentException ex) {
//            Log.d(TAG, "network provider does not exist, " + ex.getMessage());
//        }
//
//
//        try {
//            locationManager.requestLocationUpdates(
//                    LocationManager.GPS_PROVIDER, LOCATION_INTERVAL, LOCATION_DISTANCE,
//                    mLocationListeners[0]);
//        } catch (SecurityException ex) {
//            Log.i(TAG, "fail to request location update, ignore", ex);
//        } catch (IllegalArgumentException ex) {
//            Log.d(TAG, "gps provider does not exist " + ex.getMessage());
//        }
//    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy");
        super.onDestroy();


        try {
            fusedLocationClient.removeLocationUpdates(locationCallback);
            status_update =  "Service Task destroyed onDestroy";

        }catch (Exception ex) {
            Log.i(TAG, "fail to remove location listeners, ignore", ex);
        }
    }

}
