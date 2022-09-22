package io.barogo.adjustment.persistence.adjustment.repository;

import io.barogo.adjustment.model.entity.adjustment.jpa.BatchExampleWriter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BatchExampleWriterRepository extends JpaRepository<BatchExampleWriter, Long> {

}
