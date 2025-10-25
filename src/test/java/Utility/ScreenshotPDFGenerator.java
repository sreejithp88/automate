package Utility;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.util.List;

public class ScreenshotPDFGenerator {

    public static void createPdfFromScreenshots(String folderPath, List<String> screenshotPaths) {
        if (screenshotPaths == null || screenshotPaths.isEmpty()) return;

        String pdfPath = folderPath + "Screenshots.pdf";
        Document document = new Document(PageSize.A4);

        try {
            PdfWriter.getInstance(document, new FileOutputStream(pdfPath));
            document.open();

            for (String path : screenshotPaths) {
                Image img = Image.getInstance(path);
                img.scaleToFit(PageSize.A4.getWidth() - 50, PageSize.A4.getHeight() - 50);
                img.setAlignment(Image.ALIGN_CENTER);
                document.add(img);
                document.newPage();
            }

            System.out.println("📄 PDF created: " + pdfPath);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            document.close();
        }
    }
}
