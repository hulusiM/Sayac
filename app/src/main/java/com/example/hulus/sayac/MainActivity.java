package com.example.hulus.sayac;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int Sayac,SayacArttir = 0;
    String Baslik;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button btnEkle = (Button)findViewById(R.id.btnSayacBilgiAl);
        final Button btnArttir = (Button)findViewById(R.id.btnSayacArttir);
        final Button btnSifirla = (Button)findViewById(R.id.btnSifirla);
        final EditText BaslikAl = (EditText)findViewById(R.id.editTxtBaslik);
        final EditText SayacNoAl = (EditText)findViewById(R.id.editTxtNo);
        final TextView txtVeriYaz = (TextView)findViewById(R.id.txtVeriYaz);


        btnArttir.setEnabled(false);
        btnSifirla.setEnabled(false);

        final MediaPlayer buttonClickSound = MediaPlayer.create(this,R.raw.buttonclick);

        btnEkle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String SayacBosmu = SayacNoAl.getText().toString();
                if (SayacBosmu.trim().equals("")){
                    SayacNoAl.setError("Bu Alan Boş Geçilemez!");
                    return;
                }

                Baslik = String.valueOf(BaslikAl.getText().toString());
                if(Baslik.trim().equals("")){
                    btnArttir.setText("Sayaç");
                }else{
                    btnArttir.setText(Baslik);
                }

                Sayac = Integer.valueOf(SayacNoAl.getText().toString());
                btnArttir.setEnabled(true);
                btnSifirla.setEnabled(true);
                txtVeriYaz.setText(String.valueOf(SayacArttir));
                BaslikAl.setText("");
                SayacNoAl.setText("");
            }
        });
        btnArttir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                buttonClickSound.start();

                    SayacArttir++;
                    txtVeriYaz.setText(String.valueOf(SayacArttir));
                    if(SayacArttir == Sayac){
                        btnArttir.setEnabled(false);
                        btnArttir.setText("Sayaç Bitti");
                        btnArttir.setEnabled(false);
                        Message("Verilen " + Sayac + " sayısı bitti.");
                        SayacArttir = 0;
                        Sayac = 0;
                    }
            }
        });

        btnSifirla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sayac = 0;
                Message("Sayaç Sıfırlandı !");
                btnArttir.setEnabled(false);
                btnSifirla.setEnabled(false);
                txtVeriYaz.setText("");
                SayacArttir = 0;

            }
        });
    }

    public void Message(String Mesaj){
        Toast.makeText(MainActivity.this,Mesaj,Toast.LENGTH_SHORT).show();
    }
}
