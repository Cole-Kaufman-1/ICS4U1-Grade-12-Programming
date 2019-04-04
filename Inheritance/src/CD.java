public class CD extends NonConsumable {
    private String artist;

    CD(String name, int cost, int upc, int weight, String isbn, String artist) {
        super(name, cost, upc, weight, isbn);
        this.artist = artist;
    }
    public String toString(){
        return "Name: " + getName() + " Cost " + getCost() + " " + getUPC() + " Weight " + getWeight() + " " + getIsbn() + " Artist: " + artist;
    }

    public String getString() {
        return artist;
    }

    public void setString(String artist) {
        this.artist = artist;
    }
}
