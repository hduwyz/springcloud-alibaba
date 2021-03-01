package com.security.demo.config;

import com.security.demo.dao.UserDetailsRepository;
import com.security.demo.service.CaptchaCacheStorage;
import com.security.demo.service.CaptchaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@Configuration
public class SecurityConfig {

    private static final String SMS_CAPTCHA_CACHE = "captcha";

    @Bean
    public UserDetailsRepository userDetailsRepository(){
        UserDetailsRepository userDetailsRepository = new UserDetailsRepository();
        UserDetails account = User.withUsername("admin").password("admin").authorities(AuthorityUtils.NO_AUTHORITIES).build();
        userDetailsRepository.createUser(account);
        return userDetailsRepository;
    }

    @Bean
    public UserDetailsManager userDetailsManager(UserDetailsRepository userDetailsRepository){
        return new UserDetailsManager() {
            @Override
            public void createUser(UserDetails userDetails) {
                userDetailsRepository.createUser(userDetails);
            }

            @Override
            public void updateUser(UserDetails userDetails) {
                userDetailsRepository.updateUser(userDetails);
            }

            @Override
            public void deleteUser(String s) {
                userDetailsRepository.deleteUser(s);
            }

            @Override
            public void changePassword(String s, String s1) {
                userDetailsRepository.changePassword(s, s1);
            }

            @Override
            public boolean userExists(String s) {
                return userDetailsRepository.userExists(s);
            }

            @Override
            public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
                return userDetailsRepository.loadUserByUsername(s);
            }
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public CaptchaCacheStorage captchaCacheStorage(){
        return new CaptchaCacheStorage() {

            @CachePut(cacheNames = SMS_CAPTCHA_CACHE, key = "#phone")
            @Override
            public String put(String phone) {
                return UUID.randomUUID().toString();
            }

            @Cacheable(cacheNames = SMS_CAPTCHA_CACHE, key = "#phone")
            @Override
            public String get(String phone) {
                return null;
            }

            @CacheEvict(cacheNames = SMS_CAPTCHA_CACHE, key = "#phone")
            @Override
            public void expire(String phone) {

            }
        };
    }

    @Bean
    public CaptchaService captchaService(CaptchaCacheStorage captchaCacheStorage){
        return new CaptchaService() {
            @Override
            public boolean sendCaptcha(String phone) {
                String existed = captchaCacheStorage.get(phone);
                if (StringUtils.hasText(existed)){
                    return true;
                }
                String captchaCode = captchaCacheStorage.put(phone);
                log.info("captchaCode:{}",captchaCode);
                return true;

            }

            @Override
            public boolean verifyCaptcha(String phone, String code) {
                String cacheCode = captchaCacheStorage.get(phone);
                if (Objects.equals(cacheCode, code)){
                    captchaCacheStorage.expire(phone);
                    return true;
                }
                return false;
            }
        };
    }

    /**
     * 自行实现根据手机号查询可用的用户，这里简单举例.
     * 注意该接口可能出现多态。所以最好加上注解@Qualifier
     * @return the user details service
     */
    @Bean
    @Qualifier("captchaUserDetailsService")
    public UserDetailsService captchaUserDetailsService() {
        // 验证码登陆后密码无意义了但是需要填充一下
        return username -> User.withUsername(username).password("TEMP")
        //todo 这里权限 你需要自己注入
        .authorities(AuthorityUtils.createAuthorityList("ROLE_ADMIN", "ROLE_APP")).build();
    }

    /**
     * 验证码认证器
     * @param captchaService
     * @param userDetailsService
     * @return
     */
    @Bean
    public CaptchaAuthenticationProvider captchaAuthenticationProvider(CaptchaService captchaService,
                                                                       @Qualifier("captchaUserDetailsService") UserDetailsService userDetailsService) {
        return new CaptchaAuthenticationProvider(userDetailsService, captchaService);
    }

    /**
     * 验证码认证过滤器.
     * @param authenticationSuccessHandler the authentication success handler
     * @param authenticationFailureHandler the authentication failure handler
     * @param captchaAuthenticationProvider the captcha authentication provider
     * @return the captcha authentication filter */
    @Bean
    public CaptchaAuthenticationFilter captchaAuthenticationFilter(AuthenticationSuccessHandler authenticationSuccessHandler,
                                                                   AuthenticationFailureHandler authenticationFailureHandler,
                                                                   CaptchaAuthenticationProvider captchaAuthenticationProvider) {
        CaptchaAuthenticationFilter captchaAuthenticationFilter = new CaptchaAuthenticationFilter();
        // 配置 authenticationManager
        ProviderManager providerManager = new ProviderManager(Collections.singletonList(captchaAuthenticationProvider));
        captchaAuthenticationFilter.setAuthenticationManager(providerManager);
        // 成功处理器
        captchaAuthenticationFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
        // 失败处理器
        captchaAuthenticationFilter.setAuthenticationFailureHandler(authenticationFailureHandler);
        return captchaAuthenticationFilter;
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler(){
        return new AuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
                System.out.println("认证成功");
            }
        };
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler(){
        return new AuthenticationFailureHandler(){
            @Override
            public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
                System.out.println("认证失败");
            }
        };
    }
}
