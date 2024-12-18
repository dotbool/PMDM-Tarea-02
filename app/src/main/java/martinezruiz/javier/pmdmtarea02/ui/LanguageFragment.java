package martinezruiz.javier.pmdmtarea02.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import java.util.Objects;

import martinezruiz.javier.pmdmtarea02.R;
import martinezruiz.javier.pmdmtarea02.databinding.FragmentLanguageBinding;
import martinezruiz.javier.pmdmtarea02.models.PropertyListener;

/**
 * Fragment que  maneja el idioma en el que se muestra la app
 */
public class LanguageFragment extends Fragment {


    public LanguageFragment() {
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        listener = (PropertyListener) context;

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        super.onCreateView(inflater,container,savedInstanceState);
        //obtenemos referencia a la clase que bindea datos y vista


        FragmentLanguageBinding binding = FragmentLanguageBinding.inflate(inflater);

        //creamos o rescatamos el archivo de las preferencias
        SharedPreferences sp = getActivity().getSharedPreferences(
                getString(R.string.shared_preferences), Context.MODE_PRIVATE);

        //Al recuperar el valor de una de las claves que haya en el archivo de preferncias, hemos de
        //hacerlo con un valor por defecto. Se escoge el valor del idioma que tenga la app en este
        //momento ya que puede ocurrir que no exista aún un valor para el idioma en el archivo de
        //preferencias. De esta manera seleccionamos como botón chekeado el del idioma que
//        esté mostrando la app, aunque toavía no esté guardado en el archivo de preferencias
        String languageSystem = Objects.requireNonNull(AppCompatDelegate.getApplicationLocales().get(0)).toString();
        String languageActual  = sp.getString("language", languageSystem);

        if(languageActual.equals("es")){
            binding.rbSpanish.setChecked(true);
        }
        else if(languageActual.equals("en")){
            binding.rbEnglish.setChecked(true);
        }


//        https://developer.android.com/guide/topics/resources/app-languages?hl=es-419#java
        //cuando se pulsa uno de los radiobuttons, se rescata en la app el lenguaje seleccionado,
//        se establece y se guarda en el archivo de preferencias


        binding.rbSpanish.setOnCheckedChangeListener((buttonView, isChecked) ->
        {
            String language = isChecked ? "es" : "en";
//            LocaleListCompat localeList = LocaleListCompat.forLanguageTags(language);
//            AppCompatDelegate.setApplicationLocales(localeList);
//            editSharedPreferences(new Pair<>("language", language));
//            getParentFragmentManager().popBackStack();
            setLanguage(language);

        });

        //No se ha implementado porque siempre, el rbSpanish tiene un estado. Si clikamos
//        el rbENglis, cambia el estado del rbSpanih y se dispara el manejador del evento
//        binding.rbEnglish.setOnCheckedChangeListener((buttonView, isChecked) -> {
//            String language = isChecked ? "es" : "en";
//            LocaleListCompat localeList = LocaleListCompat.forLanguageTags(language);
//            AppCompatDelegate.setApplicationLocales(localeList);
//            editSharedPreferences(new Pair<>("language", language));
//
//        });

        return binding.getRoot();

    }



    /**
     *  getSharePreferences obtiene el archivo cuyo nombre recibe por parámetro. Si no existe lo crea
     *  cuando se usa el método edit() sobre el objeto.
     *  apply() cambia el objeto SharedPreferences en la memoria de inmediato, pero escribe las
     *  actualizaciones en el disco de forma asíncrona. Como alternativa, puedes usar commit() para
     *  escribir los datos en el disco de forma síncrona. Sin embargo, debido a que commit() es
     *  síncrono, debes evitar llamarlo desde tu subproceso principal, ya que podría pausar el
     *  procesamiento de la IU.
     * @param preferencia es un par clave valor
     */
    private void editSharedPreferences(Pair<String, String> preferencia ){

        SharedPreferences sp = getActivity().getSharedPreferences(
                getString(R.string.shared_preferences), Context.MODE_PRIVATE);


//        The problem is each time you call edit() a new Editor object is created.You should hold
//        instance of one Editor object and perform all operations on it.
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(preferencia.first, preferencia.second);
        editor.apply();

    }

    private PropertyListener listener;

    private String language;

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
        listener.onSwicthLanguage(language);
    }
}
