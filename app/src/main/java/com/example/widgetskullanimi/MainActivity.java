package com.example.widgetskullanimi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

import com.example.widgetskullanimi.databinding.ActivityMainBinding;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private boolean kontrol = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.buttonOku.setOnClickListener(view -> {
            String alinanVeri = binding.editTextGirdi.getText().toString();
            binding.textViewSonuc.setText(alinanVeri);
        });

        binding.buttonResim1.setOnClickListener(view -> {
            binding.imageView.setImageResource(R.drawable.resim1);
        });

        binding.buttonResim2.setOnClickListener(view -> {
            binding.imageView.setImageResource(getResources().getIdentifier("resim2","drawable",getPackageName()));
        });

        binding.buttonBasla.setOnClickListener(view -> {
            binding.progressBar.setVisibility(View.VISIBLE);
        });


        binding.buttonDur.setOnClickListener(view -> {
            binding.progressBar.setVisibility(View.INVISIBLE);
        });


        binding.switch1.setOnCheckedChangeListener((mSwitch,isChecked) ->{
            if(isChecked){
                Log.e("Sonuç","Switch : ON");
            }
            else {
                Log.e("Sonuç","Switch : OFF");
            }
        });

        binding.toggleButton.addOnButtonCheckedListener(((group, checkedId, isChecked) -> {
            kontrol = isChecked;
            if (kontrol){
                Button secilenButton = findViewById(binding.toggleButton.getCheckedButtonId());
                String buttonYazi = secilenButton.getText().toString();
                Log.e("Sonuç",buttonYazi);
            }
        }));

        ArrayList<String> ulkeler = new ArrayList<>();
        ulkeler.add("Türkiye");
        ulkeler.add("İtalya");
        ulkeler.add("Japonya");
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,ulkeler);
        binding.autoCompleteTextView.setAdapter(arrayAdapter);

        binding.textViewSlider.setText("Slider : "+binding.slider.getProgress());

        binding.slider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar s, int i, boolean b) {
                binding.textViewSlider.setText("Slider : "+i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        binding.buttonSaat.setOnClickListener(view -> {
            MaterialTimePicker tp = new MaterialTimePicker.Builder()
                    .setTitleText("Saat Seç")
                    .setTimeFormat(TimeFormat.CLOCK_24H)
                    .build();

            tp.show(getSupportFragmentManager(),"");


            tp.addOnPositiveButtonClickListener(view1 -> {
                binding.editTextSaat.setText(tp.getHour()+" : "+tp.getMinute());

            });
        });

        binding.buttonTarih.setOnClickListener(view -> {
            MaterialDatePicker dp = MaterialDatePicker.Builder.datePicker()
                    .setTitleText("Tarih Seç")
                    .build();
            dp.show(getSupportFragmentManager(),"");

            dp.addOnPositiveButtonClickListener(view1 -> {
                SimpleDateFormat df = new SimpleDateFormat("dd/MMMM/yyyy", Locale.getDefault());
                String tarih = df.format(view1);
                binding.editTextTarih.setText(tarih);
            });
        });



        binding.buttonGoster.setOnClickListener(view -> {
            Log.e("Sonuç","Switch Durum : "+binding.switch1.isChecked());
            if (kontrol){
                Button secilenButton = findViewById(binding.toggleButton.getCheckedButtonId());
                String buttonYazi = secilenButton.getText().toString();
                Log.e("Sonuç","Toggle Durum"+buttonYazi);
            }
            String ulke = binding.autoCompleteTextView.getText().toString();
            Log.e("Sonuç","Ülke : "+ulke);
            Log.e("Sonuç","Slider : "+binding.slider.getProgress());
        });


        binding.buttonToast.setOnClickListener(view -> {
            Toast.makeText(this,"Merhaba",Toast.LENGTH_SHORT).show();
        });


        binding.buttonSnackbar.setOnClickListener(view -> {
            Snackbar.make(view,"Silmek ister misiniz?",Snackbar.LENGTH_SHORT)
                    .setAction("Evet",view1 -> {
                        Snackbar.make(view,"Silindi",Snackbar.LENGTH_SHORT)
                                .setBackgroundTint(Color.RED)
                                .setTextColor(Color.WHITE)
                                .show();
                    })
                    .setBackgroundTint(Color.WHITE)
                    .setTextColor(Color.BLUE)
                    .setActionTextColor(Color.RED)
                    .show();
        });


        binding.buttonAlert.setOnClickListener(view -> {
            new MaterialAlertDialogBuilder(this)
                    .setTitle("Başlık")
                    .setMessage("Mesaj")
                    .setPositiveButton("Tamam",(d,i) ->{//i=integer değer(index), d=butonun kendisini temsil eder
                        Toast.makeText(this,"Tamam Seçildi",Toast.LENGTH_SHORT).show();
                    })
                    .setNegativeButton("İptal",(d,i) ->{//i=integer değer(index), d=butonun kendisini temsil eder
                        Toast.makeText(this,"İptal Seçildi",Toast.LENGTH_SHORT).show();
                    })
                    .show();
        });



    }
}