package joni.mie.ironfist.domain;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

//tietokantakäsittelyn rajapinta
public interface FightRepository extends CrudRepository <Fight, Long>  {
	
    List<Fight> findByName(String name);

}
