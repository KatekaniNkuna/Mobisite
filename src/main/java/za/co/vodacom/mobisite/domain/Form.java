package za.co.vodacom.mobisite.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

import za.co.vodacom.mobisite.domain.enumeration.CompetitorName;

import za.co.vodacom.mobisite.domain.enumeration.Province;

import za.co.vodacom.mobisite.domain.enumeration.ProductType;

import za.co.vodacom.mobisite.domain.enumeration.TypeOfOffer;

import za.co.vodacom.mobisite.domain.enumeration.OfferType;

import za.co.vodacom.mobisite.domain.enumeration.OfferPurchaseLimitation;

import za.co.vodacom.mobisite.domain.enumeration.NatureOFOffer;

import za.co.vodacom.mobisite.domain.enumeration.OfferValidity;

import za.co.vodacom.mobisite.domain.enumeration.RiskOfMarketShareLoss;

/**
 * A Form.
 */
@Entity
@Table(name = "form")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Form implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "competitor", nullable = false)
    private CompetitorName competitor;

    @Enumerated(EnumType.STRING)
    @Column(name = "province")
    private Province province;

    @Lob
    @Column(name = "image")
    private byte[] image;

    @Column(name = "image_content_type")
    private String imageContentType;

    @NotNull
    @Column(name = "offer_date", nullable = false)
    private Instant offerDate;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "product_type", nullable = false)
    private ProductType productType;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "type_of_offer", nullable = false)
    private TypeOfOffer typeOfOffer;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "offer_type", nullable = false)
    private OfferType offerType;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "offer_purchase_limitation", nullable = false)
    private OfferPurchaseLimitation offerPurchaseLimitation;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "nature_of_offer", nullable = false)
    private NatureOFOffer natureOFOffer;

    @NotNull
    @DecimalMin(value = "0")
    @Column(name = "offer_price", precision = 21, scale = 2, nullable = false)
    private BigDecimal offerPrice;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "offer_validity", nullable = false)
    private OfferValidity offerValidity;

    @NotNull
    @DecimalMin(value = "0")
    @Column(name = "offer_allocation", precision = 21, scale = 2, nullable = false)
    private BigDecimal offerAllocation;

    @NotNull
    @Column(name = "offer_end_date", nullable = false)
    private Instant offerEndDate;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "risk_of_market_share_loss", nullable = false)
    private RiskOfMarketShareLoss riskOfMarketShareLoss;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CompetitorName getCompetitor() {
        return competitor;
    }

    public Form competitor(CompetitorName competitor) {
        this.competitor = competitor;
        return this;
    }

    public void setCompetitor(CompetitorName competitor) {
        this.competitor = competitor;
    }

    public Province getProvince() {
        return province;
    }

    public Form province(Province province) {
        this.province = province;
        return this;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public byte[] getImage() {
        return image;
    }

    public Form image(byte[] image) {
        this.image = image;
        return this;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getImageContentType() {
        return imageContentType;
    }

    public Form imageContentType(String imageContentType) {
        this.imageContentType = imageContentType;
        return this;
    }

    public void setImageContentType(String imageContentType) {
        this.imageContentType = imageContentType;
    }

    public Instant getOfferDate() {
        return offerDate;
    }

    public Form offerDate(Instant offerDate) {
        this.offerDate = offerDate;
        return this;
    }

    public void setOfferDate(Instant offerDate) {
        this.offerDate = offerDate;
    }

    public ProductType getProductType() {
        return productType;
    }

    public Form productType(ProductType productType) {
        this.productType = productType;
        return this;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public TypeOfOffer getTypeOfOffer() {
        return typeOfOffer;
    }

    public Form typeOfOffer(TypeOfOffer typeOfOffer) {
        this.typeOfOffer = typeOfOffer;
        return this;
    }

    public void setTypeOfOffer(TypeOfOffer typeOfOffer) {
        this.typeOfOffer = typeOfOffer;
    }

    public OfferType getOfferType() {
        return offerType;
    }

    public Form offerType(OfferType offerType) {
        this.offerType = offerType;
        return this;
    }

    public void setOfferType(OfferType offerType) {
        this.offerType = offerType;
    }

    public OfferPurchaseLimitation getOfferPurchaseLimitation() {
        return offerPurchaseLimitation;
    }

    public Form offerPurchaseLimitation(OfferPurchaseLimitation offerPurchaseLimitation) {
        this.offerPurchaseLimitation = offerPurchaseLimitation;
        return this;
    }

    public void setOfferPurchaseLimitation(OfferPurchaseLimitation offerPurchaseLimitation) {
        this.offerPurchaseLimitation = offerPurchaseLimitation;
    }

    public NatureOFOffer getNatureOFOffer() {
        return natureOFOffer;
    }

    public Form natureOFOffer(NatureOFOffer natureOFOffer) {
        this.natureOFOffer = natureOFOffer;
        return this;
    }

    public void setNatureOFOffer(NatureOFOffer natureOFOffer) {
        this.natureOFOffer = natureOFOffer;
    }

    public BigDecimal getOfferPrice() {
        return offerPrice;
    }

    public Form offerPrice(BigDecimal offerPrice) {
        this.offerPrice = offerPrice;
        return this;
    }

    public void setOfferPrice(BigDecimal offerPrice) {
        this.offerPrice = offerPrice;
    }

    public OfferValidity getOfferValidity() {
        return offerValidity;
    }

    public Form offerValidity(OfferValidity offerValidity) {
        this.offerValidity = offerValidity;
        return this;
    }

    public void setOfferValidity(OfferValidity offerValidity) {
        this.offerValidity = offerValidity;
    }

    public BigDecimal getOfferAllocation() {
        return offerAllocation;
    }

    public Form offerAllocation(BigDecimal offerAllocation) {
        this.offerAllocation = offerAllocation;
        return this;
    }

    public void setOfferAllocation(BigDecimal offerAllocation) {
        this.offerAllocation = offerAllocation;
    }

    public Instant getOfferEndDate() {
        return offerEndDate;
    }

    public Form offerEndDate(Instant offerEndDate) {
        this.offerEndDate = offerEndDate;
        return this;
    }

    public void setOfferEndDate(Instant offerEndDate) {
        this.offerEndDate = offerEndDate;
    }

    public RiskOfMarketShareLoss getRiskOfMarketShareLoss() {
        return riskOfMarketShareLoss;
    }

    public Form riskOfMarketShareLoss(RiskOfMarketShareLoss riskOfMarketShareLoss) {
        this.riskOfMarketShareLoss = riskOfMarketShareLoss;
        return this;
    }

    public void setRiskOfMarketShareLoss(RiskOfMarketShareLoss riskOfMarketShareLoss) {
        this.riskOfMarketShareLoss = riskOfMarketShareLoss;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Form)) {
            return false;
        }
        return id != null && id.equals(((Form) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Form{" +
            "id=" + getId() +
            ", competitor='" + getCompetitor() + "'" +
            ", province='" + getProvince() + "'" +
            ", image='" + getImage() + "'" +
            ", imageContentType='" + getImageContentType() + "'" +
            ", offerDate='" + getOfferDate() + "'" +
            ", productType='" + getProductType() + "'" +
            ", typeOfOffer='" + getTypeOfOffer() + "'" +
            ", offerType='" + getOfferType() + "'" +
            ", offerPurchaseLimitation='" + getOfferPurchaseLimitation() + "'" +
            ", natureOFOffer='" + getNatureOFOffer() + "'" +
            ", offerPrice=" + getOfferPrice() +
            ", offerValidity='" + getOfferValidity() + "'" +
            ", offerAllocation=" + getOfferAllocation() +
            ", offerEndDate='" + getOfferEndDate() + "'" +
            ", riskOfMarketShareLoss='" + getRiskOfMarketShareLoss() + "'" +
            "}";
    }
}
