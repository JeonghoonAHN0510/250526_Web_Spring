package Days.Day07;

import lombok.*;

/*
Lombok : 설계에서 자주 사용되는 코드를 자동 생성
1. Lombok 설치
    1) (환경) 인텔리제이 설치 -> 설정 > 플러그인 > Lombok
    2) (프로젝트) Gradle 설치 -> start.spring.io > Lombok
        compileOnly 'org.projectlombok:lombok'
        annotationProcessor 'org.projectlombok:lombok'
*/
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
// @NoArgsConstructor : 컴파일 시, 기본생성자 자동 생성
// @AllArgsConstructor : 컴파일 시, 전체생성자 자동 생성
// @Getter : 컴파일 시, 모든 멤버변수에 대해서 getter 메소드 자동 생성
// @Setter : 컴파일 시, 모든 멤버변수에 대해서 setter 메소드 자동 생성 / 생략 시, VO(읽기모드)
// @ToString : 객체 조회 시, 주소값 대신에 모든 멤버변수를 문자열로 출력하는 메소드 자동 생성
public class StudentDto {
    // 1. 멤버변수
    private int sno;            // 학번
    private String sname;       // 이름
    private int skor;           // 국어점수
    private int smath;          // 수학점수
    private String sdate;       // 등록일
    // 2. 생성자 : 기본생성자 / 전체생성자 -> 어노테이션을 통해 생성

    // 3. 메소드 : getter / setter / toString() -> 어노테이션을 통해 생성


} // class end
