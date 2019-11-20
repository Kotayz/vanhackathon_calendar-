package calendar.repository;

import calendar.models.EventSubscribe;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface EventSubscribeRepository extends CrudRepository<EventSubscribe, Long>, JpaSpecificationExecutor<EventSubscribe> {
}
