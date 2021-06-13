package com.example.kisyfi_njay;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        Masukkan Layout Form Ke Layout Utama
        ConstraintLayout placeHolder = (ConstraintLayout) findViewById(R.id.main_layout);
        getLayoutInflater().inflate(R.layout.form, placeHolder);

        buat_selectbox();
    }


    private void buat_selectbox(){
        String pertanyaan[] = {
                "sakit_kepala", "malas_bicara", "letih_bangun", "sulit_kuliah",
                "melamun_sendiri", "lelah_berfikir", "sulit_mikir", "lelah", "semangat_hilang"
        };

        String label_pertanyaan[] = {
                "Merasa Sakit Kepala", "Merasa Malas Berbicara Tentang Skripsi", "Merasa letih ketika bangun pagi", "Aktifitas perkuliahan saya terasa sulit",
                "Melamun Saat Sendiri", "Merasa Lelah Saat Berfikir", "Merasa kesulitan untuk berfikir atau agak lemot untuk berfikir",
                "Kegiatan yang dilakukan terasa melelahkan", "Kehilangan semangat pada apapun"
        };

        for (int i = 0; i< pertanyaan.length; i++){
            int id_induk = getResources().getIdentifier(pertanyaan[i], "id", getPackageName());

            if(id_induk > 0){
                LinearLayout induk = (LinearLayout) findViewById(id_induk);
                int child_count = induk.getChildCount();

                for (int child_index = 0; child_index < child_count; child_index++){
                    if(child_index == 0) {
                        TextView label = (TextView) induk.getChildAt(child_index);
                        label.setText(i + 1 + ". " + label_pertanyaan[i]);
                    }else{
                        Spinner dropdown = (Spinner) induk.getChildAt(child_index);

                        // Create an ArrayAdapter using the string array and a default spinner
                        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter
                                .createFromResource(this, R.array.opsi_jawaban,
                                        android.R.layout.simple_spinner_item);

                        // Specify the layout to use when the list of choices appears
                        staticAdapter
                                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                        // Apply the adapter to the spinner
                        dropdown.setAdapter(staticAdapter);
                    }
                }

            }
        }
    }
}