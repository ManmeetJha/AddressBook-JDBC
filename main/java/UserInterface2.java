import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
//import com.opencsv.bean.OpencsvUtils;
import jdk.nashorn.internal.parser.JSONParser;
import sun.rmi.runtime.Log;

import java.io.*;
import java.util.*;

public class UserInterface2 {

    public static HashMap<String, List<Address>> myDevice;

    // K: Person, V: City
    public static HashMap<String, String> cityDictionary;

    // K: Person, V: State
    public static HashMap<String, String> stateDictionary;

    public static void main(String[] args) throws IOException {

        myDevice = new HashMap<>();
        cityDictionary = new HashMap<>();
        stateDictionary = new HashMap<>();

        //ready-made device
        initialize();

        Scanner input = new Scanner(System.in);

        int choice = 0;
        do {
            System.out.println("Select option:");
            System.out.println("1. Add Address Book");
            System.out.println("2. Add contact");
            System.out.println("3. Edit contact");
            System.out.println("4. Delete contact");
            System.out.println("5. Search in a city|state");
            System.out.println("6. Search in a city|state Dictionary");
            System.out.println("7. Number of contact personsin City or State");
            System.out.println("8. Sort Address List based on Person Name");
            System.out.println("9. Sort Address List based on City, State or Zip");
            System.out.println("10. Write Addresses to file");
            System.out.println("11. Read addresses from file");
            System.out.println("12. Write Addresses to CSV file");
            System.out.println("13. Read addresses from CSV file");
            System.out.println("14. Write Addresses to JSON file");
            System.out.println("15. Read addresses from JSON file");
            System.out.println("16. Exit");

            choice = input.nextInt();
            switch (choice) {
                case 1:
                    addAddressBook(input);
                    printAddressBooks();
                    break;
                case 2:
                    insertIntoAddressBook(input);
                    printAddressBooks();
                    break;
                case 3:
                    editAddress(input);
                    printAddressBooks();
                    break;
                case 4:
                    deleteContact(input);
                    printAddressBooks();
                    break;
                case 5:
                    searchInCityState(input);
                    printAddressBooks();
                    break;
                case 6:
                    searchInCityStateUsingDictionary(input);
                    break;
                case 7:
                    countOfPersons(input);
                    break;
                case 8:
                    sortBasedOnName(input);
                    break;
                case 9:
                    sortBasedOnCityStateZip(input);
                    break;
                case 10:
                    writeToFile();
                    break;
                case 11:
                    readFromFile();
                    break;
                case 12:
                    writeToCSVFile();
                    break;
                case 13:
                    readFromCSVFile();
                    break;
                case 14:
                    writeToJSONFile();
                    break;
                case 15:
                    readFromJSONFile();
                    break;
                default:
                    break;
            }
        } while (choice != 16);

    }


    private static void sortBasedOnCityStateZip(Scanner input) {
        System.out.println("Enter the choice on which address to be sorted");
        System.out.println("1.City");
        System.out.println("2.State");
        System.out.println("3.Zip");
        int choice = input.nextInt();

        if (choice == 1) {
            Set<String> addressBookNames = myDevice.keySet();
            for (String addressBook : addressBookNames) {
                List<Address> addresses = myDevice.get(addressBook);
                System.out.println("Before sorting City");
                System.out.println(addresses.toString());
                Collections.sort(addresses, new Comparator<Address>() {

                    @Override
                    public int compare(Address address1, Address address2) {
                        if (!address1.getCity().equals(address2.getCity())) {
                            return address1.getCity().compareTo(address2.getCity());
                        }
                        return address1.getCity().compareTo(address2.getCity());
                    }
                });

                System.out.println("After sorting city");
                System.out.println(addresses.toString());
            }
        }
        if (choice == 2) {
            Set<String> addressBookNames = myDevice.keySet();
            for (String addressBook : addressBookNames) {
                List<Address> addresses = myDevice.get(addressBook);
                System.out.println("Before sorting State");
                System.out.println(addresses.toString());
                Collections.sort(addresses, new Comparator<Address>() {

                    @Override
                    public int compare(Address address1, Address address2) {
                        if (!address1.getState().equals(address2.getState())) {
                            return address1.getState().compareTo(address2.getState());
                        }
                        return address1.getState().compareTo(address2.getState());
                    }
                });

                System.out.println("After sorting State");
                System.out.println(addresses.toString());
            }
        }
        if (choice == 3) {
            Set<String> addressBookNames = myDevice.keySet();
            for (String addressBook : addressBookNames) {
                List<Address> addresses = myDevice.get(addressBook);
                System.out.println("Before sorting Zip");
                System.out.println(addresses.toString());
                Collections.sort(addresses, new Comparator<Address>() {

                    @Override
                    public int compare(Address address1, Address address2) {
                        if (!address1.getZip().equals(address2.getZip())) {
                            return address1.getZip().compareTo(address2.getZip());
                        }
                        return address1.getZip().compareTo(address2.getZip());
                    }
                });

                System.out.println("After sorting Zip");
                System.out.println(addresses.toString());
            }
        }

    }

