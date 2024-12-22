package fact.it.veldservice;

import fact.it.veldservice.converter.VeldConvertor;
import fact.it.veldservice.dto.BoerDto;
import fact.it.veldservice.dto.GewasDto;
import fact.it.veldservice.dto.VeldResponse;
import fact.it.veldservice.model.Veld;
import fact.it.veldservice.repository.VeldRepository;
import fact.it.veldservice.service.VeldService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class VeldServiceUnitTest {

    @InjectMocks
    private VeldService veldService;

    @Mock
    private VeldRepository veldRepository;

    @Spy
    private VeldConvertor veldConvertor;

    @Test
    public void getAllVelden() {
        // Arrange
        UUID uuidVeld = UUID.randomUUID();
        UUID uuidGewas  = UUID.randomUUID();
        UUID uuidBoer = UUID.randomUUID();

        BoerDto boer = BoerDto.builder()
                .uuid(uuidBoer)
                .name("Guido Truyen")
                .age(45)
                .address("Waarslaan 48")
                .phoneNumber("089 52 74 14")
                .build();

        GewasDto gewas = GewasDto.builder()
                .uuid(uuidGewas)
                .name("Maïs")
                .season("Voorjaar")
                .pricePerTon(100)
                .build();

        Veld veld = Veld.builder()
                .uuid(uuidVeld)
                .name("test veld")
                .size("1ha")
                .location("test location")
                .boerUuid(uuidBoer)
                .gewasUuid(uuidGewas)
                .build();


        when(veldRepository.findAll()).thenReturn(List.of(veld));

        //Act
        List<VeldResponse> velden = veldService.getAllVelden();

        //Assert
        assertEquals(1, velden.size());
        assertEquals(uuidVeld, velden.get(0).getUuid());
        assertEquals("test veld", velden.get(0).getName());
        assertEquals("1ha", velden.get(0).getSize());
        assertEquals("test location", velden.get(0).getLocation());
        assertEquals(boer, velden.get(0).getBoerDto());
        assertEquals(gewas, velden.get(0).getGewasDto());

        verify(veldRepository, times(1)).findAll();
    }
}
