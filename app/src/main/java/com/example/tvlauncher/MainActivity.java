package com.example.tvlauncher;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Menampilkan Jam Real-time sederhana
        android.widget.TextView tvClock = findViewById(R.id.tvClock);
        String currentTime = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date());
        tvClock.setText(currentTime);

        // Tombol Home (Fokus utama saat pertama kali dibuka)
        Button btnHome = findViewById(R.id.btnHome);
        btnHome.requestFocus();
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Anda sudah berada di Home", Toast.LENGTH_SHORT).show();
            }
        });

        // Tombol YouTube (Membuka aplikasi YouTube jika terinstal)
        Button btnYoutube = findViewById(R.id.btnYoutube);
        btnYoutube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAppByPackage("com.google.android.youtube.tv");
            }
        });

        // Tombol Netflix
        Button btnNetflix = findViewById(R.id.btnNetflix);
        btnNetflix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAppByPackage("com.netflix.ninja");
            }
        });

        // Tombol Settings (Membuka Pengaturan STB)
        Button btnSettings = findViewById(R.id.btnSettings);
        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(android.provider.Settings.ACTION_SETTINGS);
                    startActivity(intent);
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Gagal membuka Pengaturan", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Tombol Apps (Membuka daftar aplikasi / Home sistem)
        Button btnApps = findViewById(R.id.btnApps);
        btnApps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                startActivity(intent);
            }
        });

        // Tombol Sleep
        Button btnSleep = findViewById(R.id.btnSleep);
        btnSleep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Mode Sleep / Power dimatikan", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Fungsi pembantu untuk membuka aplikasi berdasarkan nama paketnya
    private void openAppByPackage(String packageName) {
        PackageManager pm = getPackageManager();
        Intent intent = pm.getLaunchIntentForPackage(packageName);
        if (intent != null) {
            startActivity(intent);
        } else {
            Toast.makeText(this, "Aplikasi tidak ditemukan di STB", Toast.LENGTH_SHORT).show();
        }
    }
                }
                  
