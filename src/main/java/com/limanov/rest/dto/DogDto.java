package com.limanov.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class DogDto implements Serializable {
  private String name;
  private int age;
  private int weight;
  private Long id;

}
