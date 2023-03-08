/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Car;
import org.apache.pdfbox.pdmodel.PDDocument;
import java.io.FileNotFoundException;  
import java.io.FileOutputStream;  
import com.itextpdf.text.Document;  
import com.itextpdf.text.DocumentException;  
import com.itextpdf.text.Paragraph;  
import com.itextpdf.text.pdf.PdfWriter;  
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rima
 */
public class CreatePdf {  
        
    public CreatePdf() throws FileNotFoundException{
            
        try {
            
            Car car = new Car();
            String pdfFile = "C:\\Users\\rima\\OneDrive\\Documents";
            Document doc = new Document();
            
            PdfWriter.getInstance(doc, new FileOutputStream(pdfFile));
            
            doc.open();
            
            Paragraph par = new Paragraph("Test test test");
            doc.add(par);
            
            Paragraph mod = new Paragraph("Model: " + car.getModel());
            doc.add(mod);
            
            Paragraph mak = new Paragraph("Make: " + car.getModel());
            doc.add(mak);
            
            Paragraph loss = new Paragraph("Loss: " + car.getModel());
            doc.add(loss);
            
            Paragraph ft = new Paragraph("FuelType: " + car.getModel());
            doc.add(ft);
            
            Paragraph type = new Paragraph("Type: " + car.getModel());
            doc.add(type);
            
            Paragraph pd = new Paragraph("Primarydamage: " + car.getModel());
            doc.add(pd);
            
            Paragraph sd = new Paragraph("Secoondarydamage: " + car.getModel());
            doc.add(sd);
            
            Paragraph mileage = new Paragraph("Mileage: " + car.getModel());
            doc.add(mileage);
            
            Paragraph fp = new Paragraph("Fiscalpower: " + car.getModel());
            doc.add(fp);
            
            Paragraph color = new Paragraph("Color: " + car.getModel());
            doc.add(color);
            
            Paragraph desc = new Paragraph("Description: " + car.getModel());
            doc.add(desc);
            
            Paragraph year = new Paragraph("Year: " + car.getModel());
            doc.add(year);
            
            Paragraph trans = new Paragraph("Transmission: " + car.getModel());
            doc.add(trans);
            
            
            doc.close();
            
            System.out.println("done 111111111111111111");
        } catch (DocumentException ex) {
            System.err.println(ex);
        }
    }
    
}
