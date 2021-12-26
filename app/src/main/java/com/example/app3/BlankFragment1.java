package com.example.app3;

import android.os.Bundle;
import android.os.Environment;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import android.widget.Toast;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import static com.example.app3.MainActivity.*;

/**
 * A simple {@link Fragment} subclass.

 */
public class BlankFragment1 extends Fragment implements View.OnClickListener {
    TextView textViewF1,
             textViewF2,
             textViewF3;
    EditText editTextF1,
             editTextF2,
             editTextF3;
    Button   buttonF1,
             buttonF2,
             buttonF3;

    public BlankFragment1() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_blank1, container, false);
        View rootView = inflater.inflate(R.layout.fragment_blank1, container, false);
        TextView textViewF1 = (TextView) rootView.findViewById(R.id.textViewF1);
        TextView textViewF2 = (TextView) rootView.findViewById(R.id.textViewF2);
        TextView textViewF3 = (TextView) rootView.findViewById(R.id.textViewF3);
        EditText editTextF1 = (EditText) rootView.findViewById(R.id.editTextF1);
        EditText editTextF2 = (EditText) rootView.findViewById(R.id.editTextF2);
        EditText editTextF3 = (EditText) rootView.findViewById(R.id.editTextF3);
        Button buttonF1 =(Button) rootView.findViewById(R.id.buttonF1);
        Button buttonF2 =(Button) rootView.findViewById(R.id.buttonF2);
        Button buttonF3 =(Button) rootView.findViewById(R.id.buttonF3);
        buttonF1.setOnClickListener(this);
        buttonF2.setOnClickListener(this);
        buttonF3.setOnClickListener(this);
        return rootView;
    }
    @Override
    public void onStart(){
        super.onStart();
        textViewF1 = (TextView) getActivity().findViewById(R.id.textViewF1);
        textViewF2 = (TextView) getActivity().findViewById(R.id.textViewF2);
        textViewF3 = (TextView) getActivity().findViewById(R.id.textViewF3);
        editTextF1 = (EditText) getActivity().findViewById(R.id.editTextF1);
        editTextF2 = (EditText) getActivity().findViewById(R.id.editTextF2);
        editTextF3 = (EditText) getActivity().findViewById(R.id.editTextF3);
        buttonF1 = (Button)getActivity().findViewById(R.id.buttonF1);
        buttonF2 = (Button)getActivity().findViewById(R.id.buttonF2);
        buttonF3 = (Button)getActivity().findViewById(R.id.buttonF3);
        if (OFD_ButtonPress == 2){
            textViewF2.setTextColor(ContextCompat.getColor(getContext(), android.R.color.holo_blue_light));
            editTextF2.setEnabled(false);
        }
        if((OFD_ButtonPress == 3) | (OFD_ButtonPress == 4)){
            textViewF2.setTextColor(ContextCompat.getColor(getContext(), R.color.black));
            editTextF2.setEnabled(true);
        }
        editTextF1.setText(GlobalFolderName);
        editTextF2.setText("");
        editTextF3.setText(GlobalFilessName);
    }
    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.buttonF2:
                if(OFD_ButtonPress == 3) {
                    if (((editTextF3.getText()).length()) ==0 ){

                    }
                    else {
                        GlobalFilessName = String.valueOf(editTextF3.getText())+".txt";
                        if (((editTextF2.getText()).length()) !=0 ){
                            if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
                                Str1 = "SD-карта не доступна";
                                Toast toast1 = Toast.makeText(getContext(), Str1, Toast.LENGTH_SHORT);
                                toast1.show();
                            }
                            direcN1 = new File(GlobalFolderName+ "/"+String.valueOf(editTextF2.getText()));
                            if (!direcN1.exists()){
                                direcN1.mkdir();
                                Str1 = "Директорий "+ String.valueOf(editTextF2.getText())+" создан";
                                Toast toast2 = Toast.makeText(getContext(), Str1, Toast.LENGTH_SHORT);
                                toast2.show();
                            }
                            else {
                                direcN1 = new File(GlobalFolderName);
                            }
                            filesN1 = new File(direcN1, GlobalFilessName);
                            try {
                                BufferedWriter bw = new BufferedWriter(new FileWriter(filesN1));
                                bw.write(String.valueOf(editBufer.getText()));
                                bw.close();
                                Str1 = "Файл "+GlobalFilessName+" сохранен";
                                Toast toast3 = Toast.makeText(getContext(), Str1, Toast.LENGTH_SHORT);
                                toast3.show();
                            }
                            catch (FileNotFoundException e){
                                e.printStackTrace();
                            }
                            catch (IOException e){
                                e.printStackTrace();
                            }
                        }
                    }
                }

                if (OFD_ButtonPress == 4){
                    if (((editTextF3.getText()).length())==0){
                        Str1 = "Введите имя файла";
                        Toast toast5 = Toast.makeText(getContext(), Str1, Toast.LENGTH_SHORT);
                        toast5.show();
                    }
                    else {
                        GlobalFilessName = String.valueOf(editTextF3.getText())+".txt";
                        if(((editTextF2.getText()).length()) !=0) {
                            direcN1 = new File(GlobalFolderName+ "/"+String.valueOf(editTextF2.getText()));
                            if (!direcN1.exists()){
                                direcN1.mkdir();
                            }
                            else {
                                direcN1 = new File(GlobalFolderName);
                            }
                            filesN1 = new File(direcN1, GlobalFilessName);
                            try {
                                BufferedWriter bw = new BufferedWriter(new FileWriter(filesN1));
                                bw.write(String.valueOf(editBufer.getText()));
                                bw.close();
                                Str1 = "Файл "+GlobalFilessName+" сохранен";
                                Toast toast5 = Toast.makeText(getContext(),Str1, Toast.LENGTH_SHORT);
                                toast5.show();
                            }
                            catch (FileNotFoundException e){
                                e.printStackTrace();
                            }
                            catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        if (((editTextF2.getText()).length())==0){
                            direcN1 = new File(GlobalFolderName);
                            filesN1 = new File(direcN1, GlobalFilessName);
                            try {
                                BufferedWriter bw = new BufferedWriter(new FileWriter(filesN1));
                                bw.write(String.valueOf(editBufer.getText()));
                                bw.close();
                                Str1 = "Файл "+GlobalFilessName+" сохранен";
                                Toast toast5 = Toast.makeText(getContext(), Str1, Toast.LENGTH_SHORT);
                                toast5.show();
                            }
                            catch (FileNotFoundException e){
                                e.printStackTrace();
                            }
                            catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
                StartFragment1 = 2;
                getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
                break;
            case R.id.buttonF3:
                StartFragment1 = 2;
                getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
                break;
            case R.id.buttonF1:
                StartFragment1 = 0;
                getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
                OpenFileDialog fileDialog = new OpenFileDialog(getContext());
                fileDialog.show();
        }

    }

}