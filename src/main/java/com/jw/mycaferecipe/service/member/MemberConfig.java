package com.jw.mycaferecipe.service.member;

import com.jw.mycaferecipe.repository.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class MemberConfig {

    private final MemberRepository memberRepository;
    private DataSource dataSource;

    @Autowired
    public MemberConfig(MemberRepository memberRepository, DataSource dataSource) {
        this.memberRepository = memberRepository;
        this.dataSource = dataSource;
    }

//    @Bean
//    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//        return new JpaMemberRepository(em);
//    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }

    //Spring Security 로그인처리
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .antMatchers("/", "/members/joinForm", "/menu/list", "/join/new", "/board/list", "/map").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .permitAll()
                )
                .logout((logout) -> logout.permitAll());

        return http.build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(passwordEncoder())
                .usersByUsernameQuery("select id, pw, enabled "
                        + "from member "
                        + "where id = ?")
                .authoritiesByUsernameQuery("select m.id, r.name " //ManyToMany 맵핑
                        + "from member_role mr inner join member m on mr.member_num = m.num "
                        + "inner join role r on mr.role_num = r.num "
                        + "where m.id = ?");
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
