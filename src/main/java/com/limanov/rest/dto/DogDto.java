package com.limanov.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class DogDto extends RepresentationModel<DogDto> implements Serializable {
  private String name;
  private int age;
  private int weight;
  private Long id;

}
