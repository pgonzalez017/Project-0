package db;

public class JDBCFactory {
    public static GenericDao daoFactory(Class c){
        switch(c.getName()){
            case("model.Customer"):{
                return CustomerJDBC.getInstance();
            }
            case("model.Employee"):{
                return EmployeeJDBC.getInstance();
            }
            default:{
                throw new IllegalArgumentException("The class provided does not match any of the possible outputs");
            }
        }
    }
}
