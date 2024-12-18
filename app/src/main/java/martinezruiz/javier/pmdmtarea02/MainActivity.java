package martinezruiz.javier.pmdmtarea02;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.os.LocaleListCompat;
import androidx.core.splashscreen.SplashScreen;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.util.Locale;

import martinezruiz.javier.pmdmtarea02.databinding.ActivityMainBinding;
import martinezruiz.javier.pmdmtarea02.models.PropertyListener;
import martinezruiz.javier.pmdmtarea02.ui.HomeFragment;
import martinezruiz.javier.pmdmtarea02.ui.LanguageFragment;
import martinezruiz.javier.pmdmtarea02.ui.SettingsFragment;

/**
 *
 */
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, PropertyListener{

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

        //seteamos la toolbar para que funciones como ActionBar para esta actividad
        setSupportActionBar(toolbar);

        //El DrawerLayout es un layout top para una ventana y su función es la de lograr que las
        //vistas que aloja sean interactivas y puedan ser sacadas de la pantalla. La posición de los
        //hijos se controla con layout:gravity. El primer hijo, que es el que debe mostrar el contenido
//        principal de la app no tiene gravity y tanto el width com el heigth son mathparent. Los demás
        //hijos se posicionan con el layout_gravity
        DrawerLayout drawerLayout = binding.drawerLayout;

        //Este objeto lo que hace es vincular la actionBar con el drawer
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        //Añadimos un listener al menu lateral

        NavigationView navView = binding.navView;
        navView.setNavigationItemSelectedListener(this);

        //Mostramos un welcome cuando se inicia o reinicia la app
        Snackbar welcome = Snackbar.make(this.findViewById(R.id.contenedor), R.string.welcome, 3000);
        welcome.show();

        setApplicationLocale();


    }


    /**
     * Método que es ejecutado cuando uno de los iconos del  menú lateral es pulsado
     * @param item Es el icono seleccionado. Una vez se ha pulsdado un icono se sustituye
     *             el fragmento que esté mostrándose por el seleccionado
     * @return
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        boolean fragmentTransaction = false;
        Fragment fragment = null;

        if(item.getItemId() == R.id.drawer_home){
            fragment = new HomeFragment();
            fragmentTransaction = true;

        }
        else if (item.getItemId() == R.id.drawer_settings) {

            fragment = new SettingsFragment();
            fragmentTransaction = true;
        }
        else if(item.getItemId() == R.id.drawer_language){
            fragment = new LanguageFragment();
            fragmentTransaction = true;
        }

        if(fragmentTransaction){
            getSupportFragmentManager()
                    .beginTransaction()
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
     * Crea el botón del menú de ocpiones
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
     * Cuando se selecciona el item Acerca de: del menú de opciones
     *             despliega una alerta con la información
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


    private void setApplicationLocale(){

        if(AppCompatDelegate.getApplicationLocales().get(0) == null){
            String languageSystem = Locale.getDefault().toString();
            LocaleListCompat localeList = LocaleListCompat.forLanguageTags(languageSystem);

            AppCompatDelegate.setApplicationLocales(localeList);
        }

    }

    @Override
    public void onSwicthLanguage(String language) {

        SharedPreferences sp = getSharedPreferences(
                getString(R.string.shared_preferences), Context.MODE_PRIVATE);



//        The problem is each time you call edit() a new Editor object is created.You should hold
//        instance of one Editor object and perform all operations on it.
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("language", language);
        editor.apply();

        LocaleListCompat localeList = LocaleListCompat.forLanguageTags(language);
        AppCompatDelegate.setApplicationLocales(localeList);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();

    }
}