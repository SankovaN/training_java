package training.addressbook;

public class ContactData {
    private final String name;
    private final String lastname;
    private final String email;
    private final String address;
    private final String phone;

    public ContactData(String name, String lastname, String email, String address, String phone) {
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.address = address;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }
}
