package com.toolsapp.service.property;

import com.toolsapp.domain.property.Producer;
import com.toolsapp.repository.extra.property.ProducerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class AbstractPropertyServiceTest {

    @Mock
    ProducerRepository repository;

    @InjectMocks
    ProducerService service;


    @Test
    @DisplayName("save property test")
    void saveTest() {
        Producer producer = new Producer();

        doReturn(producer).when(repository).save(any());
        Producer returnedProducer = service.save(producer);

        Assertions.assertNotNull(returnedProducer,
                "Saved producer shouldn't be null");
        Assertions.assertEquals(returnedProducer, producer,
                "Both producers should be equals");
    }

    @Test
    @DisplayName("findAll success test")
    void findAllTest() {
        Producer producer1 = new Producer();
        Producer producer2 = new Producer();

        doReturn(Arrays.asList(producer1, producer2)).when(repository).findAll();
        List<Producer> producers = service.findAll();

        Assertions.assertEquals(2, producers.size(),
                "should find 2 producers");
    }

    @Test
    @DisplayName("findAll with empty list test")
    void findAllEmptyTest() {
        doReturn(Collections.emptyList()).when(repository).findAll();
        List<Producer> producers = service.findAll();

        Assertions.assertEquals(0, producers.size(),
                "should find nothing");
    }

    @Test
    @DisplayName("findById success")
    void findByIdSuccessTest() {
        Producer producer = new Producer();
        producer.setId(1L);

        doReturn(Optional.of(producer)).when(repository).findById(1L);
        Optional<Producer> returnedProducer = service.findById(1L);

        Assertions.assertTrue(returnedProducer.isPresent(),
                "Producer was not found");
        Assertions.assertSame(returnedProducer.get(), producer,
                "Returned producer is not the same as mock");
    }

    @Test
    @DisplayName("findById not found")
    void findByIdNotFoundTest() {
        doReturn(Optional.empty()).when(repository).findById(1L);
        Optional<Producer> returnedProducer = service.findById(1L);

        Assertions.assertFalse(returnedProducer.isPresent(),
                "Worker shouldn't be found");
    }

    @Test
    @DisplayName("deleteById success test")
    void deleteByIdTest() {
        Producer producer = new Producer();
        producer.setId(1L);
        when(repository.findById(1L)).thenReturn(Optional.of(producer));
        service.deleteById(1L);
        verify(repository, times(1)).deleteById(1L);
    }
}