public class VideoDVD extends NonConsumable {
    private String director;

    VideoDVD(String name, int cost, int upc, int weight, String isbn, String director) {
        super(name, cost, upc, weight, isbn);
        this.director = director;
    }

    public String toString(){
        return "Name: " + getName() + " Cost " + getCost() + " " + getUPC() + " Weight " + getWeight() + " " + getIsbn() + " Director " + director;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }
}
