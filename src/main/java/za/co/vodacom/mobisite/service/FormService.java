package za.co.vodacom.mobisite.service;

import za.co.vodacom.mobisite.domain.Form;
import za.co.vodacom.mobisite.repository.FormRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Form}.
 */
@Service
@Transactional
public class FormService {

    private final Logger log = LoggerFactory.getLogger(FormService.class);

    private final FormRepository formRepository;

    public FormService(FormRepository formRepository) {
        this.formRepository = formRepository;
    }

    /**
     * Save a form.
     *
     * @param form the entity to save.
     * @return the persisted entity.
     */
    public Form save(Form form) {
        log.debug("Request to save Form : {}", form);
        return formRepository.save(form);
    }

    /**
     * Get all the forms.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<Form> findAll() {
        log.debug("Request to get all Forms");
        return formRepository.findAll();
    }


    /**
     * Get one form by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Form> findOne(Long id) {
        log.debug("Request to get Form : {}", id);
        return formRepository.findById(id);
    }

    /**
     * Delete the form by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Form : {}", id);
        formRepository.deleteById(id);
    }
}
