package cl.app.apk_certamen_2.data;
import

import static androidx.core.location.LocationManagerCompat.requestLocationUpdates;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import cl.app.apk_certamen_2.R;

public class MainActivity<locationResult, activity, locationCallback> extends AppCompatActivity {

    private static final int REQUEST_CODE = 1;
    EditText lat, lon, dir;
    Button obtener, salida;
    ProgressBar progressBar;
    FusedLocationProviderClient fusedLocationProviderClient;
    private Context context;
    private Object text;


    @Override
        protected void onCreate(Bundle savedInstanceState) {


            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            lat = findViewById(R.id.edtlatitud);
            lon = findViewById(R.id.edtlongitud);


            obtener = findViewById(R.id.btnobtenerCoordenda);
            salida = findViewById(R.id.btnSalir);


            progressBar findViewById (R.id.progressBar);

        }

    public void ObtenerCoordendasActual (View view) {
        Context ContextCompat;
        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
            {
                Activity activity;
                ActivityCompat.requestPermissions(activity: MainActivity.this, new String[]{Manifest. permission.ACCESS_FINE_LOCATION},REQUEST_CODE);
            } else {
                getCoordenada();
            }
        }

}
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
    super.onRequestPermissionsResult(requestCode , permissions, grantResults);
    if (requestCode == REQUEST CODE && grantResults.length > 0) { if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
        getCoordenada();
        }
    } else {
        Toast.makeText( context: this, text: "Permiso Denegado ..", Toast.LENGTH_SHORT).show();
    }
}
    private void getCoordenada() {
        try { progressBar.setVisibility(View. VISIBLE);
            LocationRequest locationRequest = new LocationRequest ();
            locationRequest.setInterval(10000);
            locationRequest.setFastestInterval (3000);
            locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
            if (ActivityCompat.checkSelfPermission( context: this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager. PERMISSION_GRANTED) {
                return;
            }
            LocationServices.getFusedLocationProviderClient(activity: this).requestLocationUpdates(locationRequest,
                    new LocationCallback() {
 @Override
         LocationServices.getFusedLocationProviderClient( activity: MainActivity.this).removelocationUpdates( locationCallback: this);
                if (locationResult != null && locationResult.getLocations().size() > 0) {
                            int latestlocationIndex = locationResult.getLocations().size() - 1;
                            double latitud = locationResult.getLocations().get(latestLocationIndex).getLatitude();
                            double longitude = locationResult.getLocations ().get (latestLocationIndex).getLongitude();
                            lat.setText (String.valueOf (latitud));
                            lon.setText (String.valueOf (longitude));
                        }
            progressBar.setVisibility(View.GONE);

                    },
                    Looper.myLooper());
    }catch (Exception ex){ System.out.println("Error es :" + ex);
        }
        } public void Exit(View view){
    this.finish();
        }
}

