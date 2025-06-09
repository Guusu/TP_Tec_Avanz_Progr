package ar.com.up.theater.manager.service;

import ar.com.up.theater.manager.model.ShowType;
import ar.com.up.theater.manager.model.User;
import ar.com.up.theater.manager.repository.ShowTypeRepository;
import ar.com.up.theater.manager.service.impl.ShowTypeServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ShowTypeServiceImplTest {
    @Mock
    private ShowTypeRepository showTypeRepository;
    @InjectMocks
    private ShowTypeServiceImpl showTypeServiceImpl;

    @Test
    @DisplayName("getTypeByName --> Ok")
    void getTypwByName() {
        ShowType showType = new ShowType();
        showType.setName("musical");
        when(showTypeRepository.findByNameIgnoreCase(anyString())).thenReturn(Optional.of(showType));

        ShowType showTypeUsername = showTypeServiceImpl.getShowTypeByName(showType.getName());

        assertNotNull(showTypeUsername);
        assertEquals(showType, showTypeUsername);

    }
}