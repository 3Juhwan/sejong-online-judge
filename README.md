# SejongOnlineJudge

기존 세종대학교 OJ의 기능을 개선한 온라인 알고리즘 채점 시스템 개발 프로젝트입니다. 

<img width="1278" alt="main-page" src="https://github.com/3Juhwan/sejong-online-judge/assets/64410384/2754f0b8-43c6-44c4-a66b-d2bfff142e8a">


## 요구사항 분석

- 사용자 (user)
  - 사용자는 권한에 따라 관리자/교수/조교/학생으로 구분된다.
    - 기본 권한은 학생, 상위 권한은 관리자가 설정
  - 사용자는 회원 가입과 로그인을 한다.
      - 필요 정보: username, password, email

- 수업 (course)
  - 수업을 생성/수정/삭제한다. 
    - 필요 권한: 관리자, 교수
    - 필요 정보: course name, language, semester
  - 사용자 권한 별, 수업 목록을 조회한다.
    - 관리자: 모든 수업 목록 
    - 교수: 생성한 모든 수업 목록
    - 조교: 등록된 수업 목록
    - 학생: 수강 중인 수업 목록
  - 수업의 자세한 사항을 조회한다. 
    - 조회 정보: course name, semester, language, contest list

- 콘테스트 (contest)
  - 콘테스트를 생성/수정/삭제한다. 
    - 필요 권한: 관리자, 교수, 조교
    - 필요 정보: contest name, start-end time
  - 콘테스트 기본 정보를 조회한다. 
    - 조회 정보: contest name, start-end time, contest-problem list
  - 제출된 코드의 전체 현황을 조회한다. 
    - 필요 권한: 관리자, 교수, 조교

- 콘테스트 문제 (contest-problem)
  - 설명: 미리 등록된 문제를 콘테스트에 등록하기 위한 컨테이너이다. 기본 OJ의 시스템을 그대로 설계했다.   
  - 콘테스트 문제를 생성/수정/삭제한다. 
    - 필요 권한: 관리자, 교수, 조교
    - 필요 정보: contest id, problem id, contest-problem name
  - 콘테스트 문제를 조회한다. 
    - 조회 정보: contest-problem name, problem id
  
- 문제 (problem)
  - 설명: 실제 문제 엔티티이다. 특이 사항으로 폴더 경로를 저장하고 있는데, 이는 폴더 구조로 문제를 관리하기 위함이다.  
  - 문제를 생성/수정/삭제한다. 
    - 필요 권한: 관리자, 교수, 조교
    - 필요 정보: title, content, time/memory limit, dir-path
  - 문제를 조회한다. 
    - 조회 정보: title, content, time/memory limit

- 테스트 데이터 (test-data)
  - 설명: 문제마다 0개 이상의 테스트 데이터가 등록된다. 사용자의 코드 제출은 테스트 테이터를 기반으로 채점된다. 테스트 데이터는 공개와 비공개로 구분된다.  
  - 데스트 데이터 생성/수정/삭제한다. 
    - 필요 권한: 관리자, 교수, 조교
    - 필요 정보: problem id, input-output data list, is-hidden 

- 제출 (submission)
  - 설명: 사용자가 콘테스트-문제에 대해 제출한 코드 정보를 담고 있다. 제출이 생성되면 submit-status가 생성/갱신된다. 
  - 제출을 생성/수정/삭제한다. 
    - 필요 권한: 모든 사용자
    - 필요 정보: source-code, contest-problem id
  - 제출을 조회한다. 
    - 관리자: 모든 제출 조회 가능 
    - 교수/조교: 본인 수업에 제출된 제출 조회 가능 
    - 학생: 본인의 제출은 코드 조회 가능. 다른 제출은 결과만 조회 가능 
  - 기타 기능
    - 기한, 사용자 이름, 언어 등의 조건으로 조회 기능 
    - 특정 contest-problem의 모든 코드, 특정 contest의 모든 코드 조회 기능 

- 성적표 (grade)
  - 설명: 시간을 설정하여 contest에 contest-problem 별 학생의 최고점을 표로 저장한다. 성적표에서 개별 코드를 조회할 수 있고 점수 변경 및 변경 사유를 작성할 수 있다.   
  - 성적표를 생성/수정/삭제한다. 
    - 필요 권한: 관리자, 교수, 조교
    - 필요 정보: contest id, reference time

- 수업 사용자 (course-user)
  - 설명: 수업에 등록된 사용자 목록이다. 학생과 조교가 그 대상이다. 수업에 학생 또는 조교를 등록한다.  
  - 수업 사용자를 생성/수정/삭제한다. 
    - 필요 권한: 관리자, 교수
    - 필요 정보: user list

- 질의 응답 (post-box)
  - 설명: 학생이 교수/조교에게 질의 응답하는 기능이다. 사용자는 contest-problem 별로 최대 1개 생성 가능하다.  
  - 질의 응답을 생성/수정/삭제한다. 
    - 필요 권한: 학생
    - 필요 정보: user id, contest-problem id, course id, contest id
 
- 포스트 (post)
  - 설명: post-box 내에 등록된다. 질문 내용과 제출 기록을 포함할 수 있다. 
  - 포스트 생성/수정/삭제한다. 
    - 필요 권한: 모든 사용자
    - 필요 정보: contest, submission id, user id
  - 포스트를 조회한다. 
    - 필요 정보: post-box id

- 채점
  - 샘플 채점
    - 설명: 테스트 케이스에 대한 코드 실행 결과만을 응답한다.
      - 필요 권한: 모든 사용자
      - 필요 정보: source-code, test-data list
  - 실제 채점
    - 설명: 실제 제출이다. 언어별 채점 API를 호출한다. submission이 생성되고 submit-status가 갱신된다. 
      - 필요 권한: 모든 사용자 
      - 필요 정보: source-code, problem id

## DB Diagram

아래는 프로젝트의 간단한 DB 다이어그램 입니다. 

![db-diagram](https://github.com/3Juhwan/sejong-online-judge/assets/64410384/9811e1a6-7be7-4854-a7c9-8b617caa784a)

## 프로젝트 결과

![demo1](https://github.com/3Juhwan/sejong-online-judge/assets/64410384/c6249731-0884-474d-b7e7-349df0e6c9d0)

![demo2](https://github.com/3Juhwan/sejong-online-judge/assets/64410384/74d3232b-fbb0-479d-a4c6-58eda7a11572)


