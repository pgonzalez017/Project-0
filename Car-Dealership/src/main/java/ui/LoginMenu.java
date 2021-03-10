package ui;

import db.EmployeeJDBC;
import db.CustomerJDBC;
import db.JDBCFactory;
import model.Employee;
import model.Customer;

public class LoginMenu extends Menu{

    private boolean emp;
    private EmployeeMenu empMenu = new EmployeeMenu();
    private CustomerMenu custMenu;
    private EmployeeJDBC empjdbc = (EmployeeJDBC) JDBCFactory.daoFactory(Employee.class);
    private CustomerJDBC custjdbc = (CustomerJDBC) JDBCFactory.daoFactory(Customer.class);

    public void setEmployee(boolean emp) {
        this.emp = emp;
    }

    public void showMenu(){
        String email, password;

        do{
            System.out.println("Please enter your e-mail:");
            email = scan.nextLine();
            if(email.equals("return"))
                return;
            System.out.println("Please enter your password:");
            password = scan.nextLine();

            // TODO: Check the login information against the database
            if(emp == true){
                // Check the Employee Login Database
                Employee curr = empjdbc.getById(email);
                if(curr == null){
                    System.out.println("Null");
                }
                else if(curr.getEmail().equals(email)){
                    if(curr.getPassword().equals(password)){
                        System.out.println("Login Successful!");
                        empMenu.showMenu();
                    }
                    else{
                        System.out.println(curr.getPassword());
                        System.out.println("Yo!");
                        System.out.println("Wrong E-mail or Password");
                    }
                }
                else{
                    System.out.println("Wrong E-mail or Password");
                }
            }
            else{
                // Check the Customer Login Database
                Customer cust = custjdbc.getById(email);
                if(cust == null){
                    System.out.println("Null");
                }
                else{
                    if(cust.getPassword().equals(password)){
                        System.out.println("Login Successful!");
                        custMenu = new CustomerMenu(cust.getId());
                        custMenu.setId(cust.getId());
                        custMenu.showMenu();
                    }
                    else{
                        System.out.println("YO");
                        System.out.println("Wrong E-mail or Password!");
                    }
                }
            }
        }while(true);
    }

    public LoginMenu(){
        emp = false;
    }

}
