package martinezruiz.javier.pmdmtarea02;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.os.LocaleListCompat;
import androidx.core.splashscreen.SplashScreen;

public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_DURATION = 3000;


//    /**
//     * https://developer.android.com/reference/androidx/appcompat/app/AppCompatDelegate#setApplicationLocales(androidx.core.os.LocaleListCompat)
//     * Cuando se realiza un cambio de idioma el sistema entra en bucle porque se produce
//     * un bloqueo de lectura en el subproceso principal. En este caso la API AppCompactDelegate
//     * debe ser llamada antes de Activity.OnCreate
//     * @param newBase The new base context for this wrapper.
//     */
//    @Override
//    protected void attachBaseContext(Context newBase) {
//        super.attachBaseContext(newBase);
//    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        SplashScreen splash = SplashScreen.installSplashScreen(this);
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.S) {

            setContentView(R.layout.splash);

            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }, SPLASH_DURATION);


        } else {

            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
            finish();


        }
    }

    private void goFromTo(AppCompatActivity from, Class<?> to){
        Intent intent = new Intent(from, to);
        startActivity(intent);
        finish();
    }


}
