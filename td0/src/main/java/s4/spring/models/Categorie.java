package s4.spring.models;

import java.util.ArrayList;
import java.util.List;

public class Categorie {
	
	private String libelle;
	private List<Element> listElements;
	
	public Categorie(String libelle) {
		super();
		this.libelle = libelle;
		this.listElements = new ArrayList<>();
	}
	
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public List<Element> getListElements() {
		return listElements;
	}
	public void setListElements(List<Element> listElements) {
		this.listElements = listElements;
	}
	
	public Element getElement(String nom) {
		for(Element i : this.listElements) {
			if(i.getNom().equals(nom)) {
				return i;
			}
		}
		return null;
	}
	
	public void add(Element e) {
		this.listElements.add(e);
	}
	
	public void delete(Element e) {
		this.listElements.remove(this.listElements.indexOf(e));
	}
}
