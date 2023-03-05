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
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.demo.Service.UserDetailServiceImpl;

// import com.example.demo.Service.UserDetailServiceImpl;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig  {

    @Autowired
    DataSource dataSource;

    @Autowired
    UserDetailServiceImpl userDetailServiceImpl;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    // @Autowired
    // protected void configure(AuthenticationManagerBuilder auth) throws Exception{
    
    //     auth.jdbcAuthentication()
    //   .dataSource(dataSource)
    //   .usersByUsernameQuery("select name, password,'true' as enabled "
    //     + "from users "
    //     + "where username = ?")
    //   .authoritiesByUsernameQuery("select username, authority "
    //     + "from authorities "
    //     + "where username = ?").passwordEncoder(new BCryptPasswordEncoder(12));
    //     // UserDetails user = User
    //     //     .withUsername("user")
    //     //     .password(passwordEncoder().encode("password"))
    //     //     .roles("USER")
    //     //     .build();
    //     //     UserDetails manager = User
    //     //     .withUsername("manager")
    //     //     .password(passwordEncoder().encode("password"))
    //     //     .roles("MANAGER")
    //     //     .build();
    //     //     users.createUser(user);
    //     //     users.createUser(manager);
              
    // }
    



	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**")).permitAll()
                        .requestMatchers("/")
                        .permitAll()
                        .requestMatchers("/manager").hasRole("MANAGER")
                        .anyRequest().authenticated()
                )
                .headers(headers -> headers.frameOptions().disable())
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**")))
                .userDetailsService(userDetailServiceImpl)
                .formLogin(form -> form
                        .failureUrl("/login?error=true")
                        .permitAll())
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .permitAll());
                
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