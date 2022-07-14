package com.semp.service;

import com.semp.entity.*;
import com.semp.repository.ProductPropertyForRemainingRepository;
import com.semp.repository.ProductRemainingByPropertyRepository;
import com.semp.repository.ProductRepository;
import com.semp.pojo.Remaining;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class RemainingService {

    private final ProductPropertyForRemainingRepository productPropertyForRemainingRepo;
    private final ProductRemainingByPropertyRepository productRemainingByPropertyRepo;
    private final ProductRepository productRepo;

    public List<ProductRemainingByProperty> getRemainingByCategory(String slugCategory) {
        List<Product> products = productRepo.findByCategorySlug(slugCategory);
        List<Long> ids = products.stream().map(Product::getId).collect(Collectors.toList());
        return productRemainingByPropertyRepo.findAllByProductIdIn(ids);
    }

    public List<ProductPropertyForRemaining> getPropertyValueByRemaining(ProductRemainingByProperty remaining) {
        List<ProductPropertyForRemaining> listChild = new ArrayList<>();
        listChild.add(remaining.getLastProperty());
        Long parentId = remaining.getLastProperty().getParentId();
        while (parentId != null) {
            Optional<ProductPropertyForRemaining> parent = productPropertyForRemainingRepo.findAllById(parentId);
            if (parent.isPresent()) {
                parentId = parent.get().getParentId();
                listChild.add(0, parent.get());
            } else {
                break;
            }
        }

        return listChild;
    }

    public List<Remaining> getRemainingAndPropertyByCategory(String slug) {
        List<ProductRemainingByProperty> remaining = getRemainingByCategory(slug);
        List<Remaining> res = new ArrayList<>();
        for (ProductRemainingByProperty rem : remaining) {
            List<ProductPropertyForRemaining> property = getPropertyValueByRemaining(rem);
            if (!property.isEmpty()) {
                Remaining tmp = new Remaining(rem, property);
                res.add(tmp);
            }
        }
        return res;
    }
}
