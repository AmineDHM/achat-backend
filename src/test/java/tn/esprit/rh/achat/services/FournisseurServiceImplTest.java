package tn.esprit.rh.achat.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import tn.esprit.rh.achat.entities.CategorieFournisseur;
import tn.esprit.rh.achat.entities.CategorieProduit;
import tn.esprit.rh.achat.entities.Fournisseur;
import tn.esprit.rh.achat.repositories.FournisseurRepository;

@ExtendWith(MockitoExtension.class)
class FournisseurServiceImplTest {

    @Mock
    private FournisseurRepository fournisseurRepository;
    
    @InjectMocks
    private FournisseurServiceImpl fournisseurService;
    
    private Fournisseur fournisseur;
    
    @BeforeEach
    public void setup() {
    	fournisseur = Fournisseur.builder()
    			.idFournisseur(1L)
    			.code("F123")
    			.libelle("lib123")
    			.categorieFournisseur(CategorieFournisseur.ORDINAIRE)
    			.build();
    }
    
    @DisplayName("JUnit test")
    @Test
    void retrieveAllFournisseursTest() {
    	Fournisseur fournisseur2 = Fournisseur.builder()
    			.idFournisseur(1L)
    			.code("F1234")
    			.libelle("lib1234")
    			.categorieFournisseur(CategorieFournisseur.ORDINAIRE)
    			.build();

        given(fournisseurRepository.findAll()).willReturn(new ArrayList<>(Arrays.asList(fournisseur, fournisseur2)));
        List<Fournisseur> fournisseurs = fournisseurService.retrieveAllFournisseurs();
        assertThat(fournisseurs).isNotNull();
        assertThat(fournisseurs.size()).isEqualTo(2);

        //negative scenario
        given(fournisseurRepository.findAll()).willReturn(Collections.emptyList());
        fournisseurs = fournisseurService.retrieveAllFournisseurs();
        assertThat(fournisseurs).isEmpty();
        assertThat(fournisseurs.size()).isEqualTo(0);
    }
    
    @Test
    void addFournisseurTest() {
    	 given(fournisseurRepository.save(fournisseur)).willReturn(fournisseur);
    	 Fournisseur save = fournisseurService.addFournisseur(fournisseur);
         assertThat(save).isNotNull();
    }
    
    @Test
    void deleteFournisseurTest() {
    	long id = 1L;
    	  willDoNothing().given(fournisseurRepository).deleteById(id);
    	  fournisseurService.deleteFournisseur(id);
          verify(fournisseurRepository, times(1)).deleteById(id);
    }
    
    @Test
    void updateFournisseurTest() {
        given(fournisseurRepository.save(fournisseur)).willReturn(fournisseur);
        fournisseur.setCode("F321");
        Fournisseur updated = fournisseurService.updateFournisseur(fournisseur);
        assertThat(updated.getCode()).isEqualTo("F321");
    }
    
    @Test
    void retrieveFournisseurTest() {
        given(fournisseurRepository.findById(1L)).willReturn(Optional.of(fournisseur));
        Fournisseur saved = fournisseurService.retrieveFournisseur(fournisseur.getIdFournisseur());
        assertThat(saved).isNotNull();
    }
    
    
}
