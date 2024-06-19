package duck.manager;

public class ForeignPassenger extends Passenger{
    public ForeignPassenger(String phoneNumber, String firstName, String lastName, int yearOfBirth, String gender, String ID) {
        super(phoneNumber, firstName, lastName, yearOfBirth, gender, ID);
        this.passportNumber = passportNumber;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    private String passportNumber;
}
