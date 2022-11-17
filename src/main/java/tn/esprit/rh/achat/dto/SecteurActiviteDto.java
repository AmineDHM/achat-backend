package tn.esprit.rh.achat.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SecteurActiviteDto {
    private Long idSecteurActivite;
    private String codeSecteurActivite;
    private String libelleSecteurActivite;
}
