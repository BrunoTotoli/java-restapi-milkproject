package com.bruno.springmyproject.repository;

import com.bruno.springmyproject.entity.Milk;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MilkRepository extends JpaRepository<Milk, Long> {
}
