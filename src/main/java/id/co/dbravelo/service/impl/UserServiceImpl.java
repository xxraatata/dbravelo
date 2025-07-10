package id.co.dbravelo.service.impl;

import id.co.dbravelo.dto.UserProfileResponse;
import id.co.dbravelo.model.User;
import id.co.dbravelo.repository.UserRepository;
import id.co.dbravelo.service.UserService;
import id.co.dbravelo.dto.RegisterRequest;
import id.co.dbravelo.dto.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User register(RegisterRequest req) {
        if (req.getUsername() == null || req.getUsername().isEmpty() ||
                req.getFirstName() == null || req.getFirstName().isEmpty() ||
                req.getLastName() == null || req.getLastName().isEmpty() ||
                req.getPhoneNumber() == null || req.getPhoneNumber().isEmpty() ||
                req.getPassword() == null || req.getPassword().isEmpty() ||
                req.getConfirmPassword() == null || req.getConfirmPassword().isEmpty()) {
            throw new IllegalArgumentException("All fields must be filled.");
        }

        // Validasi nomor HP (angka dan panjang minimum)
        if (!req.getPhoneNumber().matches("^\\d{10,15}$")) {
            throw new IllegalArgumentException("Phone number must be 10 to 15 digits.");
        }

        // Password harus sama
        if (!req.getPassword().equals(req.getConfirmPassword())) {
            throw new IllegalArgumentException("Password and Confirm Password do not match.");
        }

        // Username harus unik
        if (userRepository.existsByUsername(req.getUsername())) {
            throw new IllegalStateException("Username is already taken.");
        }

        // Simpan ke DB (yang disimpan hanya confirmPassword)
        User user = new User();
        user.setUsername(req.getUsername());
        user.setFirstName(req.getFirstName());
        user.setLastName(req.getLastName());
        user.setPhoneNumber(req.getPhoneNumber());
        user.setPasswordHash(req.getConfirmPassword()); // sesuai permintaan

        return userRepository.save(user);
    }

    @Override
    public Optional<User> login(LoginRequest req) {
        // Validasi: field tidak boleh kosong
        if (req.getUsername() == null || req.getUsername().isEmpty() ||
                req.getPassword() == null || req.getPassword().isEmpty() ||
                req.getConfirmPassword() == null || req.getConfirmPassword().isEmpty()) {
            throw new IllegalArgumentException("All fields must be filled.");
        }

        // Validasi: password dan confirm password harus sama
        if (!req.getPassword().equals(req.getConfirmPassword())) {
            throw new IllegalArgumentException("Password and Confirm Password do not match.");
        }

        // Cari user berdasarkan username
        User user = userRepository.findByUsername(req.getUsername());
        if (user != null && user.getPasswordHash().equals(req.getConfirmPassword())) {
            return Optional.of(user);
        }

        return Optional.empty();
    }

    @Override
    public boolean isUsernameTaken(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Optional<UserProfileResponse> getProfileByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            UserProfileResponse response = new UserProfileResponse(
                    user.getFullName(),
                    user.getEmail(),
                    user.getPhoneNumber(),
                    user.getAddress()
            );
            return Optional.of(response);
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> updateUserProfile(String username, User updateData) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            user.setEmail(updateData.getEmail());
            user.setAddress(updateData.getAddress());
            user.setPhoneNumber(updateData.getPhoneNumber());

            User updatedUser = userRepository.save(user);
            return Optional.of(updatedUser);
        }
        return Optional.empty();
    }
}
