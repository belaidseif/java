package application;

import matiere.Matiere;

public class MissedProfException extends Exception {
	Matiere matiere;

	public MissedProfException(Matiere matiere) {
		super();
		this.matiere = matiere;
	}

	public Matiere getMatiere() {
		return matiere;
	}
	
	
}
