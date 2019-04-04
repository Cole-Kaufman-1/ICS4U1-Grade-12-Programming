public class Person {
    String name;
    String street;
    String postCode;
    String phoneNum;
    String email;

    Person(String name, String street, String postCode, String phoneNum, String email) {
        this.name = name;
        this.street = street;
        this.postCode = postCode;
        this.phoneNum = phoneNum;
        this.email = email;
    }

    public String toString() {
        return this.name + " lives at " + this.street + " " + this.postCode + " . Their phone number and email are: " + this.phoneNum + " " + this.email;
    }

    public String getLastName() {
        for (int i = 0; i < name.length(); i++) {
            if (name.substring(i, i+1).equals(" ")) {
                return name.substring(i+1);
            }
        }
        return name;
    }
}

