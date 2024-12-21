package fact.it.gewasservice.controller;

import fact.it.gewasservice.dto.GewasRequest;
import fact.it.gewasservice.dto.GewasResponse;
import fact.it.gewasservice.repository.GewasRepository;
import fact.it.gewasservice.service.GewasService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/gewassen")
@RequiredArgsConstructor
public class GewasController {
    private final GewasService gewasService;

//    @PostMapping
//    @ResponseStatus(HttpStatus.OK)
//    public void createGewas(@RequestBody GewasRequest gewasRequest){
//        gewasService.createGewas(gewasRequest);
//    }

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public List<GewasResponse> getAllGewassen() {
        return gewasService.getAllGewassen();
    }

    @GetMapping("/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public GewasResponse getGewasByUuid(@PathVariable UUID uuid) {
        return gewasService.getGewasByUuid(uuid);
    }
}
