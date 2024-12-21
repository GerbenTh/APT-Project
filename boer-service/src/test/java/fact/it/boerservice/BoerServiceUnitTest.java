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

    private BoerConvertor boerConvertor;

    @Test
    public void getAllBoeren() {

        // Arrange
        Boer boer = Boer.builder()
                .uuid(UUID.fromString("uuid1"))
                .name("Test boer")
                .age(50)
                .address("Test address")
                .phoneNumber("012 34 56 78")
                .build();

        when(boerRepository.findAll()).thenReturn(Arrays.asList(boer));

        //Act
        List<BoerResponse> boeren = boerService.getAllBoeren();

        //Assert
        assertEquals(1, boeren.size());
        assertEquals(UUID.fromString("uuid1"), boeren.get(0).getUuid());
        assertEquals("Test boer", boeren.get(0).getName());
        assertEquals(50, boeren.get(0).getAge());
        assertEquals("Test address", boeren.get(0).getAddress());
        assertEquals("012 34 56 78", boeren.get(0).getPhoneNumber());

        verify(boerRepository, times(1)).findAll();
    }

    @Test
    public void getBoerenByUuid() {
        UUID uuid = UUID.randomUUID();

        // Arrange
        Boer boer = Boer.builder()
                .uuid(uuid)
                .name("Test boer")
                .age(50)
                .address("Test address")
                .phoneNumber("012 34 56 78")
                .build();

        when(boerRepository.findAll()).thenReturn(Arrays.asList(boer));

        //Act
        BoerResponse boerResponse = boerService.getBoerByUuid(uuid);

        //Assert
        assertEquals(uuid, boerResponse.getUuid());
        assertEquals("Test boer", boerResponse.getName());
        assertEquals(50, boerResponse.getAge());
        assertEquals("Test address", boerResponse.getAddress());
        assertEquals("012 34 56 78", boerResponse.getPhoneNumber());

        verify(boerRepository, times(1)).findBoerByUuid(uuid);
    }

    @Test
    public void testCreateBoer() {

        // Arrange
        BoerRequest boerRequest = BoerRequest.builder()
                .name("Test boer")
                .age(50)
                .address("Test address")
                .phoneNumber("012 34 56 78")
                .build();

        // Act
        boerService.createBoer(boerRequest);

        // Assert
        verify(boerRepository, times(1)).save(any(Boer.class));
    }

    @Test
    public void testUpdateBoer() {

        UUID uuid = UUID.randomUUID();

        // Arrange
        Boer boer = Boer.builder()
                .uuid(uuid)
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

        when(boerRepository.findAll()).thenReturn(Arrays.asList(boer));

        Optional<Boer> optionalBoer = boerRepository.findByUuid(uuid);

        if (optionalBoer.isPresent()) {
            Boer updatedBoer = optionalBoer.get();

            // Act
            updatedBoer = boerConvertor.convert(boer.getId(), uuid, boerRequest);
            when(boerRepository.findAll()).thenReturn(Arrays.asList(updatedBoer));
        }


        // Assert
        verify(boerRepository, times(2)).save(any(Boer.class));

    }


    @Test
    public void testDeleteBoer() {

        UUID uuid = UUID.randomUUID();

        // Arrange
        Boer boer = Boer.builder()
                .uuid(uuid)
                .name("Test boer")
                .age(50)
                .address("Test address")
                .phoneNumber("012 34 56 78")
                .build();

        when(boerRepository.findAll()).thenReturn(Arrays.asList(boer));
        Optional<Boer> optionalBoer = boerRepository.findByUuid(uuid);

        // Act
        boerService.deleteBoer(uuid);

        // Assert
        verify(boerRepository, times(1)).delete(optionalBoer.get());


    }
}
