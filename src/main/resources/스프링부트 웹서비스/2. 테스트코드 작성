ㅁ TDD와 단위 테스트
 ㅇ TDD : 테스트 주도 개발
 ㅇ 단위테스트 : TDD의 첫 번째 단계인 기능 단위의 테스트 코드를 작성하는 것
 
ㅁ 테스트 프레임워크
 ㅇ xUnit : 가장 대중적인 테스트 프레임워크로서 unit 테스트를 도와주는 도구.
 - xUnit 프레임워크	
	- JUnit - Java
	- DBUnit - DBUnit
	- JUnit4로 진행

ㅁ Hello Controller 테스트 코드 작성
 1) scr > main > java 우클릭하고 package 생성
 2) 그 안에 java class 생성 (Application)
 
 > 안의 내용 설명
  1) Application 클래스는 프로젝트의 메인 클래스가 됨
  2) @SpringBootApplication으로 스프링 부트의 자동 설정, 스프링 Bean 읽기와 생성이 자동으로 설정
  3) 이때 @SpringBootApplication이 있는 위치부터 설정을 읽어가기 때문에 이 클래스는 항상 프로젝트의 최상단에 위치해야함
  4) SpringApplication.run으로 내장 WAS 를 실행함
	> 톰캣을 설치할 필요가 없고, 스프링 부트로 만들어진 Jar 파일로 실행하면 됨

ㅁ Controller 만들기
 1) 위 Application 패키지 아래 web 패키지 생성
 2) 컨트롤러와 관련된 클래스들은 모두 이 패키지로 담을 것
 3) 패키지 안에 javaClass 생성 (HelloContorller)
 4) 코드 설명
  > 1) @RestController
		- 컨트롤러를 JSON을 반환하는 컨트롤러로 만들어줌
		- 예전에는 @ResponseBody를 각 메소드마다 선언했던 것을 한번에 사용할 수 있게 해주는 것.
	2) @Getmapping
		- HTTP method인 Get의 요청을 받을 수 있는 API를 만들어 줌
		- 예전에는 @RequestMapping(method = RequestMethod.GET)으로 사용되었으나 이제 /hello로 요청이 오면 문자열 hello를 반환하는 기능을 갖게 됨

ㅁ 롬복
 - 자바 개발 시 자주 사용하는 Getter, Setter, 기본생성자, toString 등을 어노테이션으로 자동 생성해줌
 - 이클립스에서는 롬복 설치가 번거롭지만 인텔리제이에서는 플러그인 덕분에 쉽게 설정이 가능함.
 - bundle.gradle에 코드 추가하여 설치
 - Setting/ Build, Excution, Deployment > Annotation Processor > Enable annotion processing 체크
 - main 의 dto 패키지에 HelloResponseDto를 만들고 아직 Test에는 동일한 클래스를 만들기 전엔 오류?나다가 Test에 만들면 없어짐
 
 ㅇ assertj의 장점 (Junit과 비교 - 프로젝트에서 사용한 assertThat 은 Junit의 기본 assertThat 이 아닌 assertj의 assertThat을 사용함)
  - CoreMatchers 와 달리 추가적으로 라이브러리가 필요하지 않음
   > Junit의 assertThat을 쓰게 되면 is()와 같이 CoreMatchers 라이브러리가 필요함
  - 자동완성이 좀 더 확실하게 지원됨
   > IDE에서는 CoreMatcher와 같은 Matcher 라이브러리의 자동완성 지원이 약함
   
 ㅇ HelloContorller 에도 ResponseDto를 추가
  - package com.itbebop.documentation.springboot.webd 의 HelloController에 추가
	<기존코드>
		@RestController
		public class HelloController {

			@GetMapping("/hello")
			public String hello() {
				return "hello";
			}
		}

  ㅇ JsonPath 사용법 
	- JsonPath에 해당하는 값이 존재하는지
	- JsonPath에 해당하는 값이 존해하지 않는지
	- JsonPath에 해당하는 값의 동일성 비교
	- JsonPath로 가져온 값을 hamcrestMatcher로 검증하기
	  
	  https://ykh6242.tistory.com/entry/MockMvc%EB%A5%BC-%EC%9D%B4%EC%9A%A9%ED%95%9C-REST-API%EC%9D%98-Json-Response-%EA%B2%80%EC%A6%9D