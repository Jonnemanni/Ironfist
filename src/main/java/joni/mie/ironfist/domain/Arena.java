package joni.mie.ironfist.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Arena {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long arenaid;
	private String name;
	private String type;
	private String description;
	
	@JsonBackReference
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "arena")
	private List<Fight> fights;
	
	public Arena(String name, String type, String description) {
		super();
		this.name = name;
		this.type = type;
		this.description = description;
	}
	
	public Arena(Long arenaid, String name, String type, String description) {
		super();
		this.arenaid = arenaid;
		this.name = name;
		this.type = type;
		this.description = description;
	}

	public Arena() {
		super();
	}

	public Long getArenaid() {
		return arenaid;
	}

	public void setArenaid(Long arenaid) {
		this.arenaid = arenaid;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public List<Fight> getFights() {
		return fights;
	}

	public void setFights(List<Fight> fights) {
		this.fights = fights;
	}

	@Override
	public String toString() {
		return "Arena [arenaid=" + arenaid +
				", name=" + name +
				", type=" + type +
				", description=" + description + "]";
	}
	
	
	
}
