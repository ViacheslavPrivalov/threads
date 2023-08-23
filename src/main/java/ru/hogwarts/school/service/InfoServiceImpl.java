package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.stream.Stream;

@Service
public class InfoServiceImpl implements InfoService {
    private final Integer port;

    public InfoServiceImpl(@Value("${server.port}") Integer port) {
        this.port = port;
    }

    @Override
    public Integer getPort() {
        return port;
    }

}
