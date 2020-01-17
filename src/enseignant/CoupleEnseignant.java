package enseignant;

import classe.Classe;
import matiere.Matiere;

public class CoupleEnseignant {
	private Matiere matiere;
	private Classe classe;
	
	public CoupleEnseignant(Matiere matiere, Classe classe) {
		super();
		this.matiere = matiere;
		this.classe = classe;
	}
	public Matiere getMatiere() {
		return matiere;
	}
	public void setMatiere(Matiere matiere) {
		this.matiere = matiere;
	}
	public Classe getClasse() {
		return classe;
	}
	public void setClasse(Classe classe) {
		this.classe = classe;
	}
	@Override
	public String toString() {
		return  ""+matiere +" "+ classe ;
	}
	

}
