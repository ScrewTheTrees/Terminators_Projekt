/**
 * Created by Robin on 15/02/2017.
 */
public class Customer {
    private int kNR;
    private String fornamn;
    private String efternamn;
    private String gata;
    private String ort;
    private String epost;

    public Customer(int kNR, String fornamn, String efternamn, String gata, String ort, String epost) {
        this.kNR = kNR;
        this.fornamn = fornamn;
        this.efternamn = efternamn;
        this.gata = gata;
        this.ort = ort;
        this.epost = epost;
    }

    public int getkNR() {
        return kNR;
    }

    public void setkNR(int kNR) {
        this.kNR = kNR;
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

}
