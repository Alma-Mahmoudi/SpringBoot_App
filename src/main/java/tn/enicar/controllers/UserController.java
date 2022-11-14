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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lowagie.text.DocumentException;

import tn.enicar.DAO.entities.Role;
import tn.enicar.DAO.entities.User;
import tn.enicar.DAO.repository.UserRepository;
import tn.enicar.PDFexporter.UserPDFExporter;
import tn.enicar.service.UserService;

//accés a localhost d'Angular
//@CrossOrigin(origins= "http://localhost:4200/")
@CrossOrigin("*") 
@RestController
@RequestMapping("/api/users")
public class UserController {
	
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserRepository userRep ;
	/*
	@Autowired
	GroupeService groupeService ; 
	*/
	
	//-------------------------------------------
	//---- get all users :
	@GetMapping
	public ResponseEntity<List<User>> getAllUsers(){
		return new ResponseEntity<>(userService.getAllUser(),HttpStatus.OK);
	}
	
	//---- get user by id
	@GetMapping("/{userId}")
	public ResponseEntity<User> getUser(@PathVariable("userId") Long userId){
		return new ResponseEntity<>(userService.getById(userId),HttpStatus.OK);
	}
	
	//---- save user (create user) :
	@PostMapping
	public ResponseEntity<User> addUser(@RequestBody User user) {
			return ResponseEntity.ok().body(userService.addUser(user));
	}
	
//-------Role-----------------------------------------------------------------------------------------------------------
	@PostMapping("/role/save")
	public ResponseEntity<Role>saveRole(@RequestBody Role role) {
		    return ResponseEntity.ok().body(userService.saveRole(role));
	}
	@PostMapping("/role/addtouser")
	public ResponseEntity<?>addRoleToUser(@RequestBody RoleToUserForm form) {
		userService.addRoleToUser(form.getUsername(), form.getRoleName() );
		return ResponseEntity.ok().build();
	}
	
//------------------------------------------------------------------------------------------------------------------
	//update user by id 
	@PutMapping("/{userId}")
	public ResponseEntity<User> updateUser(@PathVariable Long userId,@RequestBody User user) {
		return ResponseEntity.ok(userService.updateUser(userId, user));
	}
    /*
    @Transactional
    @DeleteMapping("/{userId}")
    public String deleteUser (@PathVariable("userId") Long userId) {
        userService.deleteUser(userId);
        return "User: "+ userId +" ==> deleted successfully";  
    }*/
	@DeleteMapping("/{userId}")
	public ResponseEntity<Map<String , Boolean>> deleteSujet(@PathVariable long userId){
		User user =userRep.getById(userId);
			
		userRep.delete(user);
		Map<String ,Boolean> response =new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
		}


    //-----------  Login   ---------------------------
    @GetMapping("/info/{email}/{password}")
	public User getByInfo(@PathVariable String email,@PathVariable String password) {
		return this.userService.getByInfo(email,password);
	}
    
    
    //j'ai besoi de cette conteoller pour le Role
    @RequestMapping(value ="/login/{username}",method = RequestMethod.GET)
	public User getUserByUsernamePassword(@PathVariable("username") String username) {
	return userRep.findByUsername(username);
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
           
          List<User> listUsers = userService.listAll();
           
          UserPDFExporter exporter = new UserPDFExporter(listUsers);
          exporter.export(response);
           
      }
    
    
    
	
}

class RoleToUserForm{
	private String username;
	private String roleName;
	public RoleToUserForm(String username, String roleName) {
		this.username = username;
		this.roleName = roleName;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	} 
	
}
