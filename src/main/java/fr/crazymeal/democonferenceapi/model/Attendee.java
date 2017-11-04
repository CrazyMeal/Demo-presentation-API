package fr.crazymeal.democonferenceapi.model;

import javax.persistence.Entity;

/**
 * Created by Crazymeal on 04/11/2017.
 */
@Entity
public class Attendee extends Person {

    public void registerTo(Presentation presentation) {
        presentation.addAttendee(this);
    }
}
