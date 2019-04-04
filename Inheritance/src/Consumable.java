public class Consumable extends Product {
    private String expiryData;

    Consumable(String name, int cost, int upc, int weight, String expiryData) {
        super(name, cost, upc, weight);
        this.expiryData = expiryData;
    }

    public String toString(){
        return "Name: " + getName() + " Cost " + getCost() + " " + getUPC() + "Weight " + getWeight() + "Expiry " + expiryData;
    }

    public String getExpiryData() {
        return expiryData;
    }

    public void setExpiryData() {
        this.expiryData = expiryData;
    }
}
