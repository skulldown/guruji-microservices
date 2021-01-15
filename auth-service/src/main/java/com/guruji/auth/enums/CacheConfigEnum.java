package com.guruji.auth.enums;

public enum CacheConfigEnum {
  MAX_ENTRIES_LOCAL_HEAP("Max entries Heap", 1000L),
  TEN_SECOND_TO_LIVE("ten-second-cache", 10L),
  TEN_MINUTES_TO_LIVE("ten-minute-cache", 600L),
  TWENTY_MINUTES_TO_LIVE("twenty-minute-cache", 1200L);

  private String name;
  private long value;

  CacheConfigEnum(String name, long value) {
    this.name = name;
    this.value = value;
  }

  public String getName() {
    return name;
  }

  public long getValue() {
    return value;
  }
}