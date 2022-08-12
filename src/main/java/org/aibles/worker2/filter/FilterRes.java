package org.aibles.worker2.filter;

import java.util.List;

import lombok.Data;
import org.springframework.data.domain.Page;

@Data
public class FilterRes<T> {
  private List<T> pageData;
  private int pageNo;
  private int pageSize;
  private long total;

  protected FilterRes(Page<T> page) {
    this.pageData = page.getContent();
    this.total = page.getTotalElements();
    this.pageNo = page.getNumber() + 1;
    this.pageSize = page.getSize();
  }

  public static <H> FilterRes<H> of(Page<H> page) {
    return new FilterRes<>(page);
  }
}
