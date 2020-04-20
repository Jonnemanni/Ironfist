package joni.mie.ironfist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import joni.mie.ironfist.domain.Arena;
import joni.mie.ironfist.domain.ArenaRepository;
import joni.mie.ironfist.domain.Fight;
import joni.mie.ironfist.domain.FightRepository;
import joni.mie.ironfist.domain.Fighter;
import joni.mie.ironfist.domain.FighterRepository;
import joni.mie.ironfist.domain.Participant;
import joni.mie.ironfist.domain.ParticipantRepository;
import joni.mie.ironfist.domain.User;
import joni.mie.ironfist.domain.UserRepository;


@SpringBootApplication
public class IronfistApplication {
	
	private static final Logger log = LoggerFactory.getLogger(IronfistApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(IronfistApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner insertData(FighterRepository FrRepository, UserRepository URepository, ArenaRepository ARepository, FightRepository FRepository, ParticipantRepository PtRepository) {
		return (args) -> {
			
			log.info("Save a couple fighters");
			
			FrRepository.save(new Fighter("Eddy Gordo", "Capoeira", "188", "75"));
			FrRepository.save(new Fighter("Tiger Jackson", "Capoeira", "Unknown", "Unknown"));
			
			ARepository.save(new Arena("Lapland", "Boundless", "The cold wastes of northern finland avait for spilled blood."));
			ARepository.save(new Arena("Hartwall Arena", "Cage", "The cage hanging above the ring of Hartwall in helsinki is ready to drop at any moment."));
			ARepository.save(new Arena("Tuusula Center", "Standard", "Tuusula town center, where a fighting festival is looking for participants."));
			
			FRepository.save(new Fight("Coldnorth Marathon", "Endurance", ARepository.findByName("Lapland").get(0)));
			FRepository.save(new Fight("Helsinki Supersmash", "2V2", ARepository.findByName("Hartwall Arena").get(0)));
			
			PtRepository.save(new Participant("1", "Winner", FrRepository.findByName("Eddy Gordo").get(0), FRepository.findByName("Coldnorth Marathon").get(0)));
			PtRepository.save(new Participant("2", "Loser", FrRepository.findByName("Tiger Jackson").get(0), FRepository.findByName("Coldnorth Marathon").get(0)));
			PtRepository.save(new Participant("1", "Winner", FrRepository.findByName("Tiger Jackson").get(0), FRepository.findByName("Helsinki Supersmash").get(0)));
			
			// Create users: admin/admin user/user
			User user1 = new User("Joni", "$2a$04$GMZSaEXIsXs4zciwOQvCteEdjacIw6aTvuunm91y/FLNMfA30looC", "ADMIN", "Joni1999@gmail.com");
			URepository.save(user1);
			
		};
	}

}
