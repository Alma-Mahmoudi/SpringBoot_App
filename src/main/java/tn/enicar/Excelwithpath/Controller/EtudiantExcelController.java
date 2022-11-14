package tn.enicar.Excelwithpath.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.enicar.DAO.entities.Etudiant;
import tn.enicar.DAO.repository.EtudiantRepository;
import tn.enicar.Excelwithpath.Excel.EtudiantExcelImporter;
import tn.enicar.exception.ResourceNotFoundExcep;

@RestController
@RequestMapping("/web")
@CrossOrigin(origins= "http://localhost:4200/")	
public class EtudiantExcelController {
	
	/*@RequestMapping("/home")
	public String homePage() {
		return "HomePage";
	}*/

	@Autowired
	private EtudiantRepository studentRepo;

	//----- ecrit ce lien pour importer le fichier excel (Insert) : 
	@RequestMapping("/etudiant/import/excel")
	@ResponseBody
	public String importFromExcel() {
		
		EtudiantExcelImporter excelImporter=new EtudiantExcelImporter();
		List<Etudiant> listStudent= excelImporter.excelImport();
		studentRepo.saveAll(listStudent);
		return "Import Successfully";
	}
	
	//------- get all students :
		@GetMapping("/etudiant")
		public List<Etudiant> getAllStudents(){
			return studentRepo.findAll();
		}		
		
	// ------ create student rest api :
		@PostMapping("/etudiant")
		public Etudiant createStudent(@RequestBody Etudiant etudiant) {
			return studentRepo.save(etudiant);
		}
		
	//------- get student by id rest api :
		@GetMapping("/etudiant/{id}")
		public ResponseEntity<Etudiant> getStudentById(@PathVariable Long id) {
			Etudiant etudiant = studentRepo.findById(id)
					.orElseThrow(() -> new ResourceNotFoundExcep("Etudiant not exist with id :" + id));
			return ResponseEntity.ok(etudiant);
		}
		
    // ------ update student rest api :
		
		@PutMapping("/etudiant/{id}")
		public ResponseEntity<Etudiant> updateEmployee(@PathVariable Long id, @RequestBody Etudiant etudiantDetails){
			Etudiant etudiant= studentRepo.findById(id)
					.orElseThrow(() -> new ResourceNotFoundExcep("Student not exist with id :" + id));
			
			etudiant.setNom(etudiantDetails.getNom());
			etudiant.setNom(etudiantDetails.getPrenom());
			etudiant.setAdr(etudiantDetails.getAdr());
			etudiant.setSpecialite(etudiantDetails.getSpecialite());
			etudiant.setScore(etudiantDetails.getScore());
			
			
			Etudiant updatedStudent = studentRepo.save(etudiant);
			return ResponseEntity.ok(updatedStudent);
		}
		
	  //------ delete student rest api :
		@DeleteMapping("/etudiant/{id}")
		public ResponseEntity<Map<String, Boolean>> deleteStudent(@PathVariable Long id){
			Etudiant etudiant = studentRepo.findById(id)
					.orElseThrow(() -> new ResourceNotFoundExcep("Etudiant not exist with id :" + id));
			
			studentRepo.delete(etudiant);
			Map<String, Boolean> response = new HashMap<>();
			response.put("deleted", Boolean.TRUE);
			return ResponseEntity.ok(response);
		}
		

}

