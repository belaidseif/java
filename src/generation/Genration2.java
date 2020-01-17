package generation;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import application.MissedProfException;
import classe.Classe;
import classe.Couple;
import enseignant.Enseignant;
import matiere.Matiere;

public class Genration2 {

	public static void generation(List<Classe> list) {
		for(Classe classe:list) {
			List<Matiere> listNotAffected = new CopyOnWriteArrayList<Matiere>();
			List<Matiere> listSoloNotAffected = new CopyOnWriteArrayList<Matiere>();
			
			
				
				for(Matiere matiere : classe.getDepartement().listMatier2.keySet()) {
					boolean affected = false;
					boolean test = false;
					Couple couple = null;
					if(classe.getDepartement().listMatier2.get(matiere)==2) {						
						couple = Couple.twice(matiere);
					}else{
						
						for(int i=0;i<9;i++) {
							
							if(classe.emploi2[i] != null && classe.emploi2[i].getMatiere2()== null) {								
								classe.emploi2[i].setOne(matiere);
								couple = classe.emploi2[i];
								couple.getEnseignant1().removeOneDisponibilite2(classe.getId(),i*2);
								classe.emploi2[i]=null;
								
								test = true;
								
							}
						}
						if(!test) {		
							
							couple = new Couple();
							couple.setOne(matiere);
						}
					}
					
					if(!couple.isDifferent) {
						out:
						for(Enseignant enseignant:matiere.listEnseignant) {
							int i=0;
							int j=0;
							while(i<9) {
								if(enseignant.getDisponibilite(i)== false&&enseignant.getNewDisponibilite2(j)==null&&enseignant.getNewDisponibilite2(j+1) == null&&classe.emploi2[i] == null) {
									couple.setEnseignant1(enseignant);
									classe.emploi2[i]=couple;
									enseignant.setCoupleDisponibilite2(matiere, classe, j);
									affected = true;
									break out;
								}
								i++;
								j+=2;
							}
						}
					}else {
						if(test) {
							
							if(couple.getMatiere2()!=null) {
								out2:
									for(Enseignant enseignant:couple.getMatiere2().listEnseignant) {
										int i=0;
										int j=0;
										while(i<9) {
											if(enseignant.getDisponibilite(i)== false&&(enseignant.getNewDisponibilite2(j)==null||enseignant.getNewDisponibilite2(j+1) == null)&&classe.emploi2[i] == null
													&&couple.getEnseignant1().getDisponibilite(i)==false&&(couple.getEnseignant1().getNewDisponibilite2(j)==null||couple.getEnseignant1().getNewDisponibilite2(j+1) == null)) {
												couple.setEnseignant2(enseignant);
												
												classe.emploi2[i]=couple;
												couple.getEnseignant1().setOneDiponibilite2(couple.getMatiere1(), classe, j);
												enseignant.setOneDiponibilite2(matiere, classe, j);
												affected = true;
												break out2;
											}
											i++;
											j+=2;
										}
									}
							}
						}else {
							
							if(couple.getMatiere1()!=null) {
								
								out1:
									for(Enseignant enseignant:couple.getMatiere1().listEnseignant) {
										int i=0;
										int j=0;
										while(i<9) {
											if(enseignant.getDisponibilite(i)== false&&(enseignant.getNewDisponibilite2(j)==null||enseignant.getNewDisponibilite2(j+1) == null)&&classe.emploi2[i] == null) {
												couple.setEnseignant1(enseignant);
												
												classe.emploi2[i]=couple;
												
												enseignant.setOneDiponibilite2(matiere, classe, j);
												affected = true;
												break out1;
											}
											i++;
											j+=2;
										}
									}
							}
						}
					}
					if(!affected) {
						if(classe.getDepartement().listMatier2.get(matiere)==2)
							listNotAffected.add(matiere);
						else {
							listSoloNotAffected.add(couple.getMatiere1());
							listSoloNotAffected.add(couple.getMatiere2());
							
						}
						
					}
				}
				int count = 0;
			while(!classe.isOK2()) {
				if(count == 5) {
					GenerationController.warning.add("Le classe "+classe+" n'est pas complet.");
					break;
				}				
				if(!listSoloNotAffected.isEmpty()) {
					
					try {
						switchSoloMatiere(classe, listSoloNotAffected, listNotAffected);
					} catch (MissedProfException e) {
						GenerationController.warning.add("Il y a un manque des enseignants en "+e.getMatiere());
						listNotAffected.remove(e.getMatiere());
					}
				}
				if(!listNotAffected.isEmpty()) {
					
					try {
						switchMatiere(classe, listNotAffected);
					} catch (MissedProfException e) {
						GenerationController.warning.add("Il y a un manque des enseignants en "+e.getMatiere());
						listNotAffected.remove(e.getMatiere());
					}
					
				}
				count++;				
			}
		}
		
	}
	
