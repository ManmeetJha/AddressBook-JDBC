import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddressBookJDBCServices{

    public List<Address> readData() {
        String sql = String.format("select * from addressBook");
        return getContactList(sql);
    }

    private List<Address> getContactList(String sql) {
        List<Address> contactList = new ArrayList<>();
        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                contactList.add(new Address(resultSet.getString("firstName"), resultSet.getString("lastName"),
                        resultSet.getString("Address"), resultSet.getString("City"), resultSet.getString("State"),
                        resultSet.getString("Zip"), resultSet.getString("Phone_No"), resultSet.getString("Email")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contactList;
    }

    private Connection getConnection() throws SQLException {
        String jdbcURL = "jdbc:mysql://localhost:3306/addressBook?useSSL=false";
        String userName = "root";
        String password = "Monu@12783";
        return DriverManager.getConnection(jdbcURL, userName, password);
    }
}
