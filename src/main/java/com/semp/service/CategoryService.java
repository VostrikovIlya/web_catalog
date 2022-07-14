package com.semp.service;

import com.semp.entity.Category;
import com.semp.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getChild(String slug) {
        Category category = categoryRepository.findBySlug(slug);
        return categoryRepository.findByParentIdOrderById(category.getId());
    }

    public Category getById(Long id) {
        return categoryRepository.getReferenceById(id);
    }

    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    public Category getByName(String name) {
        return categoryRepository.findByName(name);
    }
}
