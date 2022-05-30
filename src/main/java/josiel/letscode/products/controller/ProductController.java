package josiel.letscode.products.controller;

import josiel.letscode.products.dto.ProductRequestDto;
import josiel.letscode.products.domain.Product;
import josiel.letscode.products.dto.ProductResponseDto;
import josiel.letscode.products.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> findAllByPage(@PageableDefault(size = 5)
                                                           @SortDefault.SortDefaults({
                                                                   @SortDefault(sort = "name", direction = Sort.Direction.ASC)
                                                           })Pageable pageable) {
        Page<Product> rebels = productService.findAllByPage(pageable);

        return ResponseEntity.ok(rebels.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> findById(@PathVariable String id) {
        return ResponseEntity.ok(productService.findById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<String> save(@Valid @RequestBody ProductRequestDto productRequestDto) {
        if (productService.save(productRequestDto) == null) {
            ResponseEntity.badRequest();
        }
        return ResponseEntity.ok("Cadastrado com sucesso");
    }


}
