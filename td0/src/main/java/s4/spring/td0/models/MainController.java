package s4.spring.td0.models;

import java.util.ArrayList;
import java.util.Iterator;
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
@SessionAttributes("items")
public class MainController {
	
    @ModelAttribute("items") 
    public List<Element> getItems(){
        return new ArrayList<>();
    }
    
    @RequestMapping("/items")
    public ModelAndView items(@ModelAttribute("items") List<Element> items,Model model) {
    	model.addAttribute("items", items);
    	return new ModelAndView("items");
    }
    
    @RequestMapping("/items/new")
    public ModelAndView itemNew() {
    	return new ModelAndView("itemsNew");
    }
    
    @PostMapping("items/addNew")
    public RedirectView addNew(@ModelAttribute("items") List<Element> items,@RequestParam String nom) {
    	Element newItem=new Element(nom);
    	items.add(newItem);
        return new RedirectView("/items");
    }
    
    @GetMapping("items/del/{nom}")
    public RedirectView del(@ModelAttribute("items") List<Element> items,@PathVariable String nom) {
    	for (Element i : new ArrayList<Element>(items)) {
    	    items.remove(i);
    	}
    	return new RedirectView("/items");
    }
    
    @GetMapping("items/inc/{nom}")
    public RedirectView inc(@ModelAttribute("items") List<Element> items,@PathVariable String nom) {
    	for (Element i : new ArrayList<Element>(items)) {
    	    i.setEvaluation(i.getEvaluation()+1);
    	}
    	return new RedirectView("/items");
    }
}