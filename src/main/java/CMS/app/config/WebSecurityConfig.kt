package CMS.app.config

import CMS.app.service.impl.UserDetailsServiceImpl
import jakarta.annotation.Resource
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistration
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry





@Configuration
@EnableWebSecurity
class WebSecurityConfig (
    val userService: UserDetailsServiceImpl
        ){

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .authorizeHttpRequests { requests ->
                requests
                    .requestMatchers("/login").permitAll()
                    .requestMatchers("/uploads/**").permitAll()
                    .anyRequest().authenticated()
            }
            .formLogin { form ->
                form
                    .loginPage("/login")
                    .defaultSuccessUrl("/main", true)
                    .permitAll()
            }
            .logout { logout ->
                logout
                    .logoutSuccessUrl("/login?logout")
                    .permitAll()
            }
            .userDetailsService(userService)
        return http.build()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }
//
//    @Bean
//    fun addResourceHandlers(registry: ResourceHandlerRegistry): ResourceHandlerRegistration {
//        return registry
//            .addResourceHandler("/resources/**")
//            .addResourceLocations("/resources/")
//    }
}