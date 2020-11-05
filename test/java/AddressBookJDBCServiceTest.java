import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
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
}