package com.example.easydonatemaster.controller;

import com.example.easydonatemaster.JWT.JwtUtils;
import com.example.easydonatemaster.Request.*;
import com.example.easydonatemaster.Request.Response.JwtResponse;
import com.example.easydonatemaster.Request.Response.MessageResponse;
import com.example.easydonatemaster.entites.ERole;
import com.example.easydonatemaster.entites.Role;
import com.example.easydonatemaster.entites.User;
import com.example.easydonatemaster.repositories.IRoleRepository;
import com.example.easydonatemaster.repositories.IUserRepository;
import com.example.easydonatemaster.services.IUserServices;
import com.example.easydonatemaster.services.MailerService;
import com.example.easydonatemaster.services.RandomString;
import com.example.easydonatemaster.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
public class AuthController {

  final AuthenticationManager authenticationManager;
    @Autowired

    IUserRepository userRepository;
    @Autowired
    IRoleRepository roleRepository;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    RandomString randomString;
    @Autowired
    MailerService mailerService;
    @Autowired
    IUserServices userServices;


    public AuthController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser( @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        User u = userRepository.findByUsername(loginRequest.getUsername()).get();

            if (u.getStatus()) {
                SecurityContextHolder.getContext().setAuthentication(authentication);
                String jwt = jwtUtils.generateJwtToken(authentication);


                UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
                List<String> roles = userDetails.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList());

                return ResponseEntity.ok(new JwtResponse(jwt,
                        userDetails.getId(),
                        userDetails.getUsername(),
                        userDetails.getEmail(),
                        roles));
            } else return new ResponseEntity("Account Non verified ! Please Proceed to Verification Or your Account will be deleted Automatically", HttpStatus.BAD_REQUEST);

    }
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }
        // Create new user's account
        User user = new User();
     user.setUsername(signUpRequest.getUsername());
     user.setEmail(signUpRequest.getEmail());
     user.setFirstName(signUpRequest.getFirstName());
     user.setLastName(signUpRequest.getLastName());
     user.setCinUser(signUpRequest.getCinUser());
     user.setPhoneNumber(signUpRequest.getPhoneNumber());
     user.setPassword(encoder.encode(signUpRequest.getPassword()));
        LocalDateTime d = LocalDateTime.now();
        user.setCreatedAt(d);
        String code = randomString.randomGeneratedString(8);
     user.setCode(code);
       mailerService.sendEmail(user.getEmail(),"Account Creation"," Hello "+user.getFirstName()+" "+user.getLastName()+"\n Your account is almost created, please Login and write this code in the right field to complete your inscription !\n Your code is :"+ code);
    user.setStatus(false);
       /* User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));*/

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "staff":
                        Role staffRole = roleRepository.findByName(ERole.STAFF)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(staffRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }
        user.setRoles(roles);
        userRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }


    @PostMapping("/verification")
    String verifyUser(@RequestBody VerificationRequest verificationRequest){
        return userServices.Verification(verificationRequest.getEmail(),verificationRequest.getCode());
    }
    @PostMapping("/forgotPassword")
    String forgotPassword(@RequestBody ForgotPassword forgotPassword) {
        return userServices.forgotPassword(forgotPassword.getEmail());
    }
    @PostMapping("/resetPassword")
    String resetPassword(@RequestBody ResetPassword resetPassword) {
        return userServices.resetPassword(resetPassword.getVerificationCode(),resetPassword.getNewPassword());
    }

    @PostMapping("/signout")
    public ResponseEntity<?> signOut(HttpServletRequest request, HttpServletResponse response) {

        SecurityContextHolder.getContext().setAuthentication(null);
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                cookie.setValue("");
                cookie.setPath("/");
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
        }
        return ResponseEntity.ok(new MessageResponse("Logout successful!"));
    }

   /* @PutMapping("/users/{userId}/role")
    public ResponseEntity<?> changeUserRole(@PathVariable Long id,
                                            @RequestParam("role") String roleName,
                                            Authentication authentication) {
        // Check if the authenticated user is an admin
        if (!isAdmin(authentication)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(new MessageResponse("Error: Only admins can change user roles!"));
        }


        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        Role role = roleRepository.findByName(ERole.valueOf(roleName.toUpperCase()))
                .orElseThrow(() -> new ResourceNotFoundException("Role not found: " + roleName));


        user.setRoles(Collections.singleton(role));
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User role updated successfully."));
    }

    // Helper method to check if the authenticated user is an admin
    private boolean isAdmin(Authentication authentication) {
        return authentication.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"));
    }*/



}
