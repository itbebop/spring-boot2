ㅁ compile('org.springframework.boot:spring-boot-starter-web')
- Gradle버전을 확인해 보니 7 버전
7버전에서는 compile, testComplie 대신 implementataion, testImplementation을 사용해야 

	> 변경전
	dependencies {
		compile('org.springframework.boot:spring-boot-starter-web')
		testCompile('org.springframework.boot:spring-boot-starter-test')
	}
	> 변경후
	dependencies {
		implementation('org.springframework.boot:spring-boot-starter-web')
		testImplementation('org.springframework.boot:spring-boot-starter-test')
	}

ㅁ Annotations are not allowed here
	HelloController 생성중 @RestController 이 부분에서 발생함
	
ㅁ org.junit.platform.commons.PreconditionViolationException: Cannot create Launcher without at least one TestEngine; consider adding an engine implementation JAR to the classpath
	at org.junit.platform.commons.util.Preconditions.condition(Preconditions.java:296)
	- TestController의 hello_리턴된다()를 실행했을 때 나온 오류메세지
	- bundle.gradle 안의 dependencies에 추가를 해야하는 것으로 보임
	
		> testImplementation 'org.junit.jupiter:junit-jupiter-api:5.7.1'
		  testImplementation 'org.junit.jupiter:junit-jupiter-engine:5.7.1' 를 dependencies에 추가함
		- org.junit.platform.commons.JUnitException: TestEngine with ID 'junit-jupiter' failed to discover tests 라는 메세지로 변경됨
		- 여기에 뭔가를 추가하는 것은 맞는듯
		
ㅁ java.lang.NoClassDefFoundError: org/junit/platform/engine/support/discovery/SelectorResolver

	- 변경 전
		compile('org.springfromwork.boot:spring-boot-starter-web')
		testCompile('org.springframework.boot:spring-boot-starter-test')
	- 변경 후 
		implementation('org.springframework.boot:spring-boot-starter-web')
		implementation('org.springframework.boot:spring-boot-starter-mustache')
		> compile을 implementataion 으로는 바꿨는데 test 를 mustache 로 바꾸는 원인은 아직 모름.

ㅁ java: cannot find symbol		
  symbol:   method is(java.lang.String)
  location: class com.itbebop.documentation.springboot.web.HelloControllerTest
  
   - dtoAPI 테스트 (helloDto가_리턴된다() 실행) 시에 발생함
   - 원인 : 포괄적인 원인으로 대소문자, 오타 등의 원인으로 발생이 가능함
   - 이 경우에는 우선 andExpect가 perform 메소드 밖으로 나와야함. 그 이후에 .andExpect(status().isOk())까지만 실행하면 오류가 안나는데
   .andExpect(jsonPath("$.name", is(name))) 부터 오류남
   
   - 해결 : 
     //.andExpect(jsonPath("$.name", is(name)))
	 //.andExpect(jsonPath("$.amount", is(amount))) 를 아래와 같이 고침
	 
	 .andExpect(jsonPath("$.name").value(name))
	 .andExpect(jsonPath("$.amount").value(amount))
	  > 여기서 "$.name" 앞에는 expression이 들어가도록 ctrl+enter로 import하여 MockMvc 선택