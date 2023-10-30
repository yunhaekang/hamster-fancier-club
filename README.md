# Hamster Fancier Club

햄스터 애호가 클럽

햄스터를 테마로 제작한 커뮤니티형 웹 사이트이다.  
2019년에 php를 공부하며 만든 게시판을 Spring Boot와 JPA 스터디를 위해 변환 작업 중...  
화면과 CSS는 최소한으로 수정할 것이다. 정적 리소스도 기존보다 줄여서 시작할 예정이다.
* ERD: https://www.erdcloud.com/d/CamhSoN89N5mo4SZx (비공개; 개인확인용. 추후 공개로 전환 예정)
### 2023.10.30
 1. 요구사항
    - 회원은 게시판에 글이나 댓글을 작성할 수 있다.
    - 게시판에 글이나 댓글을 작성하면 포인트를 얻을 수 있다.
    - 포인트는 게시판마다 다르게 주어지고, 댓글은 작성시 1 포인트를 적립할 수 있다.
 2. ERD ver0.1
    - 회원과 게시판에 대한 내용까지 정리
    - 레벨 관련 내용은 아직 고민 중...
    - 회원과 회원 상세로 분리했던 엔티티를 합쳐서 수정
    <img src="https://github.com/yunhaekang/hamster-fancier-club/assets/140871418/a4dc27c6-8f39-4fcc-aac4-02e9b2c1d6a5">
    
### 2023.10.16 요구사항 정의 + ERD 그리는 중  

AS-IS   
- 화면/액션마다 나누어놓은 php 파일에서 각각 db 커넥션을 맺고 DML 쿼리를 하고 있다.  
- DDL 스크립트가 없다.   

TO-BE  
- 데이터 모델링부터 진행할 계획이다.  
  1. 정리된 요구사항 작성 예정  
  2. ERD 작성 후 공유 예정  
