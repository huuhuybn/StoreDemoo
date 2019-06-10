package com.dotplays.storedemoo;

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
}
