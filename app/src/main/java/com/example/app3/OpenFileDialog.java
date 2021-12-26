package com.example.app3;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Environment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.graphics.Rect;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.widget.Toast;
import android.content.DialogInterface;
import android.content.Intent;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.io.FilenameFilter;

import static com.example.app3.MainActivity.*;

public class OpenFileDialog extends AlertDialog.Builder{
    private String currentPath = Environment.getExternalStorageDirectory().getPath();
    private List<File> files = new ArrayList<File>();
    private ListView listView;
    private TextView textView;
    private TextView title;
    private FilenameFilter filenameFilter;
    private Drawable folderIcon;
    private Drawable fileIcon;
    private String accessDeniedMessage;
    private boolean isOnlyFoldersFilter;
    //private File[] files;
    File directory;
    //String[] result;
    List<File> fileList;
    LinearLayout linearLayout;
    Drawable drawable;
    String fileNameSt, fileExtSt;
    int fileNameLength;
    private int selectedIndex = -1;
    public OpenFileDialog setFolderIcon(Drawable drawable){
        this.folderIcon = drawable;
        return this;
    }
    public OpenFileDialog setFileIcon(Drawable drawable){
        this.fileIcon = drawable;
        return this;
    }
    public OpenFileDialog setAccessDeniedMessage(String message){
        this.accessDeniedMessage = message;
        return  this;
    }

    public interface OpenDialogListener{
        public void OnSelectedFile(String fileName);
    }

