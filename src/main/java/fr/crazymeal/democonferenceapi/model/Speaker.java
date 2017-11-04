package fr.crazymeal.democonferenceapi.model;

import javax.persistence.Entity;

/**
 * Created by Crazymeal on 04/11/2017.
 */
@Entity
public class Speaker extends Person {

    public void animate(Presentation presentation) {
        presentation.setSpeaker(this);
    }


}
