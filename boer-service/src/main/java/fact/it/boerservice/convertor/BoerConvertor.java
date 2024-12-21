package fact.it.boerservice.convertor;

import fact.it.boerservice.dto.BoerRequest;
import fact.it.boerservice.dto.BoerResponse;
import fact.it.boerservice.model.Boer;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class BoerConvertor {

    public BoerResponse convert(Boer boer) {
        return BoerResponse.builder()
                .uuid(boer.getUuid())
                .name(boer.getName())
                .age(boer.getAge())
                .phoneNumber(boer.getPhoneNumber())
                .address(boer.getAddress())
                .build();
    }

    public Boer convert(UUID uuid, BoerRequest boerRequest) {
        return Boer.builder()
                .uuid(uuid)
                .name(boerRequest.getName())
                .age(boerRequest.getAge())
                .phoneNumber(boerRequest.getPhoneNumber())
                .address(boerRequest.getAddress())
                .build();
    }

    public Boer convert(long id, UUID uuid, BoerRequest boerRequest) {
        return Boer.builder()
                .id(id)
                .uuid(uuid)
                .name(boerRequest.getName())
                .age(boerRequest.getAge())
                .phoneNumber(boerRequest.getPhoneNumber())
                .address(boerRequest.getAddress())
                .build();
    }
}
