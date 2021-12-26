package com.example.app3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.app.Activity;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.app3.MainActivity.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {
    TextView
            textViewM2_1,
            textViewM2_2,
            textViewM2_3;
    EditText
            editTextM2_1,
            editTextM2_2,
            editTextM2_3;
    Button
            buttonM2_1,
            buttonM2_2,
            buttonM2_3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        //getSupportActionBar().hide();
        textViewM2_1 =(TextView) findViewById(R.id.textViewM2_1);
        textViewM2_2 =(TextView) findViewById(R.id.textViewM2_2);
        textViewM2_3 =(TextView) findViewById(R.id.textViewM2_3);
        editTextM2_1 = (EditText) findViewById(R.id.editTextM2_1);
        editTextM2_2 = (EditText) findViewById(R.id.editTextM2_2);
        editTextM2_3 = (EditText) findViewById(R.id.editTextM2_3);

        editTextM2_1.setText(GlobalFolderName);
        editTextM2_2.setText("");
        editTextM2_3.setText(GlobalFilessName);

        buttonM2_1 = (Button) findViewById(R.id.buttonM2_1);
        buttonM2_2 = (Button) findViewById(R.id.buttonM2_2);
        buttonM2_3 = (Button) findViewById(R.id.buttonM2_3);

        buttonM2_1.setOnClickListener(this);
        buttonM2_2.setOnClickListener(this);
        buttonM2_3.setOnClickListener(this);
    }
    @Override
    protected void onStart(){
        super.onStart();
        if (OFD_ButtonPress ==2){
            buttonM2_1.setText("Открыть файл");
            buttonM2_3.setText("Вернуться к выбору файла");
        }
    }
    @Override
    public void onClick(View view){
        switch (view.getId()) {
            case R.id.buttonM2_1:
                if (OFD_ButtonPress == 2) {
                    if (((editTextM2_3.getText()).length()) == 0) {
                        Str1 = "Введите имя файла";
                        Toast toast2 = Toast.makeText(Main2Activity.this, Str1, Toast.LENGTH_SHORT);
                        toast2.show();
                    } else {
                        GlobalFolderName = String.valueOf(editTextM2_1.getText());
                        GlobalFilessName = String.valueOf(editTextM2_3.getText());
                        direcN1 = new File(GlobalFolderName);
                        filesN1 = new File(direcN1, GlobalFilessName);
                        try {
                            BufferedReader br = new BufferedReader(new FileReader(filesN1));
                            String str = "";
                            while ((str = br.readLine()) != null) {
                                editBufer.setText(editBufer.getText() + str + "\n");
                            }
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                if (OFD_ButtonPress == 3) {
                    if (((editTextM2_3.getText()).length()) == 0) {
                    }
                    else{
                        GlobalFilessName = String.valueOf(editTextM2_3.getText())+" .txt";
                        if(((editTextM2_2.getText()).length()) !=0){
                            if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                                Str1 = "SD - карта не доступна";
                                Toast toast1 = Toast.makeText(Main2Activity.this, Str1, Toast.LENGTH_SHORT);
                                toast1.show();
                            }
                            direcN1 = new File(GlobalFolderName + "/" + String.valueOf(editTextM2_2.getText()));
                            if (!direcN1.exists()) {
                                direcN1.mkdir();
                                Str1 = "Директорий" + String.valueOf(editTextM2_2.getText()) + " создан";
                                Toast toast2 = Toast.makeText(Main2Activity.this, Str1, Toast.LENGTH_SHORT);
                                toast2.show();
                            } else {
                                direcN1 = new File(GlobalFolderName);
                            }
                            filesN1 = new File(GlobalFolderName);
                            try {
                                BufferedWriter bw = new BufferedWriter(new FileWriter(filesN1));
                                bw.write(String.valueOf(editBufer.getText()));
                                bw.close();
                                Str1 = "Файл " + GlobalFilessName + " сохранен";
                                Toast toast3 = Toast.makeText(Main2Activity.this, Str1, Toast.LENGTH_SHORT);
                                toast3.show();
                            }
                            catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }

                            catch (IOException e){
                                e.printStackTrace();
                            }
                        }
                    }
                }
                if (OFD_ButtonPress == 4){
                    if(((editTextM2_3.getText()).length()) == 0){
                        Str1 = "Введите имя файла";
                        Toast toast5 = Toast.makeText(Main2Activity.this, Str1, Toast.LENGTH_SHORT);
                        toast5.show();
                    }
                    else {
                        GlobalFilessName = String.valueOf(editTextM2_3.getText())+".txt";
                        if(((editTextM2_2.getText()).length()) !=0){
                            direcN1 = new File(GlobalFolderName+"/"+String.valueOf(editTextM2_2.getText()));
                            if(!direcN1.exists()){
                            direcN1.mkdir();
                        }
                        else{
                            direcN1 = new File(GlobalFolderName);
                        }
                        filesN1 = new File(direcN1, GlobalFilessName);
                        try {
                            BufferedWriter bw = new BufferedWriter(new FileWriter(filesN1));
                            bw.write(String.valueOf(editBufer.getText()));
                            bw.close();
                            Str1 = "Файл "+GlobalFilessName+" сохранен";
                            Toast toast5 = Toast.makeText(Main2Activity.this, Str1, Toast.LENGTH_SHORT);
                            toast5.show();
                        }
                        catch (FileNotFoundException e){
                            e.printStackTrace();
                        }
                        catch (IOException e){
                            e.printStackTrace();
                        }
                        }
                        if(((editTextM2_2.getText()).length()) ==0){
                            direcN1 = new File(GlobalFolderName);
                            filesN1 = new File(direcN1, GlobalFilessName);
                            try {
                                BufferedWriter bw = new BufferedWriter(new FileWriter(filesN1));
                                bw.write(String.valueOf(editBufer.getText()));
                                bw.close();
                                Str1 = "Файл "+GlobalFilessName+" сохранен";
                                Toast toast5 = Toast.makeText(Main2Activity.this, Str1, Toast.LENGTH_SHORT);
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
                this.finish();
                break;
        case R.id.buttonM2_2:
            this.finish();

            break;
        case R.id.buttonM2_3:
            OpenFileDialog fileDialog = new OpenFileDialog(this);
            fileDialog.show();
            break;
        }
    }

}