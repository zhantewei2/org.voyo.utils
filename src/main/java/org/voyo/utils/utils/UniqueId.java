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

    String randomStr= this.index<100? this.getRandomStr(3):
              this.index<1000? this.getRandomStr(2): this.getRandomStr(1);

    return Long.valueOf(
         nowTime+YoStr.padsLeft(2,String.valueOf(this.index),(char)48) + randomStr
    );

  }

}