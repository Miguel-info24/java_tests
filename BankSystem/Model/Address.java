package Model;

public class Address {

    private String street;
    private String number;

    public Address(String street, String number) {
        this.street = street;
        this.number = number;
    }

    public Address() {
    }

    public String getStreet() {
        return street;
    }

    public String getNumber()
    {
        return number;
    }

    public void setNumber(String number)
    {
        this.number = number;
    }

    public void setStreet(String street) {
        this.street = street;
    }

}
