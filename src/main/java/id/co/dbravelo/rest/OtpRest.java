package id.co.dbravelo.rest;

import id.co.dbravelo.service.OtpService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/otp")
@CrossOrigin(origins = "*")
public class OtpRest {

    @Autowired
    private OtpService otpService;

    @PostMapping("/generate")
    public OtpResponse generateOtp(@RequestBody UsernameRequest req) {
        String otp = otpService.generateOtp(req.getUsername());
        return new OtpResponse("OTP generated", otp);
    }

    // 2. Verifikasi OTP
    @PostMapping("/verify")
    public MessageResponse verifyOtp(@RequestBody OtpVerificationRequest req) {
        otpService.verifyOtp(req.getUsername(), req.getOtp());
        return new MessageResponse("OTP verified");
    }

    @PostMapping("/reset")
    public MessageResponse resetPassword(@RequestBody ResetPasswordRequest req) {
        otpService.resetPassword(req.getUsername(), req.getNewPassword());
        return new MessageResponse("Password updated");
    }

    @Data
    public static class UsernameRequest {
        private String username;
    }

    @Data
    public static class OtpVerificationRequest {
        private String username;
        private String otp;
    }

    @Data
    public static class ResetPasswordRequest {
        private String username;
        private String newPassword;
    }

    @Data
    public static class MessageResponse {
        private String message;

        public MessageResponse(String message) {
            this.message = message;
        }
    }

    @Data
    public static class OtpResponse {
        private String message;
        private String otp;

        public OtpResponse(String message, String otp) {
            this.message = message;
            this.otp = otp;
        }
    }
}
