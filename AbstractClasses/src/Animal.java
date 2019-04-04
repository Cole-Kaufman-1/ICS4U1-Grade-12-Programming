abstract public class Animal implements Comparable{
    String name;

    public Animal(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

   abstract public void makeNoise();

    public int compareTo(Object o) {
        return this.getName().compareTo(((Animal)o).getName());
    }
}
