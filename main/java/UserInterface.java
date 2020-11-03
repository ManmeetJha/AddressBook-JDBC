
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserInterface {

    public static List<Address> addressbook;

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        addressbook = new ArrayList<Address>();
        int choice = 0;
        do {
            System.out.println("Select option:");
            System.out.println("1. Add contact");
            System.out.println("2. Edit contact");
            System.out.println("3. Delete contact");
            System.out.println("4. Exit");

            choice = input.nextInt();
            switch (choice) {
                case 1:
                    insertIntoAddressBook(input);
                    break;
                case 2:
                    editAddress(input);
                    break;
                case 3:
                    deleteContact(input);
                    break;
                default:
                    break;
            }
        } while (choice != 4);
    }

    public static void insertIntoAddressBook(Scanner input) {
        System.out.println("Enter user details to update");
        System.out.println("Enter first name:");
        String first_name = input.next();
        System.out.println("Enter last name:");
        String last_name = input.next();
        System.out.println("Enter address");
        String address = input.next();
        System.out.println("Enter city:");
        String city = input.next();
        System.out.println("Enter state:");
        String state = input.next();
        System.out.println("Enter zip:");
        String zip = input.next();
        System.out.println("Enter phone no:");
        String phone_no = input.next();
        System.out.println("Enter email:");
        String email = input.next();

        Address newAddress = new Address(first_name, last_name, address, city, state, zip, phone_no, email);
        addressbook.add(newAddress);
        System.out.println("Address added successfully");
        printAddressBook();
    }

    public static void editAddress(Scanner input) {
        System.out.println("Enter user details");
        System.out.println("Enter first name:");
        String first_name = input.next();
        System.out.println("Enter last name:");
        String last_name = input.next();

        System.out.println("Enter new address");
        String newAddress = input.next();
        System.out.println("Enter new city:");
        String newCity = input.next();
        System.out.println("Enter new state:");
        String newState = input.next();
        System.out.println("Enter new zip:");
        String newZip = input.next();

        boolean userFound = false;
        for (int i = 0; i < addressbook.size(); i++) {
            Address currentAddress = addressbook.get(i);
            if (currentAddress.getFirst_name().equals(first_name) && currentAddress.getLast_name().equals(last_name)) {
                currentAddress.setAddress(newAddress);
                currentAddress.setCity(newCity);
                currentAddress.setState(newState);
                currentAddress.setZip(newZip);
                userFound = true;
                break;
            }
        }
        if (userFound == false)
            System.out.println("User Not Found");
        else
            System.out.println("Address updated for " + first_name + " " + last_name);
        printAddressBook();
    }

    public static void deleteContact(Scanner input) {
        System.out.println("Enter user details to delete");
        System.out.println("Enter first name:");
        String first_name = input.next();
        System.out.println("Enter last name:");
        String last_name = input.next();

        boolean userFound = false;
        for (int i = 0; i < addressbook.size(); i++) {
            Address currentAddress = addressbook.get(i);
            if (currentAddress.getFirst_name().equals(first_name) && currentAddress.getLast_name().equals(last_name)) {
                addressbook.remove(i);
                userFound = true;
                break;
            }
        }
        if (userFound == false)
            System.out.println("User Not Found");
        else
            System.out.println("Address deleted for " + first_name + " " + last_name);
        printAddressBook();
    }

    static void printAddressBook() {
        for (int i = 0; i < addressbook.size(); i++) {
            Address currentAddress = addressbook.get(i);
            System.out.println(currentAddress.toString());
        }
    }
}
