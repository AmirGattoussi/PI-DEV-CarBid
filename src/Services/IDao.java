/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;
import Entities.*;
import java.util.List;
/**
 *
 * @author rima
 * @param <cars>
 */
public interface IDao<cars> {
    public void insert(Cars car);
    public void delete(Cars car);
    public List<Cars> displayAll();
    public Cars displayById(int id);
    
    public boolean update(Cars car);
}
