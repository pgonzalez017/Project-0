package ui;
import db.*;
import model.Car;
import model.Customer;
import util.ArrayList;

public class CustomerMenu extends Menu{
    private Integer id;
    private String userInput;
    private CustomerJDBC custjdbc = (CustomerJDBC) JDBCFactory.daoFactory(Customer.class);
    private OfferMenu offerMenu;

    public void showMenu(){
        while(true) {
            System.out.println("Please enter one of the following options:");
            System.out.println("\tEnter \"return\" to return to log out and return to the previous menu");
            System.out.println("\tEnter \"view\" to view the cars on the lot:");
            System.out.println("\tEnter \"payments\" to view remaining payments:");
            System.out.println("\tEnter \"cars\" to view the cars that you own:");
            System.out.println("\tEnter \"offer\" to make an offer for a car");

            do {
                userInput = scan.nextLine();
                if (userInput.equals("return")) {
                    return;
                }
                else if (userInput.equals("view")) {
                    // Show cars on the lot
                    System.out.println(custjdbc.getAllCars());
                    break;
                }
                else if (userInput.equals("payments")) {
                    // View Remaining Payments
                    ArrayList<Car> cars = custjdbc.getOwnCars(id);
                    Car c;
                    for(int i = 0; i < cars.size(); i++){
                        c = (Car) cars.get(i);
                        System.out.println("Make: " + c.getMake() + ", Model: "  + c.getModel() +
                                            ", Year: " + c.getYear() + ", Remaining Payment: " + c.getRemainingPayment() +
                                                ", Monthly Payment over 5 years: " + c.getMonthly());
                    }
                    System.out.println();
                    /*
                    for (int i = 0; i < custs.size(); i++) {
                        cust = (Customer) custs.get(i);
                        c = (Car) cust.getCars().get(i);
                        System.out.println("Payment Remaining: " + c.getRemainingPayment() + "Year: " + c.getYear() + "Make: " +
                                c.getMake() + "Model: " + c.getModel() + "Customer: " + cust.getFirstName() + " " + cust.getLastName());
                    }

                     */
                    break;
                } else if (userInput.equals("cars")) {
                    // Show the cars that the customer owns
                    ArrayList<Car> cars = custjdbc.getOwnCars(id);
                    Car c;
                    for(int i = 0; i < cars.size(); i++){
                        c = (Car) cars.get(i);
                        System.out.println("Make: " + c.getMake() + ", Model: "  + c.getModel() + ", Year: " + c.getYear());
                    }
                    System.out.println();
                    break;
                } else if (userInput.equals("offer")) {
                    //
                    System.out.println(custjdbc.getAllCars());
                    offerMenu.showMenu();
                    break;
                } else {
                    System.out.println("Please enter a valid option!");
                }
            }while(true);
        }
    }

    public void setId(Integer id){
        this.id = id;
    }
    public Integer getId(){
        return id;
    }

    public CustomerMenu(Integer id){
        this.id = id;
        offerMenu = new OfferMenu(id);
    }
}
