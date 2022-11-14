package tn.enicar.PDFexporter;

import java.awt.Color;
import java.io.IOException;
import java.util.List;
 
import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

import tn.enicar.DAO.entities.User;
 


public class UserPDFExporter {
	private List<User> listUsers;
    
    public UserPDFExporter(List<User> listUsers) {
        this.listUsers = listUsers;
    }
 
    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);
         
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);
         
        cell.setPhrase(new Phrase("ID", font));
         
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("E-mail", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Nom ", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Role", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Les binomes", font));
        table.addCell(cell);       
    }
     
    private void writeTableData(PdfPTable table) {
        for (User user : listUsers) {
            table.addCell(String.valueOf(user.getUserId()));
            table.addCell(user.getEmail());
            table.addCell(user.getUsername());
            table.addCell(user.getRole().toString());
            table.addCell(user.getEtudiants().toString());
            //table.addCell(user.getChoixItems().toString() );
            		
        }
    }
     
    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
         
        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);
         
        Paragraph p = new Paragraph("Liste des utilisateurs", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);
         
        document.add(p);
         
        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {1.2f, 3.2f, 2.0f, 2.0f, 4.0f});
        table.setSpacingBefore(10);
         
        writeTableHeader(table);
        writeTableData(table);
         
        document.add(table);
         
        document.close();
         
    }

}
