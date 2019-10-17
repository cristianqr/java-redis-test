package com.nadall.redistest.dtos;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Person implements Serializable {
  Long personId;
  String firstName;
  String firstSurname;
  String lastSurname;
  MasterDetail document;
}
