package org.example.controller;

import com.github.dozermapper.core.Mapper;
import org.example.data.dto.CategoryInDto;
import org.example.data.entity.Category;
import org.example.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {

    private final Mapper dozerMapper;

    private final CategoryService categoryService;

    public CategoryController(Mapper dozerMapper, CategoryService categoryService) {
        this.dozerMapper = dozerMapper;
        this.categoryService = categoryService;
    }

    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping("head")
    ResponseEntity<?> getHeadCategories(){
        var res = categoryService.getHeadCategory();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping("descendants")
    ResponseEntity<?> getDescendants(@RequestParam(name = "parentId") Long id){
        var res = categoryService.findCategoriesWithParentId(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("path")
    ResponseEntity<?> getPathFromBegin(@RequestParam(name = "destination") Long id){
        var res = categoryService.findPathTo(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @PostMapping
    ResponseEntity<?> addCategory(@RequestBody CategoryInDto categoryInDto){
        Category category = dozerMapper.map(categoryInDto, Category.class);

        var res = categoryService.addCategory(category);

        return new ResponseEntity<>(res, HttpStatus.OK);
    }


    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @DeleteMapping
    ResponseEntity<?> deleteCategory(@RequestParam(name = "id") Long id){
        categoryService.deleteRecursive(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
