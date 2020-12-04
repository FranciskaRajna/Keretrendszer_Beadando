package uni.eszterhazy.keretrendszer.model;

import org.springframework.format.annotation.DateTimeFormat;
import uni.eszterhazy.keretrendszer.exceptions.CimNemLehetNull;
import uni.eszterhazy.keretrendszer.exceptions.RosszErtekeles;
import uni.eszterhazy.keretrendszer.exceptions.RosszMegjelenesDatum;

import java.time.LocalDate;
import java.util.UUID;


public class Film {
    private String id;
    private String cim;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate megjelenes;
    private Mufaj mufaj;
    private double ertekeles;

    public Film() {
        this.id= UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCim() {
        return cim;
    }

    public void setCim(String cim) throws CimNemLehetNull {
        if (cim.trim().length() <= 0){
            throw new CimNemLehetNull("Cím nem lehet null:"+cim);
        }

        this.cim = cim;
    }

    public LocalDate getMegjelenes() {
        return megjelenes;
    }

    public void setMegjelenes(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate megjelenes) throws RosszMegjelenesDatum {
        if (megjelenes.isAfter(LocalDate.now())){
            throw new RosszMegjelenesDatum("Még nem jelenhetett meg a film");
        }
        if (megjelenes.isBefore(LocalDate.ofYearDay(1917,1))){
            throw new RosszMegjelenesDatum("Ekkor még nem jelenhetett meg" + megjelenes);
        }
        this.megjelenes = megjelenes;
    }

    public Mufaj getMufaj() {
        return mufaj;
    }

    public void setMufaj(Mufaj mufaj) {
        this.mufaj = mufaj;
    }

    public double getErtekeles() {
        return ertekeles;
    }

    public void setErtekeles(double ertekeles) throws RosszErtekeles {
        if (ertekeles < 0 || ertekeles >5){
            throw new RosszErtekeles("Rossz értéket adott meg: "+ ertekeles +"Csak 0 és 5 közötti érték adható.");
        }
        this.ertekeles = ertekeles;
    }

    @Override
    public String toString() {
        return "Film{" +
                "id='" + id + '\'' +
                ", cim='" + cim + '\'' +
                ", megjelenes=" + megjelenes +
                ", mufaj=" + mufaj +
                ", ertekeles=" + ertekeles +
                '}';
    }
}
