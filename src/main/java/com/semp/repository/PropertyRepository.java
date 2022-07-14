package com.semp.repository;

import com.semp.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyRepository  extends JpaRepository<Property, Long> {
    Property findPropertyBySlug(String slug);
    Property findBySlug(String slug);
}
