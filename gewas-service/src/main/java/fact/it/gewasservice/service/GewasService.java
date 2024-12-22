package fact.it.gewasservice.service;

import fact.it.gewasservice.dto.GewasRequest;
import fact.it.gewasservice.dto.GewasResponse;
import fact.it.gewasservice.model.Gewas;
import fact.it.gewasservice.repository.GewasRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GewasService {

    private final GewasRepository gewasRepository;

    @PostConstruct
    public void loadData() {
        if(gewasRepository.count() <= 0){

            List<Gewas> gewasList = new ArrayList<>();

            Gewas gewas1 = Gewas.builder()
                    .uuid(UUID.fromString("4c9bdf18-1cb7-437b-8945-daf811fe6df3"))
                    .name("Maïs")
                    .season("Voorjaar")
                    .pricePerTon(100)
                    .build();

            Gewas gewas2 = Gewas.builder()
                    .uuid(UUID.randomUUID())
                    .name("Groenbemester")
                    .season("Najaar")
                    .pricePerTon(0)
                    .build();

            Gewas gewas3 = Gewas.builder()
                    .uuid(UUID.randomUUID())
                    .name("Ajuin")
                    .season("Voorjaar")
                    .pricePerTon(150)
                    .build();

            gewasList.add(gewas1);
            gewasList.add(gewas2);
            gewasList.add(gewas3);

            gewasRepository.saveAll(gewasList);
        }
    }

    public List<GewasResponse> getAllGewassen() {
        List<Gewas> gewassen = gewasRepository.findAll();

        return gewassen.stream().map(this::mapToGewasResponse).toList();
    }

    public GewasResponse getGewasByUuid(UUID uuid) {
        Optional<Gewas> gewasOptional = gewasRepository.findByUuid(uuid);
        if (gewasOptional.isPresent()) {
            Gewas gewas = gewasOptional.get();
            return GewasResponse.builder()
                    .uuid(gewas.getUuid())
                    .name(gewas.getName())
                    .season(gewas.getSeason())
                    .pricePerTon(gewas.getPricePerTon())
                    .build();
        }

        throw new RuntimeException("gewas not found");
    }

    private GewasResponse mapToGewasResponse(Gewas gewas) {
        return GewasResponse.builder()
                .uuid(gewas.getUuid())
                .name(gewas.getName())
                .season(gewas.getSeason())
                .pricePerTon(gewas.getPricePerTon())
                .build();

    }
}
