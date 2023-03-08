/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.BidController;
import Dao.AuctionDaoImplementation;
import Dao.CarDao;
import Entities.Car;
import Entities.CurrentUser;
import Utils.DBconnexion;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import java.net.HttpURLConnection;
import java.net.URL;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
//import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;






/**
 * FXML Controller class
 *
 * @author rima
 */
public class DisplayCarController implements Initializable {
    
    @FXML
    private Button btnBack;
    @FXML
    private Button btncarspecification;
    @FXML
    private Button btnphoto;
    @FXML
    private Button btnBid;
    @FXML
    private Button btnExport;
    @FXML
    private TextField tfDesc;
    @FXML
    private Pane Photos;
    @FXML
    private Pane Bidinformation;
    @FXML
    private Pane Carspecification;
    @FXML
    private Text textcolor;
    private Car selectedCar;
    @FXML
    private Text texttype;
    @FXML
    private Text entryPrice;
    @FXML
    private Text Textmodel;
    @FXML
    private Text Textyear;
    @FXML
    private Text textsd;
    @FXML
    private Text textfp;
    @FXML
    private Text textmileage;
    @FXML
    private Text textmake;
    @FXML
    private Text textpd;
    @FXML
    private Text textloss;
    @FXML
    private Text textft;
    @FXML
    private Text textDesc;
      @FXML
    private Text textOwner;
      @FXML
    private ImageView imageC;
      private javafx.scene.image.Image imageData;
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @FXML
    private void Back(ActionEvent event) {
        try {
            
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/ListCars.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            ListCarsController controller = loader.getController();
            
            stage.setScene(scene);
            stage.show();
            CarDao carDao;
            
        } catch (IOException ex) {
            Logger.getLogger(ListCarsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void fncarspecification(KeyEvent event) {
    }
    
    @FXML
    private void carspecification(ActionEvent event) {
    }
    
    @FXML
    private void fnphoto(KeyEvent event) {
        Carspecification.toFront();
    }
    
    @FXML
    private void photo(ActionEvent event) {
        Photos.toFront();
        
    }
    @FXML
    private void makeRes(ActionEvent event) {
    }
    
    @FXML
    private void setbid() {
       try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Bid.fxml"));
            AuctionDaoImplementation auc = new AuctionDaoImplementation();
            BidController bidcontroller = new BidController(CurrentUser.getUser().getId(), selectedCar.getId(), auc.getIdAuctionByCar(selectedCar.getId()));
            System.out.println(auc.getIdAuctionByCar(selectedCar.getId()));
            loader.setController(bidcontroller);
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("New View");
            stage.show();
            
        } catch (IOException ex) {
            Logger.getLogger(BidController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//
    public void setValue(Car car) throws IOException {

        this.selectedCar = car;
        initcar();
    }

    private void initcar() throws MalformedURLException, IOException {
        System.out.println(selectedCar);
        textcolor.setText(selectedCar.getColor());
        texttype.setText(selectedCar.getType());
        Textmodel.setText(selectedCar.getModel());
        Textyear.setText(selectedCar.getYear() + " ");
        entryPrice.setText(selectedCar.getbasevalue() + " ");
        textfp.setText(selectedCar.getFiscalpower() + " ");
        textmileage.setText(selectedCar.getMileage() + " ");
        texttype.setText(selectedCar.getType());
        textmake.setText(selectedCar.getMake());
        textloss.setText(selectedCar.getLoss());
        textft.setText(selectedCar.getFueltype());
        textDesc.setText(selectedCar.getDescription());
        textsd.setText(selectedCar.getSecondarydamage());
        textpd.setText(selectedCar.getPrimarydamage());
        textOwner.setText(selectedCar.getOwner().getName());
        System.out.println(selectedCar.getOwner().getName());
        System.out.println(selectedCar.getCarImg());
        String path = "http://localhost/piImg/"+selectedCar.getCarImg();
        //URL url = new URL(path);
        //HttpURLConnection connection = (HttpURLConnection) url.openConnection();
       // connection.setDoOutput(true);
      //  connection.setRequestMethod("GET");
      try {
       imageData =new javafx.scene.image.Image( new java.net.URL(path).openStream());
      //new javafx.scene.image.Image(in)
       imageC.setImage(imageData);

        // Decode the Base64 string to a byte array
        //byte[] decodedData = Base64.getDecoder().decode(imageData);
        //imageC.setImage(new javafx.scene.image.Image(imageData,245,237,false,true));
        //URL url = new URL(selectedCar.getCarImg()+"http://localhost/piImg/"+selectedCar.getCarImg());
        //System.out.println(url.getPath());
        //imageC.setImage(new javafx.scene.image.Image(url.openStream(),245,237,false,true));
        } catch (Exception e) {
            e.printStackTrace();
           // System.err.println(e.fillInStackTrace());
      }
    //    textcolor.setText(selectedCar.getColor());

    }
    
    @FXML
    private void exportPDF(ActionEvent event) throws SQLException, DocumentException, BadElementException, IOException {
        try {
            DBconnexion cnx = DBconnexion.getInstance();
            
            //PreparedStatement pst=null;
            //ResultSet rs=null;
            
            //Car car = new Car();
            
            //String guery = " select * from cars WHERE id_car= '" + car.getId()+"'";
            //try {
            
            //pst= cnx.getConnection().prepareStatement(guery);
            //rs= pst.executeQuery();
            
            String file_name = selectedCar.getId()+"_" +selectedCar.getMake()+".pdf";
            Document doc = new Document();
            PdfWriter.getInstance(doc, new FileOutputStream(file_name));
            
            doc.open();
            
            /*com.itextpdf.text.Image img = com.itextpdf.text.Image.getInstance("C:\\xampp\\htdocs\\FirstProject-main\\public\\uploads\\logo.png");
            img.scaleAbsoluteWidth(600);
            img.scaleAbsoluteHeight(92);
            img.setAlignment(com.itextpdf.text.Image.ALIGN_CENTER);
            doc.add(img); */
            
            doc.add(new Paragraph(" "));
            doc.add(new Paragraph("Car details",FontFactory.getFont(FontFactory.TIMES_BOLD,20,BaseColor.BLUE)));
            doc.add(new Paragraph(" "));
            
            PdfPTable table = new PdfPTable(13);
            table.setWidthPercentage(100);
            PdfPCell cell;
            cell = new PdfPCell (new Phrase("Model", FontFactory.getFont("Comic Sans MS",12)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.GRAY);
            table.addCell(cell);
            
            cell = new PdfPCell (new Phrase("Color", FontFactory.getFont("Comic Sans MS",12)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.GRAY);
            table.addCell(cell);
            
            cell = new PdfPCell (new Phrase("Type", FontFactory.getFont("Comic Sans MS",12)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.GRAY);
            table.addCell(cell);
            
            
            cell = new PdfPCell (new Phrase("Make", FontFactory.getFont("Comic Sans MS",12)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.GRAY);
            table.addCell(cell);
            
            
            cell = new PdfPCell (new Phrase("Description", FontFactory.getFont("Comic Sans MS",12)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.GRAY);
            table.addCell(cell);
            
            
            
            cell = new PdfPCell (new Phrase("Mileage", FontFactory.getFont("Comic Sans MS",12)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.GRAY);
            table.addCell(cell);
            
            
            
            cell = new PdfPCell (new Phrase("Year", FontFactory.getFont("Comic Sans MS",12)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.GRAY);
            table.addCell(cell);
            
            cell = new PdfPCell (new Phrase("Fiscalpower", FontFactory.getFont("Comic Sans MS",12)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.GRAY);
            table.addCell(cell);
            
            
            cell = new PdfPCell (new Phrase("Transmission", FontFactory.getFont("Comic Sans MS",12)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.GRAY);
            table.addCell(cell);
            
            cell = new PdfPCell (new Phrase("Loss", FontFactory.getFont("Comic Sans MS",12)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.GRAY);
            table.addCell(cell);
            
            cell = new PdfPCell (new Phrase("Primarydamage", FontFactory.getFont("Comic Sans MS",12)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.GRAY);
            table.addCell(cell);
            
            cell = new PdfPCell (new Phrase("Secondarydamage", FontFactory.getFont("Comic Sans MS",12)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.GRAY);
            table.addCell(cell);
            
            cell = new PdfPCell (new Phrase("Fueltype", FontFactory.getFont("Comic Sans MS",12)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.GRAY);
            table.addCell(cell);
            
            
            /////////////////////////////////////////////////////////////////////////////////
            /*while(rs.next()) {
            
            car.setModel(rs.getString("model"));
            car.setColor(rs.getString("color"));
            car.setType(rs.getString("type"));
            car.setMake(rs.getString("make"));
            car.setDescription(rs.getString("description"));
            car.setMileage(rs.getInt("mileage"));
            car.setYear(rs.getInt("year"));
            car.setFiscalpower(rs.getInt("fiscalpower"));
            car.setTransmission(rs.getString("transmission"));
            car.setLoss(rs.getString("loss"));
            car.setPrimarydamage(rs.getString("primarydamage"));
            car.setSecondarydamage(rs.getString("secondarydamage"));
            car.setFueltype(rs.getString("fueltype"));
            System.out.println(rs.next());
            */
            cell = new PdfPCell (new Phrase(selectedCar.getModel(), FontFactory.getFont("Arial",12)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            System.out.println(cell);
            
            cell = new PdfPCell (new Phrase(selectedCar.getColor(), FontFactory.getFont("Arial",12)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            
            cell = new PdfPCell (new Phrase(selectedCar.getType(), FontFactory.getFont("Arial",12)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            
            cell = new PdfPCell (new Phrase(selectedCar.getMake(), FontFactory.getFont("Arial",12)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            
            cell = new PdfPCell (new Phrase(selectedCar.getDescription(), FontFactory.getFont("Arial",12)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            
            cell = new PdfPCell (new Phrase((Integer.toString(selectedCar.getMileage())), FontFactory.getFont("Arial",12)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            
            cell = new PdfPCell (new Phrase((Integer.toString(selectedCar.getYear())), FontFactory.getFont("Arial",12)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            
            cell = new PdfPCell (new Phrase((Integer.toString(selectedCar.getFiscalpower())), FontFactory.getFont("Arial",12)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            
            cell = new PdfPCell (new Phrase(selectedCar.getTransmission(), FontFactory.getFont("Arial",12)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            
            cell = new PdfPCell (new Phrase(selectedCar.getLoss(), FontFactory.getFont("Arial",12)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            
            cell = new PdfPCell (new Phrase(selectedCar.getPrimarydamage(), FontFactory.getFont("Arial",12)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            
            cell = new PdfPCell (new Phrase(selectedCar.getSecondarydamage(), FontFactory.getFont("Arial",12)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            
            cell = new PdfPCell (new Phrase(selectedCar.getFueltype(), FontFactory.getFont("Arial",12)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            
            doc.add(table);
            com.itextpdf.text.Image imagePdf = com.itextpdf.text.Image.getInstance(SwingFXUtils.fromFXImage(imageData, null),null);
            //imagePdf.setDpi(150, 100);
            imagePdf.scaleAbsolute(300, 250);
            imagePdf.setAbsolutePosition(150, 400);
            
            doc.add( imagePdf);

            System.out.println("PDF exported");
            doc.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DisplayCarController.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        
        
        //} catch (DocumentException | SQLException | IOException ex) {
           // Logger.getLogger(DisplayCarController.class.getName()).log(Level.SEVERE, null, ex);
        //}
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Car details exported to PDF Sheet");
            alert.show();

            
}
           
    }
        
   // }