	public static void switchMatiere(Classe classe, List<Matiere> listNotAffected) throws MissedProfException {

		for(Matiere matiereNotAffected : listNotAffected) {
			
			Enseignant enseignant = null;
			try {
			enseignant = matiereNotAffected.listEnseignant.get(0);
			}catch(IndexOutOfBoundsException e) {
				throw new MissedProfException(matiereNotAffected);
			}
			for(Enseignant e : matiereNotAffected.listEnseignant) 
				if(e.getHeureustique2()>enseignant.getHeureustique2())
					enseignant = e;
			
			
			int d=0;
			boolean test =false;
			while(d<9) {
				if(enseignant.getDisponibilite(d)== false&&enseignant.getNewDisponibilite2(d*2)==null&&enseignant.getNewDisponibilite2(d*2+1) == null&&classe.emploi2[d] == null) {
					Couple cc = Couple.twice(matiereNotAffected);
					cc.setEnseignant1(enseignant);
					classe.emploi2[d]=cc;
					enseignant.setCoupleDisponibilite2(matiereNotAffected, classe, d*2);
					test = true;
					listNotAffected.remove(matiereNotAffected);
					break ;
				}
				d++;
				
			}
			
			if(!test) {
				Couple couple = null;
				int i=0;
				int j=0;
				int k = -1;
				while(i<9) {
					if(enseignant.getDisponibilite(i) == false && enseignant.getNewDisponibilite2(j) == null&&enseignant.getNewDisponibilite2(j+1) == null)
						if(couple == null) {						
							couple = classe.emploi2[i];
							k = i;
						}else if(classe.emploi2[i] != null&&classe.emploi2[i].getHeureustique2()>couple.getHeureustique2()) {						
							couple = classe.emploi2[i];
							k = i;
						}
					i++;
					j+=2;	
				}
				if(k>=0) {
					Couple c = Couple.twice(matiereNotAffected);
					c.setEnseignant1(enseignant);
					enseignant.setCoupleDisponibilite2(matiereNotAffected, classe, k*2);
					classe.emploi2[k] = c;
					listNotAffected.remove(c.getMatiere1());
					enseignant = couple.getEnseignant1();
					enseignant.removeCoupleDisponibilite2(k*2);
					i=0;
					j=0;
					boolean affected = false;
					while(i<9) {
						if(enseignant.getDisponibilite(i)== false&&enseignant.getNewDisponibilite2(j)==null&&enseignant.getNewDisponibilite2(j+1) == null&&classe.emploi2[i] == null) {
							
							classe.emploi2[i]=couple;
							enseignant.setCoupleDisponibilite2(couple.getMatiere1(), classe, j);
							affected = true;
							break ;
						}
						i++;
						j+=2;
					}
					if(!affected)
						listNotAffected.add(couple.getMatiere1());
				}else {
					throw new MissedProfException(matiereNotAffected);
				}
			}
			
			
		}
	
	}
	
