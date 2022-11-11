package tn.esprit.rh.achat.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ProduitDto {
    private Long idProduit;
    private String codeProduit;
    private String libelleProduit;
    private float prix;
    private Date dateCreation;
    private Date dateDerniereModification;
}
