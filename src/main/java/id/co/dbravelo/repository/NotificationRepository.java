package id.co.dbravelo.repository;

import id.co.dbravelo.model.Notification;
import id.co.dbravelo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {
    List<Notification> findByUserOrderByCreatedAtDesc(User user);
}
