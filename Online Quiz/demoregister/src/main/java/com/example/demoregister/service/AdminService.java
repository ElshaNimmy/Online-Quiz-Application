package com.example.demoregister.service;

import com.example.demoregister.entity.Admin;
import com.example.demoregister.entity.User;
import com.example.demoregister.repository.AdminRepository;
import com.example.demoregister.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    public AdminRepository adminRepository;

    public Admin saveAdmin(Admin admin){return adminRepository.save(admin);}

    public Admin identifyAdminByAdminName(String adminname) {
        return adminRepository.findAdminByAdminName(adminname);
    }


}
