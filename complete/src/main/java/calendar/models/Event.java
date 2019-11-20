package calendar.models;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "events")
public class Event implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "event_date")
    private LocalDate eventDate;

    @Column(name = "event_description")
    private String eventDescription;

    @Column(name = "category")
    private String category;

    @Column(name = "type")
    private String type;

    @OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL,
            mappedBy = "event")
    private Address address;

    @Column(name = "premium_event")
    private boolean premiumEvent;

    public Event() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public String getCategory() {
        return category;
    }

    public String getType() {
        return type;
    }

    public Address getAddress() {
        return address;
    }

    public boolean isPremiumEvent() {
        return premiumEvent;
    }

    public Event(String name, LocalDate eventDate, String eventDescription, String category, String type, Address address, boolean premiumEvent) {
        this.name = name;
        this.eventDate = eventDate;
        this.eventDescription = eventDescription;
        this.category = category;
        this.type = type;
        this.address = address;
        this.premiumEvent = premiumEvent;
    }
}
