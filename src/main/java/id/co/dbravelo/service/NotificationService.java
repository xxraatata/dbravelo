package id.co.dbravelo.service;

import id.co.dbravelo.dto.NotificationRequest;
import id.co.dbravelo.dto.NotificationResponse;

import java.util.List;

public interface NotificationService {
    void createNotification(NotificationRequest request);
    List<NotificationResponse> getNotificationsByUsername(String username);
    void markAsRead(Integer notificationId);
}
