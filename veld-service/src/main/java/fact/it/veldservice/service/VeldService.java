package fact.it.veldservice.service;

import fact.it.veldservice.converter.VeldConvertor;
import fact.it.veldservice.dto.VeldResponse;
import fact.it.veldservice.model.Veld;
import fact.it.veldservice.repository.VeldRepository;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class VeldService {

    private VeldRepository veldRepository;
    private VeldConvertor veldConvertor;

    @PostConstruct
    public void loadData() {
        if(veldRepository.count() <= 0) {

            List<Veld> veldList = new ArrayList<>();

            Veld veld1 = Veld.builder()
                    .uuid(UUID.randomUUID())
                    .name("Kuil 1")
                    .size("1.5 ha")
                    .location("Spreeuwerstraat")
                    .boerUuid(UUID.fromString("97338dbf-7257-44a2-95c7-8f4df043b607"))
                    .build();

            Veld veld2 = Veld.builder()
                    .uuid(UUID.randomUUID())
                    .name("Veld bij brug")
                    .size("0.9 ha")
                    .location("Spreeuwerstraat")
                    .boerUuid(UUID.fromString("97338dbf-7257-44a2-95c7-8f4df043b607"))
                    .build();

            Veld veld3 = Veld.builder()
                    .uuid(UUID.randomUUID())
                    .name("Kuil 3")
                    .size("1.2 ha")
                    .location("Spreeuwerstraat")
                    .boerUuid(UUID.fromString("97338dbf-7257-44a2-95c7-8f4df043b607"))
                    .build();


            veldList.add(veld1);
            veldList.add(veld2);
            veldList.add(veld3);

            veldRepository.saveAll(veldList);
            }
    }

    public List<VeldResponse> getAllVelden() {
        List<Veld> velden = veldRepository.findAll();

        List<VeldResponse> veldResponses = new ArrayList<>();

        for (var veld : velden) {
            veldResponses.add(veldConvertor.convert(veld));
        }

        return veldResponses;
    }

}
