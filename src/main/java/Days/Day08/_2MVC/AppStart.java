package Days.Day08._2MVC;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppStart {
    public static void main(String[] args) {

        SpringApplication.run( AppStart.class );

    } // main end
} // class end

// [ MVC 패턴 흐름 ]
// JS -(JSON)-> Controller -(DTO)-> Service -(DTO)-> Dao -(SQL)-> DB -(SQL)-> Dao -(DTO)-> Service -(DTO)-> Controller -(JSON)-> JS