package id.co.dbravelo.service;

public interface OtpService {
    String generateOtp(String username);
    boolean verifyOtp(String username, String inputOtp);
    void resetPassword(String username, String newPassword);
}


