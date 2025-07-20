package id.co.dbravelo.rest;

import id.co.dbravelo.dto.NotificationRequest;
import id.co.dbravelo.dto.NotificationResponse;
import id.co.dbravelo.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/notifications")
public class NotificationRest {

    @Autowired
    private NotificationService service;

    @PostMapping
    public String sendNotification(@RequestBody NotificationRequest request) {
        service.createNotification(request);
        return "Notification sent";
    }

    @GetMapping("/{username}")
    public List<NotificationResponse> getUserNotifications(@PathVariable String username) {
        return service.getNotificationsByUsername(username);
    }
}
