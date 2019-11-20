package calendar.models;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "event_subscribe")
public class EventSubscribe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "candidate_id")
    private Long candidateId;

    @Column(name = "event_id")
    private Long eventId;

    @Column(name = "subscribe_date")
    private LocalDate subscribeDate;

    public EventSubscribe(Long candidateId, Long eventId, LocalDate subscribeDate) {
        this.candidateId = candidateId;
        this.eventId = eventId;
        this.subscribeDate = subscribeDate;
    }
}
