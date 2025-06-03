package Utility;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.action.PdfAction;
import com.itextpdf.kernel.pdf.annot.PdfLinkAnnotation;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Link;
import com.itextpdf.layout.element.Paragraph;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.util.List;

public class PDFReportGenerator {

    public static void generatePDFSummary(List<String> testCases) throws FileNotFoundException {
        PdfWriter writer = new PdfWriter("reports/SummaryReport.pdf");
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        document.add(new Paragraph("Test Case Summary Report").setBold());

        for (String tc : testCases) {
            PdfLinkAnnotation annotation = new PdfLinkAnnotation(new Rectangle(0, 0))
                    .setAction(PdfAction.createURI("file:reports/" + tc + ".pdf"));
            Link link = new Link(tc, annotation);
            document.add(new Paragraph(link));
        }

        document.close();
    }

    public static void generateDetailedPDF(String testCaseName, List<String> steps, List<String> screenshots) throws FileNotFoundException, MalformedURLException {
        PdfWriter writer = new PdfWriter("reports/" + testCaseName + ".pdf");
        PdfDocument pdf = new PdfDocument(writer);
        Document doc = new Document(pdf);
        doc.add(new Paragraph("Detailed Report for " + testCaseName).setBold());

        for (int i = 0; i < steps.size(); i++) {
            doc.add(new Paragraph((i + 1) + ". " + steps.get(i)));
            if (i < screenshots.size()) {
                Image img = new Image(ImageDataFactory.create(screenshots.get(i)));
                img.scaleToFit(400, 300);
                doc.add(img);
            }
        }
        doc.close();
    }
}
