package com.example.demoregister.service;

import com.example.demoregister.entity.Admin;
import com.example.demoregister.entity.MyUserDetails;
import com.example.demoregister.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserService userService;
    @Autowired
    private  AdminService adminService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userService.identifyUserByUsername(username);
        if(Objects.isNull(user)){
            Admin admin= adminService.identifyAdminByUsername(username);
            return new MyUserDetails(admin.getUsername(), admin.getPassword(),"ROLE_ADMIN");
        }
        return new MyUserDetails(user.getUsername(), user.getPassword(), "ROLE_USER");
    }
}
