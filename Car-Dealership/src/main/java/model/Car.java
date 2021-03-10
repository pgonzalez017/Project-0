package model;

public class Car {
    private Integer id;
    private String make;
    private String model;
    private String year;
    private Integer price;
    private Integer remainingPayment;
    private Integer monthly;

    public Car(Integer id, String make, String model, String year, Integer price){
        this.id = id;
        this.year = year;
        this.make = make;
        this.model = model;
        this.price = price;
        remainingPayment = 0;
        monthly = 0;
    }

    public Car(Integer id, Integer remainingPayment, Integer monthly, String make, String model, String year){
        this.id = id;
        this.make = make;
        this.model = model;
        this.year = year;
        this.remainingPayment = remainingPayment;
        this.monthly = monthly;
        price = 0;
    }

    public Car(){}

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getRemainingPayment(){
        return remainingPayment;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("[ ID:" + id + ", Make:" + make + ", Model:" + model + ", Year:" + year + ", Price:" + price + ", " +  "Remaining Payment:" + remainingPayment + "]");
        return sb.toString();
    }

    public Integer getMonthly() {
        return monthly;
    }

    public void setMonthly(Integer monthly) {
        this.monthly = monthly;
    }
}
