package fact.it.veldservice.converter;

import fact.it.veldservice.dto.BoerDto;
import fact.it.veldservice.dto.GewasDto;
import fact.it.veldservice.dto.VeldResponse;
import fact.it.veldservice.model.Veld;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

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
