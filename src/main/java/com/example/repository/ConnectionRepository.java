package com.example.repository;

import com.example.entity.Connection;
import org.springframework.data.jpa.repository.JpaRepository;




public interface ConnectionRepository extends JpaRepository<Connection, Integer> {
}