	public static void switchSoloMatiere(Classe classe, List<Matiere> listSoloNotAffected, List<Matiere> listNotAffected) throws MissedProfException {
		
		if(listSoloNotAffected.size()%2==0) {
			for(int l=0;l<listSoloNotAffected.size();l=l+2) {
				Couple couple = createCouple(listSoloNotAffected, l);
				boolean test =affecterDifferent(classe, couple, listSoloNotAffected);
				if(!test) {
					replaceDifferent(classe, couple, listSoloNotAffected, listNotAffected);
					}
				
			}
		}else {
			int l=0;
			while(l<listSoloNotAffected.size()-1) {

				Couple couple = createCouple(listSoloNotAffected, l);
				boolean test = affecterDifferent(classe, couple, listSoloNotAffected);
				if(!test) {replaceDifferent(classe, couple, listSoloNotAffected, listNotAffected);}
				
				l+=2;
			}
			
			Couple couple =null;
			for(int i=0;i<9;i++) {				
				if(classe.emploi2[i] != null && classe.emploi2[i].getMatiere2()== null) {								
					classe.emploi2[i].setOne(listSoloNotAffected.get(listSoloNotAffected.size()-1));
					couple = classe.emploi2[i];
					classe.emploi2[i]=null;	
				}
			}
			if(couple != null) {
				Enseignant enseignant1 = null;
				try {
					enseignant1 = couple.getMatiere1().listEnseignant.get(0);
				} catch (IndexOutOfBoundsException e2) {
					throw new MissedProfException(couple.getMatiere1());
				} 
				for(Enseignant e : couple.getMatiere1().listEnseignant)
					if(e.getHeureustique2()>enseignant1.getHeureustique2())
						enseignant1 = e;
				Enseignant enseignant2 = null;
				try {
					enseignant2 = couple.getMatiere2().listEnseignant.get(0);
				} catch (IndexOutOfBoundsException e1) {
					throw new MissedProfException(couple.getMatiere2());
				} 
				for(Enseignant e : couple.getMatiere2().listEnseignant)
					if(e.getHeureustique2()>enseignant2.getHeureustique2())
						enseignant2 = e;
				couple.setEnseignant1(enseignant1);
				couple.setEnseignant2(enseignant2);
				boolean test =affecterDifferent(classe, couple, listSoloNotAffected);
				if(!test) {replaceDifferent(classe, couple, listSoloNotAffected, listNotAffected);}
				
			}else {
				couple = new Couple();
				couple.setOne(listSoloNotAffected.get(listSoloNotAffected.size()-1));
				Enseignant enseignant1 = null;
				try {
					enseignant1 = couple.getMatiere1().listEnseignant.get(0);
				} catch (IndexOutOfBoundsException e2) {
					throw new MissedProfException(couple.getMatiere1());
				}
				for(Enseignant e : couple.getMatiere1().listEnseignant)
					if(e.getHeureustique2()>enseignant1.getHeureustique2())
						enseignant1 = e;
				couple.setEnseignant1(enseignant1);
				
				Couple couple2 = null;
				int i=0;
				int j=0;
				int k = -1;
				while(i<9) {
					if(enseignant1.getDisponibilite(i) == false &&( enseignant1.getNewDisponibilite2(j) == null||enseignant1.getNewDisponibilite2(j+1) == null))
						if(couple2 == null) {						
							couple2 = classe.emploi2[i];
							k = i;
						}else if(classe.emploi2[i].getHeureustique2()>couple2.getHeureustique2()) {						
							couple2 = classe.emploi2[i];
							k = i;
						}
					i++;
					j+=2;	
				}
				if(k>=0) {
					enseignant1.setOneDiponibilite2(couple.getMatiere1(), classe, k*2);
					classe.emploi2[k] = couple;
					listSoloNotAffected.remove(couple.getMatiere1());
					enseignant1 = couple2.getEnseignant1();
					enseignant1.removeCoupleDisponibilite2(k*2);
					i=0;
					j=0;
					boolean affected = false;
					while(i<9) {
						if(enseignant1.getDisponibilite(i)== false&&enseignant1.getNewDisponibilite2(j)==null&&enseignant1.getNewDisponibilite2(j+1) == null&&classe.emploi2[i] == null) {
							
							classe.emploi2[i]=couple2;
							enseignant1.setCoupleDisponibilite2(couple2.getMatiere1(), classe, j);
							affected = true;
							break ;
						}
						i++;
						j+=2;
					}
					if(!affected)
						listNotAffected.add(couple2.getMatiere1());
				}else {
					throw new MissedProfException(couple.getMatiere1());
				}
				
			}
			
		}
	}
	public static boolean affecterDifferent(Classe classe, Couple couple, List<Matiere> listSoloNotAffected) {
		int d=0;
		while(d<9) {
			if(classe.emploi2[d]==null&&couple.getEnseignant1().getDisponibilite(d) == false&&(couple.getEnseignant1().getNewDisponibilite2(d*2) == null||couple.getEnseignant1().getNewDisponibilite2(d*2+1) == null)
					&&couple.getEnseignant2().getDisponibilite(d) == false&&(couple.getEnseignant2().getNewDisponibilite2(d*2)==null||couple.getEnseignant2().getNewDisponibilite2(d*2+1)==null)){
				classe.emploi2[d]=couple;
				couple.getEnseignant1().setOneDiponibilite2(couple.getMatiere1(), classe, d*2);
				couple.getEnseignant2().setOneDiponibilite2(couple.getMatiere2(), classe, d*2);
				listSoloNotAffected.remove(couple.getMatiere1());
				listSoloNotAffected.remove(couple.getMatiere2());
				return true;
			}
			d++;
		}
		return false;
	}
	
