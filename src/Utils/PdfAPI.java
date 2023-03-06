/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Utils;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.awt.Color;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javafx.scene.control.Cell;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import Entities.SpareParts;


/**
 *
 * @author Yasmine
 */
public class PdfAPI {
    Connection cnx;
    PreparedStatement ste;

    public PdfAPI() throws SQLException {
        cnx = DBconnexion.getInstance().getConnection();

    }
    Document doc = new Document();

    public void listAllSpareParts() throws SQLException, FileNotFoundException, DocumentException, IOException {
        try {
            String requete = "SELECT * FROM spareparts";
            ste = cnx.prepareStatement(requete);
        } catch (SQLException ex) {

            System.out.println(ex.getMessage());
        }

        ResultSet rs = ste.executeQuery("SELECT * FROM carbid.`spareparts`");
        PdfWriter.getInstance(doc, new FileOutputStream("./spareparts.pdf"));

        doc.open();
        //Image image = Image.getInstance("C:\\Users\\drwhoo\\Desktop\\projet-webjava\\Projet3eme\\JavaApplication\\src\\HolidaysHiatus\\images\\logo.png");

        //document.add(new Paragraph("test\n  test")); 
        // doc.add(image);
        doc.add(new Paragraph("   "));
        doc.add(new Paragraph("  Liste of spareparts  "));
        doc.add(new Paragraph("   "));

        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100);
        PdfPCell cell;

        cell = new PdfPCell(new Phrase("Type", FontFactory.getFont("Comic Sans MS", 15)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Typec", FontFactory.getFont("Comic Sans MS", 15)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Description", FontFactory.getFont("Comic Sans MS", 15)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

       

        //cell = new PdfPCell(new Phrase("Prix", FontFactory.getFont("Comic Sans MS", 15)));
        //cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        //table.addCell(cell);
        while (rs.next()) {

            SpareParts c = new SpareParts();

            c.setType(rs.getString(2));
            c.setTypec(rs.getString(6));
            c.setDescription(rs.getString(4));
           
     

            cell = new PdfPCell(new Phrase(c.getType(), FontFactory.getFont("Comic Sans MS", 12)));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(c.getTypec(), FontFactory.getFont("Comic Sans MS", 12)));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(c.getDescription(), FontFactory.getFont("Comic Sans MS", 12)));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);

            

          

            //   table.addCell(cell1);
        }

        doc.add(table);
        doc.close();
        Desktop.getDesktop().open(new File("./spareparts.pdf"));
    }
}
