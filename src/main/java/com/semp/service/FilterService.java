package com.semp.service;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.semp.entity.Product;
import com.semp.entity.Property;
import com.semp.entity.PropertyValue;
import com.semp.pojo.Filter;
import com.semp.repository.ProductRemainingByPropertyRepository;
import com.semp.repository.ProductRepository;
import com.semp.repository.PropertyValueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FilterService {
    private final PropertyValueRepository propertyValueRepo;
    private final ProductRepository productRepo;

    public Filter getFilter(String slugCategory) {
        List<Product> products = productRepo.findByCategorySlug(slugCategory);
        List<PropertyValue> propertyValues = propertyValueRepo.findAllByProductListInOrderByProperty(products);
        List<Property> properties = propertyValues.stream().map(PropertyValue::getProperty).distinct().collect(Collectors.toList());
        return new Filter(slugCategory, properties);
    }
}
