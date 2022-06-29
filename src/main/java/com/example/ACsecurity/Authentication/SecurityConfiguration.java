package com.example.ACsecurity.Authentication;



import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.ACsecurity.Service.UserService;
import com.example.ACsecurity.model.Role;
import com.example.ACsecurity.model.User;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
// We will create userService class in upcoming step
   @Autowired
   private UserService userService;

   @Override
   protected void configure(HttpSecurity http) throws Exception {
       http
               .authorizeRequests()
                   .antMatchers(
                           "/registration**",
                           "/js/**",
                           "/css/**",
                           "/img/**",
                           "/webjars/**").permitAll()
                   .anyRequest().authenticated()
               .and()
                   .formLogin()
                       .loginPage("/login")
                           .permitAll()
               .and()
                   .logout()
                       .invalidateHttpSession(true)
                       .clearAuthentication(true)
                       .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                       .logoutSuccessUrl("/login?logout")
               .permitAll();
   }
   
   public String root(HttpSession session,Authentication authentication) {
       //return "index";
   	System.out.println("IN  MainController->root()");
   	System.out.println(">>>>>>>USER ="+authentication.getName());
   	User existing = userService.findByEmail(authentication.getName());
   	System.out.println("User firstName="+existing.getFirstName());
   	System.out.println("User lastName="+existing.getLastName());
   	System.out.println("User Id="+existing.getId());
   	
		System.out.println("USER ROLE="+existing.getRoles());
   	
       // IN DB: update role set name = "ROLE_SUPER" where id = 12;
		java.util.Collection<Role> roles = existing.getRoles();
		String userRole = roles.toString();
		System.out.println("COLLECTION USER ROLE="+userRole);
		
		if(userRole.equals("[ROLE_SUPER]")) {
			return "redirect:/admin/pages";
		}
		
		return "redirect:/home";
   }

   @Bean
   public BCryptPasswordEncoder passwordEncoder(){
       return new BCryptPasswordEncoder();
   }

   @Bean
   public DaoAuthenticationProvider authenticationProvider(){
       DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
       auth.setUserDetailsService(userService);
       auth.setPasswordEncoder(passwordEncoder());
       return auth;
   }

   @Override
   protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       auth.authenticationProvider(authenticationProvider());
   }

}


