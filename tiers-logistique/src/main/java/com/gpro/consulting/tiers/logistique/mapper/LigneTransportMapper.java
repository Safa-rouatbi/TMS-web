package com.gpro.consulting.tiers.logistique.mapper;

import com.gpro.consulting.tiers.logistique.dto.LigneTransportCreateDTO;
import com.gpro.consulting.tiers.logistique.dto.LigneTransportReadDTO;
import com.gpro.consulting.tiers.logistique.dto.LigneTransportUpdateDTO;
import com.gpro.consulting.tiers.logistique.persistance.gc.bonlivraison.entitie.LigneTransportEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(config = MapStructCentralConfig.class)
public interface LigneTransportMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "detailOrdreTransport", ignore = true)
    LigneTransportEntity toEntity(LigneTransportCreateDTO dto);

    LigneTransportReadDTO toReadDTO(LigneTransportEntity entity);

    @Mapping(target = "detailOrdreTransport", ignore = true)
    LigneTransportEntity toEntity(LigneTransportUpdateDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "detailOrdreTransport", ignore = true)
    void updateEntityFromDTO(LigneTransportUpdateDTO dto, @MappingTarget LigneTransportEntity entity);
}
