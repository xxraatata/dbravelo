package id.co.dbravelo.service.impl;

import id.co.dbravelo.model.OtpCode;
import id.co.dbravelo.model.User;
import id.co.dbravelo.repository.OtpCodeRepository;
import id.co.dbravelo.repository.UserRepository;
import id.co.dbravelo.service.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
public class OtpServiceImpl implements OtpService {

    @Autowired
    private OtpCodeRepository otpRepo;

    @Autowired
    private UserRepository userRepo;

    @Override
    public String generateOtp(String username) {
        User user = userRepo.findByUsername(username);
        if (user == null) throw new RuntimeException("Username not found");

        String otp = String.format("%04d", new Random().nextInt(10000));

        OtpCode code = new OtpCode();
        code.setUsername(username);
        code.setCode(otp);
        otpRepo.save(code);

        return otp;
    }

    @Override
    public boolean verifyOtp(String username, String inputOtp) {
        Optional<OtpCode> latestOtpOpt = otpRepo.findTopByUsernameOrderByCreatedAtDesc(username);
        if (latestOtpOpt.isEmpty()) {
            throw new RuntimeException("No OTP found for this user");
        }

        OtpCode latestOtp = latestOtpOpt.get();

        // Cek apakah OTP sudah expired
        if (latestOtp.getCreatedAt().plusMinutes(1).isBefore(LocalDateTime.now())) {
            throw new RuntimeException("OTP expired. Please request a new one.");
        }

        // Cek apakah OTP cocok
        if (!latestOtp.getCode().equals(inputOtp)) {
            throw new RuntimeException("Invalid OTP code.");
        }

        return true;
    }



    @Override
    public void resetPassword(String username, String newPassword) {
        User user = userRepo.findByUsername(username);
        if (user == null) throw new RuntimeException("User not found");

        user.setPasswordHash(newPassword);
        userRepo.save(user);
    }
}
