package ui;

public class InitialMenu extends Menu{

     private RegistrationMenu regMenu = new RegistrationMenu();
     private LoginMenu logMenu = new LoginMenu();

    public void showMenu(){
        while(true) {
            String userInput;
            System.out.println("Hello, please enter one of the following options to login or register:\n");
            System.out.println("Enter \"customer\" to login as a customer:");
            System.out.println("Enter \"employee\" to login as an employee:");
            System.out.println("Enter \"register\" to register for a customer account:");

            do {
                userInput = scan.nextLine();
                if (userInput.equals("exit")) {
                    System.exit(0);
                } else if (userInput.equals("customer")) {
                    // Run the Login Menu
                    logMenu.setEmployee(false);
                    logMenu.showMenu();
                    break;
                } else if (userInput.equals("employee")) {
                    logMenu.setEmployee(true);
                    logMenu.showMenu();
                    break;
                } else if (userInput.equals("register")) {
                    // Go to Registration Menu
                    regMenu.showMenu();
                    break;
                } else {
                    System.out.println("Please enter a valid option!");
                }
            } while (true);
        }
    }
}
