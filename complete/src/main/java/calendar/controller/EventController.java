package calendar.controller;

import calendar.models.Candidate;
import calendar.models.Event;
import calendar.models.Filter;
import calendar.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path="/calendar")
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping(value = "/events", consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Event> findEvents(@RequestBody Filter filter) {
        return this.eventService.findEvents(filter);
    }

    @PostMapping("/subscribe")
    public void subscribe (Long candidateId, Long eventId) throws Exception {
        this.eventService.subscribe(candidateId, eventId);
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping(value = "/create-event", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createEvent(@RequestBody Event event) {
        this.eventService.createEvent(event);
    }
}
