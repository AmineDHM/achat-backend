package tn.esprit.rh.achat.dto;

import lombok.Data;

import java.util.Date;

@Data
public class FactureDto {
    private Long idFacture;
    private float montantRemise;
    private float montantFacture;
    private Date dateCreationFacture;
    private Date dateDerniereModificationFacture;
    private Boolean archivee;
}
