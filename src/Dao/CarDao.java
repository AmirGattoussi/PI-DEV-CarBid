/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Entities.Car;
import Entities.CurrentUser;
import java.sql.*;
import Services.*;
import Utils.DBconnexion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * /**
 *
 * @author rima
 */
public class CarDao implements IDao<Car> {

    private static CarDao instance;
    private PreparedStatement pst;
    private ResultSet rs;
    private Statement st;
    private Connection conn;
        UserDao  userDao = new UserDao();

    public static CarDao getInstance() {
        if (instance == null) {
            try {
                instance = new CarDao();
            } catch (SQLException ex) {
                Logger.getLogger(CarDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return instance;
    }

    public CarDao() throws SQLException {
        conn = DBconnexion.getInstance().getConnection();
        DBconnexion cs = DBconnexion.getInstance();
        try {
            st = cs.getConnection().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(CarDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void insert(Car car) {
        /*   try {
        String req = "insert into 'cars' ('model','color','type','make','description','mileage','year','fiscalpower','transmission','loss','primarydamage','secondarydamage','fueltype') values ('" + car.getModel() + "','" + car.getColor() + "','"
                + "'" + car.getMake() + "','" + car.getDescription() + "','" + car.getLoss() + "','" + car.getType() + "','" + car.getTransmission() + "',"
                + "'" + car.getFiscalpower() + "','" + car.getMileage() + "','" + car.getYear() + "','" + car.getPrimarydamage() + "','" + car.getSecondarydamage() + "','" + car.getFueltype() + "')";
        
            st.executeUpdate(req);
        } catch (SQLException ex) {
            System.out.println("Insert statement false****************************************************************************");
        } */

        //To change body of generated methods, choose Tools | Templates.
        String req = "insert into cars (model,color,type,make,description,mileage,year,fiscalpower,transmission,loss,primarydamage,secondarydamage,fueltype,id_user,carImg) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try {
            pst = conn.prepareStatement(req);
            pst.setString(1, car.getModel());
            pst.setString(2, car.getColor());
            pst.setString(3, car.getType());
            pst.setString(4, car.getMake());
            pst.setString(5, car.getDescription());
            pst.setInt(6, car.getMileage());
            pst.setInt(7, car.getYear());
            pst.setInt(8, car.getFiscalpower());
            pst.setString(9, car.getTransmission());
            pst.setString(10, car.getLoss());
            pst.setString(11, car.getPrimarydamage());
            pst.setString(12, car.getSecondarydamage());
            pst.setString(13, car.getFueltype());
            pst.setInt(14, CurrentUser.getUser().getId());
            pst.setString(15, car.getCarImg());
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(IDao.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

    }





    @Override
    public void delete(Car car) {
        String req = "delete from Cars where id_car=" + car.getId();
        Car p = displayById(car.getId());

        if (p != null) {
            try {

                st.executeUpdate(req);

            } catch (SQLException ex) {
                Logger.getLogger(CarDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("n'existe pas");
        }; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ObservableList<Car> displayAll() {
        String req = "select * from cars";
        ObservableList<Car> list = FXCollections.observableArrayList();

        try {
            rs = st.executeQuery(req);
            while (rs.next()) {
                Car p = new Car();
                p.setId(rs.getInt(1));
                p.setColor(rs.getString("color"));
                p.setDescription(rs.getString("description"));
                p.setType(rs.getString("type"));
                p.setMake(rs.getString("make"));
                p.setFiscalpower(rs.getInt("fiscalpower"));
                p.setFueltype(rs.getString("fueltype"));
                p.setLoss(rs.getString("loss"));
                p.setMileage(rs.getInt("mileage"));
                p.setModel(rs.getString("model"));
                p.setMake(rs.getString("make"));
                p.setPrimarydamage(rs.getString("primarydamage"));
                p.setSecondarydamage(rs.getString("secondarydamage"));
                p.setTransmission(rs.getString("transmission"));
                p.setYear(rs.getInt("year"));
                            p.setOwner(userDao.getUserById(rs.getInt("id_user")));
                            p.setCarImg(rs.getString("carImg"));

                list.add(p);
            }

        } catch (SQLException ex) {
            Logger.getLogger(CarDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public List<Car> displayAllList() {
        String req = "select * from cars";
        List<Car> list = new ArrayList<>();

        try {
            rs = st.executeQuery(req);
            while (rs.next()) {
                Car p = new Car();
                p.setId(rs.getInt(1));
                p.setColor(rs.getString("color"));
                p.setDescription(rs.getString("description"));
                p.setType(rs.getString("type"));
                p.setMake(rs.getString("make"));
                p.setFiscalpower(rs.getInt("fiscalpower"));
                p.setFueltype(rs.getString("fueltype"));
                p.setLoss(rs.getString("loss"));
                p.setMileage(rs.getInt("mileage"));
                p.setModel(rs.getString("model"));
                p.setMake(rs.getString("make"));
                p.setPrimarydamage(rs.getString("primarydamage"));
                p.setSecondarydamage(rs.getString("secondarydamage"));
                p.setTransmission(rs.getString("transmission"));
                p.setYear(rs.getInt("year"));

                list.add(p);
            }

        } catch (SQLException ex) {
            Logger.getLogger(CarDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Car displayById(int id_car) {
        String req = "select * from Cars where id_car =" + id_car;
        Car p = new Car();
        try {
            rs = st.executeQuery(req);
            // while(rs.next()){
            rs.next();
            p.setId(rs.getInt("id_car"));
            p.setColor(rs.getString("color"));
            p.setDescription(rs.getString("description"));
            p.setType(rs.getString("type"));
            p.setMake(rs.getString("make"));
            p.setFiscalpower(rs.getInt("fiscalpower"));
            p.setFueltype(rs.getString("fueltype"));
            p.setLoss(rs.getString("loss"));
            p.setMileage(rs.getInt("mileage"));
            p.setModel(rs.getString("model"));
            p.setMake(rs.getString("make"));
            p.setPrimarydamage(rs.getString("primarydamage"));
            p.setSecondarydamage(rs.getString("secondarydamage"));
            p.setTransmission(rs.getString("transmission"));
            p.setYear(rs.getInt("year"));
            p.setOwner(userDao.getUserById(rs.getInt("id_user")));

            //}  
        } catch (SQLException ex) {
            Logger.getLogger(CarDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Car car) {
        String qry = "UPDATE Cars SET model = '" + car.getModel() + "', color = '" + car.getColor() + "',  make = '" + car.getMake() + "', type = '" + car.getType() + "', fueltype = '" + car.getFueltype() + "', description = '" + car.getDescription() + "', primarydamage = '" + car.getPrimarydamage() + "',"
                + "   transmission = '" + car.getTransmission() + "',  fiscalpower = '" + car.getFiscalpower() + "',  loss = '" + car.getLoss() + "', mileage = '" + car.getMileage() + "',  year = '" + car.getYear() + "',  secondarydamage = '" + car.getSecondarydamage() + "'WHERE id_car= '" + car.getId() + "'";
        System.out.println(qry);
        try {

            if (st.executeUpdate(qry) > 0) {
                return true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(CarDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false; //To change body of generated methods, choose Tools | Templates.
    }
    
    public void updateWinnerCar(int idAuc) throws SQLException{
                                    int idCar = 0;
                                    int iduser = 0;
                                        PreparedStatement statement1 = conn.prepareStatement(
                                        "SELECT u.id_user,a.idCar FROM user u join bid b join auction a on u.id_user=b.userId and b.idAuction=a.idAuction where a.idAuction=? and a.highestBid=b.liveBidAmount"
                                );
                                        statement1.setInt(1, idAuc);
                                         PreparedStatement statement = conn.prepareStatement(
                                        "update cars set id_user=?,solde = ? where id_car=?"
                                );
                                         try{
                                         ResultSet resultSet = statement1.executeQuery();
                                         if (resultSet.next()) {
                                    iduser = resultSet.getInt(1);
                                    idCar = resultSet.getInt(2);
                                    System.out.println(iduser + " --- "+ idCar);
                                    
                                  
                                } 
                            } catch (SQLException ex) {
                                Logger.getLogger(BidDaoImplementation.class.getName()).log(Level.SEVERE, null, ex);
                            }
                                         
                                            statement.setInt(3, idCar);
                                            statement.setInt(1, iduser);
                                            statement.setBoolean(2, true);
                                            statement.executeUpdate();
                
                
}
}
