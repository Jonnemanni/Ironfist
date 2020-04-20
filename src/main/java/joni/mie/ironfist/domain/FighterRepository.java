package joni.mie.ironfist.domain;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

//tietokantakäsittelyn rajapinta
public interface FighterRepository extends CrudRepository <Fighter, Long>  {
	
    List<Fighter> findByName(String name);

}
