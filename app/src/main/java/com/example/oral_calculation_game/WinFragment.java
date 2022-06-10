package com.example.oral_calculation_game;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.SavedStateViewModelFactory;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.oral_calculation_game.databinding.FragmentWinBinding;

public class WinFragment extends Fragment {

    public WinFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_win, container, false);
        GameViewModel gameViewModel;
        gameViewModel = new ViewModelProvider(requireActivity(), new SavedStateViewModelFactory(requireActivity().getApplication(), this)).get(GameViewModel.class);
        FragmentWinBinding binding;
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_win, container, false);
        binding.setData(gameViewModel);
        binding.setLifecycleOwner(requireActivity());

        binding.button15.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_winFragment_to_homeFragment);
        });

        return binding.getRoot();
    }
}