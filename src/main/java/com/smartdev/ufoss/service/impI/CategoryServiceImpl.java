package com.smartdev.ufoss.service.impI;

import com.smartdev.ufoss.dto.CategoryDTO;
import com.smartdev.ufoss.entity.CategoryEntity;
import com.smartdev.ufoss.exception.MessageErrorException;
import com.smartdev.ufoss.exception.UserNotFoundException;
import com.smartdev.ufoss.repository.CategoryRepository;
import com.smartdev.ufoss.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public List<CategoryEntity> getMenu() {
        List<CategoryEntity> menu = categoryRepository.getMenu();

        return menu;
    }

    @Override
    public List<CategoryEntity> getSubCategory(String category) {
        List<CategoryEntity> subCategory = categoryRepository.getSubCategory(category);

        return subCategory;
    }

    @Override
    public String newCategory(CategoryDTO model, String parentName) throws MessageErrorException{

        Optional<CategoryEntity> category = categoryRepository.findByName(model.getName());
        Optional<CategoryEntity> categoryParent = categoryRepository.findByName(parentName);

        if (!category.isPresent() ) {

            if(categoryParent.isPresent()){
                categoryRepository.newCategory(model.getName(),categoryParent.get().getId());
            } else {
                categoryRepository.newCategory(model.getName(),Long.valueOf(null));
            }

        } else {
            throw new MessageErrorException("Failed!");
        }

        return "Successfully";
    }

    @Override
    public String updateParentID(String subcategory, String category) throws UserNotFoundException {

        Optional<CategoryEntity> checkSubCategory = categoryRepository.findByName(subcategory);
        Optional<CategoryEntity> checkCategory = categoryRepository.findByName(category);


        System.out.println(checkSubCategory.get().getName() + "is sub");
        System.out.println(checkCategory.get().getName() + "is category");

        if (checkSubCategory.isPresent() && checkCategory.isPresent()) {
            categoryRepository.updateParentID(
                    subcategory,
                    checkCategory.get().getId(),
                    checkSubCategory.get().getId()
            );

        } else {
            throw new UserNotFoundException("Category does not exists!");
        }

        return "Successfully";
    }
}
