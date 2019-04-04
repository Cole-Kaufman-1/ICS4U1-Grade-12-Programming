public class Food extends Consumable {
    private boolean packaged;

    Food(String name, int cost, int upc, int weight, String expiryData, boolean packaged) {
        super(name, cost, upc, weight, expiryData);
        this.packaged = packaged;
    }

    public String toString(){
        return "Name: " + getName() + " Cost " + getCost() + " " + getUPC() + " Weight " + getWeight() + " Expiry " + getExpiryData() + " Packaging: " + packaged;

    }

    public boolean isPackaged() {
        return packaged;
    }

    public void setPackaged(boolean packaged) {
        this.packaged = packaged;
    }
}
