package fact.it.boerservice.service;

import fact.it.boerservice.convertor.BoerConvertor;
import fact.it.boerservice.dto.BoerRequest;
import fact.it.boerservice.dto.BoerResponse;
import fact.it.boerservice.model.Boer;
import fact.it.boerservice.repository.BoerRepository;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class BoerService {

    private BoerRepository boerRepository;
    private BoerConvertor boerConvertor;

    @PostConstruct
    public void loadData() {
        if(boerRepository.count() <= 0){

            List<Boer> boerList = new ArrayList<>();

            Boer boer1 = Boer.builder()
                    .uuid(UUID.randomUUID())
                    .name("Ties Doe")
                    .age(56)
                    .address("Steenweg 27")
                    .phoneNumber("089 12 55 98").build();

            Boer boer2 = Boer.builder()
                    .uuid(UUID.randomUUID())
                    .name("Guido Truyen")
                    .age(45)
                    .address("Waarslaan 48")
                    .phoneNumber("089 52 74 14").build();

            Boer boer3 = Boer.builder()
                    .uuid(UUID.randomUUID())
                    .name("Ivan Shabon")
                    .age(66)
                    .address("Venlosesteenweg 99")
                    .phoneNumber("0470 27 28 88").build();

            boerList.add(boer1);
            boerList.add(boer2);
            boerList.add(boer3);

            boerRepository.saveAll(boerList);
        }
    }

    public List<BoerResponse> getAllBoeren() {
        List<Boer> boeren = boerRepository.findAll();

        List<BoerResponse> boerResponses = new ArrayList<>();

        for (var boer : boeren) {
            boerResponses.add(boerConvertor.convert(boer));
        }

        return boerResponses;
    }

    public BoerResponse getBoerByUuid(UUID uuid) {
        Optional<Boer> boerOptional = boerRepository.findByUuid(uuid);
        if (boerOptional.isPresent()) {
            Boer boer = boerOptional.get();
            return BoerResponse.builder()
                    .uuid(boer.getUuid())
                    .name(boer.getName())
                    .age(boer.getAge())
                    .address(boer.getAddress())
                    .phoneNumber(boer.getPhoneNumber())
                    .build();
        }

        throw new RuntimeException("boer not found");
    }

    public BoerResponse createBoer(BoerRequest boerRequest) {
        Boer boer = boerConvertor.convert(UUID.randomUUID(), boerRequest);

        boerRepository.save(boer);
        return boerConvertor.convert(boer);
    }

    public Boer updateBoer(UUID uuid, BoerRequest boerUpdate) {
        Optional<Boer> boerOptional = boerRepository.findByUuid(uuid);

        if (boerOptional.isPresent()) {
            Boer boer = boerOptional.get();

            boer = boerConvertor.convert(boer.getId(), uuid, boerUpdate);

            return boerRepository.save(boer);
        }

        throw new RuntimeException("boer not found");
    }

    public void deleteBoer(UUID uuid) {
        Optional<Boer> boerOptional = boerRepository.findByUuid(uuid);

        if (boerOptional.isPresent()){
            boerRepository.delete(boerOptional.get());
        }

        throw new RuntimeException("boer not found");
    }
}
