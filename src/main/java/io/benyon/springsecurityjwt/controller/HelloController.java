package io.benyon.springsecurityjwt.controller;

import io.benyon.springsecurityjwt.models.AuthenticateRequest;
import io.benyon.springsecurityjwt.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloController{
  
  @RequestMapping({ "/hello" })
  public String hello(){
    return "Hello World";
  }
  @Autowired
  private AuthenticationManager authenticationManager;
  
  @Autowired
  private UserDetailsService userDetailsService;
  
  @Autowired
  private JwtUtil jwtTokenUtil;
  
  @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
  public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticateRequest authenticateRequest) throws Exception {
    try{
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticateRequest.getUsername(), authenticateRequest.getPassword())
      );
    } catch (BadCredentialsException e){
      throw new Exception("Incorrect username or password", e);
    }
    final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticateRequest.getUsername());
    
    final String jwt = jwtTokenUtil.generateToken(userDetails);
    
    return ResponseEntity.ok(jwt);
  }
  
  
}
