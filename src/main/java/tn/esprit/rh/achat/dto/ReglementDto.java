package tn.esprit.rh.achat.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ReglementDto {
    private Long idReglement;
    private float montantPaye;
    private float montantRestant;
    private Boolean payee;
    private Date dateReglement;
}
