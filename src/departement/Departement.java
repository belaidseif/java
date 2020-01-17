package departement;
import java.util.HashMap;
import java.util.*;

import matiere.*;
public class Departement {
	private Integer id;
	private String name;
	
	public Map<Matiere, Integer> listMatier1 = new HashMap<>();
	public Map<Matiere, Integer> listMatier2 = new HashMap<>();
	
	public Departement(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String toString() {
		return name;
	}
	

}
