/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;
import Entities.*;


/**
 *
 * @author gtsia
 */
public interface EmployeeDao {
    public void addEmployee();
    public void deleteEmployee();
    public void updateEmployee();
    public Employee getEmployee();
}
