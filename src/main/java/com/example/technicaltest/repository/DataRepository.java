package com.example.technicaltest.repository;

import com.example.technicaltest.model.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataRepository extends JpaRepository<Data,Long> {

}
