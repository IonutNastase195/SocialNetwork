package com.example.repository;

import com.example.entity.Connection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ConnectionRepository extends JpaRepository<Connection, Integer> {
    @Query("select t from Connection t where t.post = :post")
    List<Connection> findByConnection(@Param("connection") String connection);
}
