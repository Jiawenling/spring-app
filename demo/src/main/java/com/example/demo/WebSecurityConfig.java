package com.example.demo;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.demo.Service.UserDetailServiceImpl;

// import com.example.demo.Service.UserDetailServiceImpl;

@Configuration
@EnableWebSecurity(debug=false)
public class WebSecurityConfig  {

    @Autowired
    DataSource dataSource;

    @Autowired
    UserDetailServiceImpl userDetailServiceImpl;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.cors().and()
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**")).permitAll()
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/login", "/api/login").permitAll()
                        .requestMatchers("/api/manager").hasRole("MANAGER")
                        .requestMatchers("/css/**","/js/**","/images/**").permitAll()
                        .anyRequest().authenticated()
                )
                .headers(headers -> headers.frameOptions().disable())
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**")))
                        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                        .and()            
                        .userDetailsService(userDetailServiceImpl);
                
        http
                .csrf().disable();
        http
                .headers().frameOptions().disable();
        
        return http.build();
        
        // return http.authorizeHttpRequests()
        // .requestMatchers("/h2/**").permitAll()
        // .requestMatchers("/manager").hasRole("MANAGER")
        // .requestMatchers("/", "/login", "/logout", "/error").permitAll()
        // .and()
        // .csrf().ignoringRequestMatchers("/h2/**")
        // .and()
        // .headers().frameOptions().disable()
        // .and()
        // .anyRequest().authenticated()
        // .formLogin()
        // .defaultSuccessUrl("/profile")
        // .and()
        // .build();

            // (form) -> form
			// 	.loginPage("/login")
			// 	.permitAll()
			// .logout((logout) -> logout.permitAll());

	}

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.debug(true)
        .ignoring()
        .requestMatchers("/css/**", "/js/**", "/img/**", "/lib/**", "/favicon.ico", "/h2-console/**");
    }
    
}