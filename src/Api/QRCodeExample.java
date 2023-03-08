///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package Api;
//import java.io.IOException;
//import org.apache.pdfbox.pdmodel.PDDocument;
//import org.apache.pdfbox.pdmodel.PDPage;
//import org.apache.pdfbox.pdmodel.common.PDRectangle;
//import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
//import org.apache.pdfbox.pdmodel.graphics.image.LosslessFactory;
//import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
//import com.google.zxing.WriterException;
//import com.google.zxing.qrcode.QRCodeWriter;
//import com.google.zxing.qrcode.encoder.ByteMatrix;
//
//public class QRCodeExample {
//   public static void main(String[] args) throws IOException, WriterException {
//      
//      // Create a new document
//      PDDocument doc = new PDDocument();
//      
//      // Add a new page to the document
//      PDPage page = new PDPage(PDRectangle.A4);
//      doc.addPage(page);
//      
//      // Create a new content stream for the page
//      PDPageContentStream contentStream = new PDPageContentStream(doc, page);
//      
//      // Create a new QR code writer
//      QRCodeWriter writer = new QRCodeWriter();
//      
//      // Generate the QR code image data
//      ByteMatrix matrix = writer.encode("https://example.com", com.google.zxing.BarcodeFormat.QR_CODE, 200, 200);
//      int width = matrix.getWidth();
//      int height = matrix.getHeight();
//      byte[][] array = matrix.getArray();
//      
//      // Create a new image object from the QR code data
//      PDImageXObject image = LosslessFactory.createFromImage(doc, array, 4, width, height, PDImageXObject.IMAGE_GRAY);
//      
//      // Draw the image on the page
//      contentStream.drawImage(image, 100, 400, 200, 200);
//      
//      // Close the content stream and save the document
//      contentStream.close();
//      doc.save("qrcode.pdf");
//      
//      // Close the document
//      doc.close();
//   }
//}