package com.example.user;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Main extends Fragment {

    MainActivity mainActivity;

    // 메인 액티비티 위에 올린다.
   @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mainActivity = (MainActivity) getActivity();
    }

    // 메인 액티비티에서 내려 온다.
    @Override
    public void onDetach() {
        super.onDetach();
        mainActivity = null;
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_main, container, false);

        Button button = rootView.findViewById(R.id.toList);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //mainActivity.fragmentChange(2); 잠시 RecipeView 기능 확인 위해 코멘트 아웃 처리.
                mainActivity.fragmentChange(7);
            }

        });

        Button button2 = rootView.findViewById(R.id.toUd);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.fragmentChange(3);
            }

        });

        Button button3 = rootView.findViewById(R.id.toErl);

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.fragmentChange(5);
            }

        });

        Button button4 = rootView.findViewById(R.id.toMyPg);

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.fragmentChange(4);
            }

        });

        Button button5 = rootView.findViewById(R.id.vsrc);

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.fragmentChange(6);
            }

        });

//        Button button5 = rootView.findViewById(R.id.vsrc);
//
//        button5.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mainActivity.fragmentChange(7);
//            }
//
//        });


        return rootView;
    }
}