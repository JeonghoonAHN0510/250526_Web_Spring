package Days.Day08._1의존성;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// [ 방법1 ] : new를 통해 객체 생성 후, 메소드 호출
class SampleDao1 { void method1(){} }
class SampleController1 {
    SampleDao1 sampleDao1 =  new SampleDao1();
    public void method2(){
        sampleDao1.method1();
    } // func end
} // class end

// [ 방법2 ] : 해당 객체를 미리 생성하여, 메소드 호출
class SampleDao2 {
    // 싱글톤을 생성하여, 미리 객체 만들어놓기
    private SampleDao2(){}
    private static final SampleDao2 instance = new SampleDao2();
    public static SampleDao2 getInstance() {
        return instance;
    } // func end
    public void method1(){}
} // class end
class SampleController2 {
    // 싱글톤 가져오기
    private SampleDao2 sampleDao2 = SampleDao2.getInstance();
    public void method2(){
        sampleDao2.method1();
    } // func end
} // class end

// [ 방법3 ] : IOC 와 DI 사용하기
@Component
// @Repository : @Component(스프링 컨테이너에 bean 등록)
class SampleDao3 {
    public void method1(){}
} // class end

    // [ DI 방법1 ]
class SampleController3 {
    // @Autowired : 스프링 컨테이너에 등록된 bean을 꺼내서 멤버변수에 주입
    @Autowired
    private SampleDao3 sampleDao3;

    public void method2(){
        sampleDao3.method1();
    } // func end
} // class end

    // [ DI 방법2 ] : Spring 공식 권장
class SampleController4 {
    private final SampleDao3 sampleDao3;
    // final을 사용함으로써, 불변성(안전) 보장
    @Autowired
    // @Autowired : 생성자에 bean을 주입하여 사용
    public SampleController4( SampleDao3 sampleDao3 ) {
        this.sampleDao3 = sampleDao3;
    } // func end

    public void method2(){
        sampleDao3.method1();
    } // func end
} // class end


public class Dependency {

} // class end
