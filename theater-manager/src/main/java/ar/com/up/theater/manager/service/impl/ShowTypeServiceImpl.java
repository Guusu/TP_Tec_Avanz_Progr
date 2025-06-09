package ar.com.up.theater.manager.service.impl;

import ar.com.up.theater.manager.model.ShowType;
import ar.com.up.theater.manager.repository.ShowTypeRepository;
import ar.com.up.theater.manager.service.ShowTypeService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShowTypeServiceImpl implements ShowTypeService {
    private final ShowTypeRepository showTypeRepository;

    public ShowTypeServiceImpl(ShowTypeRepository showTypeRepository) {
        this.showTypeRepository = showTypeRepository;
    }

    @Override
    public ShowType getShowTypeByName(String name) {

        return showTypeRepository.findByNameIgnoreCase(name).orElse(null);
    }
}
