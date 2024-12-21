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
public class BoerDto {
    private UUID uuid;
    private String name;
    private int age;
    private String phoneNumber;
    private String address;
}


