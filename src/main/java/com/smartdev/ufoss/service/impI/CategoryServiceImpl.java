package com.smartdev.ufoss.service.impI;

import com.smartdev.ufoss.dto.CategoryDTO;
import com.smartdev.ufoss.entity.CategoryEntity;
import com.smartdev.ufoss.exception.MessageErrorException;
import com.smartdev.ufoss.exception.UserNotFoundException;
import com.smartdev.ufoss.repository.CategoryRepository;
import com.smartdev.ufoss.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<CategoryEntity> getMenu() {
        List<CategoryEntity> menu = categoryRepository.getMenu();

        return menu;
    }

    @Override
    public CategoryDTO newCategory(CategoryDTO model) throws MessageErrorException{

        Optional<CategoryEntity> category = categoryRepository.findByName(model.getName());
        Optional<CategoryEntity> categoryParent = categoryRepository.findByName(model.getParent());

        if (!category.isPresent() && categoryParent.isPresent()) {
            categoryRepository.newCategory(model.getName(),categoryParent.get().getId());
            System.out.println(categoryParent.get().getId());
        } else {
            throw new MessageErrorException("Failed!");
        }

        return model;
    }

    @Override
    public String updateParentID(String name, String parent) throws UserNotFoundException {

        Optional<CategoryEntity> category = categoryRepository.findByName(name);
        Optional<CategoryEntity> categoryParent = categoryRepository.findByName(parent);

        if (category.isPresent() && categoryParent.isPresent()) {
            categoryRepository.updateParentID(
                    name,
                    categoryParent.get().getId(),
                    category.get().getId()
            );

        } else {
            throw new UserNotFoundException("Category not exists!");
        }

        return "Successfully";
    }
}
