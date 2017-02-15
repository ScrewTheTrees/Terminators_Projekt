/**
 * Created by Robin on 10/02/2017.
 */
public class Kund {
    private int kundnummer;
    private String fornamn, efternamn, gata, ort, epost;

    public Kund(int kundnummer, String fornamn, String efternamn, String gata, String ort, String epost) {
        this.kundnummer = kundnummer;
        this.fornamn = fornamn;
        this.efternamn = efternamn;
        this.gata = gata;
        this.ort = ort;
        this.epost = epost;
    }

    public int getKundnummer() {

        return kundnummer;
    }

    public void setKundnummer(int kundnummer) {
        this.kundnummer = kundnummer;
    }

    public String getFornamn() {
        return fornamn;
    }

    public void setFornamn(String fornamn) {
        this.fornamn = fornamn;
    }

    public String getEfternamn() {
        return efternamn;
    }

    public void setEfternamn(String efternamn) {
        this.efternamn = efternamn;
    }

    public String getGata() {
        return gata;
    }

    public void setGata(String gata) {
        this.gata = gata;
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    public String getEpost() {
        return epost;
    }

    public void setEpost(String epost) {
        this.epost = epost;
    }

    @Override
    public String toString() {
        return "Kund{" +
                "kundnummer=" + kundnummer +
                ", fornamn='" + fornamn + '\'' +
                ", efternamn='" + efternamn + '\'' +
                ", gata='" + gata + '\'' +
                ", ort='" + ort + '\'' +
                ", epost='" + epost + '\'' +
                '}';
    }
}