    public OpenFileDialog(Context context){
        super(context);
        title = createTitle(context);
        changeTitle();
        linearLayout = createMainLayout(context);
        linearLayout.addView(createBackIteam(context));
        files.addAll(getFiles(currentPath));
        listView = createListView(context);
        //listView.setAdapter(new FileAdapter(context,files));
        linearLayout.addView(listView);
        setCustomTitle(title);
        setView(linearLayout);
        setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (selectedIndex > -1 && listener != null){
                    listener.OnSelectedFile(listView.getItemAtPosition(selectedIndex).toString());
                }
                if (listener != null && isOnlyFoldersFilter){
                    listener.OnSelectedFile(currentPath);
                }
                //Intent intent;
              //  intent = new Intent(OpenFileDialog.super.getContext(), Main2Activity.class);
              //  getContext().startActivity(intent);

                StartFragment1 = 1;
            }
        });
        setNegativeButton(R.string.cancel, null);
        //AlertDialog.Builder positiveButton = setPositiveButton("OK", null);
        //AlertDialog.Builder negativeButton = setNegativeButton("CANCEL", null);
    }

    private  OpenDialogListener listener;
    public OpenFileDialog setOpenDialogListener(OpenDialogListener listener){
        this.listener = listener;
        return (this);
    }
    public OpenFileDialog setFilter(final String filter){
        filenameFilter = new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                File tempFile = new File(String.format("%s/%s", dir.getPath(),name));
                /*OpenFileDialog fileDialog = new OpenFileDialog((FilenameFilter) this)
                        .setFilter(".*\\csv")
                        .setOpenDialogListener(new OpenDialogListener(){
                    @Override
                    public void OnSelectedFile(String fileName){
                        Toast.makeText(getContext(),fileName, Toast.LENGTH_SHORT).show();
                    }
                });
                fileDialog.show();*/
                if (tempFile.isFile())
                    return tempFile.getName().matches(filter);
                return true;
            }
        };
        return this;
    }
    public OpenFileDialog setOnlyFoldersFilter(){
        isOnlyFoldersFilter = true;
        filenameFilter = new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                File tempFile = new File(String.format("%s/%s", dir.getPath(),name));
                return tempFile.isDirectory();
            }
        };
        return this;
    }
    public OpenFileDialog(FilenameFilter filenameFilter){

        super((Context) filenameFilter);
    }
    @Override
    public AlertDialog show(){
        files.addAll(getFiles(currentPath));
        listView.setAdapter(new FileAdapter(getContext(), files));
        return super.show();
    }

    private TextView createTextView(Context context, int style){
        textView = new TextView(context);
        textView.setTextAppearance(context,style);
        int itemHeight = getItemHeidht(context);
        textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,itemHeight));
        textView.setMinimumHeight(itemHeight);
        textView.setGravity(Gravity.CENTER_VERTICAL);
        textView.setPadding(15,0,0,0);
        textView.setText(currentPath);
        return textView;
    }
    public int getTextWidth(String text, Paint paint){
        Rect bounds = new Rect();
        paint.getTextBounds(text,0,text.length(),bounds);
        return bounds.left +bounds.width() + 80;
    }
    private void changeTitle(){
        String titleText = currentPath;
        GlobalFolderName = currentPath;
        int screenWidth = getScreenSize(getContext()).x;
        int maxWidth = (int) (screenWidth * 0.99);
        if(getTextWidth(titleText, title.getPaint())>maxWidth){
            while (getTextWidth("..."+ titleText,title.getPaint())> maxWidth){
                int start = titleText.indexOf("/", 2);
                if(start > 0 ){
                    titleText = titleText.substring(start);
                }else {
                    titleText = titleText.substring(2);
                }
            }
            title.setText("..." + titleText);
        }
        else {
            title.setText(titleText);
        }
    }
    private static int getItemHeidht(Context context){
        TypedValue value = new TypedValue();
        DisplayMetrics metrics = new DisplayMetrics();
        context.getTheme().resolveAttribute(R.attr.height, value,true);
        getDefaultDisplay(context).getMetrics(metrics);
        return (int) TypedValue.complexToDimension(value.data, metrics);
    }
    private TextView createTitle(Context context){
        textView = createTextView(context, R.style.TextAppearance_AppCompat_Widget_ActionBar_Title);
        return textView;
    }
    private TextView createBackIteam(Context context){
        textView = createTextView(context, R.style.TextAppearance_AppCompat_Widget_ActionBar_Title);
        drawable = getContext().getResources().getDrawable(R.drawable.button_item_back_green04_yellow04_200_200);
        drawable.setBounds(0,0,60,60);
        textView.setCompoundDrawables(drawable, null, null, null);
        textView.setText(" . . . ");
        textView.setLayoutParams( new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                File file = new File(currentPath);
                File parentDirectory = file.getParentFile();
                if (parentDirectory != null){
                    currentPath = parentDirectory.getPath();
                    RebuildFiles(((FileAdapter) listView.getAdapter()));
                }
            }
        });
        
        /*textView= new TextView(context);
        textView.setTextAppearance(context, R.style.TextAppearance_AppCompat_Widget_ActionBar_Title);
        int itemHeight = getItemHeidht(context);
        textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,itemHeight));
        textView.setMinimumHeight(itemHeight);
        textView.setGravity(Gravity.CENTER_VERTICAL);
        textView.setPadding(15,0,0,0);
        textView.setText(currentPath);
        textView = createTextView(context, R.style.TextAppearance_AppCompat_Widget_ActionBar_Title);*/
        return textView;
    }
   /*private void RebuildFiles (ArrayAdapter<File> adapter){
        files.clear();
        files.addAll(getFiles(currentPath));
        adapter.notifyDataSetChanged();
        title.setText(currentPath);
    }*/
    private static Display getDefaultDisplay(Context context){
        return ((WindowManager)context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
    }
    private static Point getScreenSize(Context context){
        Point screenSize = new Point();
        getDefaultDisplay(context).getSize(screenSize);
        return screenSize;
    }
    private static int getLinearLayoutMinheight(Context context){
        return getScreenSize(context).y;
    }
    private LinearLayout createMainLayout(Context context){
        linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setMinimumHeight(getLinearLayoutMinheight(context));
        return linearLayout;
    }
    /*private ListView createListView (Context context){
        listView =new ListView(context);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                final ArrayAdapter<File> adapter = (FileAdapter) adapterView.getAdapter();
                File file = adapter.getItem(position);
                if(file.isDirectory()){
                    currentPath=file.getPath();
                    RebuildFiles(adapter);
                }

            }
        });
        return listView;
    }*/
    private List<File> getFiles(String directoryPath){
        directory = new File(directoryPath);
        File[] list = directory.listFiles(filenameFilter);
        if (list == null)
            list = new File[]{};
        fileList= Arrays.asList(list);
        Collections.sort(fileList, new Comparator<File>(){
            @Override
            public int compare(File file, File file2){
                if (file.isDirectory()&& file2.isFile())
                    return  -1;
                else if (file.isFile()&& file2.isDirectory())
                    return 1;
                else
                    return file.getPath().compareTo(file2.getPath());
            }

        });
        return fileList;
    }
    private void RebuildFiles(ArrayAdapter<File> adapter){
        try {
            fileList = getFiles(currentPath);
            files.clear();
            selectedIndex = -1;
            files.addAll(fileList);
            adapter.notifyDataSetChanged();
            changeTitle();


        } catch (NullPointerException e) {
            String message = getContext().getResources().getString(R.string.unknownName);
            if (!accessDeniedMessage.equals(""))//426
                message = accessDeniedMessage;
            Toast.makeText(getContext(), R.string.Large_text, Toast.LENGTH_SHORT).show();
        }
    }
    private ListView createListView(Context context){
        listView = new ListView(context);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                final ArrayAdapter<File> adapter = (FileAdapter) adapterView.getAdapter();
                File file = adapter.getItem(position);
                if (file.isDirectory()){
                    currentPath = file.getPath();
                    RebuildFiles(adapter);
                }
                else {
                    if (position != selectedIndex){
                        selectedIndex =position;
                    }
                    else {
                        selectedIndex = -1;
                    }
                    adapter.notifyDataSetChanged();
                    GlobalFilessName = file.getName();
                }
            }
        });
        return  listView;
    }

    private class FileAdapter extends ArrayAdapter<File>{
        File file;
        TextView view;
        private Context context = null;
        public FileAdapter(Context context, List<File> files){
            super(context, R.layout.support_simple_spinner_dropdown_item, files);
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            view = (TextView) super.getView(position, convertView, parent);
            file = getItem(position);
            view.setText(file.getName());
            if (file.isDirectory()){
                GlobalFilessName = "";
                setDrawable(view, folderIcon);
            }else {
                fileNameSt = file.getName();
                GlobalFolderName = file.getParent();
                setDrawableF(view, fileIcon);
                if( selectedIndex == position){
                    view.setBackgroundColor(getContext().getResources().getColor(R.color.teal_200));
                }
                else {
                    view.setBackgroundColor(getContext().getResources().getColor(android.R.color.holo_green_light));
                }

            }

            return view;
        }
    }
    private void setDrawableF(TextView view, Drawable drawable){
        if (view != null){
            fileNameLength = fileNameSt.length();
            fileExtSt = fileNameSt.substring(fileNameLength-3, fileNameLength);
            switch (fileExtSt){
                case "avi":
                    drawable = getContext().getResources().getDrawable(R.drawable.avi);
                    break;
                case "doc":
                    drawable = getContext().getResources().getDrawable(R.drawable.doc);
                    break;
                case "flv":
                    drawable = getContext().getResources().getDrawable(R.drawable.flv);
                    break;
                case "qif":
                    drawable = getContext().getResources().getDrawable(R.drawable.gif);
                    break;
                case "jpg":
                    drawable = getContext().getResources().getDrawable(R.drawable.jpg);
                    break;
                case "jpeg":
                    drawable = getContext().getResources().getDrawable(R.drawable.jpg);
                    break;
                case "mov":
                    drawable = getContext().getResources().getDrawable(R.drawable.mov);
                    break;
                case "mp3":
                    drawable = getContext().getResources().getDrawable(R.drawable.mp3);
                    break;
                case "mpg":
                    drawable = getContext().getResources().getDrawable(R.drawable.mpg);
                    break;
                case "mpeg":
                    drawable = getContext().getResources().getDrawable(R.drawable.mpg);
                    break;
                case "pdf":
                    drawable = getContext().getResources().getDrawable(R.drawable.pdf);
                    break;
                case "ppt":
                    drawable = getContext().getResources().getDrawable(R.drawable.ppt);
                    break;
                case "rtf":
                    drawable = getContext().getResources().getDrawable(R.drawable.rtf);
                    break;
                case "txt":
                    drawable = getContext().getResources().getDrawable(R.drawable.txt);
                    break;
                case "wav":
                    drawable = getContext().getResources().getDrawable(R.drawable.wav);
                    break;
                case "wmv":
                    drawable = getContext().getResources().getDrawable(R.drawable.wma);
                    break;
                case "xls":
                    drawable = getContext().getResources().getDrawable(R.drawable.xls);
                    break;
                case "zip":
                    drawable = getContext().getResources().getDrawable(R.drawable.zip);
                    break;
                case "rar":
                    drawable = getContext().getResources().getDrawable(R.drawable.rar);
                    break;
                default:
                    drawable = getContext().getResources().getDrawable(R.drawable.file);
                    break;
            }

            drawable.setBounds(0,0,40,40);
            view.setCompoundDrawables(drawable,null,null,null);
            /*drawable = getContext().getResources().getDrawable(R.drawable.avi);
            drawable.setBounds(0,0,40,40);
            view.setCompoundDrawables(drawable,null,null,null);*/
        }
    }
    private void setDrawable (TextView view, Drawable drawable){
        if(view != null){
            drawable = getContext().getResources().getDrawable(R.drawable.folder);
            drawable.setBounds(0,0,40,40);
            view.setCompoundDrawables(drawable, null,null,null);
        }
    }

    /*private String[] getFiles(String dirPath){
        directory = new File(dirPath);
        files = directory.listFiles();
        result = new String[files.length];
        for (int i = 0; i < files.length; i++){
            result[i] = files[i].getName();
        }
        return result;
}*/
}