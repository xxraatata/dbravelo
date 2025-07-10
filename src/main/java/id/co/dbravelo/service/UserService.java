package id.co.dbravelo.service;

import id.co.dbravelo.model.User;
import id.co.dbravelo.dto.RegisterRequest;
import id.co.dbravelo.dto.LoginRequest;
import id.co.dbravelo.dto.UserProfileResponse;


import java.util.Optional;

public interface UserService {
    User register(RegisterRequest request);
    Optional<User> login(LoginRequest request);
    boolean isUsernameTaken(String username);
    User findByUsername(String username);
    Optional<UserProfileResponse> getProfileByUsername(String username);
    Optional<User> updateUserProfile(String username, User updateData);

}
