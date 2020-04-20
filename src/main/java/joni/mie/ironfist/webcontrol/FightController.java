package joni.mie.ironfist.webcontrol;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import joni.mie.ironfist.domain.Fight;
import joni.mie.ironfist.domain.FightRepository;

import joni.mie.ironfist.domain.Arena;
import joni.mie.ironfist.domain.ArenaRepository;

@Controller
public class FightController {
	
	@Autowired
	private FightRepository FtRepository;
	
	@Autowired
	private ArenaRepository ArRepository;
	
	
	// --------------------- FIGHTS ---------------------------
	
	
	//list of fights
	@RequestMapping(value="/fightlist", method = RequestMethod.GET)
	public String fight(Model model) {
		model.addAttribute("fights", FtRepository.findAll());
		return "fightlist"; 
	}
	
	// RESTful service to get all fighters
    @RequestMapping(value="/fights", method = RequestMethod.GET)
    public @ResponseBody List<Fight> fightListRest() {	
        return (List<Fight>) FtRepository.findAll();	
    }
    
	// Empty fighter form
    @PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/newfight", method = RequestMethod.GET)
	public String getNewFightForm(Model model) {
		model.addAttribute("fight", new Fight());
		model.addAttribute("arenas", ArRepository.findAll());
		return "fightform";
	}

	// Filled fighter thing for editing.
    @PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/editfight/{fightid}", method = RequestMethod.GET)
	public String getEditFightForm(Model model, @PathVariable("fightid") Long fightid) {
		model.addAttribute("fight", FtRepository.findById(fightid)); //Using ID to find the right fighter
		model.addAttribute("arenas", ArRepository.findAll());
		return "editfightform";
	}

	// saving the fighter
    @PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/savefight", method = RequestMethod.POST)
	public String saveFight(@ModelAttribute@Valid Fight fight, BindingResult bindingResult) {
		// saving the fighter into the h2.
    	if (bindingResult.hasErrors()) {
    		return "fightform";
    	}
		FtRepository.save(fight);
		return "redirect:/fightlist";
	}

	// Removing a fighter.
    @PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/deletefight/{fightid}", method = RequestMethod.GET)
	public String deleteFight(@PathVariable("fightid") Long fightid) {
		FtRepository.deleteById(fightid);
		return "redirect:../fightlist";
    }
    
}
