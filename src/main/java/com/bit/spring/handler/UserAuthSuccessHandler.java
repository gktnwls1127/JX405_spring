package com.bit.spring.handler;

import com.bit.spring.model.UserCustomDetails;
import com.bit.spring.model.UserDTO;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Service
public class UserAuthSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        //System.out.println(authentication.getName());
        //System.out.println(authentication.getDetails());
        UserDTO userDTO = ((UserCustomDetails)authentication.getPrincipal()).getUserDTO();
        userDTO.setPassword(null);
        // 세션 정보 저장
        HttpSession session = httpServletRequest.getSession();
        session.setAttribute("logIn", userDTO);

        httpServletResponse.sendRedirect("/board/showAll/1");

        //System.out.println(authentication.getPrincipal());
    }
}
