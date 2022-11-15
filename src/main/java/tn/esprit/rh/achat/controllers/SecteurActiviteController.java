package tn.esprit.rh.achat.controllers;

import io.swagger.annotations.Api;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.rh.achat.dto.SecteurActiviteDto;
import tn.esprit.rh.achat.entities.SecteurActivite;
import tn.esprit.rh.achat.services.ISecteurActiviteService;

import java.util.List;

@RestController
@Api(tags = "Gestion des secteurs activites")
@RequestMapping("/secteurActivite")
@CrossOrigin("*")
public class SecteurActiviteController {

    @Autowired
    ISecteurActiviteService secteurActiviteService;

    @Autowired
    ModelMapper modelMapper;

    // http://localhost:8089/SpringMVC/secteurActivite/retrieve-all-secteurActivite
    @GetMapping("/retrieve-all-secteurActivite")
    @ResponseBody
    public List<SecteurActivite> getSecteurActivite() {
        return secteurActiviteService.retrieveAllSecteurActivite();
    }

    // http://localhost:8089/SpringMVC/secteurActivite/retrieve-secteurActivite/8
    @GetMapping("/retrieve-secteurActivite/{secteurActivite-id}")
    @ResponseBody
    public SecteurActivite retrieveSecteurActivite(@PathVariable("secteurActivite-id") Long secteurActiviteId) {
        return secteurActiviteService.retrieveSecteurActivite(secteurActiviteId);
    }

    // http://localhost:8089/SpringMVC/secteurActivite/add-secteurActivite
    @PostMapping("/add-secteurActivite")
    @ResponseBody
    public SecteurActivite addSecteurActivite(@RequestBody SecteurActiviteDto sa) {
        SecteurActivite secteurActivite = modelMapper.map(sa, SecteurActivite.class);
        return secteurActiviteService.addSecteurActivite(secteurActivite);
    }

    @DeleteMapping("/remove-secteurActivite/{secteurActivite-id}")
    @ResponseBody
    public void removeSecteurActivite(@PathVariable("secteurActivite-id") Long secteurActiviteId) {
        secteurActiviteService.deleteSecteurActivite(secteurActiviteId);
    }

    // http://localhost:8089/SpringMVC/secteurActivite/modify-secteurActivite
    @PutMapping("/modify-secteurActivite")
    @ResponseBody
    public SecteurActivite modifySecteurActivite(@RequestBody SecteurActiviteDto sa) {
        SecteurActivite secteurActivite = modelMapper.map(sa, SecteurActivite.class);
        return secteurActiviteService.updateSecteurActivite(secteurActivite);
    }


}