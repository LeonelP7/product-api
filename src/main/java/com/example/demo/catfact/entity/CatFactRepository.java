package com.example.demo.catfact.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatFactRepository extends JpaRepository<CatFactEntity, Integer> {
}
