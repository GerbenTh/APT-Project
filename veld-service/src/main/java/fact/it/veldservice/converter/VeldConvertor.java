package fact.it.veldservice.converter;

import fact.it.veldservice.dto.VeldResponse;
import fact.it.veldservice.model.Veld;
import org.springframework.stereotype.Component;

@Component
public class VeldConvertor {

    public VeldResponse convert(Veld veld) {
        return VeldResponse.builder()
                .uuid(veld.getUuid())
                .name(veld.getName())
                .size(veld.getSize())
                .location(veld.getLocation())
                .boerUuid(veld.getBoerUuid())
                .gewasUuid(veld.getGewasUuid())
                .build();
    }
}
