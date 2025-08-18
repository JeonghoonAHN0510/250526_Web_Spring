package Days.Day02._1Annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 상위(부모) 클래스
class SuperClass{
    public void method1(){}
} // class end

// 하위(자식) 클래스
class SubClass extends SuperClass{
    // extends란? SubClass가 SuperClass 로부터 상속받는다.
    // 상속이란? 부모클래스에게 멤버변수/메소드를 물려받는 행위
    // 오버라이딩이란? 부모클래스에게 물려받은 메소드를 재정의하는 행위
    // @ : 어노테이션
    @Override   // 상위클래스로부터 메소드를 재정의할 때 사용
    public void method1(){}
    @Deprecated // 해당 함수를 더이상 사용하지 않음을 알릴 때 사용, 실행은 가능하다.
    public void method2(){}
} // class end

// [2] 어노테이션 생성
@Retention( RetentionPolicy.RUNTIME )   // 런타임(실행중)까지 유지
@Target( ElementType.METHOD )           // 해당 어노테이션을 메소드에 적용
@interface Annotation1{
    // 추상메소드 : 구현부가 없는 메소드
    String value1();
}
// [3] 어노테이션 사용
class TestClass1{
    @Annotation1( value1 = "어노테이션 주입" )
    // 내가 만든 어노테이션을 아래 코드에 런타임때 주입
    public void method3(){
        // 런타임할 때 사용하므로, 직접적인 코드를 주입받는 것이 아니다.
        // -> 프로그램 실행 시, method3이 실행되면, value1 메소드도 같이 실행된다.
    } // func end
} // class end

public class Example1 {
    public static void main(String[] args) {
        // [1] 표준 어노테이션
        SubClass subClass = new SubClass();
        subClass.method1();     // @Override
        subClass.method2();     // @Deprecated
    } // main end
} // class end
