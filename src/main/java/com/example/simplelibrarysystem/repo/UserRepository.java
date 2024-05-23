package com.example.simplelibrarysystem.repo;

import com.example.simplelibrarysystem.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users,Long> {
}
