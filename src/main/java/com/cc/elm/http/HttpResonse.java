package com.cc.elm.http;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HttpResonse {
   private String body;
   private Integer statusCode;

}
