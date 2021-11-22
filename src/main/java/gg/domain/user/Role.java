package gg.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    
    GUEST("ROLE_GUEST", "손님"), // 스프링 시큐리티에서는 권한코드에 항상 ROLE_ 이 앞에 있어야만함
    USER("ROLE_USER", "일반사용자");
    
    private final String key;
    private final String title;

}
