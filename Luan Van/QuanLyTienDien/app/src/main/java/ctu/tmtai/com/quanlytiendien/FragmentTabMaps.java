package ctu.tmtai.com.quanlytiendien;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import ctu.tmtai.com.api.MyProgressDialog;
import ctu.tmtai.com.models.KhachHang;
import ctu.tmtai.com.models.KhuVuc;
import ctu.tmtai.com.notify.Notify;

import static ctu.tmtai.com.util.Constant.BUNDLE_KHU_VUC;
import static ctu.tmtai.com.util.Constant.HTTP_ALL_KHACH_HANG_THEO_KHU_VUC;
import static ctu.tmtai.com.util.Constant.HTTP_GET_LOCATION;
import static ctu.tmtai.com.util.Constant.KHU_VUC;
import static ctu.tmtai.com.util.Constant.MA_KHU_VUC;


/**
 * Created by tranm on 05-Aug-17.
 */

public class FragmentTabMaps extends Fragment implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, LocationListener, GoogleMap.OnMyLocationButtonClickListener {
    private List<KhachHang> khachHangList;
    private KhuVuc kv;
    private GoogleMap mMap;
    private Location location;
    private GoogleApiClient gac;
    private List<JSONObject> listObject;

    public FragmentTabMaps() {

    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab_maps, container, false);
        listObject = new ArrayList<>();

        khachHangList = new ArrayList<>();
        Intent intent = getActivity().getIntent();
        Bundle bundle = intent.getBundleExtra(BUNDLE_KHU_VUC);
        if (bundle != null) {
            kv = (KhuVuc) bundle.getSerializable(KHU_VUC);
        }

        new ConnectionServer().execute();

        SupportMapFragment mapFragment = (SupportMapFragment) this.getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        buildGoogleApiClient();

        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (ContextCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        }
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.setOnMyLocationButtonClickListener(this);
    }

    @Override
    public boolean onMyLocationButtonClick() {
        try {
            if (listObject != null) {
                for (int i = 0; i < listObject.size(); i++) {

                    JSONArray array = listObject.get(i).getJSONArray("results");

                    JSONObject json = array.getJSONObject(0);
                    JSONObject geometry = json.getJSONObject("geometry");
                    JSONObject location = geometry.getJSONObject("location");
                    double lat = location.getDouble("lat");
                    double lng = location.getDouble("lng");

                    LatLng latLng = new LatLng(lat, lng);

                    MarkerOptions marker = new MarkerOptions()
                            .position(latLng)
                            .title(khachHangList.get(i).getTenkh());

                    mMap.addMarker(marker);
                }

                showMyLocation();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }


    /**
     * Tạo đối tượng google api client
     */
    protected synchronized void buildGoogleApiClient() {
        if (gac == null) {
            gac = new GoogleApiClient.Builder(getContext())
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API).build();
        }
        if (ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
    }

    private void showMyLocation() {
        if (ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        location = LocationServices.FusedLocationApi.getLastLocation(gac);

        if (location != null) {

            Logger.getLogger("Vị trí hiện tại của bạn là : " + location.getLatitude());

            LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 13));

            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(latLng)      // Sets the center of the map to location user
                    .zoom(15)                   // Sets the zoom
                    .bearing(90)                // Sets the orientation of the camera to east
                    .tilt(40)                   // Sets the tilt of the camera to 30 degrees
                    .build();                   // Creates a CameraPosition from the builder
            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        } else {
            Notify.showToast(getContext(), getContext().getText(R.string.not_show_location).toString(), Notify.SHORT);
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        gac.connect();
    }

    @Override
    public void onConnectionSuspended(int i) {
        gac.connect();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStart() {
        gac.connect();
        super.onStart();
    }

    @Override
    public void onStop() {
        gac.disconnect();
        super.onStop();
    }

    public class ConnectionServer extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... params) {
            try {

                String str = Jsoup.connect(HTTP_ALL_KHACH_HANG_THEO_KHU_VUC).data(MA_KHU_VUC, kv.getMakv()).get().body().text();
                JSONArray arrayKhachHang = new JSONArray(str);
                String key = "AIzaSyDoHL8_Cq50yCBJxniWPsLt8c2bxxKJ1ew";
                for (int i = 0; i < arrayKhachHang.length(); i++) {
                    KhachHang khachHang = new KhachHang(arrayKhachHang.getJSONObject(i));
                    khachHangList.add(khachHang);

                    String address = Jsoup.connect(String.format(HTTP_GET_LOCATION, khachHang.getDiachi(), key))
                            .ignoreContentType(true)
                            .get().body().text();

                    JSONObject object = new JSONObject(address);

                    listObject.add(object);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }
    }


}
