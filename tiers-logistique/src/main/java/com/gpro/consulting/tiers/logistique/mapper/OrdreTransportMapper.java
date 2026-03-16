package com.gpro.consulting.tiers.logistique.mapper;

import com.gpro.consulting.tiers.logistique.dto.OrdreTransportCreateDTO;
import com.gpro.consulting.tiers.logistique.dto.OrdreTransportReadDTO;
import com.gpro.consulting.tiers.logistique.dto.OrdreTransportUpdateDTO;
import com.gpro.consulting.tiers.logistique.persistance.gc.bonlivraison.entitie.DetailOrdreTransportEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.bonlivraison.entitie.OrdreTransportEntity;
import org.mapstruct.AfterMapping;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(config = MapStructCentralConfig.class, uses = DetailOrdreTransportMapper.class)
public interface OrdreTransportMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "montantHTaxe", ignore = true)
    @Mapping(target = "montantTTC", ignore = true)
    @Mapping(target = "montantTaxe", ignore = true)
    @Mapping(target = "montantRemise", ignore = true)
    @Mapping(target = "metrageTotal", ignore = true)
    @Mapping(target = "listDetLivraisonVente", source = "details")
    OrdreTransportEntity toEntity(OrdreTransportCreateDTO dto);

    @Mapping(target = "details", source = "listDetLivraisonVente")
    OrdreTransportReadDTO toReadDTO(OrdreTransportEntity entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "montantHTaxe", ignore = true)
    @Mapping(target = "montantTTC", ignore = true)
    @Mapping(target = "montantTaxe", ignore = true)
    @Mapping(target = "montantRemise", ignore = true)
    @Mapping(target = "metrageTotal", ignore = true)
    @Mapping(target = "listDetLivraisonVente", source = "details")
    OrdreTransportEntity toEntity(OrdreTransportUpdateDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "montantHTaxe", ignore = true)
    @Mapping(target = "montantTTC", ignore = true)
    @Mapping(target = "montantTaxe", ignore = true)
    @Mapping(target = "montantRemise", ignore = true)
    @Mapping(target = "metrageTotal", ignore = true)
    @Mapping(target = "listDetLivraisonVente", source = "details")
    void updateEntityFromDTO(OrdreTransportUpdateDTO dto, @MappingTarget OrdreTransportEntity entity);

    List<OrdreTransportReadDTO> toReadDTOList(List<OrdreTransportEntity> entities);

    @AfterMapping
    default void bindChildren(@MappingTarget OrdreTransportEntity entity) {
        if (entity.getListDetLivraisonVente() != null) {
            for (DetailOrdreTransportEntity detail : entity.getListDetLivraisonVente()) {
                detail.setOrdreTransport(entity);
            }
        }
    }
}
