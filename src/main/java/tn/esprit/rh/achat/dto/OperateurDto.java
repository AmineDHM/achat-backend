package tn.esprit.rh.achat.dto;

import lombok.Data;

@Data
public class OperateurDto {
    private Long idOperateur;
    private String nom;
    private String prenom;

    private String password;
}
