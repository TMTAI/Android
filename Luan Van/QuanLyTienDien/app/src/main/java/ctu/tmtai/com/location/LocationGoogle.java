package ctu.tmtai.com.location;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import ctu.tmtai.com.notify.Notify;

/**
 * Created by tranm on 14-Jul-17.
 */

public class LocationGoogle extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, LocationListener {

    // Đối tượng tương tác với Google API
    private GoogleApiClient gac;

    private static Location location;

    private static LocationGoogle instance;

    private LocationGoogle(){
        // Trước tiên chúng ta cần phải kiểm tra play services
        if (checkPlayServices()) {
            // Building the GoogleApi client
            buildGoogleApiClient();
        }
    }

    public LocationGoogle getInstance(){
        if(instance == null){
            instance = new LocationGoogle();
        }
        return instance;
    }

    public static Location getMyLocation(){
        return location;
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        // Đã kết nối với google api, lấy vị trí
        location = getLocation();
    }

    @Override
    public void onConnectionSuspended(int i) {
        gac.connect();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(this, "Lỗi kết nối: " + connectionResult.getErrorMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLocationChanged(Location location) {
        this.location = location;
    }



    /**
     * Phương thức này dùng để hiển thị trên UI
     */
    private Location getLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // Kiểm tra quyền hạn
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 2);
        } else {
            location = LocationServices.FusedLocationApi.getLastLocation(gac);

            if (location != null) {
                return location;
            } else {
                Notify.showToast(this, "Không thể hiển thị vị trí. " +
                        "Bạn đã kích hoạt location trên thiết bị chưa?", Notify.SHORT);
                return null;
            }
        }
        return location;
    }

    /**
     * Tạo đối tượng google api client
     */
    protected synchronized void buildGoogleApiClient() {
        if (gac == null) {
            gac = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API).build();
        }
    }

    /**
     * Phương thức kiểm chứng google play services trên thiết bị
     */
    private boolean checkPlayServices() {
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                GooglePlayServicesUtil.getErrorDialog(resultCode, this, 1000).show();
            } else {
                Toast.makeText(this, "Thiết bị này không hỗ trợ.", Toast.LENGTH_LONG).show();
                finish();
            }
            return false;
        }
        return true;
    }
}
