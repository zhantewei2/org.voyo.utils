package org.voyo.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.voyo.utils.HttpException.ReqBad;
import org.voyo.utils.HttpException.ReqBadEnum;
import org.voyo.utils.params.Result;
import org.voyo.utils.utils.YoObject;


public class App {
  public static void main(String[] args){
    Result<String> r1=new Result<>(200,"X");
    Result<String> r2=new Result<>(200,"X");
    YoObject.compareSame(r1,r2);
  }
}
