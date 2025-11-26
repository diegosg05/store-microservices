package com.plinplin.category_service.infrastructure.adapter.input.rest.controller;

import com.plinplin.category_service.application.input.CategoryServicePort;
import com.plinplin.category_service.infrastructure.adapter.input.rest.dto.CategoryRequestDto;
import com.plinplin.category_service.infrastructure.adapter.input.rest.dto.CategoryResponseDto;
import com.plinplin.category_service.infrastructure.adapter.input.rest.dto.CategoryUpdateDto;
import com.plinplin.category_service.infrastructure.adapter.input.rest.mapper.CategoryRestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryServicePort service;
    private final CategoryRestMapper mapper;

    @GetMapping
    public ResponseEntity<List<CategoryResponseDto>> findAll() {

        var categories = service.findAll().stream()
                .map(mapper::toDto)
                .toList();

        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDto> findById(@PathVariable Long id) {

        var optionalCategory = service.findById(id)
                .map(mapper::toDto);

        return optionalCategory
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CategoryResponseDto> save(@RequestBody CategoryRequestDto newCategory) {

        var categorySaved = service.save(mapper.requestToDomain(newCategory));

        return ResponseEntity.created(
                URI.create("/categories/".concat(categorySaved.getId().toString()))
        ).body(mapper.toDto(categorySaved));
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody CategoryUpdateDto updateCategory) {

        if (service.findById(updateCategory.id()).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        service.update(mapper.updateToDomain(updateCategory));

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        if (service.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        service.delete(id);

        return ResponseEntity.noContent().build();

    }
}
