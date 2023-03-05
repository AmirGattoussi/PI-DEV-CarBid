/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Entities.*;
import java.util.List;

/**
 *
 * @author rima
 * @param <cars>
 */
public interface IDao<cars> {
    public void insert(Car car);

    public void delete(Car car);

    public List<Car> displayAll();

    public List<Car> displayAllList();

    public Car displayById(int id_car);

    public boolean update(Car car);
}
