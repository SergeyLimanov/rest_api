package com.limanov.rest.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DogDto implements Serializable {
  private String name;
  private int age;
  private int weight;

  public String getName() {
    return name;
  }

  public int getAge() {
    return age;
  }

  public int getWeight() {
    return weight;
  }
}
