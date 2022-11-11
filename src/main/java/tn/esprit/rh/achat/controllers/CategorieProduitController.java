package tn.esprit.rh.achat.controllers;

import io.swagger.annotations.Api;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.rh.achat.dto.CategorieProduitDto;
import tn.esprit.rh.achat.entities.CategorieProduit;
import tn.esprit.rh.achat.services.ICategorieProduitService;

import java.util.List;

@RestController
@Api(tags = "Gestion des categories Produit")
@RequestMapping("/categorieProduit")
public class CategorieProduitController {

    @Autowired
    ICategorieProduitService categorieProduitService;

    @Autowired
    ModelMapper modelMapper;

    // http://localhost:8089/SpringMVC/categorieProduit/retrieve-all-categorieProduit
    @GetMapping("/retrieve-all-categorieProduit")
    @ResponseBody
    public List<CategorieProduit> getCategorieProduit() {
        return categorieProduitService.retrieveAllCategorieProduits();
    }

    // http://localhost:8089/SpringMVC/categorieProduit/retrieve-categorieProduit/8
    @GetMapping("/retrieve-categorieProduit/{categorieProduit-id}")
    @ResponseBody
    public CategorieProduit retrieveCategorieProduit(@PathVariable("categorieProduit-id") Long categorieProduitId) {
        return categorieProduitService.retrieveCategorieProduit(categorieProduitId);
    }

    // http://localhost:8089/SpringMVC/categorieProduit/add-categorieProduit
    @PostMapping("/add-categorieProduit")
    @ResponseBody
    public CategorieProduit addCategorieProduit(@RequestBody CategorieProduitDto cp) {
        CategorieProduit categorieProduit = modelMapper.map(cp, CategorieProduit.class);
        return categorieProduitService.addCategorieProduit(categorieProduit);
    }

    @DeleteMapping("/remove-categorieProduit/{categorieProduit-id}")
    @ResponseBody
    public void removeCategorieProduit(@PathVariable("categorieProduit-id") Long categorieProduitId) {
        categorieProduitService.deleteCategorieProduit(categorieProduitId);
    }

    @PutMapping("/modify-categorieProduit")
    @ResponseBody
    public CategorieProduit modifyCategorieProduit(@RequestBody CategorieProduitDto cp) {
        CategorieProduit categorieProduit = modelMapper.map(cp, CategorieProduit.class);
        return categorieProduitService.updateCategorieProduit(categorieProduit);
    }


}
