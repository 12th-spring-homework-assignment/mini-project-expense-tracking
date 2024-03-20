package org.example.miniprojectexpensetracking.service.serviceimpl;

import lombok.AllArgsConstructor;
import org.example.miniprojectexpensetracking.repository.AppUserRepository;
import org.example.miniprojectexpensetracking.service.AppUserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AppUserServiceImpl implements AppUserService {

    private final AppUserRepository appUserRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return appUserRepository.findByEmail(email);
    }
}
