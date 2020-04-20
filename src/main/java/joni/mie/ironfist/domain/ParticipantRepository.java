package joni.mie.ironfist.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

//tietokantakäsittelyn rajapinta
public interface ParticipantRepository extends CrudRepository <Participant, Long>  {
	
	List<Participant> findByFight(Fight fight);

}
