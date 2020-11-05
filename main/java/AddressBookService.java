import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;


public class AddressBookService {




    public enum IOService {
        CONSOLE_IO, FILE_IO, DB_IO, REST_IO, CSV, JSON
    }

    private static List<Address> contactList;

    public static List<Address> readContactData(IOService ioService) throws IOException {
        AddressBookJDBCServices addressBookJDBCServices =new AddressBookJDBCServices();
//        System.out.println(ioService + " " + IOService.DB_IO);
//        if (ioService.equals(IOService.FILE_IO))
//            contactList = new UserInterface2().readFromCSVFile();
        if (ioService.equals(IOService.DB_IO)) {
            contactList = addressBookJDBCServices.readData();
        }
        return contactList;
    }

    public static void updateCity(String firstName, String city) throws AddressBookDBException, IOException {
        AddressBookJDBCServices addressBookJDBCServices = new AddressBookJDBCServices();
        int result = addressBookJDBCServices.updateContactUsingSQL(firstName, "city", city);
        Address contact = getContactData(firstName);
        if (result != 0 && contact != null)
            contact.setCity(city);
        if (result == 0)
            throw new AddressBookDBException("Wrong name given", AddressBookDBException.ExceptionType.WRONG_NAME);
        if (contact == null)
            throw new AddressBookDBException("No data found", AddressBookDBException.ExceptionType.NO_DATA_FOUND);
    }

    private static Address getContactData(String name) throws IOException {
        readContactData(IOService.DB_IO);
        return contactList.stream().filter(e -> e.getFirst_Name().equals(name)).findFirst().orElse(null);
    }

    public static boolean isAddressBookSyncedWithDB(String firstName) throws IOException {
        Address contact = getContactData(firstName);
        return AddressBookJDBCServices.getContacts(firstName).get(0).getCity().equals(contact.getCity());
    }

    public static List<Address> getContactsForDateRange(LocalDate startDate, LocalDate endDate){
        return AddressBookJDBCServices.getContactsForDateRange(startDate, endDate);
    }

    public static List<Address> getContactsByCity(String city) {
        return AddressBookJDBCServices.getContactsByCity(city);
    }

    public static void addNewContact(String Date, String firstName, String lastName,
                                     String Address, String City, String State, String Zip, String Phone_No, String Email) {
        AddressBookJDBCServices.insertIntoDB(firstName,lastName,Address,City,State,Zip,Phone_No,Email,Date);
    }

    public static void addNewMultipleContacts(List<Address> addresses) {
        HashMap<String,Boolean> statusMap = new HashMap<String, Boolean>();

        for(Address address : addresses){
            statusMap.put(address.getFirst_name(), false);
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    AddressBookJDBCServices.insertIntoDB(address.getFirst_name(), address.getLast_name(),
                            address.getAddress(), address.getCity(), address.getState(), address.getZip(), address.getPhone_no(),
                            address.getEmail(), LocalDate.now().toString());
                    statusMap.put(address.getFirst_name(), true);
                }
            }, address.getFirst_name());
            thread.start();
            while(!statusMap.get(address.getFirst_name())){
                try {
                    System.out.println("Currently running thread is : "+thread.getName());
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}