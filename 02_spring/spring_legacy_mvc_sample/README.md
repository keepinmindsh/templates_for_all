# 기본 프로젝트 생성 

##### Idea 를 이용한 기본 프로젝트 생성 ( Pom.xml 바탕으로 작성 및 구성 )

# Framework Support 추가 하기 

![Framework Support Image](https://github.com/keepinmindsh/templates_for_all/blob/main/02_spring/spring_legacy_mvc_sample/assets/0001_addframework.png)

##### 생성된 Package 구조 ( 아래 이미지 참조 )
![Package Structure](https://github.com/keepinmindsh/templates_for_all/blob/main/02_spring/spring_legacy_mvc_sample/assets/0002_packagestructure.png)

##### Dependency 추가 

Pom.xml에서 ⌘ + N 을 클릭하면 아래와 같이 Add Dependency 가 가능함.

![Package Structure](https://github.com/keepinmindsh/templates_for_all/blob/main/02_spring/spring_legacy_mvc_sample/assets/0003_addDependency.png)

##### Dependency 정리 

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>

  <groupId>org.example</groupId>
  <artifactId>spring_legacy_mvc_sample</artifactId>
  <version>1.0-SNAPSHOT</version>

  <properties>
    <java-version>1.8</java-version>
    <org.springframework-version>5.2.3.RELEASE</org.springframework-version>
    <org.aspectj-version>1.6.10</org.aspectj-version>
    <org.slf4j-version>1.6.6</org.slf4j-version>
  </properties>

  <dependencies>

    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-context</artifactId>
      <version>${org.springframework-version}</version>
      <exclusions>
        <exclusion>
          <groupId>commons-logging</groupId>
          <artifactId>commons-logging</artifactId>
        </exclusion>
      </exclusions>
    </dependency>

    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-webmvc</artifactId>
      <version>${org.springframework-version}</version>
    </dependency>

    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-expression</artifactId>
      <version>${org.springframework-version}</version>
    </dependency>

    <!-- https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind -->
    <dependency>
      <groupId>com.fasterxml.jackson.core</groupId>
      <artifactId>jackson-databind</artifactId>
      <version>2.13.3</version>
    </dependency>


    <dependency>
      <groupId>org.aspectj</groupId>
      <artifactId>aspectjrt</artifactId>
      <version>${org.aspectj-version}</version>
    </dependency>

    <dependency>
      <groupId>org.slf4j</groupId>
      <artifactId>slf4j-api</artifactId>
      <version>${org.slf4j-version}</version>
    </dependency>

    <dependency>
      <groupId>org.slf4j</groupId>
      <artifactId>jcl-over-slf4j</artifactId>
      <version>${org.slf4j-version}</version>
    </dependency>

    <dependency>
      <groupId>org.slf4j</groupId>
      <artifactId>slf4j-log4j12</artifactId>
      <version>${org.slf4j-version}</version>
    </dependency>

    <dependency>
      <groupId>log4j</groupId>
      <artifactId>log4j</artifactId>
      <version>1.2.15</version>
      <exclusions>
        <exclusion>
          <groupId>javax.mail</groupId>
          <artifactId>mail</artifactId>
        </exclusion>
        <exclusion>
          <groupId>javax.jms</groupId>
          <artifactId>jms</artifactId>
        </exclusion>
        <exclusion>
          <groupId>com.sun.jdmk</groupId>
          <artifactId>jmxtools</artifactId>
        </exclusion>
        <exclusion>
          <groupId>com.sun.jmx</groupId>
          <artifactId>jmxri</artifactId>
        </exclusion>
      </exclusions>
      <scope>runtime</scope>
    </dependency>

    <dependency>
      <groupId>javax.inject</groupId>
      <artifactId>javax.inject</artifactId>
      <version>1</version>
    </dependency>

    <dependency>
      <groupId>javax.servlet</groupId>
      <artifactId>servlet-api</artifactId>
      <version>2.5</version>
      <scope>provided</scope>
    </dependency>

    <dependency>
      <groupId>javax.servlet.jsp</groupId>
      <artifactId>jsp-api</artifactId>
      <version>2.1</version>
      <scope>provided</scope>
    </dependency>

    <dependency>
      <groupId>javax.servlet</groupId>
      <artifactId>jstl</artifactId>
      <version>1.2</version>
    </dependency>

    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>4.7</version>
      <scope>test</scope>
    </dependency>

    <!-- https://mvnrepository.com/artifact/org.apache.maven.plugins/maven-eclipse-plugin -->
    <dependency>
      <groupId>org.apache.maven.plugins</groupId>
      <artifactId>maven-eclipse-plugin</artifactId>
      <version>2.9</version>
    </dependency>

  </dependencies>

  <build>
    <plugins>
      <plugin>
        <artifactId>maven-eclipse-plugin</artifactId>
        <version>2.9</version>
        <configuration>
          <additionalProjectnatures>
            <projectnature>org.springframework.ide.eclipse.core.springnature</projectnature>
          </additionalProjectnatures>
          <additionalBuildcommands>
            <buildcommand>org.springframework.ide.eclipse.core.springbuilder</buildcommand>
          </additionalBuildcommands>
          <downloadSources>true</downloadSources>
          <downloadJavadocs>true</downloadJavadocs>
        </configuration>
      </plugin>
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-compiler-plugin</artifactId>
        <version>2.5.1</version>
        <configuration>
          <source>8</source>
          <target>8</target>
          <compilerArgument>-Xlint:all</compilerArgument>
          <showWarnings>true</showWarnings>
          <showDeprecation>true</showDeprecation>
        </configuration>
      </plugin>
      <plugin>
        <groupId>org.codehaus.mojo</groupId>
        <artifactId>exec-maven-plugin</artifactId>
        <version>1.2.1</version>
        <configuration>
          <mainClass></mainClass>
        </configuration>
      </plugin>
    </plugins>
  </build>

</project>

```

# Project Structure 설정하기 

- Modules 의 설정 확인 및 점검 

# Project XML 설정 

##### Dispatcher Servlet 설정 

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/mvc
       https://www.springframework.org/schema/mvc/spring-mvc.xsd
       http://www.springframework.org/schema/context
       https://www.springframework.org/schema/context/spring-context.xsd">

    <mvc:annotation-driven/>

    <context:annotation-config/>

    <context:component-scan base-package="bong.lines" />

    <mvc:resources mapping="/res/**" location="/static/" />

    <!-- 사용할 파일명을 완성해주는 객체 -->
    <bean id="viewResolver" class="org.springframework.web.servlet.view.InternalResourceViewResolver">
        <property name="prefix" value="/WEB-INF/views/" />
        <property name="suffix" value=".jsp" />
    </bean>

    <!--pom.xml에서 라이브러리 설치하고 여기서 bean등록을 해야지 쓸 수 있다-->
    <bean class="org.springframework.http.converter.json.MappingJackson2HttpMessageConverter"/>

</beans>

```

- <mvc:annotation-driven />
  - Spring MVC 컴포넌트들을 디폴트 설정을 통해 활성화한다.
  - Spring MVC @Controller에 요청을 보내기 위해 필요한 HandlerMapping과 HandlerAdapter를 Bean으로 등록한다.
    - HandlerMapping : HTTP 요청정보를 이용해서 컨트롤러를 찾아주는 기능
    - HandlerAdapter : HandlerMapping을 통해 찾은 컨트롤러를 직접 실행하는 기능을 수행
  - Bean을 생성하기 위해 xml 파일에 context:component-scan을 명시하면 이 태그를 포함하지 않아도 MVC 어플리케이션을 작동한다.
- <context:annotation-config />
  - ApplicationContext 안에 이미 등록된 Bean 들의 Annotation 을 활성화하기 위해 사용된다. 
  - Component-scan 과의 차이점은 이 설정은 Bean 을 등록하는 작업을 수행하지 않는다는 것이다.
- <context:component-scan base-package="bong.lines" />
  - 특정 패키지 내의 클래스를 스캔하고 Annotation(@Component @Controller @Service @Repository)을 확인한 후 Bean 인스턴스로 생성한다. 
  - 이를 이용하면 @Autowired 와 @Qualifier Annotation 을 인식할 수 있다. 
  - context:component-scan 을 선언했다면 context:annotation-config 를 선언할 필요가 없다.
- <mvc:resources mapping="/res/**" location="/static/" />
  - **는 Depth 와 상관 없이 접근할 수 있다는 의미     
  - web.xml <servlet-mapping>이 /로 시작하는 모든 요청을 <servlet> 의 dispatcher 가 다 받아서 handlermapping 이 그 요청을 받아주는 controller 가 있는지 물어보고 매핑시키는 것  
  - css 나 js나 image 는 controller 필요 없다    
  - /res/로 시작하는 요청이 들어오면 dispatcher 가 관여하지 않고 얘가 담당해서 "webapp"에 /static/ 폴더 로 연결시키겠다  

# Log4J 설정 

Log4J 기본적인 설정은 아래와 같이 할 수 있다. 

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE log4j:configuration PUBLIC "-//APACHE//DTD LOG4J 1.2//EN" "log4j.dtd">
<log4j:configuration xmlns:log4j="http://jakarta.apache.org/log4j/" >
    
    <appender name="console" class="org.apache.log4j.ConsoleAppender">
        <param name="Target" value="System.out"/>
        <layout class="org.apache.log4j.PatternLayout">
            <param name="ConversionPattern" value="%-5p: %c - %m%n" />
        </layout>
    </appender>
    
    <logger name="bong.lines" >
        <level value="info" />
    </logger>

    <logger name="org.springframework.core" >
        <level value="info" />
    </logger>

    <logger name="org.springframework.beans" >
        <level value="info" />
    </logger>

    <logger name="org.springframework.context" >
        <level value="info" />
    </logger>

    <logger name="org.springframework.web" >
        <level value="info" />
    </logger>
    
    <root>
        <priority value="warn" />
        <appender-ref ref="console"/>
    </root>
    
    
</log4j:configuration>

```

# Web.xml 설정 

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
         version="4.0"
        >

    <absolute-ordering />

    <context-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>/WEB-INF/spring/applicationContext.xml</param-value>
    </context-param>

    <listener>
        <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
    </listener>
    
    <servlet>
        <servlet-name>dispatcher</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <init-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>/WEB-INF/spring/appServlet/dispatcher-servlet.xml</param-value>
        </init-param>`
        <load-on-startup>1</load-on-startup>
    </servlet>

    <servlet-mapping>
        <servlet-name>dispatcher</servlet-name>
        <url-pattern>/</url-pattern>
    </servlet-mapping>
</web-app>
```

# Tomcat 설정하기 

##### Configuration 의 Service 추가 하기 

![Tomcat Setting](https://github.com/keepinmindsh/templates_for_all/blob/main/02_spring/spring_legacy_mvc_sample/assets/0004_tomcatSetting_001.png)

##### Tomcat Deploy 설정 

![Deploy Setting](https://github.com/keepinmindsh/templates_for_all/blob/main/02_spring/spring_legacy_mvc_sample/assets/0004_tomcatSetting_002.png)

##### Tomcat Server 설정

![Tomcat Server Setting](https://github.com/keepinmindsh/templates_for_all/blob/main/02_spring/spring_legacy_mvc_sample/assets/0004_tomcatSetting_003.png)

##### Maven Library 추가 

Project Structure 에서의 Artifact 에 아래와 같이 우측의 Element 요소에서 WEB-INF/lib 로 library를 추가해야한다. 

![Tomcat Server Setting](https://github.com/keepinmindsh/templates_for_all/blob/main/02_spring/spring_legacy_mvc_sample/assets/0004_tomcatSetting_004.png)


# Hello World 

[Controller Mapping](https://docs.spring.io/spring-framework/docs/3.0.0.M4/spring-framework-reference/html/ch15s03.html)

```java
package bong.lines;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloWorldController {

    @GetMapping("/hello")
    public String helloWorld(){
        return "helloworld";
    }
}
```

# Spring Dispatcher Servlet 

![Tomcat Server Setting](https://github.com/keepinmindsh/templates_for_all/blob/main/02_spring/spring_legacy_mvc_sample/assets/0005_dispatcher_servlet.png)

- [Common Configuration](https://docs.spring.io/spring-framework/docs/3.0.0.M4/spring-framework-reference/html/ch17s02.html)
- [Handler Mapping](https://docs.spring.io/spring-framework/docs/3.0.0.M4/spring-framework-reference/html/ch15s04.html)
- [Resolving Views](https://docs.spring.io/spring-framework/docs/3.0.0.M4/spring-framework-reference/html/ch15s05.html)
- [MultiPart Upload](https://docs.spring.io/spring-framework/docs/3.0.0.M4/spring-framework-reference/html/ch15s08.html)
- [Handling Exceptions](https://docs.spring.io/spring-framework/docs/3.0.0.M4/spring-framework-reference/html/ch15s09.html)
- [JSP, JSTL](https://docs.spring.io/spring-framework/docs/3.0.0.M4/spring-framework-reference/html/ch16s02.html)
- [Document View](https://docs.spring.io/spring-framework/docs/3.0.0.M4/spring-framework-reference/html/ch16s06.html)
- [Jasper Report View](https://docs.spring.io/spring-framework/docs/3.0.0.M4/spring-framework-reference/html/ch16s07.html)

> [Spring Legacy Sample](https://readinggeneral.tistory.com/entry/Spring-Study-Group-Spring-Legacy-Project-%EC%83%9D%EC%84%B1%ED%95%98%EA%B8%B0-IntelliJ-IDEA?category=1013715)

> https://yjh5369.tistory.com/entry/Tomcat-%EC%8B%A4%ED%96%89-%EC%8B%9C-%EB%91%98-%EC%9D%B4%EC%83%81%EC%9D%98-fragment%EB%93%A4%EC%9D%B4-%EB%B0%9C%EA%B2%AC%EB%90%98%EC%97%88%EC%8A%B5%EB%8B%88%EB%8B%A4-%EC%9D%B4%EB%8A%94-%EC%83%81%EB%8C%80%EC%A0%81-%EC%88%9C%EC%84%9C%EB%B0%B0%EC%97%B4%EC%97%90%EC%84%9C-%EB%B6%88%ED%97%88%EB%90%A9%EB%8B%88%EB%8B%A4-%EC%97%90%EB%9F%AC-%EB%B0%9C%EC%83%9D  
> https://blog.naver.com/PostView.nhn?isHttpsRedirect=true&blogId=webwizard83&logNo=221920497267&parentCategoryNo=18&categoryNo=&viewDate=&isShowPopularPosts=true&from=search  
> https://stackoverflow.com/questions/32034329/spring-java-lang-nosuchmethoderror-org-springframework-expression-spel-spelpars  
> http://1004lucifer.blogspot.com/2017/06/maven-package-webxml-attribute-is.html    
> [Spring Legacy MVC Web Setting 샘플 1](https://mazdah.tistory.com/880)  
> [Spring Legacy MVC Web Setting 샘플 2](https://sang5c.tistory.com/3)  
