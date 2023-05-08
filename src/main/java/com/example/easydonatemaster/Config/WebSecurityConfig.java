package com.example.easydonatemaster.Config;

import com.example.easydonatemaster.JWT.AuthEntryPointJwt;
import com.example.easydonatemaster.JWT.AuthTokenFilter;
import com.example.easydonatemaster.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
  @Autowired
  UserDetailsServiceImpl userDetailsService;

  @Autowired
  private AuthEntryPointJwt unauthorizedHandler;

  @Bean
  public AuthTokenFilter authenticationJwtTokenFilter() {
    return new AuthTokenFilter();
  }

  @Override
  public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
    authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
  }

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.cors().and().csrf().disable()
            .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
            .authorizeRequests().antMatchers( ).permitAll()

            .antMatchers("/auth/signup","/auth/signin","/auth/verification","/auth/forgotPassword","/auth/resetPassword","http://localhost:8188/SpringMVC/auth/signin","**","http://localhost:8188/SpringMVC/auth/verification").permitAll()


            .antMatchers("/auth/signup","/auth/signin","/auth/verification","/auth/forgotPassword","/auth/resetPassword","http://localhost:8188/SpringMVC/auth/signin","http://localhost:8188/SpringMVC/auth/verification","**").permitAll()

            .antMatchers("/auth/signup","/auth/signin","/auth/verification","/auth/forgotPassword","/auth/resetPassword","http://localhost:8188/SpringMVC/auth/signin","**","http://localhost:8188/SpringMVC/auth/verification").permitAll()

            .antMatchers("/auth/signup","/auth/signin","/auth/verification","/auth/forgotPassword","/auth/resetPassword","http://localhost:8188/SpringMVC/auth/signin","http://localhost:8188/SpringMVC/auth/verification","**").permitAll()



            .antMatchers().permitAll()
            .antMatchers("/test/**").permitAll()
            .anyRequest().authenticated();

    http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
  }
}