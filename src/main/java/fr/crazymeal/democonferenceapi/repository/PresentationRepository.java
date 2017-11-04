package fr.crazymeal.democonferenceapi.repository;

import fr.crazymeal.democonferenceapi.model.Presentation;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Crazymeal on 04/11/2017.
 */
public interface PresentationRepository extends CrudRepository<Presentation, Long> {
}
