package com.example.fickdendealer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.function.DoubleUnaryOperator;

public class stats extends AppCompatActivity {

    TextView Spiele,Treffer,Versuche,Durchschnitt,HighScore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_stats);
        Spiele=findViewById(R.id.spieleNummer);
        Treffer=findViewById(R.id.TrefferNummer);
        Versuche=findViewById(R.id.VersucheNummer);
        Durchschnitt=findViewById(R.id.QuotenNummer);
        HighScore=findViewById(R.id.hoechsteQuoteNummer);
        String statsString=this.getIntent().getExtras().getString("statistikString");
        String[] split=statsString.split(";");
        int treffer=Integer.parseInt(split[0]);
        int versuche=Integer.parseInt(split[1]);
        double hoechsteWahrscheinlichkeit=Double.parseDouble(split[2]);
        hoechsteWahrscheinlichkeit=Math.round(hoechsteWahrscheinlichkeit*10000)/100.0;
        double durchschnittsWahrscheinlichkeit=(1.0*treffer/versuche);
        durchschnittsWahrscheinlichkeit=Math.round(durchschnittsWahrscheinlichkeit*10000)/100.0;
        int spiele=Integer.parseInt(split[3]);
        Spiele.setText(String.valueOf(spiele));
        Treffer.setText(String.valueOf(treffer));
        Versuche.setText(String.valueOf(versuche));
        Durchschnitt.setText(String.valueOf(durchschnittsWahrscheinlichkeit)+"%");
        HighScore.setText(String.valueOf(hoechsteWahrscheinlichkeit)+"%");

    }
}