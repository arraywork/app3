package com.example.app3;

import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;// ActionBarActivity теперь устарел


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.LinearLayout;
import android.os.Handler;




import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.util.Log;
import android.os.Environment;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static String GlobalFolderName;
    public static String GlobalFilessName;
    public static String Str1;
    public static int OFD_ButtonPress;
    public static EditText editBufer;
    public static File filesN1, direcN1;

    public static int StartFragment1 =0;

    public static EditText editText1;

   // EditText        editText1;
    Button          button1,
                    button2,
                    button3,
                    button4,
                    button5,
                    button6,
                    button7,
                    button8;

    final String    LOG_TAG = "myLogs",
                    FileName_IN = "MyFile_IN",
                    FileName_SD = "MyFile_SD",
                    Dir_Name_SD="MyFiles_SD";
    String          deleteCmd;
    File            sdPath,
                    sdFile;
    public static AlertDialog.Builder aldi;
    //RelativeLayout  relativeLayout1;
    int  StartRes = 1;

    public static RelativeLayout relativeLayout1;
    public static Fragment Fragment1;
    public static FragmentTransaction FTransac1;
    public static FragmentManager FManager1;

    public static Fragment Fragment2;
    public static FragmentTransaction FTransac2;
    public static FragmentManager FManager2;

    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if(StartFragment1 == 0 ){
                handler.postDelayed(runnable, 500);
            }
            if (StartFragment1 == 2) {
                handler.removeCallbacks(runnable);
            }
            if (StartFragment1 == 10) {
                relativeLayout1.setRight(MainActivity.this.getWindow().getDecorView().getWidth()/2);
                relativeLayout1.setLeft(0);
                relativeLayout1.setTop(0);
                relativeLayout1.setBottom(480);

                StartFragment1 = 0;
                handler.postDelayed(runnable, 500);
            }
            if (StartFragment1 ==1){
                if (OFD_ButtonPress == 2){
                    handler.removeCallbacks(runnable);
                    MainActivity.this.setTitle("Текстовый редактор: "+ GlobalFilessName);
                    MainActivity.editText1.setText("");
                    direcN1 = new File(GlobalFolderName);
                    filesN1 = new File(direcN1, GlobalFilessName);
                    try{
                        BufferedReader br = new BufferedReader(new FileReader(filesN1));
                        Str1 = "";
                        while ((Str1 = br.readLine()) !=null){
                            MainActivity.editText1.setText(MainActivity.editText1.getText() + Str1 + "\n");
                        }
                    }
                    catch (FileNotFoundException e){
                        e.printStackTrace();
                    }
                    catch (IOException e){
                        e.printStackTrace();
                    }
                }
                if ((OFD_ButtonPress == 3) | (OFD_ButtonPress ==4)) {
                    //handler.removeCallbacks(runnable);
                    relativeLayout1.setRight(MainActivity.this.getWindow().getDecorView().getWidth() - 4);
                    relativeLayout1.setLeft(4);
                    relativeLayout1.setTop(100);
                    relativeLayout1.setBottom(140);
                    FManager1 = getSupportFragmentManager();
                    Fragment1 = FManager1.findFragmentById(R.id.relativeLayout1);
                    Fragment1 = new BlankFragment1();
                    FTransac1 = FManager1.beginTransaction();
                    FTransac1.add(R.id.relativeLayout1, Fragment1);
                    FTransac1.commit();
                    StartFragment1 = 0;
                    handler.postDelayed(runnable, 500);
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main_v);
        setContentView(R.layout.activity_main_v);

        editText1 = (EditText) findViewById(R.id.editText1);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);
        button6 = (Button) findViewById(R.id.button6);
        button7 = (Button) findViewById(R.id.button7);
        button8 = (Button) findViewById(R.id.button8);
        relativeLayout1 = (RelativeLayout) findViewById(R.id.relativeLayout1);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        Drawable drawable;
        drawable = this.getResources().getDrawable(R.drawable.file_icon_actionbar);
        //drawable.setBounds(0,0,60,60);
        actionBar.setBackgroundDrawable(drawable);

        editText1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (StartRes == 1){
                    StartRes = 0;
                   // button1.setText(String.valueOf(button1.getTop()));
                   //editText1.setHeight(button1.getTop());
                    //editText1.setHeight(button2.getTop());
                }

            }
        });
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);

        GlobalFilessName = "";
        GlobalFolderName = "";
        aldi = new AlertDialog.Builder(MainActivity.this);
        aldi.setTitle("Предупреждение");
        aldi.setPositiveButton("Да", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which){
                if (OFD_ButtonPress == 1) {
                    GlobalFilessName = "";
                    GlobalFolderName = "";
                    editText1.setText("");
                    MainActivity.this.setTitle("Текстовый редактор: "+GlobalFilessName);
                }
                if(OFD_ButtonPress == 6){
                    finish();
                }

        }
        private void finish(){
            this.finish();
        }

    });
    aldi.setNegativeButton("Нет", new DialogInterface.OnClickListener()    {
        @Override
        public void onClick (DialogInterface dialog,int which){
    }
    });
}
    @Override
    protected void onRestart(){
        super.onRestart();
        this.setTitle("Текстовый редактор: "+GlobalFilessName);
        editText1.setText(editBufer.getText());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:

                FManager2 = getSupportFragmentManager();
                Fragment2 = FManager2.findFragmentById(R.id.relativeLayout1);
                Fragment2 = new BlankFragment2();
                FTransac2 = FManager2.beginTransaction();
                FTransac2.add(R.id.relativeLayout1, Fragment2);
                FTransac2.commit();
                StartFragment1 = 10;
                handler.postDelayed(runnable, 50);

                break;

            case R.id.i1_set:
                OFD_ButtonPress = 1;
                aldi.setMessage("Создать новый файл без сохранения предыдущего?");
                aldi.show();
                /*-Toast toast1 = Toast.makeText(MainActivity.this, "Новый", Toast.LENGTH_SHORT);
                toast1.show();*/
                break;
            case R.id.i2_set:
                StartFragment1 = 0;
                handler.postDelayed(runnable, 500);

                OFD_ButtonPress = 2;
                editBufer = new EditText(this);
                OpenFileDialog fileDialogOpen = new OpenFileDialog(this);
                fileDialogOpen.show();
                break;
            case R.id.i3_set:

                StartFragment1 = 0;
                handler.postDelayed(runnable, 500);

                OFD_ButtonPress = 3;
                if(GlobalFilessName == ""){
                    Str1 = "Укажите директорий или имя файла";
                    Toast toast3 = Toast.makeText(MainActivity.this, Str1, Toast.LENGTH_SHORT);
                    toast3.show();
                    editBufer = new EditText(this);
                    editBufer.setText(editText1.getText());
                    OpenFileDialog fileDialogSave = new OpenFileDialog(this);
                    fileDialogSave.show();

                }
                else{
                    direcN1 = new File(GlobalFolderName + "/");
                    filesN1 = new File(direcN1, GlobalFilessName);
                    try{
                        BufferedWriter bw = new BufferedWriter(new FileWriter(filesN1));
                        bw.write(String.valueOf(editBufer.getText()));
                        bw.close();
                        Str1 = "Файл сохране: "+ String.valueOf(filesN1);
                        Toast toast0 = Toast.makeText(MainActivity.this, Str1, Toast.LENGTH_SHORT);
                        toast0.show();
                    }
                    catch (FileNotFoundException e){
                        e.printStackTrace();
                    }
                    catch (IOException e){
                        e.printStackTrace();
                    }
                }
                break;

            case R.id.i4_set:

                StartFragment1 = 0;
                handler.postDelayed(runnable, 500);

                OFD_ButtonPress =4;
                editBufer = new EditText(this);
                editBufer.setText(editText1.getText());
                OpenFileDialog fileDialogSave = new OpenFileDialog(this);
                fileDialogSave.show();
                //Toast toast4 = Toast.makeText(MainActivity.this, "Сохранить как...", Toast.LENGTH_SHORT);
                //toast4.show();
                break;
            case R.id.i5_set:
                OFD_ButtonPress = 5;
                Toast toast5 = Toast.makeText(MainActivity.this, "Настройки", Toast.LENGTH_SHORT);
                toast5.show();
                break;
            case R.id.i6_set:
                OFD_ButtonPress = 6;
                aldi.setMessage("Файл не сохранен ! Вы уверены что хотите выйти ?");
                aldi.show();
                //Toast toast6 = Toast.makeText(MainActivity.this, "Выход", Toast.LENGTH_SHORT);
                //toast6.show();
                break;
        }
        return true;
    }

    public void editText1Click(View view) {
        /*editText1.setHeight(button1.getTop());*/
        editText1.setHeight(button2.getTop());
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig){
        super.onConfigurationChanged(newConfig);
        StartRes=1;
       // button1.setText("StartRes = "+String.valueOf(StartRes)+" "+String.valueOf(button1.getTop()));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button2:
                fileWrite_IN();
                break;
            case R.id.button3:
                fileRead_IN();
                break;
            case R.id.button4:
                fileDelete_IN();
                break;
            case R.id.button5:
                fileWrite_SD();
                break;
            case R.id.button6:
                fileRead_SD();
                break;
            case R.id.button7:
                fileDelete_SD();
                break;
            case R.id.button8:
                editText1.setText("");
                break;
            case R.id.button1:
                this.finish();
                break;
        }
    }

    void fileDelete_SD() {
        if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
        {
            Log.d(LOG_TAG, "SD-карта не доступна: " +Environment.getExternalStorageState());
            return;
        }
        sdPath = Environment.getExternalStorageDirectory();
        sdPath = new File(sdPath.getAbsolutePath() + "/" +Dir_Name_SD);
        sdPath = new File(sdPath, FileName_SD);
        if (sdFile.exists()){
            deleteCmd = "rm -r " +sdFile;
            Runtime runtime = Runtime.getRuntime();
            try{
                runtime.exec(deleteCmd);
                Log.d(LOG_TAG, "Файл " + FileName_SD + " удален");
            }
            catch (FileNotFoundException e){
                e.printStackTrace();
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }

    }

    void fileRead_SD() {
        if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            Log.d(LOG_TAG,"SD-карта не доступна: "+Environment.getExternalStorageState());
            return;
        }
        sdPath = Environment.getExternalStorageDirectory();
        sdPath = new File(sdPath,FileName_SD);
        try {
            BufferedReader br = new BufferedReader(new FileReader(sdFile));
            String str = "";
            while ((str = br.readLine()) != null){
                editText1.setText(editText1.getText() + str + "\n");
                Log.d(LOG_TAG, "Файл " + FileName_SD);
            }
        }
        catch (FileNotFoundException e){
            editText1.setText("Файл "+FileName_SD+" не найден!");
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    void fileWrite_SD() {
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            Log.d(LOG_TAG, "SD-карта не доступна: "+ Environment.getExternalStorageState());
            return;
        }
        sdPath = Environment.getExternalStorageDirectory();
        sdPath = new File(sdPath.getAbsolutePath()+"/"+ Dir_Name_SD);
        sdPath.mkdirs();
        sdFile = new File(sdPath, FileName_SD);
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter(sdFile));
            bw.write(String.valueOf(editText1.getText()));
            bw.close();
            Log.d(LOG_TAG, "Файл "+FileName_IN+" записан на SD" +sdFile.getAbsolutePath());
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }

    void fileDelete_IN() {
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(openFileInput(FileName_IN)));
            this.deleteFile(FileName_IN);
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }


    }

    void fileRead_IN() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(openFileInput(FileName_IN)));
            String str = "";
            while ((str = br.readLine()) != null) {
                editText1.setText(editText1.getText() + str + "\n");
            }
            br.close();
            Log.d(LOG_TAG, "Файл " + FileName_IN);
        }
        catch (FileNotFoundException e){
            editText1.setText("Файл "+FileName_IN+"не найден");
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    void fileWrite_IN() {
        try {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(openFileOutput(FileName_IN, MODE_PRIVATE)));
            bw.write(String.valueOf(editText1.getText()));
            bw.close();
            Log.d(LOG_TAG, "Файл "+ FileName_IN + " записан");
            Toast toast = Toast.makeText(MainActivity.this, "Файл "+ FileName_IN+" записан",Toast.LENGTH_SHORT);
            toast.show();

        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

}