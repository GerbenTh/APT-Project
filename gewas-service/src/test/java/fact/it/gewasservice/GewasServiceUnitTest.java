package fact.it.gewasservice;

import fact.it.gewasservice.dto.GewasResponse;
import fact.it.gewasservice.model.Gewas;
import fact.it.gewasservice.repository.GewasRepository;
import fact.it.gewasservice.service.GewasService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class GewasServiceUnitTest {

    @InjectMocks
    private GewasService gewasService;

    @Mock
    private GewasRepository gewasRepository;

    @Test
    public void getAllGewassen()
    {
        UUID uuidGewas = UUID.randomUUID();

        // Arrange
        Gewas gewas = Gewas.builder()
                .uuid(uuidGewas)
                .name("test gewas")
                .season("test season")
                .pricePerTon(0)
                .build();

        when(gewasRepository.findAll()).thenReturn(List.of(gewas));

        // Act
        List<GewasResponse> gewassen = gewasService.getAllGewassen();

        // Assert
        assertEquals(1, gewassen.size());
        assertEquals(uuidGewas, gewassen.get(0).getUuid());
        assertEquals("test gewas", gewassen.get(0).getName());
        assertEquals("test season", gewassen.get(0).getSeason());
        assertEquals(0, gewassen.get(0).getPricePerTon());

        verify(gewasRepository, times(1)).findAll();
    }
}
