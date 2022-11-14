package tn.enicar.controllers;

import java.util.List;

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

import tn.enicar.DAO.entities.Sujet;
import tn.enicar.DAO.entities.User;
import tn.enicar.DAO.entities.choix.ChoixItem;
import tn.enicar.DAO.entities.choix.ChoixItemPK;
import tn.enicar.service.ChoixService;
import tn.enicar.service.SujetService;
import tn.enicar.service.UserService;

//accés a localhost d'Angular
//@CrossOrigin(origins= "http://localhost:4200/")
@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class ChoixController {
	
	@Autowired
	private final UserService userService;
	@Autowired
	private final SujetService sujetService;
	@Autowired
	private final ChoixService choixItemService;
	

	public ChoixController(UserService userService,SujetService sujetService,ChoixService choixItemService) {
		this.userService = userService;
		this.sujetService=sujetService;
		this.choixItemService=choixItemService;
	}
	//-----------------------------------------------------------------------------------
	 //----- ajouter a user le choix d id suivante  :
    @PostMapping("/users/{userId}/choix/add/{sujetId}")
    public ResponseEntity<User> addToUserChoix (@PathVariable("userId") Long userId,
                                                @PathVariable("sujetId") Long sujetId) {
        User user = userService.getById(userId);
        Sujet sujet = sujetService.getById(sujetId);
        
        ChoixItem choixItem = new ChoixItem(user, sujet, 1);
        choixItemService.addChoixItem(choixItem);

        return new ResponseEntity<>(userService.getById(userId), HttpStatus.CREATED);
    }
    
	//----- view choix de chaque user :
    @GetMapping("/users/{userId}/choix")
    public ResponseEntity<List<ChoixItem>> getUserChoix (@PathVariable("userId") Long userId) {
        System.out.println(userService.getById(userId).getChoixItems().size() );
        return new ResponseEntity<>(userService.getById(userId).getChoixItems(), HttpStatus.OK);

    }
   
    //------- ajouter ou mise a jour le choix d'id user .. :
    @PutMapping("/users/{userId}/choix/update/{sujetId}")
    public ResponseEntity<User> updateChoixItem (@PathVariable("userId") Long userId,
                                                @PathVariable("sujetId") Long sujetId,
                                                @RequestBody ChoixItem choixItem) {
        User user = userService.getById(userId);
        Sujet sujet = sujetService.getById(sujetId);

        choixItem.setPk(new ChoixItemPK(user, sujet));
        choixItemService.updateChoixItem(choixItem); 

        return new ResponseEntity<>(userService.getById(userId), HttpStatus.OK);
    }
    //-------------- supprimer le choix de user  :
    @DeleteMapping("/users/{userId}/choix/remove/{sujetId}")
    public ResponseEntity<User> removeFromUserChoix (@PathVariable("userId") Long userId,
                                                    @PathVariable("sujetId") Long sujetId) {
        choixItemService.deleteChoixItem(userId, sujetId) ;

        return new ResponseEntity<>(userService.getById(userId), HttpStatus.OK);
    }
	//------------------------View-------------------------------------------------------------
   
    //-- tous le contenue de choix de all users :
    @GetMapping("/choix-items")
    public ResponseEntity<List<ChoixItem>> getChoixItems () {
        return ResponseEntity.ok(choixItemService.getChoixItems());
    }
    
    // --- voir le choix de user d 'id user et de sujet d'id idSuj :
    @GetMapping("/choix-items/{userId}/{sujetId}")
    public ResponseEntity<ChoixItem> getChoixItem (@PathVariable("userId") Long userId,
                                                 @PathVariable("sujetId") Long sujetId) {
        return ResponseEntity.ok(choixItemService.getChoixItem(userId, sujetId));
    }

}
