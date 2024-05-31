package exercise.controller;

import exercise.dto.ProductCreateDTO;
import exercise.dto.ProductDTO;
import exercise.dto.ProductUpdateDTO;
import exercise.service.ProductCore;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductsController {
    private final ProductCore productCore;

//    GET /products – просмотр списка всех товаров
    @GetMapping
    public List<ProductDTO> findAllProducts() {
        return productCore.findAllProducts();
    }
//    GET /products/{id} – просмотр конкретного товара
    @GetMapping("/{id}")
    public ProductDTO findProductById(@PathVariable long id) {
        return productCore.findProductById(id);
    }
//    POST /products – добавление нового товара. При указании id несуществующей категории должен вернуться ответ с кодом 400 Bad request
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDTO createProduct(@Valid @RequestBody ProductCreateDTO createDTO) {
        return productCore.createProduct(createDTO);
    }
//    PUT /products/{id} – редактирование товара. При редактировании мы должны иметь возможность поменять название, цену и категорию товара. При указании id несуществующей категории также должен вернуться ответ с кодом 400 Bad request
    @PutMapping("/{id}")
    public ProductDTO updateProduct(@PathVariable long id, @Valid @RequestBody ProductUpdateDTO updateDTO) {
        return productCore.updateProduct(id, updateDTO);
    }
//    DELETE /products/{id} – удаление товара
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable long id) {
        productCore.deleteProductById(id);
    }
}
