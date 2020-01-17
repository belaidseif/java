package departement;

import matiere.Matiere;

public class ListeMatiere {
	private Integer id;
	private Matiere matiere;
	private Double volumeHoraire;
	
	public ListeMatiere(Integer id, Matiere matiere, Double volumeHoraire) {
		super();
		this.id = id;
		this.matiere = matiere;
		this.volumeHoraire = volumeHoraire;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Matiere getMatiere() {
		return matiere;
	}
	public void setMatiere(Matiere matiere) {
		this.matiere = matiere;
	}
	public Double getVolumeHoraire() {
		return volumeHoraire;
	}
	public void setVolumeHoraire(Double volumeHoraire) {
		this.volumeHoraire = volumeHoraire;
	}
	
}
