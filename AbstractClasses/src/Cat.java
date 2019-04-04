public class Cat extends Animal {

    public Cat(String name) {
        super(name);
    }

    public void makeNoise() {
        System.out.println("*meow*");
    }

    public void purr() {
        System.out.println(name + " purrs.");
    }
}
