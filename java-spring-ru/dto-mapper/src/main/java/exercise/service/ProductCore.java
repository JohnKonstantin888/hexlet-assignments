package exercise.service;

import exercise.dto.ProductCreateDTO;
import exercise.dto.ProductDTO;
import exercise.dto.ProductUpdateDTO;
import exercise.exception.ProductNotFoundException;
import exercise.mapper.ProductMapper;
import exercise.model.Product;
import exercise.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductCore {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public List<ProductDTO> findAllProducts() {
        return productRepository.findAll().stream().map(productMapper::map).toList();
    }

    public ProductDTO findById(long id) {
        return productRepository.findById(id).map(productMapper::map).orElseThrow(() -> new ProductNotFoundException(id));
    }

    @Transactional
    public ProductDTO createProduct(ProductCreateDTO productCreateDTO) {
        return productMapper.map(productRepository.save(productMapper.map(productCreateDTO)));
    }

    @Transactional
    public ProductDTO updateProduct(long id, ProductUpdateDTO productUpdateDTO) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
        product.setCost(productUpdateDTO.getPrice());
        return productMapper.map(product);
    }
}
