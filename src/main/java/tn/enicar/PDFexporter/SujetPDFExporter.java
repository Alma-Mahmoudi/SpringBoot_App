package tn.enicar.PDFexporter;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import tn.enicar.DAO.entities.Sujet;

public class SujetPDFExporter {
private List<Sujet> listSujets;
    
    public SujetPDFExporter(List<Sujet> listSujets) {
        this.listSujets = listSujets;
    }
 
    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.yellow);
        	
        cell.setPadding(5);
         
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.black);
         
        cell.setPhrase(new Phrase("ID", font));
         
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Titre", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Description", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Specialite", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Encadrant", font));
        table.addCell(cell);       
        
        cell.setPhrase(new Phrase("Email Encadrant", font));
        table.addCell(cell);  
        
    }
     
    private void writeTableData(PdfPTable table) {
        for (Sujet sujet : listSujets) {
            table.addCell(String.valueOf(sujet.getIdSujet()));
            table.addCell(sujet.getTitre());
            table.addCell(sujet.getDescription());
            table.addCell(sujet.getSpecialite().name());
            table.addCell(sujet.getEncadrant());
            table.addCell(sujet.getEmail_encadrant());
          		
        }
    }
     
    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
         
        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.black);
        		
         
        Paragraph p = new Paragraph("Liste des sujets", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);
         
        document.add(p);
         
        PdfPTable table = new PdfPTable(6);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {0.6f, 2.7f, 3.5f, 1.9f,2.2f, 3.3f}); //1.4:1.3
        table.setSpacingBefore(10);
         
        writeTableHeader(table);
        writeTableData(table);
         
        document.add(table);
         
        document.close();
         
    }


}
