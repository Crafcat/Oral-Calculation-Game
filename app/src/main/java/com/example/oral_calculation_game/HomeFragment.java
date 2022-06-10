package com.example.oral_calculation_game;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.SavedStateViewModelFactory;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.oral_calculation_game.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final GameViewModel gameViewModel;
        gameViewModel = new ViewModelProvider(requireActivity(),new SavedStateViewModelFactory(requireActivity().getApplication(),this)).get(GameViewModel.class);
        FragmentHomeBinding binding;
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_home,container,false);
        binding.setData(gameViewModel);
        binding.setLifecycleOwner(requireActivity());

        binding.button.setOnClickListener(v -> {
            NavController controller = Navigation.findNavController(v);
            controller.navigate(R.id.action_homeFragment_to_gameFragment);
            gameViewModel.getCurrentScore().setValue(0);
            gameViewModel.Generator();
        });
        return binding.getRoot();
    }
}