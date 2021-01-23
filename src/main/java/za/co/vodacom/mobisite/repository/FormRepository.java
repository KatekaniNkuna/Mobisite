package za.co.vodacom.mobisite.repository;

import za.co.vodacom.mobisite.domain.Form;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Form entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FormRepository extends JpaRepository<Form, Long> {
}
