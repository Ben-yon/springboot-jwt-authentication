package io.benyon.springsecurityjwt.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloController{
  
  @RequestMapping({ "/hello" })
  public String hello(){
    return "Hello World";
  }
  
  @GetMapping("/user")
  public String user(){
    return ("<h1>Welcome User</h1>");
  }
  
  @GetMapping("/admin")
  public String admin(){
    return ("<h1>Welcome Admin</h1>");
  }
//  @Autowired
//  private AuthenticationManager authenticationManager;
//  
//  @Autowired
//  private UserDetailsService userDetailsService;
//  
//  @Autowired
//  private JwtUtil jwtTokenUtil;
  
//  @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
//  public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticateRequest authenticateRequest) throws Exception {
//    try{
//      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticateRequest.getUsername(), authenticateRequest.getPassword())
//      );
//    } catch (BadCredentialsException e){
//      throw new Exception("Incorrect username or password", e);
//    }
//    final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticateRequest.getUsername());
//    
//    final String jwt = jwtTokenUtil.generateToken(userDetails);
//    
//    return ResponseEntity.ok(jwt);
//  }
  
  
}
