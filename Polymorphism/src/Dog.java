public class Dog extends Animal {

    public Dog(String name) {
        super(name);
    }

    public void makeNoise() {
        System.out.println("*bark*");
    }

    public void wagtail() {
        System.out.println(name + " wags their tail.");
    }
}
