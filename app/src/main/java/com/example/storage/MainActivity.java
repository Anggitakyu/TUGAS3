package com.example.storage;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.storage.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String FILENAME = "biodata.txt";
    Button btnBuat, btnHapus, btnUbah, btnBaca;
    TextView txtIsi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnBaca = findViewById(R.id.baca);
        btnBuat = findViewById(R.id.buat);
        btnUbah = findViewById(R.id.ubah);
        btnHapus = findViewById(R.id.delete);
        txtIsi = findViewById(R.id.isifile);

        btnBuat.setOnClickListener(this);
        btnHapus.setOnClickListener(this);
        btnBaca.setOnClickListener(this);
        btnUbah.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        jalankanPerintah(view.getId());
    }

    void jalankanPerintah(int id){
        switch(id){
            case R.id.buat:
                buatFile();
                break;
            case R.id.delete:
                hapusfile();
                break;
            case R.id.baca:
                bacaFile();
                break;
            case R.id.ubah:
                ubahFile();
                break;
        }
    }

    void ubahFile(){
        String ubah = "ini adalah update dari data file";
        File file = new File(getFilesDir(), FILENAME);
        FileOutputStream outputStream = null;
        try {
            file.createNewFile();
            outputStream = new FileOutputStream(file, true);
            outputStream.write(ubah.getBytes());
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void bacaFile(){
        File lok = getFilesDir();
        File file = new File(lok, FILENAME);

        if(file.exists()){
            StringBuilder text = new StringBuilder();
            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                String line = bufferedReader.readLine();
                while (line!=null){
                    text.append(line);
                    line = bufferedReader.readLine();

                }

                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            txtIsi.setText(text.toString());
        }
    }

    void buatFile(){
        String isiFile = "Ini adalah isi file nya";
        File file = new File(getFilesDir(), FILENAME);

        FileOutputStream outputStream = null;

        try {
            file.createNewFile();
            outputStream = new FileOutputStream(file, true);
            outputStream.write(isiFile.getBytes());
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void hapusfile(){
        File file = new File(getFilesDir(), FILENAME);
        if(file.exists()){
            file.delete();
        }
    }
}