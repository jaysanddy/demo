package com.vk.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by wj on 18-4-17.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserService customUserService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //super.configure(auth);
//        auth.inMemoryAuthentication().withUser("lzhb").password("123456").roles("USER")
//                .and().withUser("admin").password("admin").roles("ADMIN");
        auth.userDetailsService(customUserService).passwordEncoder(new MyPasswordEncoder());

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //super.configure(http);
//        http.authorizeRequests()
//                .antMatchers("/area/*").permitAll()
//                .and().formLogin().loginPage("/login").failureUrl("/login?error").permitAll()
//                .and()
//                .logout()
//                .permitAll()
//                .and()
//                .formLogin();
//        http.csrf().disable();
        http.authorizeRequests()
                .mvcMatchers("/css/**","/fonts/**","/img/**","/js/**","/**/*_allow","/upload/**","/uploadFile/**","/dld/**").permitAll()
                .anyRequest().authenticated()
                .and().formLogin().loginPage("/login")
                //设置默认登录成功跳转页面
                .defaultSuccessUrl("/index").failureUrl("/login?error=true").permitAll()
                .and()
                .headers().frameOptions().disable()
                .and()
                //开启cookie保存用户数据
                .rememberMe()
                //设置cookie有效期
                .tokenValiditySeconds(60 * 60 * 24 * 7)
                //设置cookie的私钥
                .key("cf7f09f99b2f8bc75971e090b56f8799")
                .and()
                .csrf().disable()
                .logout()
                //默认注销行为为logout，可以通过下面的方式来修改
//                .logoutUrl("/custom-logout")
                //设置注销成功后跳转页面，默认是跳转到登录页面
                .logoutSuccessUrl("/login")
                .permitAll()
                .invalidateHttpSession(true)
                .and()
                .rememberMe()//登录后记住用户，下次自动登录,数据库中必须存在名为persistent_logins的表
                .tokenValiditySeconds(24*60*60);
    }
}
