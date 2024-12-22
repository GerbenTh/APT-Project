package fact.it.veldservice.converter;

import fact.it.veldservice.dto.BoerDto;
import fact.it.veldservice.dto.GewasDto;
import fact.it.veldservice.dto.VeldResponse;
import fact.it.veldservice.model.Veld;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class VeldConvertor {

    private WebClient webClient;

    public VeldResponse convert(Veld veld, BoerDto boer, GewasDto gewas) {


        return VeldResponse.builder()
                .uuid(veld.getUuid())
                .name(veld.getName())
                .size(veld.getSize())
                .location(veld.getLocation())
                .boerDto(boer)
                .gewasDto(gewas)
                .build();
    }
}
