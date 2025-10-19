package com.mangafy.api.infra.configuration;

import com.mangafy.api.infra.security.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Autowired
	private JwtFilter jwtFilter;

	@Autowired
	private UserDetailsService userDetailsService;

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf(AbstractHttpConfigurer::disable) // Desabilita CSRF com a nova API
				.headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin))
				.authorizeHttpRequests(auth -> auth
						// consoles
						.requestMatchers("/h2-console/**").permitAll()
						.requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html").permitAll()
						// ENDPOINTS DA APLICAÇÃO
						/* ENDPOITS PUBLICAS */
						.requestMatchers(HttpMethod.GET, "/publicacoes/**").permitAll()
						.requestMatchers(HttpMethod.POST, "/autores", "/auth/login", "/leitores").permitAll()
						// AVALIACOES - V
						.requestMatchers(HttpMethod.GET, "/avaliacoes/**").authenticated()
						.requestMatchers(HttpMethod.POST, "/avaliacoes")
						.hasAnyRole("LEITOR_ASSINANTE", "LEITOR_NAO_ASSINANTE")
						.requestMatchers(HttpMethod.PUT, "/avaliacoes/**")
						.hasAnyRole("LEITOR_ASSINANTE", "LEITOR_NAO_ASSINANTE")
						.requestMatchers(HttpMethod.DELETE, "/avaliacoes/**")
						.hasAnyRole("LEITOR_ASSINANTE", "LEITOR_NAO_ASSINANTE", "AUTOR")
						// AUTORES
						.requestMatchers(HttpMethod.GET, "/autores", "/autores/**").authenticated()
						.requestMatchers(HttpMethod.PUT, "/autores/**").hasRole("AUTOR")
						.requestMatchers(HttpMethod.DELETE, "/autores/**").hasRole("AUTOR")
						// COMENTARIOS - V
						.requestMatchers(HttpMethod.GET, "/comentarios/**").authenticated()
						.requestMatchers(HttpMethod.POST, "/comentarios")
						.hasAnyRole("LEITOR_ASSINANTE", "LEITOR_NAO_ASSINANTE")
						.requestMatchers(HttpMethod.PUT, "/comentarios/**")
						.hasAnyRole("LEITOR_ASSINANTE", "LEITOR_NAO_ASSINANTE")
						.requestMatchers(HttpMethod.DELETE, "/comentarios/**")
						.hasAnyRole("LEITOR_ASSINANTE", "LEITOR_NAO_ASSINANTE", "AUTOR")
						// CAPITULOS - V
						.requestMatchers(HttpMethod.GET, "/capitulos/**").authenticated()
						.requestMatchers(HttpMethod.POST, "/capitulos/**").hasRole("AUTOR")
						.requestMatchers(HttpMethod.DELETE, "/capitulos/**").hasRole("AUTOR")
						// GENEROS - V
						.requestMatchers(HttpMethod.PUT, "/generos").authenticated()
						// LEITOR
						.requestMatchers(HttpMethod.GET, "/leitores", "/leitores/**").authenticated()
						.requestMatchers(HttpMethod.PUT, "/leitores/**")
						.hasAnyRole("LEITOR_ASSINANTE", "LEITOR_NAO_ASSINANTE")
						.requestMatchers(HttpMethod.DELETE, "/leitores/**")
						.hasAnyRole("LEITOR_ASSINANTE", "LEITOR_NAO_ASSINANTE")
						// LIVROs
						.requestMatchers(HttpMethod.GET, "/livros", "/livros/**").authenticated()
						.requestMatchers(HttpMethod.POST, "/livros").hasRole("AUTOR")
						.requestMatchers(HttpMethod.PUT, "/livros/**").hasRole("AUTOR")
						.requestMatchers(HttpMethod.DELETE, "/livros/**").hasRole("AUTOR")
						// MANGAS - V
						.requestMatchers(HttpMethod.GET, "/mangas", "/mangas/**").authenticated()
						.requestMatchers(HttpMethod.POST, "/mangas").hasRole("AUTOR")
						.requestMatchers(HttpMethod.PUT, "/mangas/**").hasRole("AUTOR")
						.requestMatchers(HttpMethod.DELETE, "/mangas/**").hasRole("AUTOR"))
				.sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authenticationProvider(authProvider())
				.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

	@Bean
	AuthenticationProvider authProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(passwordEncoder());
		return provider;
	}

	@Bean
	AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
		return http.getSharedObject(AuthenticationManagerBuilder.class).authenticationProvider(authProvider()).build();
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
