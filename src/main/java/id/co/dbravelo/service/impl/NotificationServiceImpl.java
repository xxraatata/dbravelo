package id.co.dbravelo.service.impl;

import id.co.dbravelo.dto.NotificationRequest;
import id.co.dbravelo.dto.NotificationResponse;
import id.co.dbravelo.model.Notification;
import id.co.dbravelo.model.User;
import id.co.dbravelo.repository.NotificationRepository;
import id.co.dbravelo.repository.UserRepository;
import id.co.dbravelo.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationRepository notificationRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Override
    public void createNotification(NotificationRequest request) {
        User user = userRepo.findByUsername(request.getUsername());
        if (user == null) throw new IllegalArgumentException("User not found");

        Notification notif = new Notification();
        notif.setUser(user);
        notif.setTitle(request.getTitle());
        notif.setMessage(request.getMessage());
        notif.setType(request.getType());

        Notification saved = notificationRepo.save(notif);

        // ✅ Kirim ke WebSocket client
        NotificationResponse response = new NotificationResponse(
                saved.getId(),
                saved.getTitle(),
                saved.getMessage(),
                saved.getType(),
                saved.isRead(),
                saved.getCreatedAt()
        );

        messagingTemplate.convertAndSend("/topic/notifications/" + request.getUsername(), response);
    }

    @Override
    public List<NotificationResponse> getNotificationsByUsername(String username) {
        User user = userRepo.findByUsername(username);
        if (user == null) throw new IllegalArgumentException("User not found");

        return notificationRepo.findByUserOrderByCreatedAtDesc(user)
                .stream()
                .map(n -> new NotificationResponse(
                        n.getId(),
                        n.getTitle(),
                        n.getMessage(),
                        n.getType(),
                        n.isRead(),
                        n.getCreatedAt()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public void markAsRead(Integer notificationId) {
        Notification notif = notificationRepo.findById(notificationId).orElse(null);
        if (notif != null && !notif.isRead()) {
            notif.setRead(true);
            notificationRepo.save(notif);
        }
    }
}
