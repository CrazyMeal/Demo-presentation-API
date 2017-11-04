package fr.crazymeal.democonferenceapi.controller;

import fr.crazymeal.democonferenceapi.model.Attendee;
import fr.crazymeal.democonferenceapi.model.Presentation;
import fr.crazymeal.democonferenceapi.model.Speaker;
import fr.crazymeal.democonferenceapi.repository.SpeakerRepository;
import fr.crazymeal.democonferenceapi.repository.PresentationRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Crazymeal on 04/11/2017.
 */

@RestController
@RequestMapping("/presentation")
public class PresentationController {

    @Autowired
    private PresentationRepository presentationRepository;

    @Autowired
    private SpeakerRepository speakerRepository;

    @GetMapping("/all")
    @ApiOperation(value = "Afficher toutes les présentations")
    public Iterable<Presentation> allPresentation() {
        return presentationRepository.findAll();
    }

    @GetMapping("/{presentationId}")
    @ApiOperation(value = "Afficher une présentation")
    public Presentation displayPresentation(@PathVariable Long presentationId) {
        return presentationRepository.findOne(presentationId);
    }


    @PostMapping("/create")
    @ApiOperation(value = "Créer une présentation")
    public ResponseEntity<?> createPresentation(@RequestBody Presentation presentation) {
        Presentation p = presentationRepository.save(presentation);
        return new ResponseEntity<Long>(p.getId(), HttpStatus.CREATED);
    }

    @PostMapping("/animator/{presentationId}")
    @ApiOperation(value = "Affecter un speaker sur une présentation")
    @ApiResponses({
            @ApiResponse(code = 406, message = "La présentation indiquée n'existe pas")
    })
    public ResponseEntity<?> animatePresentation(@PathVariable Long presentationId, @RequestBody Speaker speaker) {
        Speaker s = speakerRepository.findOne(speaker.getId());

        if (s == null) {
            s = new Speaker();
            s.setFirstName(speaker.getFirstName());
            s.setLastName(speaker.getLastName());
        }

        Presentation p = presentationRepository.findOne(presentationId);
        if (p == null) {
            return new ResponseEntity<String>("La présentation indiquée n'existe pas", HttpStatus.NOT_ACCEPTABLE);
        }

        s.animate(p);
        presentationRepository.save(p);

        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @PostMapping("/attend/{presentationId}")
    @ApiOperation(value = "Enregistrer un participant à la présentation")
    @ApiResponses({
            @ApiResponse(code = 406, message = "La présentation fournie n'existe pas")
    })
    public ResponseEntity<?> attendToPresentation(@PathVariable Long presentationId, @RequestBody Attendee attendee) {
        Presentation p = presentationRepository.findOne(presentationId);
        if (p == null) {
            return new ResponseEntity<String>("Presentation doesn't exists", HttpStatus.NOT_ACCEPTABLE);
        }

        attendee.registerTo(p);
        presentationRepository.save(p);

        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @DeleteMapping("/{presentationId}")
    @ApiOperation(value = "Supprime une présentation")
    @ApiResponses({
            @ApiResponse(code = 200, message = "La présentation a été supprimée"),
            @ApiResponse(code = 406, message = "La présentation à supprimer n'existe pas")
    })
    public ResponseEntity<?> deletePresentation(@PathVariable Long presentationId) {
        Presentation p = presentationRepository.findOne(presentationId);
        if (p == null) {
            return new ResponseEntity<String>("Presentation doesn't exists", HttpStatus.NOT_ACCEPTABLE);
        }
        presentationRepository.delete(presentationId);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
