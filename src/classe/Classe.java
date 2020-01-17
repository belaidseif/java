package classe;

import departement.Departement;

public class Classe {
	private Integer id;
	private String name;
	private Departement departement;
	public Couple[] emploi1 = {null,null,null,null,null,null,null,null,null};
	public Couple[] emploi2 = {null,null,null,null,null,null,null,null,null};

	public boolean isOK1() {
		for(Couple c : emploi1) {
			if(c == null)
				return false;
		}
		return true;
		
	}
	public boolean isOK2() {
		for(Couple c : emploi2) {
			if(c == null)
				return false;
		}
		return true;
	}
	public Classe(Integer id, String name, Departement departement) {
		super();
		this.id = id;
		this.name = name;
		this.departement = departement;
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
	public Departement getDepartement() {
		return departement;
	}
	public void setDepartement(Departement departement) {
		this.departement = departement;
	}
	@Override
	public String toString() {
		return departement.getName()+"-"+name ;
	}
	

}
