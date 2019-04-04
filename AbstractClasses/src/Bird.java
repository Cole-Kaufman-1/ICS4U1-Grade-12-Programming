public class Bird extends Animal {

    public Bird(String name) {
        super(name);
    }

    public void makeNoise() {
        System.out.println("*chirp*");
    }

    public void fly() {
        System.out.println(name + " flies");
    }
}
