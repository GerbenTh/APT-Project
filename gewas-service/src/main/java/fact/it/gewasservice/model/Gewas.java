package fact.it.gewasservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(value = "gewas")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Gewas {
    
    private String id;
    private UUID uuid;
    private String name;
    private String season;
    private double pricePerTon;
}
