package org.voyo.utils.utils.url;

import lombok.Data;

import java.util.Map;

@Data
public class UrlNode {
  private String pureUrl;
  private Map<String,Object> query;
  private String tag;
}
