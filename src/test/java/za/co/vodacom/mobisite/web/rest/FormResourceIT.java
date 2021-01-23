package za.co.vodacom.mobisite.web.rest;

import za.co.vodacom.mobisite.MobisiteApp;
import za.co.vodacom.mobisite.domain.Form;
import za.co.vodacom.mobisite.repository.FormRepository;
import za.co.vodacom.mobisite.service.FormService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;
import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
 * Integration tests for the {@link FormResource} REST controller.
 */
@SpringBootTest(classes = MobisiteApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class FormResourceIT {

    private static final CompetitorName DEFAULT_COMPETITOR = CompetitorName.MTN;
    private static final CompetitorName UPDATED_COMPETITOR = CompetitorName.Telkom;

    private static final Province DEFAULT_PROVINCE = Province.Gauteng;
    private static final Province UPDATED_PROVINCE = Province.Limpopo;

    private static final byte[] DEFAULT_IMAGE = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_IMAGE = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_IMAGE_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_IMAGE_CONTENT_TYPE = "image/png";

    private static final Instant DEFAULT_OFFER_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_OFFER_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final ProductType DEFAULT_PRODUCT_TYPE = ProductType.Fibre;
    private static final ProductType UPDATED_PRODUCT_TYPE = ProductType.Data;

    private static final TypeOfOffer DEFAULT_TYPE_OF_OFFER = TypeOfOffer.LocationBased;
    private static final TypeOfOffer UPDATED_TYPE_OF_OFFER = TypeOfOffer.Provincial;

    private static final OfferType DEFAULT_OFFER_TYPE = OfferType.Prepaid;
    private static final OfferType UPDATED_OFFER_TYPE = OfferType.Postpaid;

    private static final OfferPurchaseLimitation DEFAULT_OFFER_PURCHASE_LIMITATION = OfferPurchaseLimitation.All;
    private static final OfferPurchaseLimitation UPDATED_OFFER_PURCHASE_LIMITATION = OfferPurchaseLimitation.Intergrated;

    private static final NatureOFOffer DEFAULT_NATURE_OF_OFFER = NatureOFOffer.RechargOffer;
    private static final NatureOFOffer UPDATED_NATURE_OF_OFFER = NatureOFOffer.NewCustomers;

    private static final BigDecimal DEFAULT_OFFER_PRICE = new BigDecimal(0);
    private static final BigDecimal UPDATED_OFFER_PRICE = new BigDecimal(1);

    private static final OfferValidity DEFAULT_OFFER_VALIDITY = OfferValidity.OneDay;
    private static final OfferValidity UPDATED_OFFER_VALIDITY = OfferValidity.OneWeek;

    private static final BigDecimal DEFAULT_OFFER_ALLOCATION = new BigDecimal(0);
    private static final BigDecimal UPDATED_OFFER_ALLOCATION = new BigDecimal(1);

    private static final Instant DEFAULT_OFFER_END_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_OFFER_END_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final RiskOfMarketShareLoss DEFAULT_RISK_OF_MARKET_SHARE_LOSS = RiskOfMarketShareLoss.Low;
    private static final RiskOfMarketShareLoss UPDATED_RISK_OF_MARKET_SHARE_LOSS = RiskOfMarketShareLoss.Medium;

    @Autowired
    private FormRepository formRepository;

    @Autowired
    private FormService formService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restFormMockMvc;

    private Form form;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Form createEntity(EntityManager em) {
        Form form = new Form()
            .competitor(DEFAULT_COMPETITOR)
            .province(DEFAULT_PROVINCE)
            .image(DEFAULT_IMAGE)
            .imageContentType(DEFAULT_IMAGE_CONTENT_TYPE)
            .offerDate(DEFAULT_OFFER_DATE)
            .productType(DEFAULT_PRODUCT_TYPE)
            .typeOfOffer(DEFAULT_TYPE_OF_OFFER)
            .offerType(DEFAULT_OFFER_TYPE)
            .offerPurchaseLimitation(DEFAULT_OFFER_PURCHASE_LIMITATION)
            .natureOFOffer(DEFAULT_NATURE_OF_OFFER)
            .offerPrice(DEFAULT_OFFER_PRICE)
            .offerValidity(DEFAULT_OFFER_VALIDITY)
            .offerAllocation(DEFAULT_OFFER_ALLOCATION)
            .offerEndDate(DEFAULT_OFFER_END_DATE)
            .riskOfMarketShareLoss(DEFAULT_RISK_OF_MARKET_SHARE_LOSS);
        return form;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Form createUpdatedEntity(EntityManager em) {
        Form form = new Form()
            .competitor(UPDATED_COMPETITOR)
            .province(UPDATED_PROVINCE)
            .image(UPDATED_IMAGE)
            .imageContentType(UPDATED_IMAGE_CONTENT_TYPE)
            .offerDate(UPDATED_OFFER_DATE)
            .productType(UPDATED_PRODUCT_TYPE)
            .typeOfOffer(UPDATED_TYPE_OF_OFFER)
            .offerType(UPDATED_OFFER_TYPE)
            .offerPurchaseLimitation(UPDATED_OFFER_PURCHASE_LIMITATION)
            .natureOFOffer(UPDATED_NATURE_OF_OFFER)
            .offerPrice(UPDATED_OFFER_PRICE)
            .offerValidity(UPDATED_OFFER_VALIDITY)
            .offerAllocation(UPDATED_OFFER_ALLOCATION)
            .offerEndDate(UPDATED_OFFER_END_DATE)
            .riskOfMarketShareLoss(UPDATED_RISK_OF_MARKET_SHARE_LOSS);
        return form;
    }

    @BeforeEach
    public void initTest() {
        form = createEntity(em);
    }

    @Test
    @Transactional
    public void createForm() throws Exception {
        int databaseSizeBeforeCreate = formRepository.findAll().size();
        // Create the Form
        restFormMockMvc.perform(post("/api/forms")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(form)))
            .andExpect(status().isCreated());

        // Validate the Form in the database
        List<Form> formList = formRepository.findAll();
        assertThat(formList).hasSize(databaseSizeBeforeCreate + 1);
        Form testForm = formList.get(formList.size() - 1);
        assertThat(testForm.getCompetitor()).isEqualTo(DEFAULT_COMPETITOR);
        assertThat(testForm.getProvince()).isEqualTo(DEFAULT_PROVINCE);
        assertThat(testForm.getImage()).isEqualTo(DEFAULT_IMAGE);
        assertThat(testForm.getImageContentType()).isEqualTo(DEFAULT_IMAGE_CONTENT_TYPE);
        assertThat(testForm.getOfferDate()).isEqualTo(DEFAULT_OFFER_DATE);
        assertThat(testForm.getProductType()).isEqualTo(DEFAULT_PRODUCT_TYPE);
        assertThat(testForm.getTypeOfOffer()).isEqualTo(DEFAULT_TYPE_OF_OFFER);
        assertThat(testForm.getOfferType()).isEqualTo(DEFAULT_OFFER_TYPE);
        assertThat(testForm.getOfferPurchaseLimitation()).isEqualTo(DEFAULT_OFFER_PURCHASE_LIMITATION);
        assertThat(testForm.getNatureOFOffer()).isEqualTo(DEFAULT_NATURE_OF_OFFER);
        assertThat(testForm.getOfferPrice()).isEqualTo(DEFAULT_OFFER_PRICE);
        assertThat(testForm.getOfferValidity()).isEqualTo(DEFAULT_OFFER_VALIDITY);
        assertThat(testForm.getOfferAllocation()).isEqualTo(DEFAULT_OFFER_ALLOCATION);
        assertThat(testForm.getOfferEndDate()).isEqualTo(DEFAULT_OFFER_END_DATE);
        assertThat(testForm.getRiskOfMarketShareLoss()).isEqualTo(DEFAULT_RISK_OF_MARKET_SHARE_LOSS);
    }

    @Test
    @Transactional
    public void createFormWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = formRepository.findAll().size();

        // Create the Form with an existing ID
        form.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFormMockMvc.perform(post("/api/forms")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(form)))
            .andExpect(status().isBadRequest());

        // Validate the Form in the database
        List<Form> formList = formRepository.findAll();
        assertThat(formList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCompetitorIsRequired() throws Exception {
        int databaseSizeBeforeTest = formRepository.findAll().size();
        // set the field null
        form.setCompetitor(null);

        // Create the Form, which fails.


        restFormMockMvc.perform(post("/api/forms")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(form)))
            .andExpect(status().isBadRequest());

        List<Form> formList = formRepository.findAll();
        assertThat(formList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkOfferDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = formRepository.findAll().size();
        // set the field null
        form.setOfferDate(null);

        // Create the Form, which fails.


        restFormMockMvc.perform(post("/api/forms")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(form)))
            .andExpect(status().isBadRequest());

        List<Form> formList = formRepository.findAll();
        assertThat(formList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkProductTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = formRepository.findAll().size();
        // set the field null
        form.setProductType(null);

        // Create the Form, which fails.


        restFormMockMvc.perform(post("/api/forms")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(form)))
            .andExpect(status().isBadRequest());

        List<Form> formList = formRepository.findAll();
        assertThat(formList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTypeOfOfferIsRequired() throws Exception {
        int databaseSizeBeforeTest = formRepository.findAll().size();
        // set the field null
        form.setTypeOfOffer(null);

        // Create the Form, which fails.


        restFormMockMvc.perform(post("/api/forms")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(form)))
            .andExpect(status().isBadRequest());

        List<Form> formList = formRepository.findAll();
        assertThat(formList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkOfferTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = formRepository.findAll().size();
        // set the field null
        form.setOfferType(null);

        // Create the Form, which fails.


        restFormMockMvc.perform(post("/api/forms")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(form)))
            .andExpect(status().isBadRequest());

        List<Form> formList = formRepository.findAll();
        assertThat(formList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkOfferPurchaseLimitationIsRequired() throws Exception {
        int databaseSizeBeforeTest = formRepository.findAll().size();
        // set the field null
        form.setOfferPurchaseLimitation(null);

        // Create the Form, which fails.


        restFormMockMvc.perform(post("/api/forms")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(form)))
            .andExpect(status().isBadRequest());

        List<Form> formList = formRepository.findAll();
        assertThat(formList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNatureOFOfferIsRequired() throws Exception {
        int databaseSizeBeforeTest = formRepository.findAll().size();
        // set the field null
        form.setNatureOFOffer(null);

        // Create the Form, which fails.


        restFormMockMvc.perform(post("/api/forms")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(form)))
            .andExpect(status().isBadRequest());

        List<Form> formList = formRepository.findAll();
        assertThat(formList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkOfferPriceIsRequired() throws Exception {
        int databaseSizeBeforeTest = formRepository.findAll().size();
        // set the field null
        form.setOfferPrice(null);

        // Create the Form, which fails.


        restFormMockMvc.perform(post("/api/forms")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(form)))
            .andExpect(status().isBadRequest());

        List<Form> formList = formRepository.findAll();
        assertThat(formList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkOfferValidityIsRequired() throws Exception {
        int databaseSizeBeforeTest = formRepository.findAll().size();
        // set the field null
        form.setOfferValidity(null);

        // Create the Form, which fails.


        restFormMockMvc.perform(post("/api/forms")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(form)))
            .andExpect(status().isBadRequest());

        List<Form> formList = formRepository.findAll();
        assertThat(formList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkOfferAllocationIsRequired() throws Exception {
        int databaseSizeBeforeTest = formRepository.findAll().size();
        // set the field null
        form.setOfferAllocation(null);

        // Create the Form, which fails.


        restFormMockMvc.perform(post("/api/forms")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(form)))
            .andExpect(status().isBadRequest());

        List<Form> formList = formRepository.findAll();
        assertThat(formList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkOfferEndDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = formRepository.findAll().size();
        // set the field null
        form.setOfferEndDate(null);

        // Create the Form, which fails.


        restFormMockMvc.perform(post("/api/forms")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(form)))
            .andExpect(status().isBadRequest());

        List<Form> formList = formRepository.findAll();
        assertThat(formList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkRiskOfMarketShareLossIsRequired() throws Exception {
        int databaseSizeBeforeTest = formRepository.findAll().size();
        // set the field null
        form.setRiskOfMarketShareLoss(null);

        // Create the Form, which fails.


        restFormMockMvc.perform(post("/api/forms")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(form)))
            .andExpect(status().isBadRequest());

        List<Form> formList = formRepository.findAll();
        assertThat(formList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllForms() throws Exception {
        // Initialize the database
        formRepository.saveAndFlush(form);

        // Get all the formList
        restFormMockMvc.perform(get("/api/forms?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(form.getId().intValue())))
            .andExpect(jsonPath("$.[*].competitor").value(hasItem(DEFAULT_COMPETITOR.toString())))
            .andExpect(jsonPath("$.[*].province").value(hasItem(DEFAULT_PROVINCE.toString())))
            .andExpect(jsonPath("$.[*].imageContentType").value(hasItem(DEFAULT_IMAGE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].image").value(hasItem(Base64Utils.encodeToString(DEFAULT_IMAGE))))
            .andExpect(jsonPath("$.[*].offerDate").value(hasItem(DEFAULT_OFFER_DATE.toString())))
            .andExpect(jsonPath("$.[*].productType").value(hasItem(DEFAULT_PRODUCT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].typeOfOffer").value(hasItem(DEFAULT_TYPE_OF_OFFER.toString())))
            .andExpect(jsonPath("$.[*].offerType").value(hasItem(DEFAULT_OFFER_TYPE.toString())))
            .andExpect(jsonPath("$.[*].offerPurchaseLimitation").value(hasItem(DEFAULT_OFFER_PURCHASE_LIMITATION.toString())))
            .andExpect(jsonPath("$.[*].natureOFOffer").value(hasItem(DEFAULT_NATURE_OF_OFFER.toString())))
            .andExpect(jsonPath("$.[*].offerPrice").value(hasItem(DEFAULT_OFFER_PRICE.intValue())))
            .andExpect(jsonPath("$.[*].offerValidity").value(hasItem(DEFAULT_OFFER_VALIDITY.toString())))
            .andExpect(jsonPath("$.[*].offerAllocation").value(hasItem(DEFAULT_OFFER_ALLOCATION.intValue())))
            .andExpect(jsonPath("$.[*].offerEndDate").value(hasItem(DEFAULT_OFFER_END_DATE.toString())))
            .andExpect(jsonPath("$.[*].riskOfMarketShareLoss").value(hasItem(DEFAULT_RISK_OF_MARKET_SHARE_LOSS.toString())));
    }
    
    @Test
    @Transactional
    public void getForm() throws Exception {
        // Initialize the database
        formRepository.saveAndFlush(form);

        // Get the form
        restFormMockMvc.perform(get("/api/forms/{id}", form.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(form.getId().intValue()))
            .andExpect(jsonPath("$.competitor").value(DEFAULT_COMPETITOR.toString()))
            .andExpect(jsonPath("$.province").value(DEFAULT_PROVINCE.toString()))
            .andExpect(jsonPath("$.imageContentType").value(DEFAULT_IMAGE_CONTENT_TYPE))
            .andExpect(jsonPath("$.image").value(Base64Utils.encodeToString(DEFAULT_IMAGE)))
            .andExpect(jsonPath("$.offerDate").value(DEFAULT_OFFER_DATE.toString()))
            .andExpect(jsonPath("$.productType").value(DEFAULT_PRODUCT_TYPE.toString()))
            .andExpect(jsonPath("$.typeOfOffer").value(DEFAULT_TYPE_OF_OFFER.toString()))
            .andExpect(jsonPath("$.offerType").value(DEFAULT_OFFER_TYPE.toString()))
            .andExpect(jsonPath("$.offerPurchaseLimitation").value(DEFAULT_OFFER_PURCHASE_LIMITATION.toString()))
            .andExpect(jsonPath("$.natureOFOffer").value(DEFAULT_NATURE_OF_OFFER.toString()))
            .andExpect(jsonPath("$.offerPrice").value(DEFAULT_OFFER_PRICE.intValue()))
            .andExpect(jsonPath("$.offerValidity").value(DEFAULT_OFFER_VALIDITY.toString()))
            .andExpect(jsonPath("$.offerAllocation").value(DEFAULT_OFFER_ALLOCATION.intValue()))
            .andExpect(jsonPath("$.offerEndDate").value(DEFAULT_OFFER_END_DATE.toString()))
            .andExpect(jsonPath("$.riskOfMarketShareLoss").value(DEFAULT_RISK_OF_MARKET_SHARE_LOSS.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingForm() throws Exception {
        // Get the form
        restFormMockMvc.perform(get("/api/forms/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateForm() throws Exception {
        // Initialize the database
        formService.save(form);

        int databaseSizeBeforeUpdate = formRepository.findAll().size();

        // Update the form
        Form updatedForm = formRepository.findById(form.getId()).get();
        // Disconnect from session so that the updates on updatedForm are not directly saved in db
        em.detach(updatedForm);
        updatedForm
            .competitor(UPDATED_COMPETITOR)
            .province(UPDATED_PROVINCE)
            .image(UPDATED_IMAGE)
            .imageContentType(UPDATED_IMAGE_CONTENT_TYPE)
            .offerDate(UPDATED_OFFER_DATE)
            .productType(UPDATED_PRODUCT_TYPE)
            .typeOfOffer(UPDATED_TYPE_OF_OFFER)
            .offerType(UPDATED_OFFER_TYPE)
            .offerPurchaseLimitation(UPDATED_OFFER_PURCHASE_LIMITATION)
            .natureOFOffer(UPDATED_NATURE_OF_OFFER)
            .offerPrice(UPDATED_OFFER_PRICE)
            .offerValidity(UPDATED_OFFER_VALIDITY)
            .offerAllocation(UPDATED_OFFER_ALLOCATION)
            .offerEndDate(UPDATED_OFFER_END_DATE)
            .riskOfMarketShareLoss(UPDATED_RISK_OF_MARKET_SHARE_LOSS);

        restFormMockMvc.perform(put("/api/forms")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedForm)))
            .andExpect(status().isOk());

        // Validate the Form in the database
        List<Form> formList = formRepository.findAll();
        assertThat(formList).hasSize(databaseSizeBeforeUpdate);
        Form testForm = formList.get(formList.size() - 1);
        assertThat(testForm.getCompetitor()).isEqualTo(UPDATED_COMPETITOR);
        assertThat(testForm.getProvince()).isEqualTo(UPDATED_PROVINCE);
        assertThat(testForm.getImage()).isEqualTo(UPDATED_IMAGE);
        assertThat(testForm.getImageContentType()).isEqualTo(UPDATED_IMAGE_CONTENT_TYPE);
        assertThat(testForm.getOfferDate()).isEqualTo(UPDATED_OFFER_DATE);
        assertThat(testForm.getProductType()).isEqualTo(UPDATED_PRODUCT_TYPE);
        assertThat(testForm.getTypeOfOffer()).isEqualTo(UPDATED_TYPE_OF_OFFER);
        assertThat(testForm.getOfferType()).isEqualTo(UPDATED_OFFER_TYPE);
        assertThat(testForm.getOfferPurchaseLimitation()).isEqualTo(UPDATED_OFFER_PURCHASE_LIMITATION);
        assertThat(testForm.getNatureOFOffer()).isEqualTo(UPDATED_NATURE_OF_OFFER);
        assertThat(testForm.getOfferPrice()).isEqualTo(UPDATED_OFFER_PRICE);
        assertThat(testForm.getOfferValidity()).isEqualTo(UPDATED_OFFER_VALIDITY);
        assertThat(testForm.getOfferAllocation()).isEqualTo(UPDATED_OFFER_ALLOCATION);
        assertThat(testForm.getOfferEndDate()).isEqualTo(UPDATED_OFFER_END_DATE);
        assertThat(testForm.getRiskOfMarketShareLoss()).isEqualTo(UPDATED_RISK_OF_MARKET_SHARE_LOSS);
    }

    @Test
    @Transactional
    public void updateNonExistingForm() throws Exception {
        int databaseSizeBeforeUpdate = formRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFormMockMvc.perform(put("/api/forms")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(form)))
            .andExpect(status().isBadRequest());

        // Validate the Form in the database
        List<Form> formList = formRepository.findAll();
        assertThat(formList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteForm() throws Exception {
        // Initialize the database
        formService.save(form);

        int databaseSizeBeforeDelete = formRepository.findAll().size();

        // Delete the form
        restFormMockMvc.perform(delete("/api/forms/{id}", form.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Form> formList = formRepository.findAll();
        assertThat(formList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
