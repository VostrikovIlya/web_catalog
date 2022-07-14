package com.semp.controller;

import com.semp.entity.Category;
import com.semp.pojo.Filter;
import com.semp.pojo.Remaining;
import com.semp.service.CategoryService;
import com.semp.service.FilterService;
import com.semp.service.RemainingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/catalog")
@RequiredArgsConstructor
public class CatalogController {
    private final CategoryService categoryService;
    private final RemainingService remainingService;
    private final FilterService filterService;

    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.getAll();
    }

    @GetMapping("/{slugCategory}")
    public List<Category> getChildrenCategory(@PathVariable String slugCategory) {
        return categoryService.getChild(slugCategory);
    }

    @GetMapping("/{slugCategory}/products")
    public List<Remaining> getRemainingByCategory(@PathVariable String slugCategory) {
        return remainingService.getRemainingAndPropertyByCategory(slugCategory);
    }

    @GetMapping("/{slugCategory}/filter")
    public Filter getFilterByCategory(@PathVariable String slugCategory) {
        return filterService.getFilter(slugCategory);
    }
}
