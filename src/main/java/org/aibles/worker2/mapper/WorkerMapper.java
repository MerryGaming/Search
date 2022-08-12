package org.aibles.worker2.mapper;

import org.aibles.worker2.entity.Worker;
import org.modelmapper.ModelMapper;

public class WorkerMapper {
  private static ModelMapper modelMapper ;

  public WorkerMapper(ModelMapper modelMapper) {
    this.modelMapper = modelMapper;
  }

  public static Worker mapToDto(Worker worker) {
    return modelMapper.map( worker, Worker.class);
  }

}
