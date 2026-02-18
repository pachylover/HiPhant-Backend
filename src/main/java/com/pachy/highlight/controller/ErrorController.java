package com.pachy.highlight.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pachy.highlight.dto.response.Response;


@Controller
public class ErrorController {
  
  @ResponseBody
  @RequestMapping("/error")
  public ResponseEntity<Response> defaultErrorResponse() {
    HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
    Response res = new Response(status);
    return ResponseEntity.ok(res);
  }
  
}
