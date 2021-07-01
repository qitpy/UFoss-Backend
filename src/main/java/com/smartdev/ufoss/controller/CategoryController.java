package com.smartdev.ufoss.controller;

import com.smartdev.ufoss.dto.CategoryDTO;
import com.smartdev.ufoss.entity.CategoryEntity;
import com.smartdev.ufoss.exception.MessageErrorException;
import com.smartdev.ufoss.exception.UserNotFoundException;
import com.smartdev.ufoss.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categories")
    public ResponseEntity<?> getMenu() {
        List<CategoryEntity> result = categoryService.getMenu();

        return ResponseEntity.ok(result);
    }

    @GetMapping("/categories/{name}")
    public ResponseEntity<?> getSubCategory(@PathVariable("name") String name) {
        List<CategoryEntity> result = categoryService.getSubCategory(name);

        return ResponseEntity.ok(result);
    }

//    @PutMapping("/update/{name}/{parent}")
//    public ResponseEntity<?> updateParentID(
//            @PathVariable("name") String name,
//            @PathVariable("parent") String parentName
//    ) {
//        String message;
//
//        try {
//            message = categoryService.updateParentID(name, parentName);
//        } catch (UserNotFoundException e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
//        return ResponseEntity.ok(message);
//    }

    @PostMapping("/categories/{category}")
    public ResponseEntity<?> newCategory(@RequestBody CategoryDTO model,@PathVariable("category") String parentName) {

        String message = "";
        try {
            message = categoryService.newCategory(model, parentName);
        } catch (MessageErrorException e) {
            ResponseEntity.badRequest().body(e.getMessage());
        }

        return ResponseEntity.ok(message);
    }
}