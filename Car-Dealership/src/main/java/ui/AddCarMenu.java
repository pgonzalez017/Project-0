package ui;

import db.EmployeeJDBC;
import db.JDBCFactory;
import model.Car;
import model.Employee;

public class AddCarMenu extends Menu{
    EmployeeJDBC emp = (EmployeeJDBC) JDBCFactory.daoFactory(Employee.class);
    Car c = new Car();
    String userInput;
    boolean validPrice = true;

    public void showMenu(){

        do{
            System.out.println("Please enter the make of the car");
            userInput = scan.nextLine();
            if(userInput.equals("return")){
                return;
            }
            if(userInput.equals("")){
                System.out.println("Please enter a valid input");
            }
            else{
                c.setMake(userInput);
                break;
            }
        }while(true);

        do{
            System.out.println("Please enter the model of the car");
            userInput = scan.nextLine();
            if(userInput.equals("")){
                System.out.println("Please enter a valid input");
            }
            else{
                c.setModel(userInput);
                break;
            }
        }while(true);

        do{
            System.out.println("Please enter the year of the car");
            userInput = scan.nextLine();
            if(userInput.equals("return")){
                return;
            }
            if(userInput.equals("")){
                System.out.println("Please enter a valid input");
            }
            else{
                c.setYear(userInput);
                break;
            }
        }while(true);

        do{
            System.out.println("Please enter the price of the car");
            userInput = scan.nextLine();
            if(userInput.equals("return")){
                return;
            }
            if(userInput.equals("")){
                validPrice = false;
            }
            else{
                for(int i = 0; i < userInput.length() && validPrice; i++){
                    if(userInput.charAt(i) <  '0' || userInput.charAt(i) > '9'){
                        validPrice = false;
                    }
                }
            }
            if(validPrice){
                c.setPrice(Integer.parseInt(userInput));
                break;
            }
            else{
                System.out.println("Please enter a valid input");
            }
        }while(true);

        emp.addCar(c);

    }
}
