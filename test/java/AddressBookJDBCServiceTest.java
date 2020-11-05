import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class AddressBookJDBCServiceTest {
    AddressBookJDBCServices addressBookJDBCServices;

    @Before
    public void initialize() {
        addressBookJDBCServices = new AddressBookJDBCServices();
    }

    @Test
    public void givenAddressBookData_WhenRetrieved_ShouldMatchContactCount() {
        List<Address> contactList = addressBookJDBCServices.readData();
        assertEquals(4, contactList.size());
    }

    @Test
    public void givenName_WhenUpdatedContactInfo_ShouldSyncWithDB() throws AddressBookDBException, IOException {
        AddressBookService.updateCity("Navneet", "Samastipur");
        boolean isSynced = AddressBookService.isAddressBookSyncedWithDB("Navneet");
       //assertTrue(isSynced);
        Assert.assertEquals(true, isSynced);
    }

    @Test
    public void givenDateRange_WhenRetrievedContactInfo_ShouldMatchCount() throws AddressBookDBException{
        LocalDate startDate = LocalDate.of(2017, 03, 01);
        LocalDate endDate= LocalDate.now();
        List<Address> contactList= AddressBookService.getContactsForDateRange(startDate,endDate);
        System.out.println("contactList: "+ contactList.toString());
        Assert.assertEquals(4,contactList.size());
    }

    @Test
    public void givenAddressBookData_whenRetreivedByState_ShouldMatchContactCount() {
        List<Address>contactList=AddressBookService.getContactsByCity("Samastipur");
        assertEquals(1,contactList.size());
    }


}