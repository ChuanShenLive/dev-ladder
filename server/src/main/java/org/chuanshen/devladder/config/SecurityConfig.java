package org.chuanshen.devladder.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.chuanshen.devladder.filter.CustomUrlDecisionManager;
import org.chuanshen.devladder.filter.CustomFilterInvocationSecurityMetadataSource;
import org.chuanshen.devladder.model.Hr;
import org.chuanshen.devladder.model.RespBean;
import org.chuanshen.devladder.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Auther: Chuan Shen
 * @Date: 2020/4/25 17:28
 * @Description:
 **/
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserService userService;

    @Autowired
    CustomFilterInvocationSecurityMetadataSource customFilterInvocationSecurityMetadataSource;

    @Autowired
    CustomUrlDecisionManager customUrlDecisionManager;

    @Bean
    PasswordEncoder passwordEncoder () {
        // return PasswordEncoderFactories.createDelegatingPasswordEncoder();
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/login");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                        @Override
                        public <O extends FilterSecurityInterceptor> O postProcess(O object) {
                            object.setAccessDecisionManager(customUrlDecisionManager);
                            object.setSecurityMetadataSource(customFilterInvocationSecurityMetadataSource);
                            return object;
                        }
                    })
                    .anyRequest().authenticated()
                    .and()
                .formLogin()
                    .usernameParameter("username").passwordParameter("password").loginProcessingUrl("/doLogin").loginPage("/login")
                    .successHandler(new AuthenticationSuccessHandler() {
                        @Override
                        public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                            response.setContentType("application/json;charset=utf-8");
                            PrintWriter out = response.getWriter();
                            Hr hr = (Hr) authentication.getPrincipal();
                            hr.setPassword(null);
                            RespBean ok = RespBean.ok("登录成功!", hr);
                            String s = new ObjectMapper().writeValueAsString(ok);
                            out.write(s);
                            out.flush();
                            out.close();
                        }
                    })
                    .failureHandler(new AuthenticationFailureHandler() {
                        @Override
                        public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
                            response.setContentType("application/json;charset=utf-8");
                            PrintWriter out = response.getWriter();
                            RespBean respBean = RespBean.error("登录失败!");
                            if (exception instanceof LockedException) {
                                respBean.setMsg("账户被锁定, 请联系管理员!");
                            } else if (exception instanceof CredentialsExpiredException) {
                                respBean.setMsg("密码过期, 请联系管理员!");
                            } else if (exception instanceof AccountExpiredException) {
                                respBean.setMsg("账户过期, 请联系管理员");
                            } else if (exception instanceof DisabledException) {
                                respBean.setMsg("账户被禁用, 请联系管理员!");
                            } else if (exception instanceof BadCredentialsException) {
                                respBean.setMsg("用户名或者密码输入错误, 请重新输入!");
                            }
                            out.write(new ObjectMapper().writeValueAsString(respBean));
                            out.flush();
                            out.close();
                        }
                    })
                    .permitAll()
                    .and()
                .logout()
                    .logoutSuccessHandler(new LogoutSuccessHandler() {
                        @Override
                        public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                            response.setContentType("application/json;charset=utf-8");
                            PrintWriter out = response.getWriter();
                            out.write(new ObjectMapper().writeValueAsString(RespBean.ok("注销成功!")));
                            out.flush();
                            out.close();
                        }
                    })
                    .permitAll()
                    .and()
                .csrf().disable()
                .exceptionHandling().authenticationEntryPoint(new AuthenticationEntryPoint() {  // 没有认证时, 在此处处理, 不要重定向
                    @Override
                    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
                        response.setContentType("application/json;charset=utf-8");
                        PrintWriter out = response.getWriter();
                        RespBean respBean = RespBean.error("访问失败!");
                        if (authException instanceof InsufficientAuthenticationException) {
                            respBean.setMsg("非法请求失败, 请联系管理员!");
                        }
                        out.write(new ObjectMapper().writeValueAsString(respBean));
                        out.flush();
                        out.close();
                    }
                });
    }
}
