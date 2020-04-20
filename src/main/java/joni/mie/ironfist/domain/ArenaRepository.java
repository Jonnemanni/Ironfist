package joni.mie.ironfist.domain;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

//tietokantakäsittelyn rajapinta
public interface ArenaRepository extends CrudRepository <Arena, Long>  {
	
    List<Arena> findByName(String name);

}
