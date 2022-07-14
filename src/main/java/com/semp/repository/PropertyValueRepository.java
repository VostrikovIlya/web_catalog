package com.semp.repository;

import com.semp.entity.Product;
import com.semp.entity.PropertyValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface PropertyValueRepository extends JpaRepository<PropertyValue, Long> {

    PropertyValue findBySlug(String slug);

    List<PropertyValue> findAllByProductListInOrderByProperty(Collection<Product> products);
}
