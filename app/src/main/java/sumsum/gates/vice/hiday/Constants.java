package sumsum.gates.vice.hiday;

import com.google.android.gms.maps.model.LatLng;

import java.util.HashMap;

final class Constants {

    private Constants() {
    }


    public interface ACTION {
        public static String MAIN_ACTION = "com.truiton.foregroundservice.action.main";
        public static String PREV_ACTION = "com.truiton.foregroundservice.action.prev";
        public static String PLAY_ACTION = "com.truiton.foregroundservice.action.play";
        public static String NEXT_ACTION = "com.truiton.foregroundservice.action.next";
        public static String STARTFOREGROUND_ACTION = "com.truiton.foregroundservice.action.startforeground";
        public static String STOPFOREGROUND_ACTION = "com.truiton.foregroundservice.action.stopforeground";
    }
    private static final String PACKAGE_NAME = "com.google.android.gms.location.Geofence";

    static final String GEOFENCES_ADDED_KEY = PACKAGE_NAME + ".GEOFENCES_ADDED_KEY";

    /**
     * Used to set an expiration time for a geofence. After this amount of time Location Services
     * stops tracking the geofence.
     */
    private static final long GEOFENCE_EXPIRATION_IN_HOURS = 12;

    /**
     * For this sample, geofences expire after twelve hours.
     */
    static final long GEOFENCE_EXPIRATION_IN_MILLISECONDS =
            GEOFENCE_EXPIRATION_IN_HOURS * 60 * 60 * 1000;
    static final float GEOFENCE_RADIUS_IN_METERS = 1000;


    static final HashMap<String, LatLng> BAY_AREA_LANDMARKS = new HashMap<>();

    static {

//        BAY_AREA_LANDMARKS.put("NofimGate", new LatLng(32.149512,35.1092797));
//        BAY_AREA_LANDMARKS.put("HackerU", new LatLng(32.084403, 34.800743));
//        BAY_AREA_LANDMARKS.put("home", new LatLng(32.2956634,34.8759301));
    }
}