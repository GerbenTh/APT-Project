package fact.it.veldservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GewasDto {
    private UUID uuid;
    private String name;
    private String season;
    private double pricePerTon;
}
