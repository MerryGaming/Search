package org.aibles.worker2.service.impl;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.aibles.worker2.dto.WorkerDto;
import org.aibles.worker2.entity.Worker;
import org.aibles.worker2.exeption.ResourceNotFoundException;
import org.aibles.worker2.exeption.InternalServerException;
import org.aibles.worker2.repository.WorkerRepository;
import org.aibles.worker2.service.WorkerService;
import org.aibles.worker2.util.WorkerSpecification;
import org.modelmapper.ModelMapper;

import javax.transaction.Transactional;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Slf4j
public class WorkerServiceImpl implements WorkerService {
  private final WorkerRepository workerRepository;
  private final ModelMapper modelMapper;

  public WorkerServiceImpl(WorkerRepository workerRepository, ModelMapper modelMapper) {
    this.workerRepository = workerRepository;
    this.modelMapper = modelMapper;
  }
  /**
   * created worker
   *
   * @param workerDto
   * @return
   */
  @Override
  @Transactional
  public WorkerDto created(WorkerDto workerDto) {
    Worker worker = modelMapper.map(workerDto, Worker.class);
    Worker create = workerRepository.save(worker);
    Optional.ofNullable(create)
        .orElseThrow(
            () -> {
              throw new InternalServerException("Failse craeted!!! Try again");
            });
    WorkerDto workerDtoCreate = modelMapper.map(create, WorkerDto.class);
    log.info("(Create) Dto: {}", workerDtoCreate);
    return workerDtoCreate;
  }

  /**
   * delete worker
   *
   * @param id
   * @return
   */
  @Override
  @Transactional
  public void delete(Long id) {
    workerRepository.deleteById(id);
    log.info("Delete");
  }

  /**
   * created worker
   *
   * @return
   */
  @Override
  public List<Worker> list(WorkerSpecification workerSpecification, Pageable pageable) {
    List<Worker> listWorker = workerRepository
        .findAll((Sort) workerSpecification.toSpecification());
    log.info("(search) worker: {}", listWorker);
    return listWorker;
  }

  /**
   * search worker
   *
   * @param query
   * @return
   */
//  @Override
//  public List<Worker> searchWorkers(String query) {
//    List<Worker> workers = workerRepository.searchWorker(query);
//    return workers;
//  }

  /**
   * update worker
   *
   * @param id
   * @param workerDto
   * @return
   */
  @Override
  @Transactional
  public WorkerDto update(Long id, WorkerDto workerDto) {
    Worker workerCheck =
        workerRepository
            .findById(id)
            .orElseThrow(
                () -> {
                  throw new ResourceNotFoundException("Worker not found! ");
                });
    Worker worker = modelMapper.map(workerDto, Worker.class);
    worker.setId(workerCheck.getId());
    Worker update = workerRepository.save(worker);
    Optional.of(update)
        .orElseThrow(
            () -> {
              throw new InternalServerException("Update found, update again!!");
            });
    WorkerDto workerDtoUpdated = modelMapper.map(update, WorkerDto.class);
    log.info("(Update) worker update: {}, worker: {}", workerDtoUpdated, worker);
    return workerDtoUpdated;
  }
}
