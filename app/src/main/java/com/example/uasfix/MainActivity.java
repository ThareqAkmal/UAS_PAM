package com.example.uasfix;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView txjmlh, txharga, txnama;
    EditText name;
    int jumlah, total,harga, inmoka, inkops, inamer, inkap;
    CheckBox ces, cpanas, cmoka, ckapucino, ckopsus, camerikano;
    String nama, statuses = "tidak", statuspanas = "tidak", statusmoka = "tidak",
            statuscino = "tidak",statuskops = "tidak", statusamerikano = "tidak";
    boolean ises, ispanas, ismoka, iskapu, iskopsus, isamerikano;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txjmlh = (TextView) findViewById(R.id.tvjmlh);
        txharga = (TextView) findViewById(R.id.tvharga);
        txnama = (TextView) findViewById(R.id.tvnama);
        name = (EditText) findViewById(R.id.EdNama);
        ces = (CheckBox) findViewById(R.id.cbes);
        cpanas = (CheckBox) findViewById(R.id.cbpan);
        cmoka = (CheckBox) findViewById(R.id.cbMocca);
        camerikano = (CheckBox) findViewById(R.id.cbAmericano);
        ckopsus = (CheckBox) findViewById(R.id.cbKopisus);
        ckapucino = (CheckBox) findViewById(R.id.cbCappucino);
        final Button btnrest = (Button) findViewById(R.id.reset);



        final Button OrdBton = (Button) findViewById(R.id.ordah);
        OrdBton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (name.length()<1) {
                    Toast.makeText(getApplicationContext(),"Lengkapi Data Terlebih dahulu", Toast.LENGTH_SHORT).show();
                }else if (jumlah == 0){
                    Toast.makeText(getApplicationContext(),"Lengkapi Data Terlebih dahulu", Toast.LENGTH_SHORT).show();
                }else {
                    OrdBton.setVisibility(View.GONE);
                    btnrest.setVisibility(View.VISIBLE);
                    minum();

                    total = jumlah *harga;
                    if(ismoka){
                        total += (jumlah * inmoka);
                    }
                    if(isamerikano){
                        total += (jumlah * inamer);
                    }
                    if(iskopsus){
                        total += (jumlah * inkops);
                    }
                    if(iskapu){
                        total += (jumlah * inkap);
                    }
                    Log.i("harga: ", "" + total);
                    nama = name.getText().toString();
                    txnama.setText("Nama: " + nama +
                            "\n" + statusamerikano +
                            "" + statuscino +
                            "" + statusmoka +
                            "" + statuskops);
                    txharga.setText("Harga : Rp" + total + "000");
                }
            }
        });


        btnrest.setVisibility(View.GONE);
        btnrest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                restart();
            }
        });
    }

//    public void suhu(){
//        if(ces.isChecked()){
//            ises =true;
//            statuses="ICE";
//        }else{
//            ises= false;
//            statuses ="";
//        }if(cpanas.isChecked()){
//            ispanas = true;
//            statuspanas="";
//        }else{
//            ispanas = false;
//            statuspanas="";
//        }
//    }



    public void minum(){
        if(cmoka.isChecked()){
            ismoka=true;
            inmoka = 1;

            if(ces.isChecked()){
                statusmoka="Mocca ICE";
                harga = 16;
            }if(cpanas.isChecked()){
                statusmoka="Mocca";
                harga = 14;
            }
        }else{
            ismoka=false;
            statusmoka="";
            inmoka = 0;
        }
        if(camerikano.isChecked()){
            isamerikano=true;

            inamer = 1;
            if(ces.isChecked()){
                statusamerikano="Americano ICE";
                harga = 10;
            }if(cpanas.isChecked()){
                statusamerikano="Americano";
                harga = 8;
            }
        }else{
            isamerikano=false;
            statusamerikano="";
            inamer = 0;
        }if(ckopsus.isChecked()){
            iskopsus=true;
            inkops = 1;
            if(ces.isChecked()){
                statuskops="Kopi Susu ICE";
                harga = 17;
            }if(cpanas.isChecked()){
                statuskops="Kopi Susu";
                harga = 14;
            }
        }else{
            iskopsus=false;
            statuskops="";
            inkops = 0;
        }if(ckapucino.isChecked()){
            iskapu=true;
            inkap = 1;
            if(ces.isChecked()){
                statuscino="Cappucino ICE";
                harga = 20;
            }if(cpanas.isChecked()){
                statuscino="Cappucino";
                harga = 18;
            }
        }else{
            iskapu=false;
            statuscino="";
            inkap = 0;
        }

    }

    public void tambah(View view){
        if (jumlah >= 10){
            Toast.makeText(getApplicationContext(),"Maks 10 Order per orang", Toast.LENGTH_SHORT).show();
        }else {
            jumlah = jumlah + 1;
            txjmlh.setText("" + jumlah);
        }
    }
    public void kurang(View view){
        if(jumlah <= 0){
            Toast.makeText(getApplicationContext(),"Telah Mencapai Batas", Toast.LENGTH_SHORT).show();
        }else {
            jumlah = jumlah - 1;
            txjmlh.setText("" + jumlah);
        }
    }

    

    public void restart(){
        Intent i = getBaseContext().getPackageManager().getLaunchIntentForPackage(getBaseContext().getPackageName());
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
        finish();
    }
}