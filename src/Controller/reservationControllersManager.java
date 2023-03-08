package Controller;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public final class reservationControllersManager { 
    public void bridge(){
        
    }

    public void updateCounter(){
        getMRController().updateReservationCounter();
    }

    public void showDetails(){
        getMRController().pnlManageReservations.setVisible(false);
        getMRController().pnlReservationDetails.setVisible(true);
    }

    public void hideDetails(){
        this.getMRController().pnlManageReservations.setVisible(true);
        this.getMRController().pnlReservationDetails.setVisible(false);
    }

    public Parent loadMR(){
        try {
            FXMLLoader mrLoader = new FXMLLoader(getClass().getResource("../View/manageReservations.fxml"));
            return mrLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public manageReservationsController getMRController(){
        FXMLLoader mrLoader = new FXMLLoader(getClass().getResource("../View/manageReservations.fxml"));
        try {
            mrLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mrLoader.getController();
    }

    public Parent loadR(){
        try {
            FXMLLoader rLoader = new FXMLLoader(getClass().getResource("../View/reservations.fxml"));
            return rLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public reservationsController getRController(){
        FXMLLoader rLoader = new FXMLLoader(getClass().getResource("../View/reservations.fxml"));
        try {
            rLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rLoader.getController();
    }

    public Parent loadRD(){
        try {
            FXMLLoader rdLoader = new FXMLLoader(getClass().getResource("../View/reservationDetails.fxml"));
            return rdLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public manageReservationsController getRDController(){
        FXMLLoader rdLoader = new FXMLLoader(getClass().getResource("../View/reservationDetails.fxml"));
        try {
            rdLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rdLoader.getController();
    }
}
