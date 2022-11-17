package tn.esprit.rh.achat.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import tn.esprit.rh.achat.entities.SecteurActivite;
import tn.esprit.rh.achat.repositories.SecteurActiviteRepository;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@TestMethodOrder(OrderAnnotation.class)
class SecteurActiviteServiceImplTest {
	
	@InjectMocks
	SecteurActiviteServiceImpl service;
	
	//@Mock
	SecteurActiviteRepository repository =  Mockito.mock(SecteurActiviteRepository.class);

	/*SecteurActivite secteurActivite = SecteurActivite.builder()
			.idSecteurActivite(1L)
			.codeSecteurActivite("8Z4DC")
			.libelleSecteurActivite("secteur informatique")
			.build();*/
	
	SecteurActivite secteurActivite = new SecteurActivite(1L,"8Z4DC","secteur informatique");
	
	@Test
	@Order(1)
	void testRetrieveAllSecteurActivite() {
		SecteurActivite secteurActivite1 = SecteurActivite.builder()
				.idSecteurActivite(2L)
				.codeSecteurActivite("AZ123E")
				.libelleSecteurActivite("secteur marketing")
				.build();
		
		//si la liste est remplie
		when(repository.findAll()).thenReturn(new ArrayList<SecteurActivite>(Arrays.asList(secteurActivite, secteurActivite1)));
		List<SecteurActivite> listSecteurActivites = service.retrieveAllSecteurActivite();
		assertEquals(2, listSecteurActivites.size());
		
		//si la liste est vide
		when(repository.findAll()).thenReturn(Collections.emptyList());
		listSecteurActivites = service.retrieveAllSecteurActivite();
		assertEquals(true, listSecteurActivites.isEmpty());	
	}
	
	@Test
	@Order(2)
	void testAddSecteurActivite() {
		when(repository.save(secteurActivite)).thenReturn(secteurActivite);
		
		SecteurActivite savedActivite = service.addSecteurActivite(secteurActivite);
	
		assertEquals(secteurActivite, savedActivite);
		assertNotNull(savedActivite);
	}
	
	@Test
	@Order(3)
	void testDeleteSecteurActivite() {
		long idSecteurActivite = 1l;
		service.deleteSecteurActivite(idSecteurActivite);
		verify(repository, times(1)).deleteById(secteurActivite.getIdSecteurActivite());
	}
	
	@Test
	@Order(4)
	void testUpdateSecteurActivite() {
		when(repository.save(secteurActivite)).thenReturn(secteurActivite);
		secteurActivite.setCodeSecteurActivite("X25ED3");
		secteurActivite.setLibelleSecteurActivite("secteur industriel");
		
		SecteurActivite updatedSecteurActivite = service.updateSecteurActivite(secteurActivite);
		assertEquals("X25ED3", updatedSecteurActivite.getCodeSecteurActivite());
		assertEquals("secteur industriel", updatedSecteurActivite.getLibelleSecteurActivite());
	}
	
	@Test
	@Order(5)
	void testRetrieveSecteurActivite() {
		long idSecteurActivite = 1l;
		when(repository.findById(idSecteurActivite)).thenReturn(Optional.of(secteurActivite));
		
		SecteurActivite searchedSecteurActivite = service.retrieveSecteurActivite(idSecteurActivite);
		assertNotNull(searchedSecteurActivite);
	}
}
