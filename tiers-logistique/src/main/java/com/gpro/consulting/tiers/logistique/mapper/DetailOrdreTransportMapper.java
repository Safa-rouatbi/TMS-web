package com.gpro.consulting.tiers.logistique.mapper;

import com.gpro.consulting.tiers.logistique.dto.DetailOrdreTransportCreateDTO;
import com.gpro.consulting.tiers.logistique.dto.DetailOrdreTransportReadDTO;
import com.gpro.consulting.tiers.logistique.dto.DetailOrdreTransportUpdateDTO;
import com.gpro.consulting.tiers.logistique.persistance.elementBase.entity.ArticleProduitEntity;
import com.gpro.consulting.tiers.logistique.persistance.elementBase.entity.ProduitDetailEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.bonlivraison.entitie.DetailOrdreTransportEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.bonlivraison.entitie.LigneTransportEntity;
import org.mapstruct.AfterMapping;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(
        config = MapStructCentralConfig.class,
        uses = {LigneTransportMapper.class, ArticleProduitMapper.class, ProduitDetailMapper.class}
)
public interface DetailOrdreTransportMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "ordreTransport", ignore = true)
    @Mapping(target = "listTransportVenteEntities", source = "lignesTransport")
    @Mapping(target = "articleProduits", source = "articleProduits")
    @Mapping(target = "produitDetailEntities", source = "produitDetails")
    DetailOrdreTransportEntity toEntity(DetailOrdreTransportCreateDTO dto);

    @Mapping(target = "lignesTransport", source = "listTransportVenteEntities")
    @Mapping(target = "articleProduits", source = "articleProduits")
    @Mapping(target = "produitDetails", source = "produitDetailEntities")
    DetailOrdreTransportReadDTO toReadDTO(DetailOrdreTransportEntity entity);

    @Mapping(target = "ordreTransport", ignore = true)
    @Mapping(target = "listTransportVenteEntities", source = "lignesTransport")
    @Mapping(target = "articleProduits", source = "articleProduits")
    @Mapping(target = "produitDetailEntities", source = "produitDetails")
    DetailOrdreTransportEntity toEntity(DetailOrdreTransportUpdateDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "ordreTransport", ignore = true)
    @Mapping(target = "listTransportVenteEntities", source = "lignesTransport")
    @Mapping(target = "articleProduits", source = "articleProduits")
    @Mapping(target = "produitDetailEntities", source = "produitDetails")
    void updateEntityFromDTO(DetailOrdreTransportUpdateDTO dto, @MappingTarget DetailOrdreTransportEntity entity);

    List<DetailOrdreTransportReadDTO> toReadDTOList(List<DetailOrdreTransportEntity> entities);

    @AfterMapping
    default void bindChildren(@MappingTarget DetailOrdreTransportEntity entity) {
        if (entity.getListTransportVenteEntities() != null) {
            for (LigneTransportEntity ligne : entity.getListTransportVenteEntities()) {
                ligne.setDetailOrdreTransport(entity);
            }
        }
        if (entity.getProduitDetailEntities() != null) {
            for (ProduitDetailEntity produitDetail : entity.getProduitDetailEntities()) {
                produitDetail.setDetailOrdreTransport(entity);
            }
        }
        if (entity.getArticleProduits() != null) {
            for (ArticleProduitEntity article : entity.getArticleProduits()) {
                article.setDetailOrdreTransport(entity);
            }
        }
    }
}
