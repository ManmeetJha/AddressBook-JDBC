import com.opencsv.bean.CsvBindByName;

public class Address {

    @CsvBindByName(column = "First_Name")
    private String First_name;
    @CsvBindByName(column = "Last_Name")
    private String Last_name;
    @CsvBindByName(column = "Address")
    private String Address;
    @CsvBindByName(column = "City")
    private String City;
    @CsvBindByName(column = "State")
    private String State;
    @CsvBindByName(column = "Zip")
    private String Zip;
    @CsvBindByName(column = "Phone_no")
    private String Phone_no;
    @CsvBindByName(column = "Email")
    private String Email;

    public Address(String first_name, String last_name, String address, String city, String state, String zip,
                   String phone_no, String email) {
        First_name = first_name;
        Last_name = last_name;
        Address = address;
        City = city;
        State = state;
        Zip = zip;
        Phone_no = phone_no;
        Email = email;
    }

    public String getFirst_name() {
        return First_name;
    }

    public void setFirst_name(String first_name) {
        First_name = first_name;
    }

    public String getLast_name() {
        return Last_name;
    }

    public void setLast_name(String last_name) {
        Last_name = last_name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getZip() {
        return Zip;
    }

    public void setZip(String zip) {
        Zip = zip;
    }

    public String getPhone_no() {
        return Phone_no;
    }

    public void setPhone_no(String phone_no) {
        Phone_no = phone_no;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    @Override
    public String toString() {
        return "Address [First_name=" + First_name + ", Last_name=" + Last_name + ", Address=" + Address + ", City="
                + City + ", State=" + State + ", Zip=" + Zip + ", Phone_no=" + Phone_no + ", Email=" + Email + "]";
    }


    public Object getFirst_Name() {
        return First_name;
    }

}
