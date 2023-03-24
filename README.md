## 기술적 의사결정
 
### 1. 배포환경
 
 - AWS EC2
 - Ubuntu

### 2. DB
 
 - MySQL

### 3. 보안

 - Spring Security
 - JWT

### 4. CI / CD

 - Github Actions
 - Amazon CodeDeploy

## Architecture

## Ground Rule

### 1. Code Convention

 - 변수 이름 : camelCase
 - 메서드 이름 : camelCase, 동사로 시작
 
### 2. Branch 전략

 - Github Flow : CI/CD 환경에서 편의성을 위해 main 브랜치에 바로 배포
 - 단, 테스트 충분히 돌리고 merge 해주세요.

### 3. Commit 메시지 규칙
#### feat : 새로운 기능 추가, 기존 기능을 요구 사항에 맞추어 수정
ex) git commit -m "feat : 대댓글 CRUD 구현"
#### fix : 버그 수정
ex) git commit -m "fix : cors 관련 config 클래스 수정"
#### build : 빌드 관련 수정

#### chore : 패키지 매니저 수정, 그 외 기타 수정
ex) .gitignore

#### ci : CI 관련 설정 수정

#### docs : 문서(주석) 수정
ex) git commit -m "docs : README.md 규칙 추가"
#### style : 코드 스타일, 포맷팅에 대한 수정

#### refactor : 기능의 변화가 아닌 코드 리팩터링 ex) 변수 이름 변경

#### test : 테스트 코드 추가/수정

#### release : 버전 릴리즈

