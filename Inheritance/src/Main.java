public class Main {
    public static void main(String[] args) {
    Book HungerGames = new Book("The Hunger Game", 20, 1234, 1, "1ew3423e", "Codey");
    VideoDVD PulpFiction = new VideoDVD("Pulp Fiction", 60, 12224, 1, "terw3423e", "Quntien Tarintino");
    CD ToPimpAButterfly = new CD("To Pimp A Butterfly", 15, 123445, 1, "uya3423e", "Kendrick Lamar");
    Drink Coffee = new Drink("Coffee", 2, 1235424, 1,  "03/05/2019", 250);
    Food Doritos = new Food("Doritios", 4, 1231234, 1, "09/12/2021", true);

    System.out.println(HungerGames.toString());
    System.out.println(PulpFiction.toString());
    System.out.println(ToPimpAButterfly.toString());
    System.out.println(Coffee.toString());
    System.out.println(Doritos.toString());
    }
}
