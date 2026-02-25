package com.gpro.consulting.tms.mstiers.mapper;

import java.time.LocalDateTime;
import com.gpro.consulting.tms.mstiers.dto.PartieInteresseCreateDTO;
import com.gpro.consulting.tms.mstiers.dto.PartieInteresseReadDTO;
import com.gpro.consulting.tms.mstiers.dto.PartieInteresseUpdateDTO;
import com.gpro.consulting.tms.mstiers.entity.PartieInteresseEntite;

public class PartieInteresseMapper {

    public static PartieInteresseEntite toEntity(PartieInteresseCreateDTO dto) {
        PartieInteresseEntite entity = new PartieInteresseEntite();


        entity.setReference(dto.getReference());
        entity.setRaisonSociale(dto.getRaisonSociale());
        entity.setAbreviation(dto.getAbreviation());
        entity.setActivite(dto.getActivite());
        entity.setObservation(dto.getObservation());
        entity.setEmail(dto.getEmail());
        entity.setEmail2(dto.getEmail2());
        entity.setTelephone(dto.getTelephone());
        entity.setTelephoneMobile(dto.getTelephoneMobile());
        entity.setAdresse(dto.getAdresse());
        entity.setMatrFiscal(dto.getMatrFiscal());
        entity.setIdentifiantFiscal(dto.getIdentifiantFiscal());
        entity.setAutreIdentifiantFiscal(dto.getAutreIdentifiantFiscal());
        entity.setCategorieTva(dto.getCategorieTva());
        entity.setModePaiement(dto.getModePaiement());
        entity.setModalitePaiement(dto.getModalitePaiement());
        entity.setRib(dto.getRib());
        entity.setIban(dto.getIban());
        entity.setBic(dto.getBic());
        entity.setActif(dto.getActif() != null ? dto.getActif() : true);

        entity.setDateCreation(LocalDateTime.now());
        entity.setBlSuppression(false);


        if (dto.getTypeClient() != null) {
            entity.setTypeClient(dto.getTypeClient());

            switch (dto.getTypeClient()) {
                case "CLIENT", "FOURNISSEUR" -> {  }

                case "TRANSPORTEUR" -> {
                    entity.setPrestataire(true);
                    entity.setGrilleTarifaire(dto.getGrilleTarifaire());
                    entity.setPriseRdvObligatoire(dto.getPriseRdvObligatoire());
                }

                case "CHAUFFEUR" -> {
                    entity.setNom(dto.getNom());
                    entity.setPrenom(dto.getPrenom());
                    entity.setNumeroCin(dto.getNumeroCin());
                    entity.setTelephoneMobile(dto.getTelephoneMobile());
                }

                case "COMMISSIONNAIRE" -> {
                    entity.setNumeroLicenceComissionnaire(dto.getNumeroLicenceComissionnaire());
                    entity.setDateEcheanceLicenceComissionnaire(dto.getDateEcheanceLicenceComissionnaire());
                }

                case "BANQUE" -> {
                    entity.setRib(dto.getRib());
                    entity.setIban(dto.getIban());
                    entity.setBic(dto.getBic());
                }

                default -> throw new IllegalArgumentException("Type de client inconnu : " + dto.getTypeClient());
            }
        }

        return entity;
    }

    public static PartieInteresseReadDTO toReadDTO(PartieInteresseEntite entity) {
        PartieInteresseReadDTO dto = new PartieInteresseReadDTO();
        dto.setId(entity.getId());
        dto.setReference(entity.getReference());
        dto.setRaisonSociale(entity.getRaisonSociale());
        dto.setAbreviation(entity.getAbreviation());
        dto.setActivite(entity.getActivite());
        dto.setEmail(entity.getEmail());
        dto.setTelephone(entity.getTelephone());
        dto.setActif(entity.getActif());
        dto.setTypeClient(entity.getTypeClient());
        dto.setPrestataire(entity.getPrestataire());
        dto.setNom(entity.getNom());
        dto.setPrenom(entity.getPrenom());
        dto.setNumeroCin(entity.getNumeroCin());
        dto.setNumeroLicenceComissionnaire(entity.getNumeroLicenceComissionnaire());
        return dto;
    }


