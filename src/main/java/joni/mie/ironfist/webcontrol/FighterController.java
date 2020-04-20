package joni.mie.ironfist.webcontrol;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import joni.mie.ironfist.domain.Fighter;
import joni.mie.ironfist.domain.FighterRepository;


@Controller
public class FighterController {
	
	@Autowired
	private FighterRepository FrRepository;	
	
	// --------------------- FIGHTERS ---------------------------
	
	
	//list of fighters
	@RequestMapping(value="/fighterlist", method = RequestMethod.GET)
	public String fighter(Model model) {
		model.addAttribute("fighters", FrRepository.findAll());
		return "fighterlist"; 
	}	
	
	// RESTful service to get all fighters
    @RequestMapping(value="/fighters", method = RequestMethod.GET)
    public @ResponseBody List<Fighter> fighterListRest() {	
        return (List<Fighter>) FrRepository.findAll();	
    }
    
	// Empty fighter form
    @PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/newfighter", method = RequestMethod.GET)
	public String getNewFighterForm(Model model) {
		model.addAttribute("fighter", new Fighter());
		return "fighterform";
	}

	// Filled fighter thing for editing.
    @PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/editfighter/{fighterid}", method = RequestMethod.GET)
	public String getEditFighterForm(Model model, @PathVariable("fighterid") Long fighterid) {
		model.addAttribute("fighter", FrRepository.findById(fighterid)); //Using ID to find the right fighter
		return "editfighterform";
	}

	// saving the fighter
    @PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/savefighter", method = RequestMethod.POST)
	public String saveFighter(@ModelAttribute Fighter fighter) {
		// saving the fighter into the h2.
		FrRepository.save(fighter);
		return "redirect:/fighterlist";
	}

	// Removing a fighter.
    @PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/deletefighter/{fighterid}", method = RequestMethod.GET)
	public String deleteFighter(@PathVariable("fighterid") Long fighterid) {
		FrRepository.deleteById(fighterid);
		return "redirect:../fighterlist";
	}

}
