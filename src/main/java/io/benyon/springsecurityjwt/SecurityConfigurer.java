package io.benyon.springsecurityjwt;

import io.benyon.springsecurityjwt.filters.JwtRequestFilter;
import io.benyon.springsecurityjwt.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfigurer extends WebSecurityConfigurerAdapter {
  
  @Autowired
  private final MyUserDetailsService myUserDetailsService;
  
  @Autowired
  private JwtRequestFilter jwtRequestFilter;

  public SecurityConfigurer(MyUserDetailsService myUserDetailsService) {
    this.myUserDetailsService = myUserDetailsService;
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(myUserDetailsService);
  }
  
  @Override
  protected void configure(HttpSecurity http) throws Exception{
    http.csrf().disable().authorizeRequests().antMatchers("/admin").hasRole("ADMIN")
      .antMatchers("/user").hasAnyRole("ADMIN", "USER")
      .antMatchers("/")
      .permitAll()
      .and().formLogin();
//      .anyRequest().authenticated()
//      .and().sessionManagement()
//      .sessionCreationPolicy(SessionCreationPolicy.STATELESS); 
//    http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
  }
  
  @Override
  @Bean
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }
  
  @Bean
  public PasswordEncoder passwordEncoder(){
    return NoOpPasswordEncoder.getInstance();
  }
}
