package martinezruiz.javier.pmdmtarea02;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toolbar;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.graphics.Insets;
import androidx.core.os.LocaleListCompat;
import androidx.core.splashscreen.SplashScreen;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import martinezruiz.javier.pmdmtarea02.databinding.ActivityMainBinding;
import martinezruiz.javier.pmdmtarea02.ui.FragmentHome;
import martinezruiz.javier.pmdmtarea02.ui.FragmentLanguage;
import martinezruiz.javier.pmdmtarea02.ui.FragmentSettings;

public class MainActivity extends AppCompatActivity implements  NavigationView.OnNavigationItemSelectedListener{

    private ActivityMainBinding binding;
    private static final int SPLASH_DURATION = 3000;
    private boolean keep = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SplashScreen splash = SplashScreen.installSplashScreen(this);

        super.onCreate(savedInstanceState);


        //        EdgeToEdge.enable(this);
//        https://developer.android.com/reference/kotlin/androidx/core/splashscreen/SplashScreen
//        https://stackoverflow.com/questions/70857274/android-12-splash-screen-api-increasing-splashscreen-duration
        splash.setKeepOnScreenCondition(()-> keep);
        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(() -> keep = false, SPLASH_DURATION);

        //Bindeamos la vista xml. Para ello, primero incluimos en el archivo
//        build.gradle la clave buildfeatures y dentro viewBinding con valor true.
//        Esto genera una clase por cada vista identificada con un id cuyo nombre
//        sigue la nomenclatura camelcase más Binding como sufijo
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //Este método setea una toolbar para funcionar como una Appbar
        MaterialToolbar toolbar = binding.barAppMain.barApp;
        setSupportActionBar(binding.barAppMain.barApp);

        DrawerLayout drawerLayout = binding.drawerLayout;
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        //Añadimos un listener al menu lateral

        NavigationView navView = binding.navView;
        navView.setNavigationItemSelectedListener(this);

        Snackbar welcome = Snackbar.make(this.findViewById(R.id.rv_supermario), R.string.welcome, 3000);
        welcome.show();





    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        boolean fragmentTransaction = false;
        Fragment fragment = null;

        if(item.getItemId() == R.id.drawer_home){
            fragment = new FragmentHome();
            fragmentTransaction = true;

        }
        else if (item.getItemId() == R.id.drawer_settings) {

            fragment = new FragmentSettings();
            fragmentTransaction = true;
        }
        else if(item.getItemId() == R.id.drawer_language){
            fragment = new FragmentLanguage();
            fragmentTransaction = true;
        }

        if(fragmentTransaction){
            getSupportFragmentManager()
                    .beginTransaction()
                    .addToBackStack(null)
                    .setReorderingAllowed(true)
                    .replace(R.id.contenedor, fragment)
                    .commit();
        }
        DrawerLayout drawer = binding.drawerLayout;
        drawer.closeDrawers();
        return true;
    }


    /**
     *
     * @param menu The options menu in which you place your items.
     *
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_ppal, menu);
        return true;
    }



    /**
     *
     * @param item The menu item that was selected.
     *
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == R.id.menu_acerca_de){
            AlertDialog.Builder alerta = new AlertDialog.Builder(this);
            alerta.setIcon(R.drawable.boo);
            alerta.setMessage(R.string.aplicacion_desarrollada_por);
            alerta.setTitle(R.string.acerca_de);


            AlertDialog acercaDe = alerta.create();
            acercaDe.show();
        }

        return super.onOptionsItemSelected(item);
    }



}