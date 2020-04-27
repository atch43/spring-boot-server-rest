package it.project.server.security;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

public class LogFilter extends OncePerRequestFilter {

  private int maxPayloadLength = 1000;

  private String getContentAsString(byte[] buf, int maxLength, String charsetName) {
    if (buf == null || buf.length == 0) return "";
    int length = Math.min(buf.length, this.maxPayloadLength);
    try {
      return new String(buf, 0, length, charsetName);
    } catch (UnsupportedEncodingException ex) {
      return "Unsupported Encoding";
    }
  }


  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    long startTime = System.currentTimeMillis();
    StringBuffer reqInfo = new StringBuffer()
     .append("[")
     .append(startTime % 10000)
     .append("] ")
     .append(request.getMethod())
     .append(" ")
     .append(request.getRequestURL());

    String queryString = request.getQueryString();
    if (queryString != null) {
      reqInfo.append("?").append(queryString);
    }

    ContentCachingRequestWrapper wrappedRequest = new ContentCachingRequestWrapper(request);
    ContentCachingResponseWrapper wrappedResponse = new ContentCachingResponseWrapper(response);

    filterChain.doFilter(wrappedRequest, wrappedResponse);   
    long duration = System.currentTimeMillis() - startTime;

    System.err.println(reqInfo + ": returned status=" + response.getStatus() + " in "+duration + "ms");
    String requestBody = this.getContentAsString(wrappedRequest.getContentAsByteArray(), this.maxPayloadLength, request.getCharacterEncoding());
    if (requestBody.length() > 0) {
      System.out.print(requestBody+"\r\n");
    }

    wrappedResponse.copyBodyToResponse();

  }


}