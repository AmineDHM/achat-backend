package tn.esprit.rh.achat.services;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.rh.achat.entities.Fournisseur;
import tn.esprit.rh.achat.repositories.FournisseurRepository;

//@RunWith(SpringRunner.class)
//@ExtendWith(MockitoExtension.class)


@SpringBootTest
@Slf4j
public class FournisseurServiceImplTest {
    @Mock
    FournisseurRepository FournisseurRepo;

   @InjectMocks
    FournisseurServiceImpl FournisseurService;
    //Fournisseur f=Fournisseur.builder().idFournisseur(1L).code("123").libelle("test1")
         //   .categorieFournisseur(null).detailFournisseur(null).factures(null)
          //  .secteurActivites(null).build();
    @Test
    public void retrieveFournisseurTest(){
        Fournisseur f = new Fournisseur(1L,"123","test",null, null, null, null);

        when(FournisseurRepo.findById(1L)).thenReturn(Optional.of(f));
       Fournisseur fournisseur= FournisseurService.retrieveFournisseur((long) 1);
        Assertions.assertNotNull(fournisseur);
        log.info("get ==>"+ fournisseur.toString());
    }
    @Test
    public void addFournisseurTest(){
        Fournisseur f = new Fournisseur(2L,"123","test",null, null, null, null);
        f.setIdFournisseur(2L);
        FournisseurService.addFournisseur(f);
        verify(FournisseurRepo, times(1)).save(f);
        System.out.println(f);
        log.info("add ==>"+ f.toString());
    }

    @Test
    public void retrieveAllFournisseurs()
    {
        List<Fournisseur> Lf = new ArrayList<Fournisseur>() {

            {
                add(new Fournisseur(3L,"123","test",null, null, null, null));
                add(new Fournisseur(4L,"123","test",null, null, null, null));
                add(new Fournisseur(5L,"123","test",null, null, null, null));
            }};


        when(FournisseurService.retrieveAllFournisseurs()).thenReturn(Lf);
        //test
        List<Fournisseur> fournisseurList = FournisseurService.retrieveAllFournisseurs();
        assertEquals(3, fournisseurList.size());
        log.info("retrieve all ==>"+ fournisseurList.toString());

    }

    @Test
    public void deleteFournisseurTest() {
        Fournisseur f = new Fournisseur(6L,"123","test",null, null, null, null);

        FournisseurRepo.save(f);
        FournisseurService.deleteFournisseur(f.getIdFournisseur());
        verify(FournisseurRepo, times(1)).deleteById(f.getIdFournisseur());
        log.info("delete ==>"+ f.toString());

    }


    @Test
    public void updateFournisseurTest() {
        Fournisseur f = new Fournisseur(7L,"123","test",null, null, null, null);
        when(FournisseurRepo.save(f)).thenReturn(f);
        assertNotNull(f);
        assertEquals(f, FournisseurService.updateFournisseur(f));
        log.info("update ==>"+ f.toString());
    }

}