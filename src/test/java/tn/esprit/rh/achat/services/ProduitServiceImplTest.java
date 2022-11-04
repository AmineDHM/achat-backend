package tn.esprit.rh.achat.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.rh.achat.entities.Produit;
import tn.esprit.rh.achat.repositories.ProduitRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProduitServiceImplTest {

    @Mock
    ProduitRepository produitRepository;
    @InjectMocks
    ProduitServiceImpl produitService;

    List<Produit> list = new ArrayList<>(
            Arrays.asList(
                    new Produit(1L, "P1", "Product1", 10F, new Date(), new Date()),
                    new Produit(2L, "P2", "Product2", 10F, new Date(), new Date())
            )
    );

    Produit produit = new Produit(3L, "P3", "Product3", 10F, new Date(), new Date());

    @Test
    @DisplayName("Test should retrieve all products")
    public void retrieveAllProduits() {
        when(produitRepository.findAll()).thenReturn(list);
        Assertions.assertEquals(2, produitService.retrieveAllProduits().size());
    }

    @Test
    @DisplayName("Test should add a product")
    public void addProduit() {
        when(produitRepository.save(produit)).thenReturn(produit);
        Assertions.assertEquals(produit, produitService.addProduit(produit));
    }

    @Test
    @DisplayName("Test should delete a product")
    public void deleteProduit() {
    }

    @Test
    @DisplayName("Test should update a product")
    public void updateProduit() {
    }

    @Test
    @DisplayName("Test should retrieve a product")
    public void retrieveProduit() {
    }

    @Test
    @DisplayName("Test should assign a product to stock")
    public void assignProduitToStock() {
    }
}