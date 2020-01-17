package enseignant;

import classe.Classe;
import matiere.Matiere;

public class Enseignant {
	private Integer id;
	private String nom;
	private String prenom;
	private boolean[] disponibilite= {false,false,false,false,false,false,false,false,false};
	private CoupleEnseignant[] newDisponibilite= {null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null};
	private CoupleEnseignant[] newDisponibilite2= {null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null};
	private int heureustique;
	private int heureustique2;
	
	public CoupleEnseignant getNewDisponibilite(int i) {
		return newDisponibilite[i];
	}
	public CoupleEnseignant getNewDisponibilite2(int i) {
		return newDisponibilite2[i];
	}
	public void setCoupleDisponibilite(Matiere matiere, Classe classe, int a) {
		CoupleEnseignant ce = new CoupleEnseignant(matiere, classe);
		newDisponibilite[a] = ce;
		newDisponibilite[a+1] = ce;
	}
	public void setCoupleDisponibilite2(Matiere matiere, Classe classe, int a) {
		CoupleEnseignant ce = new CoupleEnseignant(matiere, classe);
		newDisponibilite2[a] = ce;
		newDisponibilite2[a+1] = ce;
	}
	public void setOneDiponibilite(Matiere matiere, Classe classe, int a) {
		CoupleEnseignant ce = new CoupleEnseignant(matiere, classe);
		if(newDisponibilite[a]==null)
			newDisponibilite[a] = ce;
		else
			newDisponibilite[a+1] = ce;
	}
	public void setOneDiponibilite2(Matiere matiere, Classe classe, int a) {
		CoupleEnseignant ce = new CoupleEnseignant(matiere, classe);
		if(newDisponibilite2[a]==null)
			newDisponibilite2[a] = ce;
		else
			newDisponibilite2[a+1] = ce;
	}
	public int getHeureustique() {
		setHeureustique();
		return heureustique;
	}
	public int getHeureustique2() {
		setHeureustique2();
		return heureustique2;
	}
	public void setHeureustique() {
		int i=0;
		int j=0;
		int h=0;
		while(i<9) {
			if(!(disponibilite[i]||newDisponibilite[j]!=null||newDisponibilite[j+1] != null)) {
				h++;
			}
			i++;
			j=j+2;
		}
		heureustique = h;
	}
	public void setHeureustique2() {
		int i=0;
		int j=0;
		int h=0;
		while(i<9) {
			if(!(disponibilite[i]||newDisponibilite2[j]!=null||newDisponibilite2[j+1] != null)) {
				h++;
			}
			i++;
			j=j+2;
		}
		heureustique2 = h;
	}
	
	
	public Enseignant(Integer id, String nom, String prenom, String disponibilite) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		for(int i=0;i<9;i++) {
			
		this.disponibilite[i] = disponibilite.charAt(i)=='1'?true:false;
		}
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public boolean getDisponibilite(int i) {
		return disponibilite[i];
	}
	public void setDisponibilite(boolean[] disponibilite) {
		this.disponibilite = disponibilite;
	}
	public String getNomEtPrenom() {
		return nom+" "+prenom;
	}
	@Override
	public String toString() {
		return nom +" "+prenom;
	}
	
	public void removeCoupleDisponibilite(int i) {
		newDisponibilite[i] =null;
		newDisponibilite[i+1] =null;
	}
	public void removeCoupleDisponibilite2(int i) {
		newDisponibilite2[i] =null;
		newDisponibilite2[i+1] =null;
	}
	public void removeOneDisponibilite(int id,int i ) {
		if(newDisponibilite[i].getClasse().getId()==id)
			newDisponibilite[i]=null;
		else if(newDisponibilite[i+1].getClasse().getId() == id)
			newDisponibilite[i+1]=null;
	}
	public void removeOneDisponibilite2(int id,int i ) {
		if(newDisponibilite2[i].getClasse().getId()==id)
			newDisponibilite2[i]=null;
		else if(newDisponibilite2[i+1].getClasse().getId() == id)
			newDisponibilite2[i+1]=null;
	}
	
	
}
