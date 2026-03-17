package com.gpro.consulting.tiers.logistique.controller;

import com.gpro.consulting.tiers.logistique.dto.ArticleProduitCreateDTO;
import com.gpro.consulting.tiers.logistique.dto.ArticleProduitReadDTO;
import com.gpro.consulting.tiers.logistique.dto.ArticleProduitUpdateDTO;
import com.gpro.consulting.tiers.logistique.dto.DetailOrdreTransportCreateDTO;
import com.gpro.consulting.tiers.logistique.dto.DetailOrdreTransportReadDTO;
import com.gpro.consulting.tiers.logistique.dto.DetailOrdreTransportUpdateDTO;
import com.gpro.consulting.tiers.logistique.dto.LigneTransportCreateDTO;
import com.gpro.consulting.tiers.logistique.dto.LigneTransportReadDTO;
import com.gpro.consulting.tiers.logistique.dto.LigneTransportUpdateDTO;
import com.gpro.consulting.tiers.logistique.dto.OrdreTransportCreateDTO;
import com.gpro.consulting.tiers.logistique.dto.OrdreTransportReadDTO;
import com.gpro.consulting.tiers.logistique.dto.OrdreTransportUpdateDTO;
import com.gpro.consulting.tiers.logistique.dto.ProduitDetailCreateDTO;
import com.gpro.consulting.tiers.logistique.dto.ProduitDetailReadDTO;
import com.gpro.consulting.tiers.logistique.dto.ProduitDetailUpdateDTO;
import com.gpro.consulting.tiers.logistique.service.command.OrdreTransportCommandService;
import com.gpro.consulting.tiers.logistique.service.exception.NotFoundException;
import com.gpro.consulting.tiers.logistique.service.query.ArticleProduitQueryService;
import com.gpro.consulting.tiers.logistique.service.query.DetailOrdreTransportQueryService;
import com.gpro.consulting.tiers.logistique.service.query.LigneTransportQueryService;
import com.gpro.consulting.tiers.logistique.service.query.OrdreTransportQueryService;
import com.gpro.consulting.tiers.logistique.service.query.ProduitDetailQueryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/ordres-transport")
public class OrdreTransportController {

    private final OrdreTransportCommandService commandService;
    private final OrdreTransportQueryService ordreQueryService;
    private final DetailOrdreTransportQueryService detailQueryService;
    private final LigneTransportQueryService ligneQueryService;
    private final ProduitDetailQueryService produitDetailQueryService;
    private final ArticleProduitQueryService articleProduitQueryService;

    public OrdreTransportController(OrdreTransportCommandService commandService,
                                    OrdreTransportQueryService ordreQueryService,
                                    DetailOrdreTransportQueryService detailQueryService,
                                    LigneTransportQueryService ligneQueryService,
                                    ProduitDetailQueryService produitDetailQueryService,
                                    ArticleProduitQueryService articleProduitQueryService) {
        this.commandService = commandService;
        this.ordreQueryService = ordreQueryService;
        this.detailQueryService = detailQueryService;
        this.ligneQueryService = ligneQueryService;
        this.produitDetailQueryService = produitDetailQueryService;
        this.articleProduitQueryService = articleProduitQueryService;
    }

    @GetMapping
    public List<OrdreTransportReadDTO> findOrdres(@RequestParam(required = false) String reference,
                                                  @RequestParam(required = false) String etat) {
        if (StringUtils.hasText(reference)) {
            return ordreQueryService.findByReference(reference);
        }
        if (StringUtils.hasText(etat)) {
            return ordreQueryService.findByEtat(etat);
        }
        return ordreQueryService.findAll();
    }

