/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

/**
 *
 * @author Yasmine
 */
public class ServicesCommand {
    Statement ste;

    Connection cnx;

    public ServicesCommand() throws SQLException {
        cnx = (Connection) DBconnexion.getInstance().getConnection();
    }

    public List<Command> display() {
        List<Command> listpieces = new ArrayList<>();
        try {
            ste = cnx.createStatement();
            String req_select = "SELECT * FROM `carbid`.`commands`";
            ResultSet res = ste.executeQuery(req_select);
            while (res.next()) {
                int Id = res.getInt(1);
                String Type = res.getString(2);
                int Pou = res.getInt(3);
                String Description = res.getString(4);
                double Price = res.getDouble(5);
                String Typec = res.getString(6);
                //   SpareParts s = new SpareParts(Id,Type,Pou,Description,Price,Typec);
                SpareParts s = new SpareParts(Id, Type, Pou, Description, Price, Typec);
                listpieces.add(s);

            }
        } catch (SQLException ex) {
            System.out.println("SQLException " + ex.getMessage());
        }

        return listpieces;
    }

    public void add(SpareParts u) throws SQLException {
        PreparedStatement pre = cnx.prepareStatement("INSERT INTO `spareparts`(`id_sparepart`, `Type`, `Pou`, `Description`, `Price`, `Typec`) VALUES (?,?,?,?,?,?)");

        pre.setInt(1, u.getId());
        pre.setString(2, u.getType());
        pre.setInt(3, u.getPou());
        pre.setString(4, u.getDescription());
        pre.setDouble(5, u.getPrice());

        pre.setString(6, u.getTypec());

        pre.executeUpdate();
        /*
           if ((text_live_id.getText().isEmpty()) && (!(txt_main_price.getText().isEmpty())) ) {
          Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Invalid Input");
                alert.setHeaderText(null);
                alert.setContentText("Please enter only a live data.");
                alert.showAndWait();}}
         */

    }

    public void delete(int id) {

        try {
            PreparedStatement pre = cnx.prepareStatement("delete from spareparts where id_sparepart = ?");
            pre.setInt(1, id);
            pre.executeUpdate();
        } catch (SQLException ex) {
            System.out.print(ex.getMessage());
        }

    }

    public void modify(SpareParts u) {

        try {
            PreparedStatement pre = cnx.prepareStatement("Update spareparts set Type=?,Pou=?,Description=?,Price=?,Typec=? where id_sparepart = ?");

            pre.setInt(6, u.getId());

            pre.setString(1, u.getType());

            pre.setInt(2, u.getPou());

            pre.setString(3, u.getDescription());

            pre.setDouble(4, u.getPrice());

            pre.setString(5, u.getTypec());

            pre.executeUpdate();
        } catch (SQLException ex) {
            System.out.print(ex.getMessage());
        }
    }

    public SpareParts GetSparePartsById(int id) {

        try {

            PreparedStatement pre = cnx.prepareStatement("select * from spareparts where id_sparepart = ?");

            pre.setInt(1, id);
            ResultSet result = pre.executeQuery();
            while (result.next()) {

                SpareParts u = new SpareParts(result.getInt(1), result.getString(2), result.getInt(3), result.getString(4), result.getDouble(5), result.getString(6));
                return u;
            }
        } catch (SQLException ex) {
            System.out.print(ex.getMessage());
        }
        return null;
    }

}
