package com.gpro.consulting.tiers.logistique.persistance.gc.bonlivraison.entitie;

import com.gpro.consulting.tiers.logistique.coordination.gc.IConstanteCommerciale;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name=IConstanteCommerciale.TABLE_GC_DETTRANSPORTVENTE)
public class LigneTransportEntity implements Serializable {

    private static final long serialVersionUID = 569048653919573589L;

    @Id
    @SequenceGenerator(name = "DETLIVRAISONVENTE_ID_GENERATOR", sequenceName = IConstanteCommerciale.SEQUENCE_GC_DET_TRANSP_SEQ, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DETLIVRAISONVENTE_ID_GENERATOR")
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="date")
    private Date date;

    @Column(name="envoi")
    private String envoi;

    @Column(name="dpt")
    private String dpt;

    @Column(name="lieu")
    private String lieu;

    @Column(name="denomination_client")
    private String denominationClient;

    @Column(name="pd_brut")
    private String pdBrut;

    @Column(name="metrage")
    private Double metrage;


    @Column(name="NOMBRE_COLIS")
    private Long nombreColis;

    @Column(name="quantite_unite_colisage")
    private Double quantiteUniteColisage;

    @Column(name="quantite")
    private Double quantite;

    @Column(name="unite")
    private String unite;


    @Column(name="pu")
    private Double pu;

    @Column(name="montant_surcharge")
    private Double montantSurcharge;

    @Column(name="chauffeur")
    private String chauffeur;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="detail_livraison_id")
    private DetailOrdreTransportEntity detailOrdreTransport = new DetailOrdreTransportEntity();


    @Column(name="numero")
    private String numero;

    @Column(name="societe")
    private String societe;

    @Column(name="adresse")
    private String adresse;

    @Column(name="code_postal")
    private String codePostal;

    @Column(name="ville")
    private String ville;

    @Column(name="vocation_cmm")
    private String vocationcmm;

    @Column(name="mail")
    private String mail;

    @Column(name="tel")
    private String tel;

    @Column(name="heure")
    private String heure;

    @Column(name="nature_colis")
    private String natureColis;}


