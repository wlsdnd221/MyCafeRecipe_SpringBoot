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
        return new MemberService(memberRepository, passwordEncoder());
    }

    /**
     * Spring Security 로그인처리
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .antMatchers("/", "/members/joinForm", "/menu/list", "/join/new", "/board/list", "/map", "/files/*", "/menu/detail", "/board/detail").permitAll() // 로그인없이도 사용가능한 페이지목록 설정
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/login") // 사용자 정의 로그인 페이지
                        .usernameParameter("id") // 아이디 변수명 설정
                        .passwordParameter("pw") // 비밀번호 변수명 설정
                        .loginProcessingUrl("/login") // 로그인 Form action url
                        .defaultSuccessUrl("/", true) // 로그인 성공 후 이동페이지(successHandler()를 사용한 로직구현이 더 좋은방식임)
                        .permitAll()
                )
                .logout((logout) -> logout.permitAll());

        return http.build();
    }

    // 로그인 아이디, 비밀번호, 권한을 DB에서 조회
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(passwordEncoder())
                .usersByUsernameQuery("select m.id, m.pw, m.enabled " //등록된 회원인지 확인
                        + "from member m "
                        + "where m.id = ?")
                .authoritiesByUsernameQuery("select m.id, r.name " //등록된 회원이면 권한을 확인
                        + "from member_role mr inner join member m on mr.member_num = m.num "
                        + "inner join role r on mr.role_num = r.num "
                        + "where m.id = ?");
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
