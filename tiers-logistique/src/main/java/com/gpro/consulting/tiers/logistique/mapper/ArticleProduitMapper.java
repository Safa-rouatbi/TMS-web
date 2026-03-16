package com.gpro.consulting.tiers.logistique.mapper;

import com.gpro.consulting.tiers.logistique.dto.ArticleProduitCreateDTO;
import com.gpro.consulting.tiers.logistique.dto.ArticleProduitReadDTO;
import com.gpro.consulting.tiers.logistique.dto.ArticleProduitUpdateDTO;
import com.gpro.consulting.tiers.logistique.persistance.elementBase.entity.ArticleProduitEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(config = MapStructCentralConfig.class)
public interface ArticleProduitMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "detailOrdreTransport", ignore = true)
    ArticleProduitEntity toEntity(ArticleProduitCreateDTO dto);

    ArticleProduitReadDTO toReadDTO(ArticleProduitEntity entity);

    @Mapping(target = "detailOrdreTransport", ignore = true)
    ArticleProduitEntity toEntity(ArticleProduitUpdateDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "detailOrdreTransport", ignore = true)
    void updateEntityFromDTO(ArticleProduitUpdateDTO dto, @MappingTarget ArticleProduitEntity entity);
}
