/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Entities.Car;
import java.sql.*;
import Services.*;
import Utils.DBconnexion;
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
public class CarsDao implements IDao<Car> {

    public CarsDao instance;

    public CarsDao getInstance() {
        if (instance == null)
            try {
                instance = new CarsDao();
            } catch (SQLException ex) {
                Logger.getLogger(CarsDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        return instance;
    }

    private Statement st;
    private ResultSet rs;

    public CarsDao() throws SQLException {
        DBconnexion cs = DBconnexion.getInstance();
        try {
            st = cs.getConnection().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(CarsDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void insert(Car car) {
        String req = "insert into Car (model,color,type,make,description,mileage,year,fiscalpower,transmission,loss,primarydamage,secondarydamage,fueltype) values ('"
                + car.getModel() + "','" + car.getColor() + "', '"
                + "'" + car.getMake() + "','" + car.getDescription() + "','" + car.getLoss() + "','" + car.getType()
                + "','" + car.getTransmission() + "',"
                + "'" + car.getFiscalpower() + "','" + car.getMileage() + "','" + car.getYear() + "','"
                + car.getPrimarydamage() + "','" + car.getSecondarydamage() + "','" + car.getFueltype() + "')";
        try {
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(CarsDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }// To change body of generated methods, choose Tools | Templates.

    @Override
    public void delete(Car car) {
        String req = "delete from Car where id=" + car.getId();
        Car p = displayById(car.getId());

        if (p != null)
            try {

                st.executeUpdate(req);

            } catch (SQLException ex) {
                Logger.getLogger(CarsDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        else
            System.out.println("n'existe pas");
        ; // To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Car> displayAll() {
        String req = "select * from Car";
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
                p.setFiscalpower(rs.getFloat("fiscalpower"));
                p.setFueltype(rs.getString("fueltype"));
                p.setLoss(rs.getString("loss"));
                p.setMileage(rs.getFloat("mileage"));
                p.setModel(rs.getString("model"));
                p.setMake(rs.getString("make"));
                p.setPrimarydamage(rs.getString("primarydamage"));
                p.setSecondarydamage(rs.getString("secondarydamage"));
                p.setTransmission(rs.getString("transmission"));
                p.setYear(rs.getInt("year"));

                list.add(p);
            }

        } catch (SQLException ex) {
            Logger.getLogger(CarsDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list; // To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Car displayById(int id) {
        String req = "select * from Car where id =" + id;
        Car p = new Car();
        try {
            rs = st.executeQuery(req);
            // while(rs.next()){
            rs.next();
            p.setId(rs.getInt("id"));
            p.setColor(rs.getString("color"));
            p.setDescription(rs.getString("description"));
            p.setType(rs.getString("type"));
            p.setMake(rs.getString("make"));
            p.setFiscalpower(rs.getFloat("fiscalpower"));
            p.setFueltype(rs.getString("fueltype"));
            p.setLoss(rs.getString("loss"));
            p.setMileage(rs.getFloat("mileage"));
            p.setModel(rs.getString("model"));
            p.setMake(rs.getString("make"));
            p.setPrimarydamage(rs.getString("primarydamage"));
            p.setSecondarydamage(rs.getString("secondarydamage"));
            p.setTransmission(rs.getString("transmission"));
            p.setYear(rs.getInt("year"));

            // }
        } catch (SQLException ex) {
            Logger.getLogger(CarsDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p; // To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Car car) {
        String qry = "UPDATE Car SET model = '" + car.getModel() + "', color = '" + car.getColor() + "',  make = '"
                + car.getMake() + "', type = '" + car.getType() + "', fueltype = '" + car.getFueltype()
                + "', descrition = '" + car.getDescription() + "', primarydamage = '" + car.getPrimarydamage() + "',"
                + "   transmission = '" + car.getTransmission() + "',  fiscalpower = '" + car.getFiscalpower()
                + "',  loss = '" + car.getLoss() + "', mileage = '" + car.getMileage() + "',  year = '" + car.getYear()
                + "',  secondarydamage = '" + car.getSecondarydamage() + "',WHERE id = " + car.getId();

        try {
            if (st.executeUpdate(qry) > 0) {
                return true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(CarsDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false; // To change body of generated methods, choose Tools | Templates.
    }
}
