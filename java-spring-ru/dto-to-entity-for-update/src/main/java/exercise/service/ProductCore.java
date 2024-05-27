package exercise.service;

import exercise.dto.ProductCreateDTO;
import exercise.dto.ProductDTO;
import exercise.dto.ProductUpdateDTO;
import exercise.exception.ProductNotFoundException;
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

    public List<ProductDTO> findAllProducts() {
        return productRepository.findAll().stream()
                .map(this::buildProductDTO)
                .toList();
    }

    public ProductDTO findProductById(Long id) {
        return buildProductDTO(productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id)));
    }

    @Transactional
    public ProductDTO createProduct(ProductCreateDTO productData) {
        return buildProductDTO(productRepository.save(
                new Product()
                        .setTitle(productData.getTitle())
                        .setPrice(productData.getPrice())
                        .setVendorCode(productData.getVendorCode()))
        );
    }

    @Transactional
    public ProductDTO updateProduct(Long id, ProductUpdateDTO productUpdateDTO) {
        return buildProductDTO(
                productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id))
                        .setTitle(productUpdateDTO.getTitle())
                        .setPrice(productUpdateDTO.getPrice())
        );
    }

    private ProductDTO buildProductDTO(Product product) {
        return ProductDTO.builder()
                .id(product.getId())
                .title(product.getTitle())
                .price(product.getPrice())
                .vendorCode(product.getVendorCode())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .build();
    }
}
