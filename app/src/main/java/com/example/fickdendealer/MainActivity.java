package com.example.fickdendealer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.PrintStream;
import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    static int[] Karten;
    int[] rueckgängigspeicher;
    int rueckgaengigNummer=-1;
    Button Acht;
    Button Ass;
    Button Koenig;
    Button Neun;
    Button Ober;
    Button Sechs;
    Button Sieben;
    private int Treffercounter = 0;
    Button Unter;
    Button Zehn;
    boolean achtT = false;
    private int anzahl;
    private int anzahlAbHier;
    boolean assT = false;
    TextView ausgabe;
    double besteTrefferquote = 0.0d;
    private int counter;
    private double hoechstWahrscheinlichkeitdrunter;
    private double hoechsteWahrscheinlichkeit2;
    TextView ausgabeDrueber;
    TextView ausgabeDrunter;
    boolean koenigT = false;
    boolean neunT = false;
    boolean oberT = false;

    boolean sechsT = false;
    boolean siebenT = false;
    SharedPreferences sp;
    String statsString = "0;0;0";

    TextView trefferText;
    boolean unterT = false;
    boolean vorbei = false;
    boolean zehnT = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(1);


        this.ausgabeDrueber = findViewById(R.id.DrueberWahll);
        this.ausgabeDrunter = findViewById(R.id.DrunterWahl);
        this.ausgabe = findViewById(R.id.ersteWahl);
        this.Sechs = findViewById(R.id.sechs);
        this.Sechs.setOnClickListener(this);
        this.Sieben = findViewById(R.id.sieben);
        this.Sieben.setOnClickListener(this);
        this.Acht = findViewById(R.id.acht);
        this.Acht.setOnClickListener(this);
        this.Neun = findViewById(R.id.neun);
        this.Neun.setOnClickListener(this);
        this.Zehn = findViewById(R.id.zehn);
        this.Zehn.setOnClickListener(this);
        this.Unter = findViewById(R.id.unter);
        this.Unter.setOnClickListener(this);
        this.Ober = findViewById(R.id.ober);
        this.Ober.setOnClickListener(this);
        this.Koenig = findViewById(R.id.koenig);
        this.Koenig.setOnClickListener(this);
        this.Ass = findViewById(R.id.ass);
        this.Ass.setOnClickListener(this);
        this.trefferText = findViewById(R.id.DurschschnittsW);
        this.sp = getSharedPreferences("statistik", 0);
        Karten = new int[9];
       auffuellen();
        GesamtWahrscheinlichkeit();
        trefferText.setText("0%");
        rueckgängigspeicher=new int[36];
        //StatsLöschen();
    }
    public void auffuellen()
    {
        for (int i = 0; i < Karten.length; i++) {
            Karten[i] = 4;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflanter=getMenuInflater();
        inflanter.inflate(R.menu.menu,menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch(item.getItemId())
        {
            case R.id.neuesSpiel:
                zurueckSetzen();
                break;
            case R.id.Statistik:
               statistikWechsel();
                break;
            case R.id.raus:
                Karten[0]=0;
                Sechs.setText("6(0)");
                GesamtWahrscheinlichkeit();
                break;
            case R.id.rueckgaengig:
                rueckgaengig();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void statistikWechsel() {
        Intent i=new Intent(MainActivity.this,stats.class);
        String uebergabe=sp.getString("statistik","0;0;0;0");
        i.putExtra("statistikString",uebergabe);
        startActivity(i);
    }

    public double getBesteTrefferquote()
    {
       String zwischenstring= sp.getString("statistik","0;0;0;0");
       String[] split=zwischenstring.split(";");
       besteTrefferquote=Double.parseDouble(split[2]);
       return besteTrefferquote;

    }

    @Override
    public void onClick(View v) {


        switch (v.getId()) {
            case R.id.sechs:
                if(Karten[0]!=0)
                {
                    rueckgaengigNummer++;
                    rueckgängigspeicher[rueckgaengigNummer]=0;
                    Karten[0]--;
                    Sechs.setText("6("+Karten[0]+")");

                    if(sechsT==true)
                    {
                        counter++;
                        Treffercounter++;
                    }
                    else
                    {
                        counter++;
                    }
                    m1trefferWZurcksetzen();
                    GesamtWahrscheinlichkeit();
                }


                break;
            case R.id.sieben:
                if(Karten[1]!=0)
                {
                    rueckgaengigNummer++;
                    rueckgängigspeicher[rueckgaengigNummer]=1;
                    Karten[1]--;
                    Sieben.setText("7("+Karten[1]+")");

                    if(siebenT==true)
                    {
                        counter++;
                        Treffercounter++;
                    }
                    else
                    {
                        counter++;
                    }
                    m1trefferWZurcksetzen();
                    GesamtWahrscheinlichkeit();
                }
                break;
            case R.id.acht:
                if(Karten[2]!=0)
                {
                    rueckgaengigNummer++;
                    rueckgängigspeicher[rueckgaengigNummer]=2;
                    Karten[2]--;
                    Acht.setText("8("+Karten[2]+")");

                    if(achtT==true)
                    {
                        counter++;
                        Treffercounter++;
                    }
                    else
                    {
                        counter++;
                    }
                    m1trefferWZurcksetzen();
                    GesamtWahrscheinlichkeit();
                }
                break;
            case R.id.neun:
                if(Karten[3]!=0)
                {
                    rueckgaengigNummer++;
                    rueckgängigspeicher[rueckgaengigNummer]=3;
                    Karten[3]--;
                    Neun.setText("9("+Karten[3]+")");

                    if(neunT==true)
                    {
                        counter++;
                        Treffercounter++;
                    }
                    else
                    {
                        counter++;
                    }
                    m1trefferWZurcksetzen();
                    GesamtWahrscheinlichkeit();
                }
                break;
            case R.id.zehn:
                if(Karten[4]!=0)
                {
                    rueckgaengigNummer++;
                    rueckgängigspeicher[rueckgaengigNummer]=4;
                    Karten[4]--;
                    Zehn.setText("10("+Karten[4]+")");

                    if(zehnT==true)
                    {
                        counter++;
                        Treffercounter++;
                    }
                    else
                    {
                        counter++;
                    }
                    m1trefferWZurcksetzen();
                    GesamtWahrscheinlichkeit();
                }
                break;
            case R.id.unter:
                if(Karten[5]!=0)
                {
                    rueckgaengigNummer++;
                    rueckgängigspeicher[rueckgaengigNummer]=5;
                    Karten[5]--;
                    Unter.setText("U("+Karten[5]+")");

                    if(unterT==true)
                    {
                        counter++;
                        Treffercounter++;
                    }
                    else
                    {
                        counter++;
                    }
                    m1trefferWZurcksetzen();
                    GesamtWahrscheinlichkeit();
                }
                break;
            case R.id.ober:
                if(Karten[6]!=0)
                {
                    rueckgaengigNummer++;
                    rueckgängigspeicher[rueckgaengigNummer]=6;
                    Karten[6]--;
                    Ober.setText("O("+Karten[6]+")");

                    if(oberT==true)
                    {
                        counter++;
                        Treffercounter++;
                    }
                    else
                    {
                        counter++;
                    }
                    m1trefferWZurcksetzen();
                    GesamtWahrscheinlichkeit();
                }
                break;
            case R.id.koenig:
                if(Karten[7]!=0)
                {
                    rueckgaengigNummer++;
                    rueckgängigspeicher[rueckgaengigNummer]=7;
                    Karten[7]--;
                    Koenig.setText("K("+Karten[7]+")");

                    if(koenigT==true)
                    {
                        counter++;
                        Treffercounter++;
                    }
                    else
                    {
                        counter++;
                    }
                    m1trefferWZurcksetzen();
                    GesamtWahrscheinlichkeit();
                }
                break;
            case R.id.ass:
                if(Karten[8]!=0)
                {
                    rueckgaengigNummer++;
                    rueckgängigspeicher[rueckgaengigNummer]=8;
                    Karten[8]--;
                    Ass.setText("A("+Karten[8]+")");
                    if(assT==true)
                    {
                        counter++;
                        Treffercounter++;
                    }
                    else
                    {
                        counter++;
                    }
                    m1trefferWZurcksetzen();
                    GesamtWahrscheinlichkeit();
                }
                break;



        }
        double trefferquote=1.0*Treffercounter/counter;
        trefferquote=Math.round(trefferquote*10000)/100.0;
        trefferText.setText(String.valueOf(trefferquote)+"%");
        if(anzahlGeben()==0 && vorbei==false)
        {
            vorbei=true;


            double Trefferquote=1.0*Treffercounter/counter;
            if(getBesteTrefferquote()<Trefferquote)
            {
                besteTrefferquote=Trefferquote;
            }
            speichern();

        }



    }
    public void rueckgaengig()
    {

        if(rueckgaengigNummer>=0) {
            int kartennummer = rueckgängigspeicher[rueckgaengigNummer];


            if (Karten[kartennummer] != 4) {
                Karten[kartennummer]++;
                GesamtWahrscheinlichkeit();
                switch (rueckgängigspeicher[rueckgaengigNummer]) {
                    case 0:
                        Sechs.setText("6(" + Karten[0] + ")");
                        if (sechsT == true) {
                            Treffercounter--;
                        }
                        counter--;
                        break;
                    case 1:
                        Sieben.setText("7(" + Karten[1] + ")");
                        if (siebenT == true) {
                            Treffercounter--;
                        }
                        counter--;
                        break;
                    case 2:
                        Acht.setText("8(" + Karten[2] + ")");

                        if (achtT == true) {
                            Treffercounter--;
                        }
                        counter--;
                        break;
                    case 3:
                        Neun.setText("9(" + Karten[3] + ")");

                        if (neunT == true) {
                            Treffercounter--;
                        }
                        counter--;
                        break;
                    case 4:
                        Zehn.setText("10(" + Karten[4] + ")");

                        if (zehnT == true) {
                            Treffercounter--;
                        }
                        counter--;
                        break;
                    case 5:
                        Unter.setText("U(" + Karten[5] + ")");

                        if (unterT == true) {
                            Treffercounter--;
                        }
                        counter--;
                        break;
                    case 6:
                        Ober.setText("O(" + Karten[6] + ")");

                        if (oberT == true) {
                            Treffercounter--;
                        }
                        counter--;
                        break;
                    case 7:
                        Koenig.setText("K(" + Karten[7] + ")");

                        if (koenigT == true) {
                            Treffercounter--;
                        }
                        counter--;
                        break;
                    case 8:
                        Ass.setText("A(" + Karten[8] + ")");

                        if (assT == true) {
                            Treffercounter--;
                        }
                        counter--;
                        break;
                }
                rueckgaengigNummer--;

            }
        }


        double dings=0;
        if(counter!=0) {
            dings = 1.0 * Treffercounter / counter;
        }
        dings=Math.round(dings*100);
        trefferText.setText(String.valueOf(dings)+"%");
    }

    public double hoechsteWahrscheinlichkeitDrueber(int Kartennummer) {
    double hoechsteW=0;
        int ergebnis = 0;
        for (int i = Kartennummer + 1; i < Karten.length; i++) {
            int aktuelleKarten = Karten[i];
            if (aktuelleKarten > Karten[ergebnis]) {
                ergebnis = i;
            }
        }
        if(Kartennummer!=8) {
            hoechsteW = 1.0 * Karten[ergebnis] / anzahlAbHierGeben(Kartennummer);
        }
        return hoechsteW;
    }

    public double hoechsteWahrscheinlichkeitdrunter(int Kartennummer) {
        double hoechsteW=0;
        int ergebnis = 0;
        for (int i = 0; i < Kartennummer; i++) {
            int aktuelleKarten = Karten[i];
            if (aktuelleKarten > Karten[ergebnis]) {

                ergebnis = i;
            }
        }
        if(Kartennummer!=0) {

             hoechsteW = 1.0 * Karten[ergebnis] / anzahlDrunterGeben(Kartennummer);
        }
        return hoechsteW;
    }

    public double hoechstwahrscheinlichkeitKarte(int Kartennummer) {
        double w =WahrscheinlichkeitersteKarte(Kartennummer)+ WahrscheinlichkeitDrunter(Kartennummer) * hoechsteWahrscheinlichkeitdrunter(Kartennummer) + WahrscheinlichkeitDrueber(Kartennummer) * hoechsteWahrscheinlichkeitDrueber(Kartennummer);

        return w;
    }

    public void GesamtWahrscheinlichkeit()
    {
        m1trefferWZurcksetzen();
        int zweiteW = 0;
        double fertigW = 0;
        String Karte = "Ende";
        for (int i = 0; i < Karten.length; i++)
        {
            double zwischenergebnis = hoechstwahrscheinlichkeitKarte(i);
            if (zwischenergebnis > fertigW) {
                zweiteW = i;
                fertigW = zwischenergebnis;
                Karte = umwandeln(i);
            }
        }
        trueOrFalse(zweiteW);
        hoechstedrunterKarteGeben(zweiteW);
        hoechstedrueberKarteGeben(zweiteW);
        fertigW=Math.round(fertigW*10000)/100.0;
        ausgabe.setText("  "+Karte+": "+fertigW+"%");


    }


    public void hoechstedrueberKarteGeben(int Kartennummer)
    {
        double Wahrscheinlichkeit=0;
        int ergebnis = 0;
        int iergebnis=0;
        for (int i = Kartennummer + 1; i < Karten.length; i++) {
            int aktuelleKarten = Karten[i];
            if (aktuelleKarten > ergebnis) {
                ergebnis = Karten[i];
                iergebnis=i;

            }
        }
        trueOrFalse(iergebnis);
        Wahrscheinlichkeit=hoechsteWahrscheinlichkeitDrueber(Kartennummer);
        Wahrscheinlichkeit=Math.round(Wahrscheinlichkeit*10000)/100.0;

        ausgabeDrueber.setText("↑ "+umwandeln(iergebnis)+": "+Wahrscheinlichkeit+"%");

    }
    public void hoechstedrunterKarteGeben(int Kartennummer)
    {
        double hoechsteW=0;
        int ergebnis = 0;
        int iergebnis=0;
        for (int i = 0; i < Kartennummer; i++) {
            int aktuelleKarten = Karten[i];
            if (aktuelleKarten >ergebnis) {

                ergebnis = Karten[i];
                iergebnis=i;
            }
        }
        trueOrFalse(iergebnis);
        hoechsteW=hoechsteWahrscheinlichkeitdrunter(Kartennummer);
        hoechsteW=Math.round(hoechsteW*10000)/100.0;

       ausgabeDrunter.setText("↓ "+umwandeln(iergebnis)+": "+hoechsteW+"%");
    }
    public double WahrscheinlichkeitDrunter(int Kartennummer)
    {
        anzahl=anzahlGeben();
        double anzahlDrunter=anzahlDrunterGeben(Kartennummer);
        double ergebnis=1.0*anzahlDrunter/anzahl;
        return ergebnis;
    }
    public double WahrscheinlichkeitDrueber(int Kartennummer)
    {
        anzahl=anzahlGeben();
       double anzahlDrueber=anzahlAbHierGeben(Kartennummer);
        double ergebnis=1.0*anzahlDrueber/anzahl;
        return ergebnis;
    }

    private double WahrscheinlichkeitDrunterKarte(int kartennummer) {
        double ergebnis=0;
        int anzahldrunter=anzahlDrunterGeben(kartennummer);
        int Kartenanzahl=Karten[kartennummer];
        if(anzahldrunter!=0) {
            ergebnis = 1.0 * Kartenanzahl / anzahldrunter;
        }

        return ergebnis;
    }

    public void trueOrFalse(int Kartennummer) {
        if (Kartennummer == 0) {
            this.sechsT = true;
        }
        if (Kartennummer == 1) {
            this.siebenT = true;
        }
        if (Kartennummer == 2) {
            this.achtT = true;
        }
        if (Kartennummer == 3) {
            this.neunT = true;
        }
        if (Kartennummer == 4) {
            this.zehnT = true;
        }
        if (Kartennummer == 5) {
            this.unterT = true;
        }
        if (Kartennummer == 6) {
            this.oberT = true;
        }
        if (Kartennummer == 7) {
            this.koenigT = true;
        }
        if (Kartennummer == 8) {
            this.assT = true;
        }
    }
    public int anzahlGeben()
    {
        anzahl=0;
        for(int i=0;i<Karten.length;i++)
        {
            anzahl+=Karten[i];
        }

        return anzahl;
    }

    public double WahrscheinlichkeitersteKarte(int Kartennummer) {

        int anzahlkarte=Karten[Kartennummer];
        double hoechsteWahrscheinlichkeit1 = 1.0*anzahlkarte / anzahlGeben();

        return hoechsteWahrscheinlichkeit1;
    }

    public double WahrscheinlichkeitDrueberKarte(int Kartennummer) {
        double ergebnis=0;
        anzahlAbHier=anzahlAbHierGeben(Kartennummer);
        int Kartenanzahl=Karten[Kartennummer];
        if(anzahlAbHier!=0)
        {
             ergebnis = 1.0 * Kartenanzahl / anzahlAbHier;
        }

        return ergebnis;
    }

    private int anzahlAbHierGeben(int Kartennummer) {
        anzahlAbHier =0;
        for(int i=Kartennummer+1;i<Karten.length;i++)
        {
            anzahlAbHier+=Karten[i];
        }

        return anzahlAbHier;
    }
    public int anzahlDrunterGeben(int Kartennummer)
    {
        int anzahlDrunter=0;
        for(int i=0;i<Kartennummer;i++)
        {
            anzahlDrunter+=Karten[i];
        }

        return anzahlDrunter;


    }






    public void speichern() {

        String[] split = this.sp.getString("statistik", "0;0;0;0").split(";");
        int gesamtTreffer = Integer.parseInt(split[0]) + this.Treffercounter;
        int gesamtVersuche = Integer.parseInt(split[1]) + this.counter;
        int Spiele=Integer.parseInt(split[3]);
        Spiele++;
        statsString=String.valueOf(gesamtTreffer)+";"+gesamtVersuche+";"+besteTrefferquote+";"+Spiele;
        SharedPreferences.Editor editor = this.sp.edit();
        editor.putString("statistik", this.statsString);
        editor.commit();
    }
    public void Toastmaker(String string)
    {
        Toast.makeText(this,string,Toast.LENGTH_LONG).show();
    }
    public String umwandeln(int Kartennummer) {
        if (Kartennummer == 0) {
            return "sechs";
        }
        if (Kartennummer == 1) {
            return "sieben";
        }
        if (Kartennummer == 2) {
            return "acht";
        }
        if (Kartennummer == 3) {
            return "neun";
        }
        if (Kartennummer == 4) {
            return "zehn";
        }
        if (Kartennummer == 5) {
            return "Unter";
        }
        if (Kartennummer == 6) {
            return "Ober";
        }
        if (Kartennummer == 7) {
            return "Koenig";
        }
        if (Kartennummer == 8) {
            return "Ass";
        }
        return "fehler";
    }
    public void m1trefferWZurcksetzen() {
        this.sechsT = false;
        this.siebenT = false;
        this.achtT = false;
        this.neunT = false;
        this.zehnT = false;
        this.unterT = false;
        this.oberT = false;
        this.koenigT = false;
        this.assT = false;
    }
    public void zurueckSetzen()
    {
        auffuellen();
        rueckgaengigNummer=-1;
        for(int i=0;i<rueckgängigspeicher.length;i++)
        {
            rueckgängigspeicher[i]=0;
        }
        this.Sechs.setText("6(4)");
        this.Sieben.setText("7(4)");
        this.Acht.setText("8(4)");
        this.Neun.setText("9(4)");
        this.Zehn.setText("10(4)");
        this.Unter.setText("U(4)");
        this.Ober.setText("O(4)");
        this.Koenig.setText("K(4)");
        this.Ass.setText("A(4)");
        GesamtWahrscheinlichkeit();
        this.Treffercounter = 0;
        this.trefferText.setText("0%");
        this.counter = 0;
        this.vorbei = false;
    }
    public void StatsLöschen()
    {
        SharedPreferences.Editor editor=sp.edit();
        editor.putString("statistik","0;0;0;0");
        editor.commit();
    }
}