package com.example.app3;

import android.os.Bundle;

import androidx.fragment.app.Fragment; // вместо import android.support.v4.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Switch;
import android.widget.Toast;

public class BlankFragment2 extends Fragment implements View.OnClickListener{

    LinearLayout linearLayoutF2_1;
    TextView textViewF2_1,
            textViewF2_2,
            textViewF2_3,
            textViewF2_4,
            textViewF2_5,
            textViewF2_6;
    public BlankFragment2() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView2 = inflater.inflate(R.layout.fragment_blank_2, container, false);
        LinearLayout linearLayoutF2_1 = (LinearLayout) rootView2.findViewById(R.id.linearLayoutF2_1);
        TextView textViewF2_1 = (TextView) rootView2.findViewById(R.id.textViewF2_1);
        TextView textViewF2_2 = (TextView) rootView2.findViewById(R.id.textViewF2_2);
        TextView textViewF2_3 = (TextView) rootView2.findViewById(R.id.textViewF2_3);
        TextView textViewF2_4 = (TextView) rootView2.findViewById(R.id.textViewF2_4);
        TextView textViewF2_5 = (TextView) rootView2.findViewById(R.id.textViewF2_5);
        TextView textViewF2_6 = (TextView) rootView2.findViewById(R.id.textViewF2_6);
        textViewF2_1.setOnClickListener(this);
        textViewF2_2.setOnClickListener(this);
        textViewF2_3.setOnClickListener(this);
        textViewF2_4.setOnClickListener(this);
        textViewF2_5.setOnClickListener(this);
        textViewF2_6.setOnClickListener(this);
        return rootView2;
    }
    @Override
    public void onStart(){
        super.onStart();
        linearLayoutF2_1 = (LinearLayout) getActivity().findViewById(R.id.linearLayoutF2_1);
        textViewF2_1 = (TextView) getActivity().findViewById(R.id.textViewF2_1);
        textViewF2_2 = (TextView) getActivity().findViewById(R.id.textViewF2_2);
        textViewF2_3 = (TextView) getActivity().findViewById(R.id.textViewF2_3);
        textViewF2_4 = (TextView) getActivity().findViewById(R.id.textViewF2_4);
        textViewF2_5 = (TextView) getActivity().findViewById(R.id.textViewF2_5);
        textViewF2_6 = (TextView) getActivity().findViewById(R.id.textViewF2_6);
    }
    @Override
    public void onClick(View view){
        switch(view.getId()){
            case R.id.textViewF2_1:
                Toast toast1 = Toast.makeText(getContext(), "textViewF2_1 Новый", Toast.LENGTH_SHORT);
                toast1.show();
                break;
            case R.id.textViewF2_2:
                Toast toast2 = Toast.makeText(getContext(), "textViewF2_2 Открыть", Toast.LENGTH_SHORT);
                toast2.show();
                break;
            case R.id.textViewF2_3:
                Toast toast3 = Toast.makeText(getContext(), "textViewF2_3 Открыть", Toast.LENGTH_SHORT);
                toast3.show();
                break;
            case R.id.textViewF2_4:
                Toast toast4 = Toast.makeText(getContext(), "textViewF2_4 Открыть", Toast.LENGTH_SHORT);
                toast4.show();
                break;
            case R.id.textViewF2_5:
                Toast toast5 = Toast.makeText(getContext(), "textViewF2_5 Открыть", Toast.LENGTH_SHORT);
                toast5.show();
                break;
            case R.id.textViewF2_6:
                Toast toast6 = Toast.makeText(getContext(), "textViewF2_6 Открыть", Toast.LENGTH_SHORT);
                toast6.show();
                break;
        }
    }
}