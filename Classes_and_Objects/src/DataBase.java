public class DataBase {
    Person[] database = new Person[10];

    public void addPerson(Person person) {
       for (int i = 0;i < database.length;i++){
           if (database[i] == null) {
               database[i] = person;
               break;
           }
       }
    }

    public void removePerson(String name) {
        for (int i = 0;i < database.length;i++) {
            if(database[i].equals(null)) {
                System.out.println("Database is empty");
                break;
            }
            else if (i == database.length) {
                System.out.println("No matching person or no one left");
                break;
            }
            else if (database[i].name.equals(name)) {
                database[i] = null;
                break;

            }
        }
    }

    public void List() {
        for (int i = 0;i < database.length;i++) {
            if (database[i] == null) {
                System.out.println("No persons left");
                break;
            }
            System.out.println(database[i].toString());
        }
    }

    public void sortListLastNames() {

    }
}
