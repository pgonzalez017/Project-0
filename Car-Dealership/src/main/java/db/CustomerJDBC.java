package db;

import model.Car;
import model.Customer;
import config.ConnectionUtil;
import util.ArrayList;

import java.sql.*;

public class CustomerJDBC implements GenericDao<Customer, String>{

    private static CustomerJDBC instance;

    static CustomerJDBC getInstance(){
        if(instance == null){
            instance = new CustomerJDBC();
        }
        return instance;
    }

    @Override
    public void save(Customer c){
        try{
            String sql = "insert into Customers values ( default, ?, ?, ?, ?, ?);";

            PreparedStatement ps = ConnectionUtil.getInstance().getConnection().prepareStatement(sql);
            ps.setString(1, c.getFirstName());
            ps.setString(2, c.getLastName());
            ps.setString(3, c.getEmail());
            ps.setString(4, c.getPhoneNumber());
            ps.setString(5, c.getPassword());
            ps.executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace();
        }

    }


    public void makeOffer(Integer custId, Integer vin, Integer offer){
        try{
            // Gotta make sure the vin is something that can legitimately be used
            String sql = "insert into offers values(?, ?, ?);";
            PreparedStatement ps = ConnectionUtil.getInstance().getConnection().prepareStatement(sql);
            ps.setInt(1, offer);
            ps.setInt(2, custId);
            ps.setInt(3, vin);
            ps.executeUpdate();

            System.out.println("Offer successfully made!");
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    public ArrayList<Car> getOwnCars(Integer id){
        try{
            ArrayList<Car> cars = new ArrayList<>();
            Car temp;
            String sql = "select * from CarPayments where cust_id = ?";
            PreparedStatement ps = ConnectionUtil.getInstance().getConnection().prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                temp = new Car(rs.getInt("vin"), rs.getInt("payment"), rs.getInt("monthly_payments"), rs.getString("make"), rs.getString("model"), rs.getString("yr"));
                cars.add(temp);
            }
            return cars;
        }
        catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Car> getAllCars(){
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

    @Override
    public Customer getById(String id) {
        try{
            String sql = "select * from Customers where Email = ?;";
            PreparedStatement ps = ConnectionUtil.getInstance().getConnection().prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();

            return new Customer(rs.getInt("id"), rs.getString("FirstName"), rs.getString("LastName"), rs.getString("PhoneNumber"), rs.getString("Email"), rs.getString("PW"));
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
    public boolean update(Customer customer) {
        return false;
    }

    @Override
    public int updateAll(ArrayList<Customer> collection) {
        return 0;
    }

    public ArrayList<Customer> getAll(){
        return null;
    }
}
