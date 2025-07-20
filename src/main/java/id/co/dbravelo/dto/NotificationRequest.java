package id.co.dbravelo.dto;

public class NotificationRequest {
    private String username;
    private String title;
    private String message;
    private String type;

    public NotificationRequest(String username, String title, String message, String type) {
        this.username = username;
        this.title = title;
        this.message = message;
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
