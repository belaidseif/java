package matiere;

import java.util.ArrayList;
import java.util.List;

import enseignant.Enseignant;

public class Matiere {
	private Integer id;
	private String name;
	public List<Enseignant> listEnseignant = new ArrayList<Enseignant>();
	private int heureustique;
	private int heureustique2;
	
	
	public int getHeureustique() {
		heureustique = 0;
		for(Enseignant ens: listEnseignant)
			heureustique+=ens.getHeureustique();
		return heureustique;
	}
	public int getHeureustique2() {
		heureustique2 = 0;
		for(Enseignant ens: listEnseignant)
			heureustique2+=ens.getHeureustique2();
		return heureustique2;
	}
	
	@Override//name and id
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Matiere other = (Matiere) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	public Matiere(Integer id, String name) {
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
	@Override
	public String toString() {
		return name;
	}
	@Override
	public int hashCode() {
		return id;
	}

}
