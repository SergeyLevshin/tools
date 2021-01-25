package com.toolsapp.repository;

import com.toolsapp.models.instrument.Instrument;
import org.springframework.data.repository.CrudRepository;

public interface InstrumentRepository extends CrudRepository<Instrument, Long> {
}
