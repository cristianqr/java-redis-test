package com.nadall.redistest.dtos;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MasterDetail implements Serializable {
  Long masterDetailId;
  String shortName;
}
