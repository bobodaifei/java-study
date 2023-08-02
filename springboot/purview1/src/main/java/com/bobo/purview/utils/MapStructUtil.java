package com.bobo.purview.utils;

import org.mapstruct.Named;

public class MapStructUtil {

  @Named("absolute")
  public static Integer absolute(Integer id) {
    if (id == null) {
      return null;
    }
    return Math.abs(id);
  }

  @Named("negation")
  public static Integer negation(Integer id) {
    if (id == null) {
      return null;
    }
    return -id;
  }

}
