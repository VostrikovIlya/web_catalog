package com.semp.repository;

import com.semp.entity.ProductPropertyForRemaining;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductPropertyForRemainingRepository extends JpaRepository<ProductPropertyForRemaining, Long> {
    Optional<ProductPropertyForRemaining> findAllById(Long id);

    List<ProductPropertyForRemaining> findByPropertyValueId(Long id);

    Optional<ProductPropertyForRemaining> findAllByParentId(Long id);
}
