package tn.esprit.rh.achat.services;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tn.esprit.rh.achat.entities.CategorieProduit;

import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class CategorieProduitServiceImplTest {

    @Autowired
    ICategorieProduitService categorieProduitService;

    @Test
    @Order(2)
    void retrieveAllCategorieProduits() {
        assertEquals(0, categorieProduitService.retrieveAllCategorieProduits().size());
        CategorieProduit categorieProduit = CategorieProduit.builder().
                libelleCategorie("test").
                codeCategorie("test").
                build();
        CategorieProduit savedCategorieProduit = categorieProduitService.addCategorieProduit(categorieProduit);
        assertEquals(1, categorieProduitService.retrieveAllCategorieProduits().size());
        categorieProduitService.deleteCategorieProduit(savedCategorieProduit.getIdCategorieProduit());
        assertEquals(0, categorieProduitService.retrieveAllCategorieProduits().size());
    }

    @Test
    @Order(1)
    void addCategorieProduit() {
        List<CategorieProduit> categorieProduits = categorieProduitService.retrieveAllCategorieProduits();
        int expected = categorieProduits.size();
        CategorieProduit categorieProduit = CategorieProduit.builder().
                libelleCategorie("test").
                codeCategorie("test").
                build();
        CategorieProduit savedCategorieProduit = categorieProduitService.addCategorieProduit(categorieProduit);
        assertEquals(expected + 1, categorieProduitService.retrieveAllCategorieProduits().size());
        assertNotNull(savedCategorieProduit.getLibelleCategorie());
        System.out.println(categorieProduitService.retrieveAllCategorieProduits().size());
        categorieProduitService.deleteCategorieProduit(categorieProduit.getIdCategorieProduit());
    }

    @Test
    void deleteCategorieProduit() {
        CategorieProduit categorieProduit = CategorieProduit.builder().
                libelleCategorie("test").
                codeCategorie("test").
                build();
        CategorieProduit savedCategorieProduit = categorieProduitService.addCategorieProduit(categorieProduit);
        int expected = categorieProduitService.retrieveAllCategorieProduits().size();
        categorieProduitService.deleteCategorieProduit(savedCategorieProduit.getIdCategorieProduit());
        assertEquals(expected - 1, categorieProduitService.retrieveAllCategorieProduits().size());
    }

    @Test
    void updateCategorieProduit() {
        CategorieProduit categorieProduit = CategorieProduit.builder().
                libelleCategorie("test").
                codeCategorie("test").
                build();
        CategorieProduit savedCategorieProduit = categorieProduitService.addCategorieProduit(categorieProduit);
        savedCategorieProduit.setLibelleCategorie("updated");
        savedCategorieProduit.setCodeCategorie("updated");
        CategorieProduit updatedCategoieProduit = categorieProduitService.updateCategorieProduit(savedCategorieProduit);
        assertEquals("updated", updatedCategoieProduit.getLibelleCategorie());
        assertEquals("updated", updatedCategoieProduit.getCodeCategorie());
        categorieProduitService.deleteCategorieProduit(updatedCategoieProduit.getIdCategorieProduit());
    }

    @Test
    void retrieveCategorieProduit() {
        CategorieProduit categorieProduit = CategorieProduit.builder().
                libelleCategorie("test").
                codeCategorie("test").
                build();
        CategorieProduit savedCategorieProduit = categorieProduitService.addCategorieProduit(categorieProduit);
        CategorieProduit expectedCategorieProduit = categorieProduitService.retrieveCategorieProduit(savedCategorieProduit.getIdCategorieProduit());
        assertNotNull(categorieProduitService.retrieveCategorieProduit(categorieProduit.getIdCategorieProduit()));
        assertEquals(savedCategorieProduit.getIdCategorieProduit(), expectedCategorieProduit.getIdCategorieProduit());
        categorieProduitService.deleteCategorieProduit(categorieProduit.getIdCategorieProduit());
        assertNull(categorieProduitService.retrieveCategorieProduit(categorieProduit.getIdCategorieProduit()));
    }
}