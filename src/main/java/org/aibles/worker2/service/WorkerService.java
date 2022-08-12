package org.aibles.worker2.service;

import java.util.List;
import org.aibles.worker2.dto.WorkerDto;
import org.aibles.worker2.entity.Worker;
import org.aibles.worker2.util.WorkerSpecification;
import org.springframework.data.domain.Pageable;

public interface WorkerService {
    WorkerDto created (WorkerDto workerDto);
    void delete(Long id);
    //Page<Worker> list();

    List<Worker> list(WorkerSpecification workerSpecification, Pageable pageable);

    //List<Worker> searchWorkers(String query);
    WorkerDto update (Long id, WorkerDto workerDto);
}
