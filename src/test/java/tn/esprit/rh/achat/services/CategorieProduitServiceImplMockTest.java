package tn.esprit.rh.achat.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.rh.achat.entities.CategorieProduit;
import tn.esprit.rh.achat.repositories.CategorieProduitRepository;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
class CategorieProduitServiceImplMockTest {
    @Mock
    private CategorieProduitRepository categorieProduitRepository;

    @InjectMocks
    private CategorieProduitServiceImpl categorieProduitService;

    private CategorieProduit categorieProduit;

    @BeforeEach
    public void setup() {
        categorieProduit = CategorieProduit.builder()
                .idCategorieProduit(1L)
                .codeCategorie("C1")
                .libelleCategorie("Category1")
                .build();
    }

    @DisplayName("JUnit test for saveEmployee method")
    @Test
    void retrieveAllCategorieProduitsTest() {
        CategorieProduit categorieProduit1 = CategorieProduit.builder()
                .idCategorieProduit(2L)
                .codeCategorie("C2")
                .libelleCategorie("Category2")
                .build();

        given(categorieProduitRepository.findAll()).willReturn(new ArrayList<>(Arrays.asList(categorieProduit, categorieProduit1)));
        List<CategorieProduit> categorieProduits = categorieProduitService.retrieveAllCategorieProduits();
        assertThat(categorieProduits).isNotNull().hasSize(2);

        //negative scenario
        given(categorieProduitRepository.findAll()).willReturn(Collections.emptyList());
        categorieProduits = categorieProduitService.retrieveAllCategorieProduits();
        assertThat(categorieProduits).isEmpty();
    }

    @Test
    void addCategorieProduitTest() {
        given(categorieProduitRepository.save(categorieProduit)).willReturn(categorieProduit);
        CategorieProduit savedEmployee = categorieProduitService.addCategorieProduit(categorieProduit);
        assertThat(savedEmployee).isNotNull();
    }

    @Test
    void deleteCategorieProduitTest() {
        long categorieProduitId = 1L;
        willDoNothing().given(categorieProduitRepository).deleteById(categorieProduitId);
        categorieProduitService.deleteCategorieProduit(categorieProduitId);
        verify(categorieProduitRepository, times(1)).deleteById(categorieProduitId);
    }

    @Test
    void updateCategorieProduitTest() {
        given(categorieProduitRepository.save(categorieProduit)).willReturn(categorieProduit);
        categorieProduit.setCodeCategorie("C3");
        categorieProduit.setLibelleCategorie("Category3");
        CategorieProduit updatedEmployee = categorieProduitService.updateCategorieProduit(categorieProduit);
        assertThat(updatedEmployee.getCodeCategorie()).isEqualTo("C3");
        assertThat(updatedEmployee.getLibelleCategorie()).isEqualTo("Category3");
    }

    @Test
    void retrieveCategorieProduitTest() {
        given(categorieProduitRepository.findById(1L)).willReturn(Optional.of(categorieProduit));
        CategorieProduit savedEmployee = categorieProduitService.retrieveCategorieProduit(categorieProduit.getIdCategorieProduit());
        assertThat(savedEmployee).isNotNull();
    }
}