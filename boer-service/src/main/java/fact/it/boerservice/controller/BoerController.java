package fact.it.boerservice.controller;

import fact.it.boerservice.dto.BoerRequest;
import fact.it.boerservice.dto.BoerResponse;
import fact.it.boerservice.model.Boer;
import fact.it.boerservice.service.BoerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/boeren")
@AllArgsConstructor
public class BoerController {

    private BoerService boerService;

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public List<BoerResponse> getBoeren() {
        return boerService.getAllBoeren();
    }

    @GetMapping("/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public BoerResponse getBoerByUuid(@PathVariable UUID uuid) {
        return boerService.getBoerByUuid(uuid);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BoerResponse createBoer(@RequestBody BoerRequest boerRequest) {
        return boerService.createBoer(boerRequest);
    }

    @PutMapping("/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public Boer updateBoer(@PathVariable UUID uuid, @RequestBody BoerRequest updatedBoer) {
        return boerService.updateBoer(uuid, updatedBoer);
    }

    @DeleteMapping("/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBoer(@PathVariable UUID uuid) {
        boerService.deleteBoer(uuid);
    }
}
