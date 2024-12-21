package fact.it.veldservice;

import fact.it.veldservice.dto.VeldResponse;
import fact.it.veldservice.model.Veld;
import fact.it.veldservice.repository.VeldRepository;
import fact.it.veldservice.service.VeldService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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

    @Test
    public void getAllVelden() {

        // Arrange
        Veld veld = Veld.builder()
                .uuid(UUID.fromString("uuid1"))
                .name("test veld")
                .size("1ha")
                .location("test location")
                .boerUuid(UUID.fromString("boer-uuid"))
                .build();

        when(veldRepository.findAll()).thenReturn(Arrays.asList(veld));

        // Act
        List<VeldResponse> velden = veldService.getAllVelden();

        // Assert
        assertEquals(1, velden.size());
        assertEquals(UUID.fromString("uuid1"), velden.get(0).getUuid());
        assertEquals("test veld", velden.get(0).getName());
        assertEquals("1ha", velden.get(0).getSize());
        assertEquals("test location", velden.get(0).getLocation());
        assertEquals(UUID.fromString("boer-uuid"), velden.get(0).getBoerUuid());

        verify(veldRepository, times(1)).findAll();
    }
}
