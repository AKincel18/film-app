package com.filmapp.category;

import com.filmapp.generic.GenericControllerImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/categories")
public class CategoryController extends GenericControllerImpl<Category> {
}
