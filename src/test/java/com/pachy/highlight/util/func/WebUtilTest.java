package com.pachy.highlight.util.func;

import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class WebUtilTest {

    @Test
    void getRemoteIP_singleHeader() {
        HttpServletRequest req = mock(HttpServletRequest.class);
        when(req.getHeader("X-FORWARDED-FOR")).thenReturn("203.0.113.42");

        String ip = WebUtil.getRemoteIP(req);
        assertEquals("203.0.113.42", ip);
    }

    @Test
    void getRemoteIP_multipleHeader_shouldTakeFirst() {
        HttpServletRequest req = mock(HttpServletRequest.class);
        when(req.getHeader("X-FORWARDED-FOR")).thenReturn("203.0.113.42, 198.51.100.17");

        String ip = WebUtil.getRemoteIP(req);
        assertEquals("203.0.113.42", ip);
    }

    @Test
    void getRemoteIP_noForwarded_fallbackToRemoteAddr() {
        HttpServletRequest req = mock(HttpServletRequest.class);
        when(req.getHeader("X-FORWARDED-FOR")).thenReturn(null);
        when(req.getHeader("Proxy-Client-IP")).thenReturn(null);
        when(req.getHeader("WL-Proxy-Client-IP")).thenReturn(null);
        when(req.getHeader("HTTP_X_FORWARDED_FOR")).thenReturn(null);
        when(req.getRemoteAddr()).thenReturn("127.0.0.1");

        String ip = WebUtil.getRemoteIP(req);
        assertEquals("127.0.0.1", ip);
    }
}