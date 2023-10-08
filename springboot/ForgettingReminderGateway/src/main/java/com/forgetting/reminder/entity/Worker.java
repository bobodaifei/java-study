package com.forgetting.reminder.entity;

import lombok.Data;
import lombok.ToString;

import java.util.Objects;

@Data
@ToString
public class Worker {

  private String id;

  private String name;


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Worker worker = (Worker) o;
    return Objects.equals(id, worker.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