    private static void sortBasedOnName(Scanner input) {
        Set<String> addressBookNames = myDevice.keySet();
        for (String addressBook : addressBookNames) {
            List<Address> addresses = myDevice.get(addressBook);
            System.out.println("Before sorting");
            System.out.println(addresses.toString());
            Collections.sort(addresses, new Comparator<Address>() {

                @Override
                public int compare(Address address1, Address address2) {
                    if (!address1.getFirst_name().equals(address2.getFirst_name())) {
                        return address1.getFirst_name().compareTo(address2.getFirst_name());
                    }
                    return address1.getLast_name().compareTo(address2.getLast_name());
                }
            });

            System.out.println("After sorting");
            System.out.println(addresses.toString());
        }
    }

    private static void addAddressBook(Scanner input) {
        System.out.println("Enter the name of Address Book");
        String addBookName = input.next();
        List<Address> addressBook = new ArrayList<Address>();
        myDevice.put(addBookName, addressBook);
    }

    public static void insertIntoAddressBook(Scanner input) {
        System.out.println("Enter the address book");
        String addressbookname = input.next();
        List<Address> addressBook = myDevice.get(addressbookname);
        if (addressBook == null) {
            System.out.println(addressbookname + " Not found ");
            return;
        }

        System.out.println("Enter user details to insert");
        System.out.println("Enter first name:");
        String first_name = input.next();
        System.out.println("Enter last name:");
        String last_name = input.next();

        for (int i = 0; i < addressBook.size(); i++) {
            Address currentAddress = addressBook.get(i);
            if (currentAddress.getFirst_name().equals(first_name) && currentAddress.getLast_name().equals(last_name)) {
                System.out.println("No Duplicate entry allowed");
                return;
            }
        }

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
        addressBook.add(newAddress);
        System.out.println("Address added successfully");

        cityDictionary.put(first_name + " " + last_name, city);
        stateDictionary.put(first_name + " " + last_name, state);
        // printAddressBooks();
    }

