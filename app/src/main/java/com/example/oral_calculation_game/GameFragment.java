package com.example.oral_calculation_game;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.SavedStateViewModelFactory;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.oral_calculation_game.databinding.FragmentGameBinding;

public class GameFragment extends Fragment {

    public GameFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_game, container, false);
        GameViewModel gameViewModel;
        gameViewModel = new ViewModelProvider(requireActivity(),new SavedStateViewModelFactory(requireActivity().getApplication(),this)).get(GameViewModel.class);
        FragmentGameBinding binding;
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_game,container,false);
        binding.setData(gameViewModel);
        binding.setLifecycleOwner(requireActivity());

        /*
         * 在GameFragment中可以归类成两大组按键
         * 一种是监听用户输入的值，显示在‘Your Answer中’
         * 一种则是监听‘SUB’键，来核验用户输入是否正确以决定是否进行下一题或者跳转到结算界面
         * */
        //通过StringBuilder可变字符序列
        final StringBuilder builder = new StringBuilder();
        //用于监听用户输入
        View.OnClickListener ListenerAdd = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.button2:
                        builder.append('1');
                        break;
                    case R.id.button3:
                        builder.append('2');
                        break;
                    case R.id.button4:
                        builder.append('3');
                        break;
                    case R.id.button5:
                        builder.append('4');
                        break;
                    case R.id.button6:
                        builder.append('5');
                        break;
                    case R.id.button7:
                        builder.append('6');
                        break;
                    case R.id.button8:
                        builder.append('7');
                        break;
                    case R.id.button9:
                        builder.append('8');
                        break;
                    case R.id.button10:
                        builder.append('9');
                        break;
                    case R.id.button11:
                        builder.setLength(0);
                        break;
                    case R.id.button12:
                        builder.append('0');
                        break;
                }
                if (builder.length() == 0){
                    binding.textView9.setText(getString(R.string.input));
                }else{
                    binding.textView9.setText(builder.toString());
                }
            }
        };

        //把按键绑定上‘ListenerAdd’的功能
        binding.button2.setOnClickListener(ListenerAdd);
        binding.button3.setOnClickListener(ListenerAdd);
        binding.button4.setOnClickListener(ListenerAdd);
        binding.button5.setOnClickListener(ListenerAdd);
        binding.button6.setOnClickListener(ListenerAdd);
        binding.button7.setOnClickListener(ListenerAdd);
        binding.button8.setOnClickListener(ListenerAdd);
        binding.button9.setOnClickListener(ListenerAdd);
        binding.button10.setOnClickListener(ListenerAdd);
        binding.button11.setOnClickListener(ListenerAdd);
        binding.button12.setOnClickListener(ListenerAdd);

        //设置'SUB'键
        binding.button13.setOnClickListener(v -> {
            //如果输入等于答案，则进行加分
            if(builder.length() == 0){
                builder.append("-1");
            }
            if(Integer.valueOf(builder.toString()).intValue() == gameViewModel.getAnswer().getValue()){
                gameViewModel.CorrectAddScore();
                //将可变字符串清空
                builder.setLength(0);
                binding.textView9.setText(R.string.correct_message);
            }else{
                NavController controller = Navigation.findNavController(v);
                if (gameViewModel.win_flag){
                    controller.navigate(R.id.action_gameFragment_to_winFragment);
                    //重置判定
                    gameViewModel.win_flag = false;
                    gameViewModel.Save();
                }else{
                    controller.navigate(R.id.action_gameFragment_to_loseFragment);
                }
            }
        });
        return binding.getRoot();
    }
}