package org.voyo.utils.utils;

import java.util.Date;

public class UniqueId {
  private int index = 0;
  private Long preTime;
  public static UniqueId uniqueIdInstance =new UniqueId();

  public static synchronized Long getUId(){
    return uniqueIdInstance.getUniqueId();
  }

  private char getRandom() {
    return (char)YoMath.randomInt(48, 57);
  }

  private String getRandomStr(int count) {
    StringBuilder builder = new StringBuilder();

    while(count-- > 0) {
      builder.append(this.getRandom());
    }

    return builder.toString();
  }

  public Long getUniqueId() {
    Long nowTime = (new Date()).getTime();
    if (!nowTime.equals(this.preTime)) {
      this.index = 0;
      this.preTime = nowTime;
    } else {
      ++this.index;
    }

    String str = "" + nowTime + this.getRandomStr(3) + this.index;
    return Long.valueOf(str);
  }
}
