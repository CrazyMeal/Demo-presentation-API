package fr.crazymeal.democonferenceapi.repository;

import fr.crazymeal.democonferenceapi.model.Person;
import fr.crazymeal.democonferenceapi.model.Speaker;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Crazymeal on 04/11/2017.
 */
public interface SpeakerRepository extends CrudRepository<Speaker, Long> {
}
