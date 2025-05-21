
public class Client {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private CheckingAccount checkingAccount;
    private SavingsAccount savingsAccount;

    public Client(String id, String firstName, String lastName, String email, String phoneNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        checkingAccount = new CheckingAccount("123", 0, this);
        savingsAccount = new SavingsAccount("456", 0, this);
    }


    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }


    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Client ID: " + id + ", Name: " + getFullName() +
                ", Email: " + email + ", Phone: " + phoneNumber;
    }
}
