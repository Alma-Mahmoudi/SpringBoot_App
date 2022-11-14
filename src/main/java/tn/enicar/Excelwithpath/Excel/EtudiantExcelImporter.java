package tn.enicar.Excelwithpath.Excel;


import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import tn.enicar.DAO.entities.Etudiant;


public class EtudiantExcelImporter {
	public List<Etudiant> excelImport(){
		List<Etudiant> listStudent=new ArrayList<>();
		 long id=0;
		 String nom="";
	     String prenom="";
		 String adr="";
		 String specialité="";
		 float score=0;
		
		
		String excelFilePath="D:\\PFA2020-VFinale\\Excel\\etudiants.xlsx";
		
		long start = System.currentTimeMillis();
		
		FileInputStream inputStream;
		try {
			inputStream = new FileInputStream(excelFilePath);
			Workbook workbook=new XSSFWorkbook(inputStream);
			Sheet firstSheet=workbook.getSheetAt(0);
			Iterator<Row> rowIterator=firstSheet.iterator();
			rowIterator.next();
			
			while(rowIterator.hasNext()) {
				Row nextRow = rowIterator.next();
				Iterator<Cell> cellIterator=nextRow.cellIterator();
				while(cellIterator.hasNext()) {
					Cell nextCell=cellIterator.next();
					int columnIndex=nextCell.getColumnIndex();
					switch (columnIndex) {
					case 0:
						id=(long)nextCell.getNumericCellValue();
						System.out.println(id);
						break;
					case 1:
						nom=nextCell.getStringCellValue();
						System.out.println(nom);
						break;
					case 2:
						prenom=nextCell.getStringCellValue();
						System.out.println(prenom);
						break;
					case 3:
						adr=nextCell.getStringCellValue();
						System.out.println(adr);
						break;
					case 4:
						specialité=nextCell.getStringCellValue();
						System.out.println(specialité);
						break;
					case 5:
						score=(float)nextCell.getNumericCellValue();
						System.out.println(score);
						break;
					
					}
					listStudent.add(new Etudiant(id , nom , prenom , adr , specialité,score ));
				}
			}
			
			workbook.close();
			long end = System.currentTimeMillis();
			System.out.printf("Import done in %d ms\n", (end - start));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}
		
		return listStudent;
	}

}