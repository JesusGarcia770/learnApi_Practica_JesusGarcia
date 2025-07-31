package IntegracionBackFront.backfront.Controller.Products.Categories;

import IntegracionBackFront.backfront.Models.DTO.Categories.CategoryDTO;
import IntegracionBackFront.backfront.Repositories.Categories.CategoryRepository;
import IntegracionBackFront.backfront.Services.Categories.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/category")
public class categoriesController {

    @Autowired
    private CategoryService service;

    @GetMapping("/ObtenerCategories")
    private ResponseEntity<Page<CategoryDTO>> getData(

            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
     ){
        if (size <= 0 || size > 50){
            ResponseEntity.badRequest().body(Map.of(
                    "status", "el tamaño de la pagina de estar entre 1 y 50"
            ));
            return ResponseEntity.ok(null);
        }
        Page<CategoryDTO> category = service.getAllCategories(page, size);
        if (category == null){
            ResponseEntity.badRequest().body(Map.of(
                    "status", "No hay categorias registradas"
            ));
        }
        return ResponseEntity.ok(category);
    }
}
