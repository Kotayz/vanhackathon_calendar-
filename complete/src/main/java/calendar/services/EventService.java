package calendar.services;

import calendar.models.*;
import calendar.repository.CandidateRepository;
import calendar.repository.EventRepository;
import calendar.repository.EventSubscribeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private EventSubscribeRepository eventSubscribeRepository;

    public List<Event> findEvents(Filter filter) {
        Specification<Event> specification = new EventSpecification(filter);
        return this.eventRepository.findAll(specification);
    }

    public void subscribe(Long candidateId, Long eventId) throws Exception {
        Optional<Event> event = findEventById(eventId);
        Optional<Candidate> candidate = findCandidateById(candidateId);

        Optional<Boolean> optionalEvent = event.map(Event::isPremiumEvent);
        Optional<Boolean> optionalCandidate = candidate.map(Candidate::isPremiumCandidate);

        boolean isPremiumEvent = optionalEvent.orElseThrow(() -> new Exception("Is Premium Event Tag, Not Defined"));
        boolean isPremiumCandidate = optionalCandidate.orElseThrow(() -> new Exception("Is Premium Event Tag, Not Defined"));

        if (isPremiumEvent) {
            if (isPremiumCandidate) {
                saveSubscription(candidateId, eventId);
            }
            throw new Exception("This Candidate Cannot Apply To Premium Events");
        } else {
            saveSubscription(candidateId, eventId);
        }
    }

    private Optional<Candidate> findCandidateById (Long id) {
        return this.candidateRepository.findById(id);
    }

    private Optional<Event> findEventById (Long id) {
        return this.eventRepository.findById(id);
    }

    private void saveSubscription(Long candidateId, Long eventId) {
        EventSubscribe eventSubscribe = new EventSubscribe(candidateId, eventId, LocalDate.now());
        this.eventSubscribeRepository.save(eventSubscribe);
    }

    public void createEvent(Event event) {
        this.eventRepository.save(event);
    }
}
