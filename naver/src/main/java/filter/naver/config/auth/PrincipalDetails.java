package filter.naver.config.auth;

import filter.naver.userEntity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

// 시큐리티가 login 페이지 잡아 오면 로그인을 진행시킨다
// 시큐리티는 세션을 만들고 Security ContextHolder 저장
//오브젝트 타입 => Authentication 객체 이 친구는 User 정보가 있어야한다.
//User 타입은 => UserDetails 이다


public class PrincipalDetails implements UserDetails {

    private User user;


    public PrincipalDetails(User useEntity) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> grantedAuthority= new ArrayList<>();
        grantedAuthority.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return user.getRole();
            }
        });

        return grantedAuthority;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {

        // 우리 사이트 1년 휴면 개정이면
        return true;
    }
}