    @GetMapping("/{ordreId}")
    public OrdreTransportReadDTO findOrdreById(@PathVariable Long ordreId) {
        return ordreQueryService.findById(ordreId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrdreTransportReadDTO createOrdre(@Valid @RequestBody OrdreTransportCreateDTO dto) {
        return commandService.createOrdre(dto);
    }

    @PutMapping("/{ordreId}")
    public OrdreTransportReadDTO updateOrdre(@PathVariable Long ordreId,
                                             @Valid @RequestBody OrdreTransportUpdateDTO dto) {
        return commandService.updateOrdre(ordreId, dto);
    }

    @DeleteMapping("/{ordreId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrdre(@PathVariable Long ordreId) {
        commandService.deleteOrdre(ordreId);
    }

    @GetMapping("/{ordreId}/details")
    public List<DetailOrdreTransportReadDTO> findDetailsByOrdre(@PathVariable Long ordreId) {
        return detailQueryService.findByOrdreTransportId(ordreId);
    }

    @GetMapping("/{ordreId}/details/{detailId}")
    public DetailOrdreTransportReadDTO findDetailById(@PathVariable Long ordreId, @PathVariable Long detailId) {
        return getDetailRequired(ordreId, detailId);
    }

    @PostMapping("/{ordreId}/details")
    @ResponseStatus(HttpStatus.CREATED)
    public DetailOrdreTransportReadDTO createDetail(@PathVariable Long ordreId,
                                                    @Valid @RequestBody DetailOrdreTransportCreateDTO dto) {
        return commandService.createDetail(ordreId, dto);
    }

    @PutMapping("/{ordreId}/details/{detailId}")
    public DetailOrdreTransportReadDTO updateDetail(@PathVariable Long ordreId,
                                                    @PathVariable Long detailId,
                                                    @Valid @RequestBody DetailOrdreTransportUpdateDTO dto) {
        return commandService.updateDetail(ordreId, detailId, dto);
    }

    @DeleteMapping("/{ordreId}/details/{detailId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDetail(@PathVariable Long ordreId, @PathVariable Long detailId) {
        commandService.deleteDetail(ordreId, detailId);
    }

    @GetMapping("/{ordreId}/details/{detailId}/lignes")
    public List<LigneTransportReadDTO> findLignesByDetail(@PathVariable Long ordreId, @PathVariable Long detailId) {
        getDetailRequired(ordreId, detailId);
        return ligneQueryService.findByDetailOrdreTransportId(detailId);
    }

    @GetMapping("/{ordreId}/details/{detailId}/lignes/{ligneId}")
    public LigneTransportReadDTO findLigneById(@PathVariable Long ordreId,
                                               @PathVariable Long detailId,
                                               @PathVariable Long ligneId) {
        getDetailRequired(ordreId, detailId);
        return ligneQueryService.findByDetailOrdreTransportId(detailId)
                .stream()
                .filter(ligne -> ligne.getId() != null && ligne.getId().equals(ligneId))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Ligne introuvable, id=" + ligneId));
    }

    @PostMapping("/{ordreId}/details/{detailId}/lignes")
    @ResponseStatus(HttpStatus.CREATED)
    public LigneTransportReadDTO createLigne(@PathVariable Long ordreId,
                                             @PathVariable Long detailId,
                                             @Valid @RequestBody LigneTransportCreateDTO dto) {
        return commandService.createLigne(ordreId, detailId, dto);
    }

    @PutMapping("/{ordreId}/details/{detailId}/lignes/{ligneId}")
    public LigneTransportReadDTO updateLigne(@PathVariable Long ordreId,
                                             @PathVariable Long detailId,
                                             @PathVariable Long ligneId,
                                             @Valid @RequestBody LigneTransportUpdateDTO dto) {
        return commandService.updateLigne(ordreId, detailId, ligneId, dto);
    }

    @DeleteMapping("/{ordreId}/details/{detailId}/lignes/{ligneId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLigne(@PathVariable Long ordreId,
                            @PathVariable Long detailId,
                            @PathVariable Long ligneId) {
        commandService.deleteLigne(ordreId, detailId, ligneId);
    }

    @GetMapping("/{ordreId}/details/{detailId}/produits-details")
    public List<ProduitDetailReadDTO> findProduitsDetailsByDetail(@PathVariable Long ordreId,
                                                                  @PathVariable Long detailId) {
        getDetailRequired(ordreId, detailId);
        return produitDetailQueryService.findByDetailOrdreTransportId(detailId);
    }

    @GetMapping("/{ordreId}/details/{detailId}/produits-details/{produitDetailId}")
    public ProduitDetailReadDTO findProduitDetailById(@PathVariable Long ordreId,
                                                      @PathVariable Long detailId,
                                                      @PathVariable Long produitDetailId) {
        getDetailRequired(ordreId, detailId);
        return produitDetailQueryService.findByDetailOrdreTransportId(detailId)
                .stream()
                .filter(produit -> produit.getId() != null && produit.getId().equals(produitDetailId))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Produit detail introuvable, id=" + produitDetailId));
    }

    @PostMapping("/{ordreId}/details/{detailId}/produits-details")
    @ResponseStatus(HttpStatus.CREATED)
    public ProduitDetailReadDTO createProduitDetail(@PathVariable Long ordreId,
                                                    @PathVariable Long detailId,
                                                    @Valid @RequestBody ProduitDetailCreateDTO dto) {
        return commandService.createProduitDetail(ordreId, detailId, dto);
    }

    @PutMapping("/{ordreId}/details/{detailId}/produits-details/{produitDetailId}")
    public ProduitDetailReadDTO updateProduitDetail(@PathVariable Long ordreId,
                                                    @PathVariable Long detailId,
                                                    @PathVariable Long produitDetailId,
                                                    @Valid @RequestBody ProduitDetailUpdateDTO dto) {
        return commandService.updateProduitDetail(ordreId, detailId, produitDetailId, dto);
    }

    @DeleteMapping("/{ordreId}/details/{detailId}/produits-details/{produitDetailId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduitDetail(@PathVariable Long ordreId,
                                    @PathVariable Long detailId,
                                    @PathVariable Long produitDetailId) {
        commandService.deleteProduitDetail(ordreId, detailId, produitDetailId);
    }

    @GetMapping("/{ordreId}/details/{detailId}/articles-produits")
    public List<ArticleProduitReadDTO> findArticlesProduitsByDetail(@PathVariable Long ordreId,
                                                                    @PathVariable Long detailId) {
        getDetailRequired(ordreId, detailId);
        return articleProduitQueryService.findByDetailOrdreTransportId(detailId);
    }

    @GetMapping("/{ordreId}/details/{detailId}/articles-produits/{articleProduitId}")
    public ArticleProduitReadDTO findArticleProduitById(@PathVariable Long ordreId,
                                                        @PathVariable Long detailId,
                                                        @PathVariable Long articleProduitId) {
        getDetailRequired(ordreId, detailId);
        return articleProduitQueryService.findByDetailOrdreTransportId(detailId)
                .stream()
                .filter(article -> article.getId() != null && article.getId().equals(articleProduitId))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Article produit introuvable, id=" + articleProduitId));
    }

    @PostMapping("/{ordreId}/details/{detailId}/articles-produits")
    @ResponseStatus(HttpStatus.CREATED)
    public ArticleProduitReadDTO createArticleProduit(@PathVariable Long ordreId,
                                                      @PathVariable Long detailId,
                                                      @Valid @RequestBody ArticleProduitCreateDTO dto) {
        return commandService.createArticleProduit(ordreId, detailId, dto);
    }

    @PutMapping("/{ordreId}/details/{detailId}/articles-produits/{articleProduitId}")
    public ArticleProduitReadDTO updateArticleProduit(@PathVariable Long ordreId,
                                                      @PathVariable Long detailId,
                                                      @PathVariable Long articleProduitId,
                                                      @Valid @RequestBody ArticleProduitUpdateDTO dto) {
        return commandService.updateArticleProduit(ordreId, detailId, articleProduitId, dto);
    }

    @DeleteMapping("/{ordreId}/details/{detailId}/articles-produits/{articleProduitId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteArticleProduit(@PathVariable Long ordreId,
                                     @PathVariable Long detailId,
                                     @PathVariable Long articleProduitId) {
        commandService.deleteArticleProduit(ordreId, detailId, articleProduitId);
    }

    private DetailOrdreTransportReadDTO getDetailRequired(Long ordreId, Long detailId) {
        return detailQueryService.findByOrdreTransportId(ordreId)
                .stream()
                .filter(detail -> detail.getId() != null && detail.getId().equals(detailId))
                .findFirst()
                .orElseThrow(() -> new NotFoundException(
                        "Detail introuvable, id=" + detailId + ", ordreId=" + ordreId));
    }
}
