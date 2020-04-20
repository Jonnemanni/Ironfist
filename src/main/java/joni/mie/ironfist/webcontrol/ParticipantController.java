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

import joni.mie.ironfist.domain.Fight;
import joni.mie.ironfist.domain.FightRepository;
import joni.mie.ironfist.domain.FighterRepository;
import joni.mie.ironfist.domain.Participant;
import joni.mie.ironfist.domain.ParticipantRepository;


@Controller
public class ParticipantController {
	
	@Autowired
	private ParticipantRepository PtRepository;
	
	@Autowired
	private FightRepository FtRepository;
	
	@Autowired
	private FighterRepository FrRepository;
	
	
	// --------------------- Participants ---------------------------
	
	
	//list of fights
	@RequestMapping(value="/partlist/{fightid}", method = RequestMethod.GET)
	public String findParticipantByFight(Model model, @PathVariable("fightid") Long fightid) {
		model.addAttribute("participants", PtRepository.findByFight(FtRepository.findById(fightid).get()));
		model.addAttribute("fight", FtRepository.findById(fightid).get());
		return "participantlist"; 
	}
	
	// RESTful service to get all fighters
    @RequestMapping(value="/participants/{fightid}", method = RequestMethod.GET)
    public @ResponseBody List<Participant> partListRest(@PathVariable("fightid") Long fightid) {	
        return (List<Participant>) PtRepository.findByFight(FtRepository.findById(fightid).get());	
    }
	
	// RESTful service to get all fighters
    @RequestMapping(value="/participants", method = RequestMethod.GET)
    public @ResponseBody List<Participant> fullPartListRest() {	
        return (List<Participant>) PtRepository.findAll();	
    }
    
	// Empty fighter form
    @PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/newpart/{fightid}", method = RequestMethod.GET)
	public String getNewParticipantForm(Model model, @PathVariable("fightid") Long fightid) {
		model.addAttribute("participant", new Participant());
		model.addAttribute("fights", FtRepository.findAll());
		model.addAttribute("fighters", FrRepository.findAll());
		return "partform";
	}

	// Filled fighter thing for editing.
    @PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/editpart/{partid}", method = RequestMethod.GET)
	public String getEditPartForm(Model model, @PathVariable("partid") Long partid) {
		model.addAttribute("participant", PtRepository.findById(partid)); //Using ID to find the right fighter
		model.addAttribute("fighters", FrRepository.findAll());
		model.addAttribute("fights", FtRepository.findAll());
		return "editpartform";
	}

	// saving the fighter
    @PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/savepart", method = RequestMethod.POST)
	public String saveFight(@ModelAttribute Participant participant) {
		// saving the participatn into the h2.
		PtRepository.save(participant);
		return "redirect:/fightlist";
	}

	// Removing a fighter.
    @PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/deletepart/{partid}", method = RequestMethod.GET)
	public String deleteFight(@PathVariable("partid") Long partid) {
		PtRepository.deleteById(partid);
		return "redirect:../fightlist";
    }
    
}
