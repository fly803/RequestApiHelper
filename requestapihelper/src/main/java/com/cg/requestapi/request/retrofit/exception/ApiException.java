package com.cg.requestapi.request.retrofit.exception;

import com.cg.requestapi.base.BaseResponse;

/** 
 * 
 * @author  sam
 * @date   2019/3/20
 * @version 1.0
 */ 
public class ApiException extends Exception {
  private int code;
  private String message;

  public ApiException(Throwable throwable, int code) {
    super(throwable);
    this.code = code;
  }

  public void setMessage(String message) {
    this.message = "ApiException:"+message;
  }

  @Override
  public String getMessage() {
    return message;
  }

  public int getCode() {
    return code;
  }

  public BaseResponse baseResult;

  public ApiException(String detailMessage) {
    super(detailMessage);
    setMessage(detailMessage);
  }

  public ApiException(BaseResponse detailMessage) {
    super(detailMessage.getMessage());
//      super(detailMessage.getMessage()+detailMessage.getMsg());
    this.baseResult = detailMessage;
  }
}
