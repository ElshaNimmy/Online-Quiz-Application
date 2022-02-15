package com.example.demoregister.repository;

import com.example.demoregister.entity.Admin;
import com.example.demoregister.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

public interface AdminRepository extends JpaRepository<Admin,Long> {
    @Query("Select admin from Admin admin where admin.username=:username")
    Admin findAdminByUsername(String username);

}
