package org.aibles.worker2.controller;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.aibles.worker2.dto.WorkerDto;
import org.aibles.worker2.entity.Worker;
import org.aibles.worker2.service.WorkerService;
import org.aibles.worker2.util.WorkerSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/workers")
@Slf4j
public class WorkerController {
  private final WorkerService workerService;

  @Autowired
  public WorkerController(WorkerService workerService) {
    this.workerService = workerService;
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public String deleteById(@PathVariable("id") long workerId) {

    workerService.delete(workerId);
    return "Successful delete";
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public WorkerDto created(@RequestBody @Validated() WorkerDto workerDto) {
    return workerService.created(workerDto);
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
//  public List<Worker> List() {
//    return workerService.list();
//  }
  public List<Worker> list(WorkerSpecification workerSpecification, @Validated() final Pageable pageable) {
    return workerService.list(workerSpecification, pageable);
  }

//  @GetMapping("/search")
//  @ResponseStatus(HttpStatus.OK)
//  public List<Worker> searchForWorker(@RequestParam("query") String query) {
//    return workerService.searchWorkers(query);
//  }

  @PutMapping("{id}")
  @ResponseStatus(HttpStatus.OK)
  public WorkerDto update(
      @PathVariable("id") Long workerId, @RequestBody @Valid WorkerDto workerDto) {

    return workerService.update(workerId, workerDto);
  }
}
