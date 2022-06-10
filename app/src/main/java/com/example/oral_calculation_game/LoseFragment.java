package com.example.oral_calculation_game;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.SavedStateViewModelFactory;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.oral_calculation_game.databinding.FragmentLoseBinding;

public class LoseFragment extends Fragment {

    public LoseFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_lose, container, false);
        GameViewModel gameViewModel;
        gameViewModel = new ViewModelProvider(requireActivity(), new SavedStateViewModelFactory(requireActivity().getApplication(), this)).get(GameViewModel.class);
        FragmentLoseBinding binding;
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_lose, container, false);
        binding.setData(gameViewModel);
        binding.setLifecycleOwner(requireActivity());

        binding.button14.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_loseFragment_to_homeFragment);
        });

        return binding.getRoot();
    }
}