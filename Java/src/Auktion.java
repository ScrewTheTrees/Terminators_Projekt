/**
 * Created by Robin on 10/02/2017.
 */
public class Auktion {
    private int aId;
    private String startDatum, slutDatum;

    public Auktion(int aId, String startDatum, String slutDatum) {
        this.aId = aId;
        this.startDatum = startDatum;
        this.slutDatum = slutDatum;
    }

    public int getaId() {
        return aId;
    }

    public void setaId(int aId) {
        this.aId = aId;
    }

    public String getStartDatum() {
        return startDatum;
    }

    public void setStartDatum(String startDatum) {
        this.startDatum = startDatum;
    }

    public String getSlutDatum() {
        return slutDatum;
    }

    public void setSlutDatum(String slutDatum) {
        this.slutDatum = slutDatum;
    }
}
