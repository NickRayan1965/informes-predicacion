package com.informes_predicacion.org.util;

public class StringUtils {
  /**
   * Returns null if the value is null or empty.
   * 
   * @param value
   * @return
   */
  public final static String optionalString(String value) 
  {
    if (value == null) {
      return value;
    }
    if (value.trim().isEmpty()) {
      return null;
    }
    return value;
  }
}
