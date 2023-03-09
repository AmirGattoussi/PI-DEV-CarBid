/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Api;

import Entities.Car;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Font;
import java.io.FileOutputStream;
import java.sql.Date;
import static javafx.scene.text.Font.font;
import static javafx.scene.text.Font.font;
import static javafx.scene.text.Font.font;

public class QRCodeExample {

    String model;
    String winnerName;
    double bidAmount;
    Date date;

    public static BitMatrix generateQRcode(String data, int h, int w) throws WriterException, IOException {
        String charset = "UTF-8";
        Map<EncodeHintType, ErrorCorrectionLevel> hashMap = new HashMap<>();
        hashMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
        return new MultiFormatWriter().encode(new String(data.getBytes(charset), charset), BarcodeFormat.QR_CODE, w, h, hashMap);
    }
    public static void main(String[] args) throws WriterException, IOException, NotFoundException, DocumentException {
        String data = "You won the auction.";
        int width = 100;
        int height = 100;
        BitMatrix bitMatrix = generateQRcode(data, height, width);
        Document document = new Document();

        try {
            PdfWriter.getInstance(document, new FileOutputStream("qr_code.pdf"));
            document.open();
            Paragraph paragraph = new Paragraph ("Company: Carbid Corporation");        
            paragraph.setAlignment(Paragraph.ALIGN_RIGHT);
            paragraph.setFont(FontFactory.getFont(FontFactory.TIMES_ROMAN, 10));
            document.add(paragraph);
            Paragraph paragraph1 = new Paragraph("Email: carbid@gmail.com       ");
            paragraph1.setAlignment(Paragraph.ALIGN_RIGHT);
            paragraph1.setFont(FontFactory.getFont(FontFactory.TIMES_ROMAN, 10));
            document.add(paragraph1);
            Paragraph paragraph2 = new Paragraph("Phone Number :71893102      ");
            paragraph2.setFont(FontFactory.getFont(FontFactory.TIMES_ROMAN, 10));
            paragraph2.setAlignment(Paragraph.ALIGN_RIGHT);
            document.add(paragraph2);
            Paragraph paragraph0 = new Paragraph();
            Chunk boldText0 = new Chunk("\nCongratulations for winning the auction");
            boldText0.setFont(FontFactory.getFont(FontFactory.HELVETICA_BOLD, 15)); // Set font and size for bold text
            paragraph0.add(boldText0);
            paragraph0.setAlignment(Paragraph.ALIGN_CENTER);
            document.add(paragraph0);
            Paragraph msg = new Paragraph("\n\nThank you for participating in the auction. We hope you enjoy your purchase!");
            document.add(msg);
            Paragraph payement = new Paragraph();
            Chunk boldText1 = new Chunk("\nPayment information :");
            boldText1.setFont(FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12)); // Set font and size for bold text
            payement.add(boldText1);
            document.add(payement);
            Paragraph payementd = new Paragraph("We're excited to help you proceed with the payment and shipping of your new vehicle. To complete the payment, please follow the payment instructions that will be provided by the email. Once the payment has been processed, please send us the confirmation details along with your shipping address");
            document.add(payementd);
            Image qrCodeImage = Image.getInstance(MatrixToImageWriter.toBufferedImage(bitMatrix), null);
            document.add(qrCodeImage);
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
