package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.example.data.dto.CategoryInDto;
import org.example.data.entity.Category;
import org.example.data.mapper.CategoryMapper;
import org.example.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    public CategoryController(CategoryService categoryService, CategoryMapper categoryMapper) {
        this.categoryService = categoryService;
        this.categoryMapper = categoryMapper;
    }


    @GetMapping("head")
    ResponseEntity<?> getHeadCategories(){
        var res = categoryService.getHeadCategory();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("{parentId}/descendants")
    ResponseEntity<?> getDescendants(@PathVariable(name = "parentId") Long id){
        var res = categoryService.findCategoriesWithParentId(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("{destination}/path")
    ResponseEntity<?> getPathFromBegin(@PathVariable(name = "destination") Long id){
        var res = categoryService.findPathTo(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }


    @PostMapping
    @SecurityRequirement(name = "bearer-key")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    ResponseEntity<?> addCategory(@RequestBody CategoryInDto categoryInDto){
        Category category = categoryMapper.categoryInToCategory(categoryInDto);

        Category addedCategory = categoryService.addCategory(category);

        return new ResponseEntity<>(addedCategory, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @SecurityRequirement(name = "bearer-key")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    ResponseEntity<?> deleteCategory(@PathVariable(name = "id") Long id, @RequestParam("recursive") Boolean recursive){

        if(recursive) {
            categoryService.deleteRecursive(id);
        } else {
            categoryService.deleteById(id);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
