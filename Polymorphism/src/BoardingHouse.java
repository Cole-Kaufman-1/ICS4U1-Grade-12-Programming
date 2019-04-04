public class BoardingHouse {
    private Animal[] kennels = new Animal[8];

    public void dropOff(String name, int animalType) {
        for (int i = 0;i < kennels.length; i++) {
            if (kennels[i] == null) {
                if (animalType == 1) {
                    Dog d1 = new Dog(name);
                    kennels[i] = d1;
                    break;
                }
                else if (animalType == 2) {
                    Cat c1 = new Cat(name);
                    kennels[i] = c1;
                    break;
                }
                else if (animalType == 3) {
                    Bird b1 = new Bird(name);
                    kennels[i] = b1;
                    break;
                }
            }
        }
    }

    public void pickUp(String name) {
        for (int i = 0;i < kennels.length;i++) {
            if (kennels[i] != null && kennels[i].name.equals(name)) {
                kennels[i].makeNoise();

                if (kennels[i] instanceof Dog) {
                    ((Dog)kennels[i]).wagtail();
                }
                else if (kennels[i] instanceof Cat) {
                    ((Cat)kennels[i]).purr();
                }
                else if (kennels[i] instanceof Bird) {
                    ((Bird)kennels[i]).fly();
                }
            }
        }
    }
}
