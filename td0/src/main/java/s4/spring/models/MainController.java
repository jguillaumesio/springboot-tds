package s4.spring.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@SessionAttributes("categorie")
public class MainController {
    
    @ModelAttribute("categorie") 
    public List<Categorie> getCategorie(){
        return new ArrayList<>(Arrays.asList(new Categorie("Amis"),new Categorie("Famille"),new Categorie("Professionnels")));
    }
    
    @RequestMapping("/")
    public ModelAndView categorie(@ModelAttribute("categorie") List<Categorie> categorie,Model model) {
    	model.addAttribute("categorie", categorie);
    	return new ModelAndView("categorie");
    }
    
    @RequestMapping("/{libelle}/items")
    public ModelAndView items(@ModelAttribute("categorie") List<Categorie> categorie,Model model,@PathVariable String libelle) {
    	for(Categorie i : categorie) {
    		if(i.getLibelle().equals(libelle)) {
    			model.addAttribute("items", i.getListElements());
    			return new ModelAndView("items");
    		}
    	}
    	return new ModelAndView("error");
    }
    
    @GetMapping("/{libelle}/items/new")
    public ModelAndView itemNew(Model model,@PathVariable String libelle) {
    	return new ModelAndView("itemsNew");
    }
    
    @PostMapping("/{libelle}/items/addNew")
    @GetMapping("/{libelle}/items/addNew")
    public RedirectView addNew(@ModelAttribute("categorie") List<Categorie> categorie,@RequestParam String nom,@PathVariable String libelle) {
    	for(Categorie i : categorie) {
    		if(i.getLibelle().equals(libelle)) {
    			Element newItem=new Element(nom);
    	    	for(Element e : new ArrayList<Element>(i.getListElements())) {
    				if(newItem.equals(e)) {
    					return new RedirectView("/"+libelle+"/items/new");
    				}
    			}
    	    	if(nom!=null && nom!="") {
    		    	i.add(newItem);
    		        return new RedirectView("/"+libelle+"/items");
    	    	}
    		}
    	}
    	return new RedirectView("/"+libelle+"/items/new");
    }
    
    @GetMapping("{libelle}/items/delete/{nom}")
    public RedirectView delete(@ModelAttribute("categorie") List<Categorie> categorie,@PathVariable String nom,@PathVariable String libelle) {
    	for(Categorie i : categorie) {
    		if(i.getLibelle().equals(libelle)) {
    			for (Element e : new ArrayList<Element>(i.getListElements())) {
    	    	    if(e.getNom().equals(nom)) {
    	    	    	i.getListElements().remove(e);
    	    	    }
    	    	}
    		}
    	}
    	return new RedirectView("/{libelle}/items");
    }
    
    @GetMapping("{libelle}/items/inc/{nom}")
    public RedirectView inc(@ModelAttribute("categorie") List<Categorie> categorie,@PathVariable String nom,@PathVariable String libelle) {
    	for(Categorie i : categorie) {
    		if(i.getLibelle().equals(libelle)) {
    			for (Element e : new ArrayList<Element>(i.getListElements())) {
    	    	    if(e.getNom().equals(nom) && e.getEvaluation()<10) {
    	    	    	e.setEvaluation(e.getEvaluation()+1);
    	    	    }
    	    	}
    		}
    	}
    	return new RedirectView("/{libelle}/items");
    }
    
    @GetMapping("{libelle}/items/dec/{nom}")
    public RedirectView dec(@ModelAttribute("categorie") List<Categorie> categorie,@PathVariable String nom,@PathVariable String libelle) {
    	for(Categorie i : categorie) {
    		if(i.getLibelle().equals(libelle)) {
    			for (Element e : new ArrayList<Element>(i.getListElements())) {
    	    	    if(e.getNom().equals(nom) && e.getEvaluation()>0) {
    	    	    	e.setEvaluation(e.getEvaluation()-1);
    	    	    }
    	    	}
    		}
    	}
    	return new RedirectView("/{libelle}/items");
    }
}