package com.gpro.consulting.tiers.logistique.mapper;

import com.gpro.consulting.tiers.logistique.dto.ProduitDetailCreateDTO;
import com.gpro.consulting.tiers.logistique.dto.ProduitDetailReadDTO;
import com.gpro.consulting.tiers.logistique.dto.ProduitDetailUpdateDTO;
import com.gpro.consulting.tiers.logistique.persistance.elementBase.entity.ProduitDetailEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(config = MapStructCentralConfig.class)
public interface ProduitDetailMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "detailOrdreTransport", ignore = true)
    ProduitDetailEntity toEntity(ProduitDetailCreateDTO dto);

    ProduitDetailReadDTO toReadDTO(ProduitDetailEntity entity);

    @Mapping(target = "detailOrdreTransport", ignore = true)
    ProduitDetailEntity toEntity(ProduitDetailUpdateDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "detailOrdreTransport", ignore = true)
    void updateEntityFromDTO(ProduitDetailUpdateDTO dto, @MappingTarget ProduitDetailEntity entity);
}
