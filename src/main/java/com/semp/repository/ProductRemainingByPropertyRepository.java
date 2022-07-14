package com.semp.repository;

import com.semp.entity.ProductRemainingByProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ProductRemainingByPropertyRepository extends JpaRepository<ProductRemainingByProperty, Long> {
    List<ProductRemainingByProperty> findAllByProductIdIn(Collection<Long> ids);
}
