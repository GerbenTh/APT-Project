package fact.it.boerservice;

import fact.it.boerservice.convertor.BoerConvertor;
import fact.it.boerservice.dto.BoerRequest;
import fact.it.boerservice.dto.BoerResponse;
import fact.it.boerservice.model.Boer;
import fact.it.boerservice.repository.BoerRepository;
import fact.it.boerservice.service.BoerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class BoerServiceUnitTest {

    @InjectMocks
    private BoerService boerService;

    @Mock
    private BoerRepository boerRepository;

    @Spy
    // code is simpel genoeg om niet te moeten mocken
    private BoerConvertor boerConvertor;

    @Test
    public void getAllBoeren() {
        // Arrange
        UUID uuidBoer = UUID.randomUUID();

        Boer boer = Boer.builder()
                .uuid(uuidBoer)
                .name("Test boer")
                .age(50)
                .address("Test address")
                .phoneNumber("012 34 56 78")
                .build();

        when(boerRepository.findAll()).thenReturn(List.of(boer));

        //Act
        List<BoerResponse> boeren = boerService.getAllBoeren();

        //Assert
        assertEquals(1, boeren.size());
        assertEquals(uuidBoer, boeren.get(0).getUuid());
        assertEquals("Test boer", boeren.get(0).getName());
        assertEquals(50, boeren.get(0).getAge());
        assertEquals("Test address", boeren.get(0).getAddress());
        assertEquals("012 34 56 78", boeren.get(0).getPhoneNumber());

        verify(boerRepository, times(1)).findAll();
    }

    @Test
    public void getBoerenByUuid() {
        // Arrange
        UUID uuidBoer = UUID.randomUUID();

        Boer boer = Boer.builder()
                .uuid(uuidBoer)
                .name("Test boer")
                .age(50)
                .address("Test address")
                .phoneNumber("012 34 56 78")
                .build();

        when(boerRepository.findByUuid(uuidBoer)).thenReturn(Optional.of(boer));

        //Act
        BoerResponse boerResponse = boerService.getBoerByUuid(uuidBoer);

        //Assert
        assertEquals(uuidBoer, boerResponse.getUuid());
        assertEquals("Test boer", boerResponse.getName());
        assertEquals(50, boerResponse.getAge());
        assertEquals("Test address", boerResponse.getAddress());
        assertEquals("012 34 56 78", boerResponse.getPhoneNumber());

        verify(boerRepository, times(1)).findByUuid(uuidBoer);
    }

    @Test
    public void testCreateBoer() {
        // Arrange
        BoerRequest boerRequest = BoerRequest.builder()
                .name("Boer Updated")
                .age(60)
                .address("updated address")
                .phoneNumber("987 65 43 21")
                .build();

        // Act
        boerService.createBoer(boerRequest);

        // Assert
        verify(boerRepository, times(1)).save(any(Boer.class));
    }

    @Test
    public void testUpdateBoer() {
        // Arrange
        UUID uuidBoer = UUID.randomUUID();

        Boer boer = Boer.builder()
                .id(0L)
                .uuid(uuidBoer)
                .name("Test boer")
                .age(50)
                .address("Test address")
                .phoneNumber("012 34 56 78")
                .build();

        BoerRequest boerRequest = BoerRequest.builder()
                .name("Boer Updated")
                .age(60)
                .address("updated address")
                .phoneNumber("987 65 43 21")
                .build();

        when(boerRepository.findByUuid(uuidBoer)).thenReturn(Optional.of(boer));

        // Act
        boerService.updateBoer(uuidBoer, boerRequest);

        // Assert
        verify(boerRepository, times(1)).findByUuid(uuidBoer);
        verify(boerRepository, times(1)).save(any(Boer.class));
    }

    @Test
    public void testDeleteBoer() {
        UUID uuidBoer = UUID.randomUUID();

        // Arrange
        Boer boer = Boer.builder()
                .uuid(uuidBoer)
                .name("Test boer")
                .age(50)
                .address("Test address")
                .phoneNumber("012 34 56 78")
                .build();

        when(boerRepository.findByUuid(uuidBoer)).thenReturn(Optional.of(boer));
        Optional<Boer> optionalBoer = boerRepository.findByUuid(uuidBoer);

        // Act
        boerService.deleteBoer(uuidBoer);

        // Assert
        verify(boerRepository, times(1)).delete(optionalBoer.get());
    }
}

