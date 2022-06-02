package josiel.letscode.products.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequestDto {
    private String name;
    private Double price;
    private int availableSeats;
    private String description;
    private String imageUrl;
}
