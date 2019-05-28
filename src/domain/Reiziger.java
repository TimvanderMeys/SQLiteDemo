package domain;

import java.time.LocalDate;
import java.util.ArrayList;

public class Reiziger {
    private int id;
    private String voorletters;
    private String tussenvoegsel;
    private String achternaam;
    private LocalDate gbDatum;
    private ArrayList<OVchipkaart> ovChipKaarten = new ArrayList<>();

    public Reiziger() {}

    public Reiziger(String voorletters, String tussenvoegsel, String achternaam, LocalDate gbDatum) {
        this.voorletters = voorletters;
        this.tussenvoegsel = tussenvoegsel;
        this.achternaam = achternaam;
        this.gbDatum = gbDatum;
    }

    public Reiziger(int id, String voorletters, String tussenvoegsel, String achternaam, LocalDate gbDatum) {
        this(voorletters, tussenvoegsel, achternaam, gbDatum);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVoorletters() {
        return voorletters;
    }

    public void setVoorletters(String voorletters) {
        this.voorletters = voorletters;
    }

    public String getTussenvoegsel() {
        return tussenvoegsel;
    }

    public void setTussenvoegsel(String tussenvoegsel) {
        this.tussenvoegsel = tussenvoegsel;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    public LocalDate getGbDatum() {
        return gbDatum;
    }

    public void setGbDatum(LocalDate gbDatum) {
        this.gbDatum = gbDatum;
    }

    public ArrayList<OVchipkaart> getOvChipKaarten() {
        return ovChipKaarten;
    }

    public void setOvChipKaarten(ArrayList<OVchipkaart> ovChipKaarten) {
        this.ovChipKaarten = ovChipKaarten;
    }

    public void addOvChipKaarten(OVchipkaart oVchipkaart) { this.ovChipKaarten.add(oVchipkaart); }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Reiziger reiziger = (Reiziger) o;

        return id == reiziger.id;
    }

    @Override
    public String toString() {
        return "Reiziger{" +
                "id=" + id +
                ", voorletters='" + voorletters + '\'' +
                ", tussenvoegsel='" + tussenvoegsel + '\'' +
                ", achternaam='" + achternaam + '\'' +
                ", gbDatum=" + gbDatum +
                ", ovChipKaarten=" + ovChipKaarten +
                '}';
    }
}
