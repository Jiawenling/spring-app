package com.example.demo;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig  {

    @Autowired
    DataSource dataSource;
    
    @Bean
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
    
        auth.jdbcAuthentication()
      .dataSource(dataSource)
      .usersByUsernameQuery("select name,password"
        + "from users "
        + "where username = ?")
      .authoritiesByUsernameQuery("select username,authority "
        + "from authorities "
        + "where username = ?");
        // UserDetails user = User
        //     .withUsername("user")
        //     .password(passwordEncoder().encode("password"))
        //     .roles("USER")
        //     .build();
        //     UserDetails manager = User
        //     .withUsername("manager")
        //     .password(passwordEncoder().encode("password"))
        //     .roles("MANAGER")
        //     .build();
        //     users.createUser(user);
        //     users.createUser(manager);
              
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }



	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
            .csrf().disable()
			.authorizeHttpRequests((requests) -> requests
            .requestMatchers("/manager").hasRole("MANAGER")
				.requestMatchers("/").permitAll()
                .requestMatchers("/", "/login", "/logout", "/error").permitAll()
				.anyRequest().authenticated()
                )
			.formLogin(
			).defaultSuccessUrl("/profile");
            // (form) -> form
			// 	.loginPage("/login")
			// 	.permitAll()
			// .logout((logout) -> logout.permitAll());

		return http.build();
	}

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.debug(true)
        .ignoring()
        .requestMatchers("/css/**", "/js/**", "/img/**", "/lib/**", "/favicon.ico");
    }
}