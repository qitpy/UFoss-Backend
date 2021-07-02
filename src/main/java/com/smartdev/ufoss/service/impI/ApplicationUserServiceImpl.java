package com.smartdev.ufoss.service.impI;

import com.smartdev.ufoss.model.ApplicationUser;
import com.smartdev.ufoss.entity.ConfirmationToken;
import com.smartdev.ufoss.entity.UserEntity;
import com.smartdev.ufoss.repository.ApplicationUserRepository;
import com.smartdev.ufoss.repository.ConfirmationTokenRepository;
import com.smartdev.ufoss.service.ApplicationUserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
public class ApplicationUserServiceImpl implements UserDetailsService, ApplicationUserService {

    private ApplicationUserRepository applicationUserRepository;

    private PasswordEncoder passwordEncoder;

    private ConfirmationTokenServiceImpl confirmationTokenService;

    private ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    public ApplicationUserServiceImpl(ApplicationUserRepository applicationUserRepository, PasswordEncoder passwordEncoder, ConfirmationTokenServiceImpl confirmationTokenService, ConfirmationTokenRepository confirmationTokenRepository) {
        this.applicationUserRepository = applicationUserRepository;
        this.passwordEncoder = passwordEncoder;
        this.confirmationTokenService = confirmationTokenService;
        this.confirmationTokenRepository = confirmationTokenRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = applicationUserRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("can't find username: %s" + username));

        Set<SimpleGrantedAuthority> authorities = (Set<SimpleGrantedAuthority>) userEntity.getAuthorities();

        ApplicationUser applicationUser = new ApplicationUser(
                userEntity.getUsername(),
                userEntity.getPassword(),
                userEntity.getEmail(),
                authorities,
                userEntity.getIsEnabled()
        );
        return applicationUser;
    }

    @Transactional
    @Override
    public String signUpUser(UserEntity userEntity) {
        boolean usernameExists = applicationUserRepository.findByUsername(userEntity.getUsername()).isPresent();
        boolean emailExists = applicationUserRepository.findByEmail(userEntity.getEmail()).isPresent();

        if (usernameExists || emailExists) {
            UserEntity userFinding = usernameExists ? applicationUserRepository
                    .findByUsername(userEntity.getUsername()).get()
                    : applicationUserRepository.findByEmail(userEntity.getEmail()).get();
            if (userFinding.getIsEnabled())
                throw new IllegalStateException("email or username already taken!");
            else {
                ConfirmationToken confirmationToken = confirmationTokenRepository
                        .findByEmail(userFinding.getEmail())
                        .get();
                LocalDateTime now = LocalDateTime.now();
                LocalDateTime createAtTime = confirmationToken.getCreateAt();

                if (now.isAfter(createAtTime.plusMinutes(1))) {
                    confirmationTokenRepository.delete(confirmationToken);
                    userEntity = userFinding;
                } else throw new IllegalStateException(
                        "Please wait for 1 minute before get another verify again!"
                );
            }
        } else {
            String encodedPassword = passwordEncoder.encode(userEntity.getPassword());
            userEntity.setPassword(encodedPassword);
            applicationUserRepository.save(userEntity);
        }

        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(1),
                userEntity
        );
        confirmationTokenService.saveConfirmationToken(confirmationToken);

        return token;
    }

    @Override
    public int enableApplicationUser(String email) {
        return applicationUserRepository.enableUser(email);
    }
}
