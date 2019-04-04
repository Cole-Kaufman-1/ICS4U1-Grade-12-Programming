public class Product {
    private String name;
    private int cost, upc, weight;

    Product(String name, int cost, int upc, int weight) {
        this.name = name;
        this.cost = cost;
        this.upc = upc;
        this.weight = weight;
    }

    public String toString(){
        return "Name: " + name + " Cost " + cost + " " + upc + "Weight " + weight;
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    public int getUPC() {
        return upc;
    }

    public int getWeight() {
        return weight;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setUPC(int upc) {
        this.upc = upc;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
