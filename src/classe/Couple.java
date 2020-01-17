package classe;

import enseignant.Enseignant;
import matiere.Matiere;

public class Couple {
	private Matiere matiere1;
	private Matiere matiere2;
	private Enseignant enseignant1;
	private Enseignant enseignant2;
	public boolean isDifferent = false;
	private int heureustique;
	
	
	
	public int getHeureustique() {
		if(!isDifferent)
			return matiere1.getHeureustique();
		else
			return 0;
		
	}
	public int getHeureustique2() {
		if(!isDifferent)
			return matiere1.getHeureustique2();
		else
			return 0;
		
	}

	public Enseignant getEnseignant1() {
		return enseignant1;
	}

	public void setEnseignant1(Enseignant enseignant1) {
		this.enseignant1 = enseignant1;
	}

	public Enseignant getEnseignant2() {
		return enseignant2;
	}

	public void setEnseignant2(Enseignant enseignant2) {
		this.enseignant2 = enseignant2;
	}

	
	
	public Couple() {
		super();
	}

	public Matiere getMatiere1() {
		return matiere1;
	}

	public void setMatiere1(Matiere matiere1) {
		this.matiere1 = matiere1;
	}

	public Matiere getMatiere2() {
		return matiere2;
	}

	public void setMatiere2(Matiere matiere2) {
		this.matiere2 = matiere2;
	}

	

	public static Couple twice(Matiere m) {
		return new Couple(m, m);
	}
	public boolean setOne(Matiere m) {
		if(matiere1 == null) {
			matiere1 = m;
			isDifferent = true;
			return true;
		}else if(matiere2 == null) {
			matiere2 = m;
			isDifferent = true;
			return true;
		}else
			return false;
	}

	private Couple(Matiere matiere1, Matiere matiere2) {
		super();
		this.matiere1 = matiere1;
		this.matiere2 = matiere2;
	}
	@Override
	public String toString() {
		return ""+matiere1+" "+enseignant1+" "+matiere2+" "+enseignant2;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((matiere1 == null) ? 0 : matiere1.hashCode());
		result = prime * result + ((matiere2 == null) ? 0 : matiere2.hashCode());
		return result;
	}

	@Override//matiere 1 et matiere 2
	public boolean equals(Object obj) {
		Couple other = (Couple)obj;
		if(other.getMatiere1().getName().equals(this.getMatiere1().getName())&&other.getMatiere2().equals(this.getMatiere2()))
			return true;
		return false;
	}

}
