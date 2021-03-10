package model;
import util.ArrayList;

public class Customer {
    private Integer id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String password;
    private Integer payment;
    private ArrayList<Integer> offers;
    private ArrayList<Car> cars;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName(){
        return firstName;
    }
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword(){return password;}

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPayment() {
        return payment;
    }

    public void setPayment(Integer payment) {
        this.payment = payment;
    }

    public void addCar(Car c){
        this.cars.add(c);
    }

    public ArrayList<Car> getCars(){
        return cars;
    }

    public ArrayList<Integer> getOffers(){
        return offers;
    }

    public void addOffer(Integer offer){
        this.offers.add(offer);
    }


    public Customer(){
        offers = new ArrayList<Integer>();
        cars = new ArrayList<Car>();
    }
    public Customer(Integer id, String firstName, String lastName, String phoneNumber, String email, String password){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        offers = new ArrayList<Integer>();
        cars = new ArrayList<Car>();
    }

}