    public static void applyUpdate(PartieInteresseEntite entity, PartieInteresseUpdateDTO dto) {


        if (dto.getRaisonSociale() != null)        entity.setRaisonSociale(dto.getRaisonSociale());
        if (dto.getAbreviation() != null)          entity.setAbreviation(dto.getAbreviation());
        if (dto.getActivite() != null)             entity.setActivite(dto.getActivite());
        if (dto.getObservation() != null)          entity.setObservation(dto.getObservation());
        if (dto.getActif() != null)                entity.setActif(dto.getActif());


        if (dto.getEmail() != null)                entity.setEmail(dto.getEmail());
        if (dto.getEmail2() != null)               entity.setEmail2(dto.getEmail2());
        if (dto.getTelephone() != null)            entity.setTelephone(dto.getTelephone());
        if (dto.getTelephoneMobile() != null)      entity.setTelephoneMobile(dto.getTelephoneMobile());
        if (dto.getFax() != null)                  entity.setFax(dto.getFax());


        if (dto.getAdresse() != null)              entity.setAdresse(dto.getAdresse());
        if (dto.getAdresseFacturation() != null)   entity.setAdresseFacturation(dto.getAdresseFacturation());
        if (dto.getAdresseLivraison() != null)     entity.setAdresseLivraison(dto.getAdresseLivraison());
        if (dto.getPaysFacturation() != null)      entity.setPaysFacturation(dto.getPaysFacturation());
        if (dto.getVilleFacturation() != null)     entity.setVilleFacturation(dto.getVilleFacturation());
        if (dto.getCodePostalFacturation() != null) entity.setCodePostalFacturation(dto.getCodePostalFacturation());
        if (dto.getRueFacturation() != null)       entity.setRueFacturation(dto.getRueFacturation());
        if (dto.getPaysLivraison() != null)        entity.setPaysLivraison(dto.getPaysLivraison());
        if (dto.getVilleLivraison() != null)       entity.setVilleLivraison(dto.getVilleLivraison());
        if (dto.getCodePostalLivraison() != null)  entity.setCodePostalLivraison(dto.getCodePostalLivraison());
        if (dto.getRueLivraison() != null)         entity.setRueLivraison(dto.getRueLivraison());


        if (dto.getCategorieTva() != null)         entity.setCategorieTva(dto.getCategorieTva());
        if (dto.getModePaiement() != null)         entity.setModePaiement(dto.getModePaiement());
        if (dto.getModalitePaiement() != null)     entity.setModalitePaiement(dto.getModalitePaiement());
        if (dto.getRib() != null)                  entity.setRib(dto.getRib());
        if (dto.getIban() != null)                 entity.setIban(dto.getIban());
        if (dto.getBic() != null)                  entity.setBic(dto.getBic());


        if (dto.getInterlocuteur() != null)        entity.setInterlocuteur(dto.getInterlocuteur());
        if (dto.getEmailInterlocuteur() != null)   entity.setEmailInterlocuteur(dto.getEmailInterlocuteur());
        if (dto.getTelInterlocuteur() != null)     entity.setTelInterlocuteur(dto.getTelInterlocuteur());


        if (dto.getNom() != null)                  entity.setNom(dto.getNom());
        if (dto.getPrenom() != null)               entity.setPrenom(dto.getPrenom());
        if (dto.getNumeroCin() != null)            entity.setNumeroCin(dto.getNumeroCin());
        if (dto.getNumeroLicence() != null)        entity.setNumeroLicence(dto.getNumeroLicence());


        if (dto.getNumeroLicenceComissionnaire() != null)
            entity.setNumeroLicenceComissionnaire(dto.getNumeroLicenceComissionnaire());
        if (dto.getDateEcheanceLicenceComissionnaire() != null)
            entity.setDateEcheanceLicenceComissionnaire(dto.getDateEcheanceLicenceComissionnaire());


        if (dto.getGrilleTarifaire() != null)      entity.setGrilleTarifaire(dto.getGrilleTarifaire());
        if (dto.getPriseRdvObligatoire() != null)  entity.setPriseRdvObligatoire(dto.getPriseRdvObligatoire());
        if (dto.getPrestataire() != null)          entity.setPrestataire(dto.getPrestataire());


        entity.setDateModification(LocalDateTime.now());
    }
}
