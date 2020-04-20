package joni.mie.ironfist.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Fighter {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long fighterid;
	private String name;
	private String style;
	private String height;
	private String weight;
	
	public Fighter(String name, String style, String height, String weight) {
		super();
		this.name = name;
		this.style = style;
		this.height = height;
		this.weight = weight;
	}
	
	public Fighter(Long fighterid, String name, String style, String height, String weight) {
		super();
		this.fighterid = fighterid;
		this.name = name;
		this.height = height;
		this.weight = weight;
		this.style = style;
	}
	
	public Fighter() {
		super();
	}

	public Long getFighterid() {
		return fighterid;
	}

	public void setFighterid(Long fighterid) {
		this.fighterid = fighterid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	@Override
	public String toString() {
		return "Fighter [fighterid=" + fighterid +
				", name=" + name +
				", height=" + height +
				", weight=" + weight +
				", style=" + style
				+ "]";
	}

}
