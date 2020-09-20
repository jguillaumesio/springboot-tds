package s4.spring.models;

public class Element extends Object{
	
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
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null || obj.getClass()!=getClass()) {
	        return false;
		}
		if(obj instanceof Element)
		{
		Element objRet = (Element) obj;
			if(this.getNom().equals(objRet.getNom())) {
				return true;
			}
		}
		return false;
	}

}
