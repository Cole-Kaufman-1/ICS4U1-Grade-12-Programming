public class NonConsumable extends Product {
    private String isbn;

    NonConsumable(String name, int cost, int upc, int weight, String isbn) {
        super(name, cost, upc, weight);
        this.isbn = isbn;
    }

    public String toString(){
        return "Name: " + getName() + " Cost " + getCost() + " " + getUPC() + "Weight " + getWeight() + " " + isbn;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}
