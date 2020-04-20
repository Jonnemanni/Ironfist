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

import joni.mie.ironfist.domain.Arena;
import joni.mie.ironfist.domain.ArenaRepository;


@Controller
public class ArenaController {
	
	@Autowired
	private ArenaRepository ARepository;
	
	// --------------------- ARENAS ---------------------------
	
		
	//list of arenas
	@RequestMapping(value="/arenalist", method = RequestMethod.GET)
	public String arenas(Model model) {
		model.addAttribute("arenas", ARepository.findAll());
		return "arenalist"; 
	}
	
	// RESTful service to get all arenas
    @RequestMapping(value="/arenas", method = RequestMethod.GET)
    public @ResponseBody List<Arena> arenaListRest() {	
        return (List<Arena>) ARepository.findAll();	
    }
    
	// Empty arena form
    @PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/newarena", method = RequestMethod.GET)
	public String getNewArenaForm(Model model) {
		model.addAttribute("arena", new Arena());
		return "arenaform";
	}

	// Filled fighter thing for editing.
    @PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/editarena/{id}", method = RequestMethod.GET)
	public String getEditArenaForm(Model model, @PathVariable("id") Long Id) {
		model.addAttribute("arena", ARepository.findById(Id)); //Using ID to find the right arena
		return "editarenaform";
	}

	// saving the fighter
    @PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/savearena", method = RequestMethod.POST)
	public String saveArena(@ModelAttribute Arena arena) {
		// saving the fighter into the h2.
		ARepository.save(arena);
		return "redirect:/arenalist";
	}

	// Removing a fighter.
    @PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/deletearena/{id}", method = RequestMethod.GET)
	public String deleteArena(@PathVariable("id") Long Id) {
		ARepository.deleteById(Id);
		return "redirect:../arenalist";
	}
}
