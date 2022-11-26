그레이들 프로젝트를 스프링 부트 프로젝트로 변경 (build.gradle 파일)
	> 스프링 이니셜라이져 사용 x
		- build.gradle의 코드가 무슨 역할을 하는지, 이니셜라이저 외에 추가로 의존성 추가가 피룡하면 어떻게 해야 할지 모르는 상태로 개발하게 되므로
	
	> ext : build.gradle에서 사용하는 전역변수를 설정하겠다는 의미
	
	> apply plugin: 'java'
	apply plugin: 'eclipse'
	apply plugin: 'org.springframework.boot'
	apply plugin: 'io.spring.dependency-management'
		- 앞서 선언한 플러그인 의존성들을 적용할 것인지 결정
		- io.spring.dependency-management은 스프링 부트의 의존성들을 관리해 주는 플러그인이라 필수
		- 자바와 스프링 부트를 사용하기 위해서는 위 4개 필수이므로 항상 추가하면 됨
		
		
	> repositories
		- 각종 의존성(라이브러리)들을 어떤 원격 저장소에서 받을지 결정함
		- mavenCentral을 많이 사용하지만, 최근에는 라이브러리 업로드 난이도 때문에 jcenter도 많이 사용한다.
	> dependencies
		- 프로젝트 개발에 필요한 의존성들을 선언하는 곳
		- 특정 버전을 명시하면 안되고 ${springBootVersion}으로 해야 이 버전을 따라감
	
* git 연결 (인텔리제이)

 ㅇ 최초 repository 생성
	- 이미 git 계정이 연결되어있는 경우
	1) 최초 프로젝트를 생성할 때 create repository 가능함
	2) 만약 거기서 repository를 생성하지 않았다면 메뉴 > git > github > create git repository(이 메뉴는 확실하지 않음) 로 생성가능한 듯하지만
	어떤 경우에만 메뉴가 나오는 것인지 확인 필요함
	- 아직 git 계정이 연결되지 않은 경우
	1) Ctrl+Shift+A > share project on Github 입력하여 메뉴 실행하여 github에서 생성한 계정으로 로그인
	2) Repository 이름 등 입력하고 share 하면 생성됨
	
	- .idea 디렉토리는 커밋하지 않는다.
	> 프로젝트 실행시 자동으로 생성되는 파일
	
	ㅇ github에 올라간 파일/디렉토리 삭제하기
	1) 로컬 디렉토리와 github에서 모두 삭제
	 git rm git rm {디렉토리명 또는 파일명}
	 git commit -m "delete source"
	 git push
	
	2) 로컬 디렉토리에는 유지하고 github에서만 삭제하기
	 git rm --cached -r {디렉토리명 또는 파일명}
	 git commit -m "delete source"
	 git push
	
 ㅇ gitignore 파일 만들기
  - 인텔리제이에서는 .gitignore 파일에 대한 지원이 없음
  - .ignore 플러그인에서 지원을 하고 있음
	> 파일 위치 자동완성
	> 이그노어 처리 여부 확인
	> 다양한 이그노어 파일 지원(.gitignore, .npmignore, .dockerignore 등등)
	
  - .ignore 플로그인 설치
	> Ctrl + Shift + A (Action 검색창)
	> plugins 검색, 설치
	> 재시작
  - gitignore 파일 생성
    > 프로젝트 우클릭 (단축키 alt+insert)
	> new > .ignore file > .gitignore filr(Git)
	> 불러올 파일이 없을 경우 그냥 Generate
	> 생성된 gitignore 파일에 아래 내용 입력
	
	.gradle
	.idea

	> git commit창을 열어서 반영 (단축키 Ctrl+k)
	
	
	