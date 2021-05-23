package org.example.service;

import org.example.data.entity.Category;
import org.example.exception.BadEntityException;
import org.example.exception.EntityNotFound;
import org.example.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getHeadCategory(){
        return categoryRepository.findHeadCategories();
    }

    public List<Category> findCategoriesWithParentId(Long parentId){
        return categoryRepository.findCategoriesByParentId(parentId);
    }

    public List<Category> findPathTo(Long id){
        return categoryRepository.findPathTo(id);
    }

    public Category addCategory(Category category){
        if(category.getParentId() != null && !categoryRepository.existsById(category.getParentId())){
            throw new EntityNotFound(Category.class, category.getParentId());
        }
        if(categoryRepository.existsByParentIdAndName(category.getParentId(), category.getName())){
            throw new BadEntityException("Category with parentId " + category.getParentId() + " and name " + category.getName() + " already exists" );
        }

        return categoryRepository.save(category);
    }

    public void deleteRecursive(Long id){
        if(!categoryRepository.existsById(id)){
            throw new EntityNotFound(Category.class, id);
        }

        categoryRepository.deleteRecursive(id);
    }

    public Category findById(Long id){
        var res = categoryRepository.findById(id);
        if(res.isPresent()){
            return res.get();
        } else {
            throw new EntityNotFound(Category.class, id);
        }
    }


    public boolean existsById(Long id) {
        return categoryRepository.existsById(id);
    }
}
