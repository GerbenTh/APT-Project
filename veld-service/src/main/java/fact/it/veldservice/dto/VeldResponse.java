package fact.it.veldservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VeldResponse {
    private UUID uuid;
    private String name;
    private String size;
    private String location;
    private UUID boerUuid;
    private UUID gewasUuid;
}
