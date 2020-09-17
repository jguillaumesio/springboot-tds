package s4.spring.td0.models;

public class Element {
	
	private int evaluation;
	private String nom;
	
	public Element(String nom) {
		super();
		this.nom = nom;
	}
	public Element(int evaluation, String nom) {
		super();
		this.evaluation = evaluation;
		this.nom = nom;
	}
	
	public int getEvaluation() {
		return evaluation;
	}
	public void setEvaluation(int evaluation) {
		this.evaluation = evaluation;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}

}
