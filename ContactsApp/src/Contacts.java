//neccessary Java/JavaFX imports in order to run the code
import java.util.ArrayList;

public class Contacts {
    // private instance variables, note the ArrayList for the dynamic column as columns will be added accordingly
    private String first;
    private String last;
    private String address;
    private String city;
    private String postalCode;
    private String number;
    private ArrayList<String> phoneNumbers = new ArrayList<String>();

    // default constructor
    public Contacts() {
        this.first = "";
        this.last = "";
        this.address = "";
        this.city = "";
        this.postalCode = "";
        this.number ="";
        this.phoneNumbers = new ArrayList<>();
    }

    // alternative constructor with all 6 attributes
    public Contacts(String first, String last, String address, String city, String postalCode, String phoneNumber) {
        this.first = first;
        this.last = last;
        this.address = address;
        this.city = city;
        this.postalCode = postalCode;
        this.number = phoneNumber;
        this.phoneNumbers.add(phoneNumber);
    }

    // setters and getters
    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    
    public ArrayList<String> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(ArrayList<String> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public void addNumber(String number){
        this.phoneNumbers.add(number);
    }

    public void setNumber(String number){
        this.number = number;
    }
    public String getNumber(){
        return number;
    }
}