package com.plinplin.category_service.application.service;

import com.plinplin.category_service.application.input.CategoryServicePort;
import com.plinplin.category_service.application.output.CategoryRepositoryPort;
import com.plinplin.category_service.domain.model.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryServicePort {

    private final CategoryRepositoryPort categoryRepositoryPort;

    @Override
    public Category save(Category category) {
        return categoryRepositoryPort.save(category);
    }

    @Override
    public Optional<Category> findById(Long id) {
        return categoryRepositoryPort.findById(id);
    }

    @Override
    public List<Category> findAll() {
        return categoryRepositoryPort.findAll();
    }

    @Override
    public void update(Category category) {
        categoryRepositoryPort.save(category);
    }

    @Override
    public void delete(Long id) {
        categoryRepositoryPort.deleteById(id);
    }
}
