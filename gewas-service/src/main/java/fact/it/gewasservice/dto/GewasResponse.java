package fact.it.gewasservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GewasResponse {
    private UUID uuid;
    private String name;
    private String season;
    private double pricePerTon;
}
