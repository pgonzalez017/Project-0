package ui;

import db.CustomerJDBC;
import db.EmployeeJDBC;
import db.JDBCFactory;
import model.Customer;
import model.Employee;

public class OfferMenu extends Menu{
    private Integer cust_id;
    private boolean emp;
    private CustomerJDBC custjdbc = (CustomerJDBC) JDBCFactory.daoFactory(Customer.class);
    private EmployeeJDBC empjdbc = (EmployeeJDBC) JDBCFactory.daoFactory(Employee.class);

    public OfferMenu(Integer cust_id) {
        this.cust_id = cust_id;
        emp = false;
    }

    public OfferMenu(){
        emp = false;
    }

    public void setEmployee(boolean emp){
        this.emp = emp;
    }

    public void showMenu(){
        String userInput;
        String id;
        String vin;
        boolean validOffer = true;

        if(emp){
            System.out.println("Enter the vin for the car you would like to accept or reject offers for:");
            vin = scan.nextLine();
            System.out.println("Enter the customer id the for the offer you would like to accept or reject:");
            id = scan.nextLine();
            System.out.println("Enter \"accept\" if you would like to accept the offer or enter \"reject\" to reject the offer");
            userInput = scan.nextLine();
            if(userInput.equals("accept")){
                empjdbc.acceptOffer(Integer.parseInt(vin), Integer.parseInt(id));
            }
            else if(userInput.equals("reject")){
                empjdbc.rejectOffer(Integer.parseInt(vin), Integer.parseInt(id));
            }
            else{
                System.out.println("Invalid Input");
            }
        }
        else{
            System.out.println("Please enter the id for the car you would like to make the offer for");
            id = scan.nextLine();
            System.out.println("Enter the offer you would like to make for the car");
            userInput = scan.nextLine();
            for (int i = 0; i < userInput.length() && validOffer; i++) {
                if (userInput.charAt(i) < '0' || userInput.charAt(i) > '9') {
                    validOffer = false;
                }
            }
            if(validOffer){
                custjdbc.makeOffer(cust_id, Integer.parseInt(id), Integer.parseInt(userInput));
            }
            else{
                System.out.println("Please enter a valid offer!");
            }
        }


    }
}
