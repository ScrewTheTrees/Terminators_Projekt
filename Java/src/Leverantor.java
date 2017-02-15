/**
 * Created by Robin on 10/02/2017.
 */
public class Leverantor {
    private int id;
    private String namn, epost, telefonnummer;

    public Leverantor(int id, String namn, String epost, String telefonnummer) {
        this.id = id;
        this.namn = namn;
        this.epost = epost;
        this.telefonnummer = telefonnummer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNamn() {
        return namn;
    }

    public void setNamn(String namn) {
        this.namn = namn;
    }

    public String getEpost() {
        return epost;
    }

    public void setEpost(String epost) {
        this.epost = epost;
    }

    public String getTelefonnummer() {
        return telefonnummer;
    }

    public void setTelefonnummer(String telefonnummer) {
        this.telefonnummer = telefonnummer;
    }

    @Override
    public String toString() {
        return "Leverantor{" +
                "id=" + id +
                ", namn='" + namn + '\'' +
                ", epost='" + epost + '\'' +
                ", telefonnummer='" + telefonnummer + '\'' +
                '}';
    }
}