    public static void editAddress(Scanner input) {

        System.out.println("Enter the address book");
        String addressbookname = input.next();
        List<Address> addressBook = myDevice.get(addressbookname);
        if (addressBook == null) {
            System.out.println(addressbookname + " Not found ");
            return;
        }

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
        for (int i = 0; i < addressBook.size(); i++) {
            Address currentAddress = addressBook.get(i);
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
        printAddressBooks();
    }

    public static void deleteContact(Scanner input) {
        System.out.println("Enter the address book");
        String addressbookname = input.next();
        List<Address> addressBook = myDevice.get(addressbookname);
        if (addressBook == null) {
            System.out.println(addressbookname + " Not found ");
            return;
        }

        System.out.println("Enter user details to delete");
        System.out.println("Enter first name:");
        String first_name = input.next();
        System.out.println("Enter last name:");
        String last_name = input.next();

        boolean userFound = false;
        for (int i = 0; i < addressBook.size(); i++) {
            Address currentAddress = addressBook.get(i);
            if (currentAddress.getFirst_name().equals(first_name) && currentAddress.getLast_name().equals(last_name)) {
                addressBook.remove(i);
                userFound = true;
                break;
            }
        }
        if (userFound == false)
            System.out.println("User Not Found");
        else
            System.out.println("Address deleted for " + first_name + " " + last_name);

    }

    public static void printAddressBooks() {
        Set<String> namesOfAddBooks = myDevice.keySet();
        for (String nameofaddbook : namesOfAddBooks) {
            System.out.println(nameofaddbook + " " + myDevice.get(nameofaddbook).toString());
        }

    }

    public static void searchInCityState(Scanner input) {
        System.out.println("Search by city or state");
        System.out.println("1. City");
        System.out.println("2. State");
        int choice = input.nextInt();

        String searchCity, searchState;
        Set<String> allAddressBooks = myDevice.keySet();

        if (choice == 1) {
            System.out.println("Enter city:");
            searchCity = input.next();

            for (String addressBookName : allAddressBooks) {
                List<Address> addressBook = myDevice.get(addressBookName);
                addressBook.stream().filter(currentAddress -> currentAddress.getCity().equals(searchCity))
                        .forEach(currentAddress -> System.out
                                .println(currentAddress.getFirst_name() + " " + currentAddress.getLast_name()));
            }
        }
        if (choice == 2) {
            System.out.println("Enter state:");
            searchState = input.next();

            for (String addressBookName : allAddressBooks) {
                List<Address> addressBook = myDevice.get(addressBookName);
                addressBook.stream().filter(currentAddress -> currentAddress.getState().equals(searchState))
                        .forEach(currentAddress -> System.out
                                .println(currentAddress.getFirst_name() + " " + currentAddress.getLast_name()));

//				for (int i = 0; i < addressBook.size(); i++) {
//					Address currentAddress = addressBook.get(i);
//					if (currentAddress.getCity().equals(searchCity) || currentAddress.getState().equals(searchState)) {
//						System.out.println(currentAddress.getFirst_name() + " " + currentAddress.getLast_name());
//					}
//				}
            }
        } else {
            return;
        }
    }

    public static void searchInCityStateUsingDictionary(Scanner input) {
        System.out.println("Search by city or state");
        System.out.println("1. City");
        System.out.println("2. State");
        int choice = input.nextInt();

        String searchCity, searchState;
        if (choice == 1) {
            System.out.println("Enter city:");
            searchCity = input.next();
            // using dictionary
            Set<String> personName = cityDictionary.keySet();
            personName.stream().filter(person -> cityDictionary.get(person).equals(searchCity))
                    .forEach(person -> System.out.println(person));
        }
        if (choice == 2) {
            System.out.println("Enter state:");
            searchState = input.next();
            // using dictionary
            Set<String> personName = stateDictionary.keySet();
            personName.stream().filter(person -> stateDictionary.get(person).equals(searchState))
                    .forEach(person -> System.out.println(person));
        } else {
            return;
        }
    }

    public static void countOfPersons(Scanner input) {
        System.out.println("Search by city or state");
        System.out.println("1. City");
        System.out.println("2. State");
        int choice = input.nextInt();

        String searchCity, searchState;
        if (choice == 1) {
            System.out.println("Enter city:");
            searchCity = input.next();
            // using dictionary
            Set<String> personName = cityDictionary.keySet();
            long count = personName.stream().filter(person -> cityDictionary.get(person).equals(searchCity)).count();
            System.out.println("Number of persons: " + count);

        }
        if (choice == 2) {
            System.out.println("Enter state:");
            searchState = input.next();
            // using dictionary
            Set<String> personName = stateDictionary.keySet();
            long count = personName.stream().filter(person -> stateDictionary.get(person).equals(searchState)).count();
            System.out.println("Number of persons: " + count);
        } else {
            return;
        }

    }

    public static void writeToFile() throws IOException {
        String filePath = System.getProperty("user.dir") + "/address.txt";
        System.out.println("Path of file: " + filePath);
        File addressFile = new File(filePath);

        //if file doesn't exist, create the file
        if (!addressFile.exists()) {
            addressFile.createNewFile();
        }

        //write to the file
        FileWriter writer = new FileWriter(filePath);
        Set<String> namesOfAddBooks = myDevice.keySet();
        writer.write("======Addresses in my device======\n");
        for (String nameofaddbook : namesOfAddBooks) {
            writer.write("Address Book Name: " + nameofaddbook + "\n");
            List<Address> addresses = myDevice.get(nameofaddbook);
            for (Address address : addresses)
                writer.write(address.toString() + "\n");

            writer.write("\n");
            writer.write("\n");
        }
        writer.close();
    }

    public static void readFromFile() throws IOException {
        String filePath = System.getProperty("user.dir") + "/address.txt";
        BufferedReader bufferedReader = null;
        bufferedReader = new BufferedReader(new FileReader(filePath));

        String curLine;
        System.out.println("Content of the file");
        while ((curLine = bufferedReader.readLine()) != null) {
            System.out.println(curLine);
        }
        bufferedReader.close();
    }

    public static void writeToCSVFile() throws IOException {
        String filePath = System.getProperty("user.dir") + "/addressCSV.csv";
        System.out.println("Path of file: " + filePath);
        File addressCSVFile = new File(filePath);
        if (!addressCSVFile.exists()) {
            addressCSVFile.createNewFile();
        }
        FileWriter writer = new FileWriter(addressCSVFile);
        CSVWriter csvwriter = new CSVWriter(writer);
        String[] header = {"Address Book Name", "First_name", "Last_name", "Address", "City", "State", "Zip", "Phone_no", "Email"};
        csvwriter.writeNext(header);
        Set<String> namesOfAddBooks = myDevice.keySet();
        for (String nameofaddbook : namesOfAddBooks) {
            List<Address> addresses = myDevice.get(nameofaddbook);
            for (Address address : addresses)
                csvwriter.writeNext(new String[]{nameofaddbook, address.getFirst_name(), address.getLast_name(),
                        address.getAddress(), address.getCity(), address.getState(), address.getZip(), address.getPhone_no(), address.getEmail()});
        }
        csvwriter.close();
    }

    public static void readFromCSVFile() throws IOException {

        String filePath = System.getProperty("user.dir") + "/addressCSV.csv";
        CSVReader reader = new CSVReader(new FileReader(filePath));
        String[] currentLine;
        while ((currentLine = reader.readNext()) != null) {
            System.out.println(Arrays.toString(currentLine));
        }
        reader.close();
    }

    public static void writeToJSONFile() throws IOException {
        String filePath = System.getProperty("user.dir") + "/addressJSON.json";
        File addressJSONFile = new File(filePath);
        if (!addressJSONFile.exists()) {
            addressJSONFile.createNewFile();
        }
        FileWriter writer = new FileWriter(addressJSONFile);
        Gson gson = new Gson();
        gson.toJson(myDevice, writer);
        writer.close();
    }

    public static void readFromJSONFile() throws IOException {
        String filePath = System.getProperty("user.dir") + "/addressJSON.json";
        FileReader reader = new FileReader(filePath);
        JsonParser jsonParser = new JsonParser();
        Object obj = jsonParser.parse(reader);

        JsonObject myDeviceObj = (JsonObject) obj;
        Set<Map.Entry<String, JsonElement>> entrySet = myDeviceObj.entrySet();
        for (Map.Entry entry : entrySet) {
            System.out.println("Address Book Name: " + entry.getKey());
            System.out.println(entry.getValue());
        }


    }

    public static void initialize() {

        Address addressA = new Address("a", "a", "a", "a", "a", "a", "a", "a");
        Address addressB = new Address("b", "b", "b", "b", "b", "b", "b", "b");
        Address addressC = new Address("c", "c", "c", "c", "c", "c", "c", "c");
        Address addressD = new Address("d", "d", "d", "d", "d", "d", "d", "d");
        Address addressE = new Address("e", "e", "e", "e", "e", "e", "e", "e");

        List<Address> book1 = new ArrayList<>();
        book1.add(addressA);
        book1.add(addressB);

        List<Address> book2 = new ArrayList<>();
        book2.add(addressC);
        book2.add(addressD);

        List<Address> book3 = new ArrayList<>();
        book3.add(addressE);

        myDevice.put("Book 1", book1);
        myDevice.put("Book 2", book2);
        myDevice.put("Book 3", book3);
    }
}
