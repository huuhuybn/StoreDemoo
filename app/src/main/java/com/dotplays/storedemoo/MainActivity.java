package com.dotplays.storedemoo;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {


    private EditText edtData;

    private String cacheFileName = "myCacheFile.cache";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtData = findViewById(R.id.edtData);


    }

    public void saveToCache(View view) {

        try {
            // lay ra du lieu tu edittext , trim() la cat khoang trang 2 dau cua string
            String data = edtData.getText().toString().trim();

            // lay ra dia chi cua thu muc Cache.
            File pathCacheDir = getCacheDir();

            // tao ra file cache tu ten file va dia chi thu muc chua file cache
            File cacheFile = new File(pathCacheDir, cacheFileName);

            // ghi file cache nay vao thu muc cache
            cacheFile.createNewFile();

            FileOutputStream fileOutputStream = new FileOutputStream(cacheFile);

            fileOutputStream.write(data.getBytes());

            fileOutputStream.close();

            Toast.makeText(this, "Ghi Thanh Cong!", Toast.LENGTH_SHORT).show();

            // clear text tai o nhap lieu
            edtData.setText("");


        } catch (IOException e) {

            e.printStackTrace();

        }


    }

    public void loadFromCache(View view) {

        try {
            // lay ra dia chi cua thu muc Cache.
            File pathCacheDir = getCacheDir();

            // tao ra file cache tu ten file va dia chi thu muc chua file cache
            File cacheFile = new File(pathCacheDir, cacheFileName);

            Scanner scanner = new Scanner(cacheFile);

            String duLieu = "";
            while (scanner.hasNext()) {
                duLieu = duLieu + scanner.nextLine();
            }
            scanner.close();

            edtData.setText(duLieu);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }

    public void saveToSharedPreference(View view) {
        // lay ra du lieu tu edittext , trim() la cat khoang trang 2 dau cua string
        String data = edtData.getText().toString().trim();

        SharedPreferences sharedPreferences =
                getSharedPreferences("myFile", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("data", data);

        // confirm la save

        editor.apply();
        // 2 cau lenh apply va commit la giong nhau.
        //editor.commit();


        // clear du lieu vua nhap
        edtData.setText("");


    }

    public void loadFromSharedPreference(View view) {


        SharedPreferences sharedPreferences =
                getSharedPreferences("myFile", Context.MODE_PRIVATE);
        String data = sharedPreferences.getString("data", null);

        if (data != null) edtData.setText(data);


    }

    public void saveToSDCard(View view) {

    Boolean isSDPresent = android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
    Boolean isSDSupportedDevice = Environment.isExternalStorageRemovable();

        if (isSDSupportedDevice && isSDPresent) {
            // yes SD-card is present
        } else {
            // Sorry
        }
    }

    public void loadFromSDCard(View view) {

        Boolean isSDPresent = android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
        Boolean isSDSupportedDevice = Environment.isExternalStorageRemovable();

        if (isSDSupportedDevice && isSDPresent) {
            // yes SD-card is present
        } else {
            // Sorry
        }
    }

}
