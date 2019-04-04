public class Book extends NonConsumable {
    private String author;

    Book(String name, int cost, int upc, int weight, String isbn, String author) {
        super(name, cost, upc, weight, isbn);
        this.author = author;
    }

    public String toString(){
        return "Name: " + getName() + " Cost " + getCost() + " " + getUPC() + " " + "Weight " + getWeight() + " Author " + author;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
