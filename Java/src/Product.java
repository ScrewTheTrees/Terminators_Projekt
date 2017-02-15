/**
 * Created by Robin on 15/02/2017.
 */
public class Product {
    private int productNumber;
    private String namn;
    private float provision;
    private String description;

    public Product(int productNumber, String namn, float provision, String description){
        this.productNumber = productNumber;
        this.namn = namn;
        this.provision = provision;
        this.description = description;
    }

    public int getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(int productNumber) {
        this.productNumber = productNumber;
    }

    public String getNamn() {
        return namn;
    }

    public void setNamn(String namn) {
        this.namn = namn;
    }

    public float getProvision() {
        return provision;
    }

    public void setProvision(float provision) {
        this.provision = provision;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String toString(){
        return productNumber + ". " + namn + ": " + description;
    }
}
