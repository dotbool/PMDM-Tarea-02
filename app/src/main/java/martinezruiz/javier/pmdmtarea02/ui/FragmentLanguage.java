package martinezruiz.javier.pmdmtarea02.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import martinezruiz.javier.pmdmtarea02.R;
import martinezruiz.javier.pmdmtarea02.databinding.FragmentLanguageBinding;

public class FragmentLanguage extends Fragment {

    private FragmentLanguageBinding binding;

    public FragmentLanguage() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_language, container, false);
    }
}
