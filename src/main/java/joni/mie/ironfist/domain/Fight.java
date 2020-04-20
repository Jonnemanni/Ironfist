package joni.mie.ironfist.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Fight {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long fightid;
	
	@Size(min=3, max=20)
	private String name;
	
	@Size(min=3, max=10)
	private String type;
	
	private String msg;
	
	@ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "arenaid")
    private Arena arena;
	
	public Fight() {
		super();
	}
	
	public Fight(String name, String type, Arena arena) {
		super();
		this.name = name;
		this.type = type;
		this.arena = arena;
	}

	public Long getFightid() {
		return fightid;
	}

	public void setFightid(Long fightid) {
		this.fightid = fightid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Arena getArena() {
		return arena;
	}

	public void setArena(Arena arena) {
		this.arena = arena;
	}
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "Fight [fightid=" + fightid +
				", name=" + name +
				", type=" + type +
				", arena=" + arena + "]";
	}
	
}
