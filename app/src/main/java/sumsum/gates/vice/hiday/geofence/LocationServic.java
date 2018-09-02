package sumsum.gates.vice.hiday.geofence;

import android.Manifest;
import android.app.IntentService;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingClient;
import com.google.android.gms.location.GeofencingEvent;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.concurrent.Executor;

import sumsum.gates.vice.hiday.MainActivity;
import sumsum.gates.vice.hiday.R;



public class LocationServic extends IntentService {

    FusedLocationProviderClient client;
    private Context context;
    Location l;
    private static final String LOG_TAG = "ForegroundService";
    Executor e = new Executor() {
        @Override
        public void execute(@NonNull Runnable runnable) {

        }
    };

    public LocationServic() {
        super("LocationServic");
    }

    LocationCallback callback = new LocationCallback() {
        @Override
        public void onLocationResult(LocationResult locationResult) {
            l = locationResult.getLastLocation();

        }
    };

    @Override
    protected void onHandleIntent(Intent intent) {

    }

    public static boolean IS_SERVICE_RUNNING = false;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        client = new FusedLocationProviderClient(getApplicationContext());
        context = getBaseContext();
        if (intent != null) {
            LocationRequest request = new LocationRequest();
            request.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);//GPS
            request.setInterval(1000);
            request.setFastestInterval(500);
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return START_STICKY;
            }
            client.requestLocationUpdates(request, callback /*callback*/, null/*Looper*/);
            Task<Location> lastLocation = client.getLastLocation();
            lastLocation.addOnCompleteListener(e, new OnCompleteListener<Location>() {
                @Override
                public void onComplete(@NonNull Task<Location> task) {
                    if (task.isSuccessful()) {
                        Location location = task.getResult();
                        Toast.makeText(getApplicationContext(), "speed = " + location.getSpeed(), Toast.LENGTH_SHORT).show();
                        if (location != null) {
                            Log.d("hiday", location.toString());
                        }
                    }
                }
            });
        }

        if (intent.getAction().equals("udate")) {
            showNotification();
            Toast.makeText(this, "Service Started!", Toast.LENGTH_SHORT).show();

        } else if (intent.getAction().equals("stop")) {
            stopForeground(true);
            Toast.makeText(this, "Service stoped!", Toast.LENGTH_SHORT)
                    .show();
        }
        return START_STICKY;
    }

    private void showNotification() {
        Intent notificationIntent = new Intent(this, MainActivity.class);
        notificationIntent.setAction("A");
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                notificationIntent, 0);

        Intent stop = new Intent(this, LocationServic.class);
        stop.setAction("stop");
        PendingIntent quit = PendingIntent.getService(this, 0,
                stop, 0);

        Bitmap icon = BitmapFactory.decodeResource(getResources(),
                R.drawable.sumsumicon);

        Notification notification = new NotificationCompat.Builder(this)
                .setContentTitle("Need to open the Gate?")
                .setTicker("TutorialsFace Music Player")
                .setContentText("Click for SumSum")
                .setSmallIcon(R.drawable.sumsumicon)
                .setLargeIcon(icon)
                .setContentIntent(pendingIntent)
                .setOngoing(true)
                .addAction(android.R.drawable.ic_lock_power_off, "Quit",
                        quit)
                .build();
        startForeground(101, notification);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(LOG_TAG, "In onDestroy");
        Toast.makeText(this, "Service Detroyed!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // Used only in case if services are bound (Bound Services).
        return null;
    }

}

