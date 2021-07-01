package com.smartdev.ufoss.controller;

import com.smartdev.ufoss.converter.CategoryConverter;
import com.smartdev.ufoss.dto.CategoryDTO;
import com.smartdev.ufoss.entity.CategoryEntity;
import com.smartdev.ufoss.exception.MessageErrorException;
import com.smartdev.ufoss.exception.NotFoundException;
import com.smartdev.ufoss.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/menu")
    public ResponseEntity<?> getMenu() {
        List<CategoryEntity> menu = categoryService.getMenu();
        for (CategoryEntity i : menu) {
            System.out.println(i.getName());
        }
        return ResponseEntity.ok(menu);
    }

    @PutMapping("/update-parent/{name}/{parent}")
    public ResponseEntity<?> updateParentID(
            @PathVariable("name") String name,
            @PathVariable("parent") String parentName
    ) {
        String message;

        try {
            message = categoryService.updateParentID(name, parentName);
        } catch (NotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok(message);
    }

    @PostMapping("/add")
    public ResponseEntity<?> newCategory(@RequestBody CategoryDTO model) {

        CategoryDTO result = null;
        try {
            result = categoryService.newCategory(model);
        } catch (MessageErrorException e) {
            ResponseEntity.badRequest().body(e.getMessage());
        }

        return ResponseEntity.ok(result);
    }
}
