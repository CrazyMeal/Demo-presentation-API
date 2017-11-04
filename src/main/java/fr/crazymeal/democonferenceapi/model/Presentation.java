package fr.crazymeal.democonferenceapi.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Crazymeal on 04/11/2017.
 */
@Entity
public class Presentation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    private String title;
    private String description;

    @OneToOne(cascade = CascadeType.ALL)
    private Speaker speaker;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Attendee> attendees = new ArrayList<>();

    public void addAttendee(Attendee attendee) {
        this.attendees.add(attendee);
    }

    public Long getId() {
        return id;
    }

    public List<Attendee> getAttendees() {
        return attendees;
    }

    public void setAttendees(List<Attendee> attendees) {
        this.attendees = attendees;
    }

    public Speaker getSpeaker() {
        return this.speaker;
    }

    public void setSpeaker(Speaker speaker) {
        this.speaker = speaker;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
