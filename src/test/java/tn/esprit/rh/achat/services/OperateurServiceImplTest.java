package tn.esprit.rh.achat.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.rh.achat.entities.Operateur;
import tn.esprit.rh.achat.repositories.OperateurRepository;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class OperateurServiceImplTest {
    @Mock
    private OperateurRepository operateurRepository;

    @InjectMocks
    private OperateurServiceImpl operateurService;

    private Operateur operateur;

    @BeforeEach
    void setUp() {
        operateur = Operateur.builder()
                .idOperateur(1L)
                .nom("test")
                .prenom("test")
                .password("test")
                .build();
    }

    @Test
    void retrieveAllOperateursTest() {
        Operateur operateur1 = Operateur.builder()
                .idOperateur(2L)
                .nom("test")
                .prenom("test")
                .password("test")
                .build();

        given(operateurRepository.findAll()).willReturn(new ArrayList<>(Arrays.asList(operateur, operateur1)));
        List<Operateur> operateurs = operateurService.retrieveAllOperateurs();
        assertThat(operateurs).isNotNull().hasSize(2);

        //negative scenario
        given(operateurRepository.findAll()).willReturn(Collections.emptyList());
        operateurs = operateurService.retrieveAllOperateurs();
        assertThat(operateurs).isEmpty();
    }

    @Test
    void addOperateurTest() {
        given(operateurRepository.save(operateur)).willReturn(operateur);
        Operateur savedOperateur = operateurService.addOperateur(operateur);
        assertThat(savedOperateur).isNotNull();
    }

    @Test
    void deleteOperateurTest() {
        long operateurId = 1L;
        willDoNothing().given(operateurRepository).deleteById(operateurId);
        operateurService.deleteOperateur(operateurId);
        verify(operateurRepository, times(1)).deleteById(operateurId);
    }

    @Test
    void updateOperateurTest() {
        given(operateurRepository.save(operateur)).willReturn(operateur);
        operateur.setNom("updated");
        operateur.setPrenom("updated");
        operateur.setPassword("updated");
        Operateur updatedOperateur = operateurService.updateOperateur(operateur);
        assertThat(updatedOperateur.getNom()).isEqualTo("updated");
        assertThat(updatedOperateur.getPrenom()).isEqualTo("updated");
        assertThat(updatedOperateur.getPassword()).isEqualTo("updated");
    }

    @Test
    void retrieveOperateurTest() {
        given(operateurRepository.findById(1L)).willReturn(Optional.of(operateur));
        Operateur savedOperateur = operateurService.retrieveOperateur(operateur.getIdOperateur());
        assertThat(savedOperateur).isNotNull();
    }
}