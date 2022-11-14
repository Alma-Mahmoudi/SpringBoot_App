package tn.enicar.controllers;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lowagie.text.DocumentException;

import tn.enicar.DAO.entities.Sujet;
import tn.enicar.DAO.repository.SujetRepository;
import tn.enicar.PDFexporter.SujetPDFExporter;
import tn.enicar.service.SujetService;

//accés a localhost d'Angular
//@CrossOrigin(origins= "http://localhost:4200/")
@CrossOrigin("*")
@RestController
@RequestMapping("/api/sujets")
public class SujetController {
	
	@Autowired
	private SujetRepository sujRep ;
	
	@Autowired
	private SujetService sujetService ;
	
	//get all sujets
	@GetMapping
	public ResponseEntity<List<Sujet>> getUSujets(){
		return new ResponseEntity<>(sujetService.getAllSujets() ,HttpStatus.OK);	
	}
	
	//get Sujet by id
	@GetMapping("/{idSujet}")
	public ResponseEntity<Sujet> getSujet(@PathVariable("idSujet") Long idSujet){
		return new ResponseEntity<>(sujetService.getById(idSujet) ,HttpStatus.OK);
	}
	
	//create sujet
	@PostMapping
	public ResponseEntity<Sujet> createSujet(@RequestBody Sujet sujet) {
			return ResponseEntity.ok().body(sujetService.addSujet(sujet) );
	}
	
	//update sujet by id 
	@PutMapping("/{idSujet}")
	public ResponseEntity<Sujet> update (@PathVariable("idSujet") Long idSujet, @RequestBody Sujet sujetDetails){
		
		return ResponseEntity.ok(sujetService.updateSujet(idSujet, sujetDetails));
	}
   
	@DeleteMapping("/{idSujet}")
	public ResponseEntity<Map<String , Boolean>> deleteSujet(@PathVariable long idSujet){
		Sujet sujet=sujRep.getById(idSujet);
				
		
	    sujRep.delete(sujet);
		Map<String ,Boolean> response =new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
		}
	
	
//-------Controller PDF ---------------------

  	@GetMapping("/export/pdf")
      public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
          response.setContentType("application/pdf");
          DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
          String currentDateTime = dateFormatter.format(new Date());
           
          String headerKey = "Content-Disposition";
          String headerValue = "attachment; filename=users_" + currentDateTime + ".pdf";
          response.setHeader(headerKey, headerValue);
           
          List<Sujet> listSujets = sujetService.listAll();
           
          SujetPDFExporter exporter = new SujetPDFExporter(listSujets);
          exporter.export(response);
           
      }
	
	//-------------------------
	/*
	//Inscription a ce sujet
	@PutMapping("/abon/{id}")
	public Sujet abon(@RequestBody Sujet sujet,@PathVariable Long id) {
		return this.sujetService.abon(sujet, id);		
	}
	*/
	
}