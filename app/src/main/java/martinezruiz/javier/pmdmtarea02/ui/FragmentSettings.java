package martinezruiz.javier.pmdmtarea02.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Objects;

import martinezruiz.javier.pmdmtarea02.R;
import martinezruiz.javier.pmdmtarea02.databinding.FragmentDetailsBinding;
import martinezruiz.javier.pmdmtarea02.databinding.FragmentSettingsBinding;


public class FragmentSettings extends Fragment {


    public FragmentSettings() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        FragmentSettingsBinding binding = FragmentSettingsBinding.inflate(inflater);
        SharedPreferences sp = getActivity().getSharedPreferences(
                getString(R.string.shared_preferences), Context.MODE_PRIVATE);


        //La primera vez que se lanza la app no existe el archivo por lo que hay que establecer
        //su valor con el valor que tenga el sistema
        String languageSystem = Objects.requireNonNull(AppCompatDelegate.getApplicationLocales().get(0)).toString();
        String settingLanguage = sp.getString("language", languageSystem);
        settingLanguage = settingLanguage.equals("es") ? getString(R.string.language_selected_spanish): getString(R.string.language_selected_english);
        binding.languageSettingValue.setText(settingLanguage);

        return binding.getRoot();
    }
}