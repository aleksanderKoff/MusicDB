package fi.hh.swd20.MusicDB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import fi.hh.swd20.MusicDB.web.UserDetailServiceImpl;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter { // spring security config methods enabled
	@Override
	protected void configure(HttpSecurity http) throws Exception { // URL securing methods defined
		http
		.authorizeRequests().antMatchers("/css/**", "/login", "/h2-console/**").permitAll()
		.anyRequest().authenticated()
		.and()
		.csrf().ignoringAntMatchers("/h2-console/**")
		.and()
		.headers().frameOptions().sameOrigin()
		.and()
	   .formLogin()
	    .loginPage("/login")
	    .defaultSuccessUrl("/songlist", true)
	    .permitAll()
	    .and()
	   .logout()
	    .permitAll();
	}
	
	// UserDetailService class implementation so that the program can use it to create users
	@Autowired
	private UserDetailServiceImpl userDetailsService;
		
	// method to encrypt passwords with BCrypt algorithm
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
			auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}
	
}
