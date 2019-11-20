package calendar.models;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Predicate;

public class EventSpecification implements Specification<Event> {

    private Filter filter;

    public EventSpecification(Filter filter) {
        super();
        this.filter = filter;
    }

    public Predicate toPredicate(Root<Event> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

        Predicate predicate = criteriaBuilder.disjunction();

        if (filter.getStartDate() != null && filter.getEndDate() != null) {
            predicate.getExpressions()
                    .add(criteriaBuilder.between(root.get("eventDate"), filter.getStartDate(), filter.getEndDate()));
        }

        return predicate;
    }
}
