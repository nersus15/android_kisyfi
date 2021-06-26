package com.example.kisyfi_njay;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    String pertanyaan[] = {
            "sakit_kepala", "malas_bicara", "letih_bangun", "sulit_kuliah", "melamun_sendiri",
            "lelah_berfikir", "sulit_mikir", "lelah", "semangat_hilang" , "mual" ,
            "berat_badan" , "selera" , "jantung" , "tangan_dingin" , "keringat" ,
            "insomnia" , "respon_tubuh" , "terjaga_malam" , "mager" , "cemas" ,
            "tremor" , "ceroboh" , "tersinggung" , "labil" , "depresi" ,
            "agresif" , "konsentrasi_mengerjakan" , "kualitas" , "mulut" , "perut" ,
            "khawatir" , "frustasi" , "motivasi" , "bosan" , "kacau" ,
            "marah" , "keputusan" , "konsentrasi" , "panik" , "nangis" ,
            "bundir" , "lola" , "bingung" , "tegang" , "lupa" ,
            "berlebihan" , "insecure" , "murung" , "mondir" , "kronis"
    };
    Button btn_send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Masukkan Layout Form Ke Layout Utama
        ConstraintLayout placeHolder = (ConstraintLayout) findViewById(R.id.main_layout);
        getLayoutInflater().inflate(R.layout.form, placeHolder);
        buat_selectbox();

        btn_send = findViewById(R.id.btn_send);
        btn_send.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(v.getId() == R.id.btn_send)
                    send();
            }
        });
        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);

        }

    }

    void send(){
        String url = "https://app.terasinovasi.me/auth";
        MultipartBody.Builder bodyBuilder = new MultipartBody.Builder();
        MultipartBody.Builder builder = bodyBuilder.setType(MultipartBody.FORM);
        OkHttpClient client = new OkHttpClient();
        String data[] = new String[50];
        System.out.println("============= void SEND CALLED ===========");

        //        Ambil Value
        for (int i = 0; i < pertanyaan.length; i++){
            int id_induk = getResources().getIdentifier(pertanyaan[i], "id", getPackageName());
            if(id_induk > 0){
                LinearLayout induk = (LinearLayout) findViewById(id_induk);
                int child_count = induk.getChildCount();

                for (int child_index = 0; child_index < child_count; child_index++){
                   if(child_index ==1){
                        Spinner dropdown = (Spinner) induk.getChildAt(child_index);
                        String value = dropdown.getSelectedItem().toString();
                        builder.addFormDataPart(pertanyaan[i], value);
                        data[i] = value;
                    }
                }
            }
        }
        System.out.println("=============== SENDING DATA =============");
        for (int i = 0; i < data.length; i++)
            System.out.println(data[i]);

        RequestBody requestBody = builder.build();
        System.out.println("============  BODY =========");
        System.out.println(requestBody);
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();

        try {
            Response response = client.newCall(request).execute();
            // Do something with the response.
            System.out.println(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void buat_selectbox(){


        String label_pertanyaan[] = {
                "Merasa Sakit Kepala",
                "Merasa Malas Berbicara Tentang Skripsi",
                "Merasa letih ketika bangun pagi",
                "Aktifitas perkuliahan saya terasa sulit",
                "Melamun Saat Sendiri",
                "Merasa Lelah Saat Berfikir",
                "Merasa kesulitan untuk berfikir atau agak lemot untuk berfikir",
                "Kegiatan yang dilakukan terasa melelahkan",
                "Kehilangan semangat pada apapun" ,
                "saya merasa mual dan / muntah-muntah" ,
                "Berat badan saya bertambah atau berkurang " ,
                "Selera makan saya menurun" ,
                "Jantung saya berdebar-debar" ,
                "Tangan dan / kaki saya dingin saat membahas tugas akhir" ,
                "Saya mengeluarkan keringat dingin ketika mengerjakan skripsi" ,
                "Saya tidak dapat tidur / insomnia" ,
                "Respon tubuh saya menjadi lambat" ,
                "Saya terjaga saat malam hari" ,
                "Saya malas beraktifitas sepanjang hari" ,
                "Saya sering merasa cemas memikirkan hal tentang pengerjaan skripsi" ,
                "Gerakan otot saya gemetar dan / gelisah tanpa sadar saat mengerjakan skripsi" ,
                "Saya ceroboh" ,
                "Saya merasa mudah tersinggung" ,
                "Suasana hati saya (mood) berubah-ubah" ,
                "saya merasa depresi" ,
                "sikap saya agresif dan tidak normal" ,
                "saya kesulitan berkonsentrasi mengerjakan tugas kuliah" ,
                "kualitas tugas yang saya kerjakan menurun" ,
                "saya merasa mulut saya kering" ,
                "saya merasa perut tidak nyaman" ,
                "saya merasa khawatir bertemu dosen pembimbing skripsi" ,
                "saya merasa tidak berdaya atau frustasi" ,
                "saya kehilangan motivasi untuk belajar" ,
                "saya merasa bosan dengan kehidupan" ,
                "pkiran saya yang kacau atau kehilangan orientasi" ,
                "saya mudah marah" ,
                "saya merasa tidak mampu membuat keputusan" ,
                "saya merasa kesulitan berkonsentrasi saat mengikuti perkuliahan" ,
                "saya mudah panik" ,
                "saya sering menangis" ,
                "muncul pikiran saya untuk bunuh diri" ,
                "saya kehilangan orientasi waktu / sering tidak tepat waktu / salah jadwal" ,
                "saya mengalami priode kebingungan" ,
                "saya merasa tegang" ,
                "saya mudah lupa" ,
                "saya mengkonsumsi makanan atau minuman tertentu berlebihan" ,
                "saya kehilangan ketertarikan pada penampilan fisik" ,
                "wajah saya tampak murung" ,
                "saya berjalan mondar mandir" ,
                "saya melakukan penundaan yang kronis"
        };

        System.out.println(pertanyaan.length);
        System.out.println(label_pertanyaan.length);

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