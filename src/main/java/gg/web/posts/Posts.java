package gg.web.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter // 클래스 내 모든 필드의 Getter 메소드 자동 생성
@NoArgsConstructor // 기본 생성자 자동 추가 = public Posts(){}
@Entity // 테이블과 링크될 클래스임을 나타냄. ex)SalesManager.java -> sales_manager table
public class Posts {

    @Id //PK 필드
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private Long id;

    //@Column을 선언하지 않더라도 해당 클래스의 필드는 모두 칼럼이 됨.
    @Column(length = 500, nullable = false) // 기본 값 외에 추가로 변경 필요한 옵션 있으면 사용
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder // 해당 클래스의 빌더 패턴 클래스를 생성. 생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함함
    public Posts(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;

    }

}
