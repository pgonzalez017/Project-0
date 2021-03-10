package db;
import config.ConnectionUtil;
import model.Car;
import model.Customer;
import model.Employee;
import util.ArrayList;

import java.sql.*;

public class EmployeeJDBC implements GenericDao<Employee, String>{

    private static EmployeeJDBC instance;

    static EmployeeJDBC getInstance(){
        if(instance == null){
            instance = new EmployeeJDBC();
        }
        return instance;
    }

    public void acceptOffer(Integer vin, Integer custId){
        try{
            String sql = "select acceptOffer(?, ?)";

            PreparedStatement ps = ConnectionUtil.getInstance().getConnection().prepareStatement(sql);
            ps.setInt(1, vin);
            ps.setInt(2, custId);
            ps.executeQuery();
            System.out.println("Offer Accepted!");
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void rejectOffer(Integer vin, Integer custId){
        try{
            String sql = "delete from offers where cust_id = ? and vin = ?";
            PreparedStatement ps = ConnectionUtil.getInstance().getConnection().prepareStatement(sql);
            ps.setInt(1, custId);
            ps.setInt(2, vin);
            ps.executeUpdate();
            System.out.println("Offer Rejected!");
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void addCar(Car c){
        try{
            String sql = "insert into CarLot values ( default, ?, ?, ?, ? );";

            PreparedStatement ps = ConnectionUtil.getInstance().getConnection().prepareStatement(sql);
            ps.setString(1, c.getMake());
            ps.setString(2, c.getModel());
            ps.setString(3, c.getYear());
            ps.setInt(4, c.getPrice());

            int i = ps.executeUpdate();
            System.out.println("Car Successfully Added!");
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    public ArrayList<Car> viewLot(){
        try{
            ArrayList<Car> cars = new ArrayList<>();
            Car temp;
            String sql = "select * from CarLot";
            PreparedStatement ps = ConnectionUtil.getInstance().getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                temp = new Car(rs.getInt("id"), rs.getString("make"), rs.getString("model"), rs.getString("yr"), rs.getInt("price"));
                cars.add(temp);
            }
            return cars;
        }
        catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public void removeCar(Integer id){
        try{
            String sql = "delete from CarLot where id = ?;";

            PreparedStatement ps = ConnectionUtil.getInstance().getConnection().prepareStatement(sql);
            ps.setInt(1, id);
            int i = ps.executeUpdate();
            System.out.println("Car Successfully Removed!");
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    public ArrayList<Customer> getPayments(){
        try{
            ArrayList<Customer> payments = new ArrayList<Customer>();
            String sql = "select * from carpayments inner join customers on carpayments.cust_id = customers.id;";
            PreparedStatement ps = ConnectionUtil.getInstance().getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            Customer cust = new Customer();
            Car c;
            while(rs.next()){
                cust.setFirstName(rs.getString("FirstName"));
                cust.setLastName(rs.getString("LastName"));
                cust.setEmail(rs.getString("Email"));
                c = new Car(rs.getInt("vin"), rs.getInt("payment"), rs.getString("make"), rs.getString("model"), rs.getString("yr"));
                cust.addCar(c);
                System.out.println(cust.getCars());
                payments.add(cust);
            }
            return payments;
        }
        catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<String> getOffers(){
        try{
            ArrayList<String> offers = new ArrayList<String>();
            String s;
            String sql = "select * from offers;";
            PreparedStatement ps = ConnectionUtil.getInstance().getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                s = "VIN:" + rs.getInt("vin") + " Customer ID:" + rs.getInt("cust_id") + " Offer:" + rs.getInt("offer");
                offers.add(s);
            }
            return offers;
        }
        catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void save(Employee employee) {
        return;
    }

    @Override
    public Employee getById(String id) {
        try{
            Employee emp = new Employee();
            String sql = "select * from Employee where email = ?;";
            PreparedStatement ps = ConnectionUtil.getInstance().getConnection().prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            emp.setEmail(rs.getString("email"));
            emp.setFirstName(rs.getString("FirstName"));
            emp.setLastName(rs.getString("LastName"));
            emp.setPassword(rs.getString("PW"));
            emp.setId(rs.getInt("id"));
            return emp;
        }
        catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public boolean remove(String id) {
        return false;
    }

    @Override
    public ArrayList<Employee> getAll() {
        return null;
    }

    @Override
    public boolean update(Employee employee) {
        return false;
    }

    @Override
    public int updateAll(ArrayList<Employee> collection) {
        return 0;
    }
}
