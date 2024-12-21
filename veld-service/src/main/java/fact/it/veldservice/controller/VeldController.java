package fact.it.veldservice.controller;

import fact.it.veldservice.dto.VeldResponse;
import fact.it.veldservice.service.VeldService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/velden")
@AllArgsConstructor
public class VeldController {

    private VeldService veldService;

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public List<VeldResponse> getVelden() {
        return veldService.getAllVelden();
    }
}
