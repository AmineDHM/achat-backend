package tn.esprit.rh.achat.services;

import javassist.NotFoundException;
import tn.esprit.rh.achat.entities.Facture;

import java.util.Date;
import java.util.List;

public interface IFactureService {
    List<Facture> retrieveAllFactures();

    List<Facture> getFacturesByFournisseur(Long idFournisseur) throws NotFoundException;

    Facture addFacture(Facture f);

    void cancelFacture(Long id);

    Facture retrieveFacture(Long id);

    void assignOperateurToFacture(Long idOperateur, Long idFacture) throws NotFoundException;

    float pourcentageRecouvrement(Date startDate, Date endDate);

}
