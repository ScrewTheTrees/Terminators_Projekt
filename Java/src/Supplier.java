/**
 * Created by Robin on 15/02/2017.
 */
public class Supplier {
    private int supplierId;
    private String namn;
    private String epost;
    private String telefonnummer;

    public Supplier(int supplierId, String namn, String epost, String telefonnummer) {
        this.supplierId = supplierId;
        this.namn = namn;
        this.epost = epost;
        this.telefonnummer = telefonnummer;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
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
        return supplierId + ". " + namn;
    }
}
