package com.plinplin.category_service.infrastructure.adapter.output.persistence;

import com.plinplin.category_service.application.output.CategoryRepositoryPort;
import com.plinplin.category_service.domain.model.Category;
import com.plinplin.category_service.infrastructure.adapter.output.persistence.entity.CategoryEntity;
import com.plinplin.category_service.infrastructure.adapter.output.persistence.mapper.CategoryPersistenceMapper;
import com.plinplin.category_service.infrastructure.adapter.output.persistence.repository.CategoryJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CategoryPersistenceAdapter implements CategoryRepositoryPort {

    private final CategoryJpaRepository categoryJpaRepository;
    private final CategoryPersistenceMapper mapper;

    @Override
    public Category save(Category category) {
        CategoryEntity categorySaved = categoryJpaRepository.save(mapper.toEntity(category));
        return mapper.toDomain(categorySaved);
    }

    @Override
    public Optional<Category> findById(Long id) {
        Optional<CategoryEntity> optionalCategoryEntity = categoryJpaRepository.findById(id);
        return optionalCategoryEntity.map(mapper::toDomain);
    }

    @Override
    public List<Category> findAll() {
        return categoryJpaRepository.findAll().stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public void deleteById(Long id) {
        categoryJpaRepository.deleteById(id);
    }
}
