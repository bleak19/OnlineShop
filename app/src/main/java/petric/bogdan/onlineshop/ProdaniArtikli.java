package petric.bogdan.onlineshop;

public class ProdaniArtikli {
    private String cena;
    private String naziv;
    private String datum;


    public ProdaniArtikli(String cena, String naziv, String datum) {
        this.cena = cena;
        this.naziv = naziv;
        this.datum = datum;
    }

    public String getCena() {
        return cena;
    }

    public String getNaziv() {
        return naziv;
    }

    public String getDatum() {
        return datum;
    }

    public void setCena(String cena) {
        this.cena = cena;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }
}