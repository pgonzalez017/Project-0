package ui;

import db.CustomerJDBC;
import db.JDBCFactory;
import model.Customer;

// TODO: Ensure all information user inputs is valid
public class RegistrationMenu extends Menu{

    /*
    private  checkName(String name){

    }
    */


    public void showMenu(){
        CustomerJDBC cj = (CustomerJDBC) JDBCFactory.daoFactory(Customer.class);
        String userInput;
        boolean validEmail = true;
        boolean validPhoneNumber = true;
        Customer newCust = new Customer();
        // TODO: Enter all the user input into the database
        while(true) {
            do {
                System.out.println("Please enter your first name:");
                userInput = scan.nextLine();
                if (userInput.equals("return")) {
                    return;
                }
                if (userInput.equals(""))
                    System.out.println("Please input a valid first name");
                else {
                    newCust.setFirstName(userInput);
                    break;
                }
            } while (true);

            do {
                System.out.println("Please enter your last name:");
                userInput = scan.nextLine();
                if (userInput.equals(""))
                    System.out.println("Please input a valid last name");
                else {
                    newCust.setLastName(userInput);
                    break;
                }
            } while (true);

            do {
                System.out.println("Please enter your e-mail:");
                userInput = scan.nextLine();
                if (userInput.equals("return")) {
                    return;
                }

                // Checking for a valid email address is a little complicated
                // Honestly there is a lot more to check for but I didn't want to get too
                // carried away with all the checks

                if (userInput.length() < 7) {
                    validEmail = false;
                } else {
                    String ending = userInput.substring(userInput.length() - 4, userInput.length());

                    if (!ending.equals(".com"))
                        validEmail = false;

                    for (int i = 0; i < userInput.length() && validEmail; i++) {
                        if (userInput.charAt(i) == ' ') {
                            validEmail = false;
                        }
                        if (userInput.charAt(i) == '@') {
                            if (i == 0 || i == userInput.length() - 1) {
                                validEmail = false;
                            }
                        }
                    }
                }

                if (validEmail) {
                    newCust.setEmail(userInput);
                    break;
                } else {
                    System.out.println("Please enter a valid e-mail address");
                }
            } while (true);

            do {
                System.out.println("Please enter your phone number:");
                userInput = scan.nextLine();
                if (userInput.equals("return")) {
                    return;
                }
                if (userInput.length() == 10) {
                    for (int i = 0; i < 10 && validPhoneNumber; i++) {
                        if (userInput.charAt(i) < '0' || userInput.charAt(i) > '9') {
                            validPhoneNumber = false;
                        }
                    }
                } else {
                    validPhoneNumber = false;
                }
                if (validPhoneNumber) {
                    newCust.setPhoneNumber(userInput);
                    break;
                } else {
                    System.out.println("Please enter a valid phone number!");
                }
            } while (true);

            do {
                System.out.println("Please enter the password for your account:");
                userInput = scan.nextLine();
                if (userInput.equals("return")) {
                    return;
                }
                if (userInput.equals(""))
                    System.out.println("Please input a valid password");
                else {
                    newCust.setPassword(userInput);
                    break;
                }
            } while (true);

            // Database stuff now
            cj.save(newCust);

            System.out.println("Account successfully created!");
            return;
        }
    }
}
