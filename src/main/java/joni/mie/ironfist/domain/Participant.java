package joni.mie.ironfist.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Participant {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long partid;
	private String team;
	private String status;
	
	@ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "fighterid")
    private Fighter fighter;
	
	@ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "fightid")
    private Fight fight;

	public Participant() {
		super();
	}

	public Participant(String team, String status, Fighter fighter, Fight fight) {
		super();
		this.team = team;
		this.status = status;
		this.fighter = fighter;
		this.fight = fight;
	}

	public Long getPartid() {
		return partid;
	}

	public void setPartid(Long partid) {
		this.partid = partid;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Fighter getFighter() {
		return fighter;
	}

	public void setFighter(Fighter fighter) {
		this.fighter = fighter;
	}

	public Fight getFight() {
		return fight;
	}

	public void setFight(Fight fight) {
		this.fight = fight;
	}

	@Override
	public String toString() {
		return "Participant [partid=" + partid + ", team=" + team + ", status=" + status + ", fighter=" + fighter
				+ ", fight=" + fight + "]";
	}

}
