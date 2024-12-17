package martinezruiz.javier.pmdmtarea02.ui;

import android.app.LocaleManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.os.LocaleList;
import android.util.DisplayMetrics;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.os.LocaleListCompat;
import androidx.fragment.app.Fragment;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

import martinezruiz.javier.pmdmtarea02.R;
import martinezruiz.javier.pmdmtarea02.databinding.FragmentLanguageBinding;

public class FragmentLanguage extends Fragment {

    private FragmentLanguageBinding binding;

    public FragmentLanguage() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        FragmentLanguageBinding binding = FragmentLanguageBinding.inflate(inflater);

        SharedPreferences sp = getActivity().getSharedPreferences(
                getString(R.string.shared_preferences), Context.MODE_PRIVATE);

        String languageSystem = Objects.requireNonNull(AppCompatDelegate.getApplicationLocales().get(0)).toString();
        String languageActual  = sp.getString("language", languageSystem);

        if(languageActual.equals("es")){
            binding.rbSpanish.setChecked(true);
        }
        else if(languageActual.equals("en")){
            binding.rbEnglish.setChecked(true);
        }


        binding.rbSpanish.setOnCheckedChangeListener((buttonView, isChecked) ->
        {
            String language = isChecked ? "es" : "en";
            LocaleListCompat localeList = LocaleListCompat.forLanguageTags(language);
            AppCompatDelegate.setApplicationLocales(localeList);
            editSharedPreferences(new Pair<>("language", language));
        });

        binding.rbEnglish.setOnCheckedChangeListener((buttonView, isChecked) -> {


        });

        return binding.getRoot();

    }


    /**

     */

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


}
