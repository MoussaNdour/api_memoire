package memoire.api.memoire_licence.dto.request;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public class ContratRequestDTO {

    @NotBlank(message = "L'horaire doit etre renseigner")
    private String horaires ;

    @NotBlank(message = "Les jours doivent etre renseigner")
    private String jours ;

    @NotNull
    @Min(value = 100,message = "le montant doit etre au minimum de 100")
    private BigDecimal montantSalaire ;

    @NotNull(message = "La duree doit etre renseigner")
    @Min(value = 1)
    private Integer duree ;


    public String getHoraires() {
        return horaires;
    }

    public void setHoraires(String horaires) {
        this.horaires = horaires;
    }

    public BigDecimal getMontantSalaire() {
        return montantSalaire;
    }

    public void setMontantSalaire(BigDecimal montantSalaire) {
        this.montantSalaire = montantSalaire;
    }

    public String getJours() {
        return jours;
    }

    public void setJours(String jours) {
        this.jours = jours;
    }

    public Integer getDuree() {
        return duree;
    }

    public void setDuree(Integer duree) {
        this.duree = duree;
    }
}
