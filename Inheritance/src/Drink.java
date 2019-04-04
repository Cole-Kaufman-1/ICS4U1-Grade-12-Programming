public class Drink extends Consumable {
    private int quantity;

    Drink(String name, int cost, int upc, int weight, String expiryData, int quantity) {
        super(name, cost, upc, weight, expiryData);
        this.quantity = quantity;
    }

    public String toString(){
        return "Name: " + getName() + " Cost " + getCost() + " " + getUPC() + " Weight " + getWeight() + " Expiry " + getExpiryData() + " Quantity: " + quantity;

    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