	public static void replaceDifferent(Classe classe, Couple couple, List<Matiere> listSoloNotAffected, List<Matiere> listNotAffected) throws MissedProfException {

		Couple couple2 = null;
		int i=0;
		int j=0;
		int k = -1;
		while(i<9) {
			if(couple.getEnseignant1().getDisponibilite(i) == false && (couple.getEnseignant1().getNewDisponibilite2(j) == null||couple.getEnseignant1().getNewDisponibilite2(j+1) == null)&&couple.getEnseignant2().getDisponibilite(i) == false && (couple.getEnseignant2().getNewDisponibilite2(j) == null||couple.getEnseignant2().getNewDisponibilite2(j+1) == null))
				if(couple2 == null&&!classe.emploi2[i].isDifferent) {						
					couple2 = classe.emploi2[i];
					k = i;
				}else if(classe.emploi2[i].getHeureustique2()>couple2.getHeureustique2()) {						
					couple2 = classe.emploi2[i];
					k = i;
				}
			i++;
			j+=2;	
		}
		if(k>=0) {
			
			
			couple.getEnseignant1().setOneDiponibilite2(couple.getMatiere1(), classe, k*2);
			couple.getEnseignant2().setOneDiponibilite2(couple.getMatiere2(), classe, k*2);
			classe.emploi2[k] = couple;
			listSoloNotAffected.remove(couple.getMatiere1());
			listSoloNotAffected.remove(couple.getMatiere2());
			Enseignant enseignant = couple2.getEnseignant1();
			enseignant.removeCoupleDisponibilite2(k*2);
			i=0;
			j=0;
			boolean affected = false;
			while(i<9) {
				if(enseignant.getDisponibilite(i)== false&&enseignant.getNewDisponibilite2(j)==null&&enseignant.getNewDisponibilite2(j+1) == null&&classe.emploi2[i] == null) {
					
					classe.emploi2[i]=couple2;
					enseignant.setCoupleDisponibilite2(couple2.getMatiere1(), classe, j);
					affected = true;
					break ;
				}
				i++;
				j+=2;
			}
			if(!affected)
				listNotAffected.add(couple2.getMatiere1());		
		}else {
			for(int a=0;a<9;a++) {
				if(classe.emploi2[a]!=null&&!classe.emploi2[a].isDifferent&&classe.emploi2[a].getEnseignant1().getId() == couple.getEnseignant1().getId()
						&&couple.getEnseignant2().getDisponibilite(a) ==false&&(couple.getEnseignant2().getNewDisponibilite2(a*2)==null||couple.getEnseignant2().getNewDisponibilite2(a*2+1)==null)) {
					
					listNotAffected.add(classe.emploi2[a].getMatiere1());
					
					couple.getEnseignant1().removeCoupleDisponibilite2(a*2);
					classe.emploi2[a]= null;
					break;
				}else if(classe.emploi2[a]!= null&&!classe.emploi2[a].isDifferent&&classe.emploi2[a].getEnseignant1().getId() == couple.getEnseignant2().getId()
						&&couple.getEnseignant1().getDisponibilite(a) ==false&&(couple.getEnseignant1().getNewDisponibilite2(a*2)==null||couple.getEnseignant1().getNewDisponibilite2(a*2+1)==null)) {
					listNotAffected.add(classe.emploi2[a].getMatiere1());				
					couple.getEnseignant2().removeCoupleDisponibilite2(a*2);
					classe.emploi2[a]= null;
					break;
				}
			}
			switchSoloMatiere(classe, listSoloNotAffected, listNotAffected);			
		}	
	}
	public static Couple createCouple(List<Matiere> listSoloNotAffected, int l) throws MissedProfException {
		Couple couple = new Couple();
		couple.setOne(listSoloNotAffected.get(l));
		couple.setOne(listSoloNotAffected.get(l+1));
		Enseignant enseignant1 = null;
		try {
			enseignant1 = couple.getMatiere1().listEnseignant.get(0);
		} catch (IndexOutOfBoundsException e1) {
			throw new MissedProfException(couple.getMatiere1());		
		} 
		for(Enseignant e : couple.getMatiere1().listEnseignant)
			if(e.getHeureustique2()>enseignant1.getHeureustique2())
				enseignant1 = e;
		Enseignant enseignant2 = null;
		try {
			enseignant2 = couple.getMatiere2().listEnseignant.get(0);
		} catch (IndexOutOfBoundsException e1) {
			throw new MissedProfException(couple.getMatiere2());
		} 
		for(Enseignant e : couple.getMatiere2().listEnseignant)
			if(e.getHeureustique2()>enseignant2.getHeureustique2())
				enseignant2 = e;
		couple.setEnseignant1(enseignant1);
		couple.setEnseignant2(enseignant2);
		return couple;
	}
}
