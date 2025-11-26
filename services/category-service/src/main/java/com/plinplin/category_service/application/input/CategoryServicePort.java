package com.plinplin.category_service.application.input;

import com.plinplin.category_service.domain.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryServicePort {
    Category save(Category category);
    Optional<Category> findById(Long id);
    List<Category> findAll();
    void update(Category category);
    void delete(Long id);
}
