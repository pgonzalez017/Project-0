package ui;

import db.EmployeeJDBC;
import db.JDBCFactory;
import model.Car;
import model.Customer;
import model.Employee;
import util.ArrayList;

public class EmployeeMenu extends Menu{
    public void showMenu(){
        String userInput;
        AddCarMenu add = new AddCarMenu();
        EmployeeJDBC empjdbc = (EmployeeJDBC) JDBCFactory.daoFactory(Employee.class);
        OfferMenu offerMenu;

        while(true){
            System.out.println("Please enter one of the following options:");
            System.out.println("\tEnter \"return\" to return to the previous menu");
            System.out.println("\tEnter \"add\" to add a car to the lot:");
            System.out.println("\tEnter \"remove\" to remove a car from the lot:");
            System.out.println("\tEnter \"payments\" to view all payments remaining:");
            System.out.println("\tEnter \"offer\" to accept or reject offers on cars:");
            System.out.println("\tEnter \"view\" to view all cars on the lot:");


            do{
                userInput = scan.nextLine();
                if(userInput.equals("return")){
                    return;
                }
                else if(userInput.equals("view")){
                    System.out.println(empjdbc.viewLot());
                    break;
                }
                else if(userInput.equals("add")){
                    add.showMenu();
                    break;
                }
                else if(userInput.equals("remove")){
                    System.out.println(empjdbc.viewLot());
                    System.out.println("Please enter the id for the car you want to remove from the lot");
                    userInput = scan.nextLine();
                    empjdbc.removeCar(Integer.parseInt(userInput));
                    break;
                }
                else if(userInput.equals("offer")){
                    System.out.println(empjdbc.getOffers());
                    offerMenu = new OfferMenu();
                    offerMenu.setEmployee(true);
                    offerMenu.showMenu();
                    break;
                }
                else if(userInput.equals("payments")){
                    ArrayList<Customer> custs = empjdbc.getPayments();
                    Customer cust = new Customer();
                    ArrayList<Car> cars;
                    Car c;
                    for(int i = 0; i < custs.size(); i++){
                        cust = (Customer) custs.get(i);
                        cars = cust.getCars();
                        System.out.println("Name: " + cust.getFirstName() + " " + cust.getLastName() + " Email: " + cust.getEmail());
                        for(int j = 0; j < cars.size(); j++){
                            c = (Car) cars.get(j);
                            System.out.println("Payment Remaining: " + c.getRemainingPayment() + ", Make: " + c.getMake() +
                                                ", Model: " +  c.getModel() + ", Year: " + c.getYear());
                        }
                    }
                    break;
                }
                else{
                    System.out.println("Please enter a valid option!");
                }
            }while(true);
        }
    }
}
