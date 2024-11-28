### 📄 개요

- 프로젝트 이름 : Son7shop
- 프로젝트 기간 : 24.6.01 ~
- 프로젝트 설명 :  이 프로젝트는 대용량 트래픽을 효과적으로 처리하기 위해 기존 Monolithic 아키텍처를 Microservice 아키텍처로 분리하고, 성능 최적화를 다양한 기법을 적용한 시스템 개선 프로젝트입니다.

 ### ⚙ 개선 및 트러블슈팅                  
1. Monolithic에서 Microservice로 아키텍처 전환
기존의 모놀리식(Monolithic) 아키텍처는 시스템의 모든 기능이 하나의 단위로 구성되어 있어 대규모 트래픽을 처리하는 데 한계가 있었습니다. 이에 따라 시스템을 여러 개의 독립적인 Microservice로 분리하여 각 서비스가 독립적으로 운영되도록 개선했습니다.
이를 통해 서비스 간의 의존성을 최소화하고, 각 서비스를 독립적으로 확장하거나 배포할 수 있는 유연성을 확보했습니다.

3. 동시성 제어 기법 적용
대량의 요청을 처리하기 위해 동시성 제어 기법을 적극적으로 활용했습니다. 여러 사용자가 동시에 서비스를 요청할 때 발생할 수 있는 데이터 충돌을 방지하기 위해 비관적 락(Pessimistic Lock)을 적용하여 데이터 일관성을 보장했습니다.
   첫번쨰 그래프는 redission에서 제공하는 distribution lock을 적용 했을떄 오차 그래프이다. 20개 쓰레드까지는 오차가 없었지만 20보다 많은 쓰레드가 전송되면 오차가 발생한다. 이전 락 방식을 변경하여 성능 개선을 하였다. 2번째 그래프는 jpa에서 제공하는 Lock 기능을 적용했을 떄 오차 그래프이다. 2000개 쓰레드까지 처리를 했다.   
<img src="https://private-user-images.githubusercontent.com/168810158/390628633-cad40139-ff83-4bde-89dc-d7e88e24f1a1.PNG?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3MzI3NzI5MjQsIm5iZiI6MTczMjc3MjYyNCwicGF0aCI6Ii8xNjg4MTAxNTgvMzkwNjI4NjMzLWNhZDQwMTM5LWZmODMtNGJkZS04OWRjLWQ3ZTg4ZTI0ZjFhMS5QTkc_WC1BbXotQWxnb3JpdGhtPUFXUzQtSE1BQy1TSEEyNTYmWC1BbXotQ3JlZGVudGlhbD1BS0lBVkNPRFlMU0E1M1BRSzRaQSUyRjIwMjQxMTI4JTJGdXMtZWFzdC0xJTJGczMlMkZhd3M0X3JlcXVlc3QmWC1BbXotRGF0ZT0yMDI0MTEyOFQwNTQzNDRaJlgtQW16LUV4cGlyZXM9MzAwJlgtQW16LVNpZ25hdHVyZT03YzUzMzMxOWU0ZmNkNDBlODI0NDRhMGI0Mjg5MTE2ZDlhZTQ2MDFiOTViYTQ5MDA5ZGEzOGY3ZGY4YzFkNDBlJlgtQW16LVNpZ25lZEhlYWRlcnM9aG9zdCJ9.1AqT44Lz95u3K-GGT5_UK_XkvZeKQweLyRyP4H8j_L8">  
<img src="https://private-user-images.githubusercontent.com/168810158/390628616-4bd8fba3-7b87-4e4e-9e9d-3b64ff05a6bf.PNG?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3MzI3NzI5NjksIm5iZiI6MTczMjc3MjY2OSwicGF0aCI6Ii8xNjg4MTAxNTgvMzkwNjI4NjE2LTRiZDhmYmEzLTdiODctNGU0ZS05ZTlkLTNiNjRmZjA1YTZiZi5QTkc_WC1BbXotQWxnb3JpdGhtPUFXUzQtSE1BQy1TSEEyNTYmWC1BbXotQ3JlZGVudGlhbD1BS0lBVkNPRFlMU0E1M1BRSzRaQSUyRjIwMjQxMTI4JTJGdXMtZWFzdC0xJTJGczMlMkZhd3M0X3JlcXVlc3QmWC1BbXotRGF0ZT0yMDI0MTEyOFQwNTQ0MjlaJlgtQW16LUV4cGlyZXM9MzAwJlgtQW16LVNpZ25hdHVyZT1jZThjNGIyMDhlM2VkN2U2MzMyNzMyYjA3MGNmZTdlOTJkMTRmMDEwMzI1M2U3NThjNjdmMjVjZjc2Y2Q1ZmM0JlgtQW16LVNpZ25lZEhlYWRlcnM9aG9zdCJ9.J6y09No_RT_bNoW1CZnv7swOy3-HbmBek0wS44l_KIU">
5. 캐싱을 통한 성능 최적화
트래픽 증가에 따른 성능 문제를 해결하기 위해 캐싱(Caching) 기법을 적용하여 자주 조회되는 데이터를 메모리에 저장하고, 데이터베이스 조회를 최소화했습니다. 이를 통해 응답 시간을 단축시키고, 서버 부하를 줄여 전체 시스템의 성능을 대폭 개선했습니다.
### ⚙개발환경

---

- <img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=OpenJDK&logoColor=white"> : 주 언어로 사용되었습니다.
- <img src="https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=Spring&logoColor=white"> : 프로젝트의 백엔드 개발에 사용되었습니다
- - <img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white"> : 프로젝트의 메인 프레임워크로 사용되었습니다.
- - <img src="https://img.shields.io/badge/Spring Security-6DB33F?style=for-the-badge&logo=Spring Security&logoColor=white"> : 보안 기능을 구현하는 데 사용되었습니다.
- - <img src="https://img.shields.io/badge/Spring Cloud-6DB33F?style=for-the-badge&logo=Spring cloud&logoColor=white"> : 클라우드 환경에서의 마이크로서비스 아키텍처를 구현하는 데 사용되었습니다.
- <img src="https://img.shields.io/badge/Hibernate-59666C?style=for-the-badge&logo=Hibernate&logoColor=white"> : 데이터베이스 ORM(Object-Relational Mapping) 프레임워크로 사용되었습니다.
- <img src="https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=MySQL&logoColor=white"> : 관계형 데이터베이스로 사용되었습니다.
- <img src="https://img.shields.io/badge/Redis-DC382D?style=for-the-badge&logo=Redis&logoColor=white"> : 캐싱을 위해 사용되었습니다.
- <img src="https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white"> : 애플리케이션의 컨테이너화를 위해 사용되었습니다.
- <img src="https://img.shields.io/badge/GitHub Actions-2088FF?style=for-the-badge&logo=GitHub Actions&logoColor=white"> : CI/CD 파이프라인을 구축하는 데 사용되었습니다.
- <img src="https://img.shields.io/badge/Amazon%20EC2-FF9900?style=for-the-badge&logo=Amazon%20EC2&logoColor=white"> : 애플리케이션의 서버 호스팅에 사용되었습니다.
- <img src="https://img.shields.io/badge/Amazon%20S3-569A31?style=for-the-badge&logo=Amazon%20S3&logoColor=white"> : 정적 파일 및 미디어 파일을 저장하는 데 사용되었습니다.
- <img src="https://img.shields.io/badge/Amazon%20RDS-527FFF?style=for-the-badge&logo=Amazon%20RDS&logoColor=white"> : 관계형 데이터베이스 서비스로 사용되었습니다.
- <img src="https://img.shields.io/badge/amazon%20route%2053-8C4FFF?style=for-the-badge&logo=amazonroute53&logoColor=white"> : 도메인 관리와 DNS 서비스에 사용되었습니다.
- <img src="https://img.shields.io/badge/Elasticsearch-005571?style=for-the-badge&logo=Elasticsearch&logoColor=white"> : 검색 기능을 구현하는 데 사용되었습니다.
- <img src="https://img.shields.io/badge/Logstash-005571?style=for-the-badge&logo=Logstash&logoColor=white"> : 로그 데이터를 수집, 처리 및 저장하는 데 사용되었습니다.
- <img src="https://img.shields.io/badge/Kibana-005571?style=for-the-badge&logo=Kibana&logoColor=white"> : 데이터 시각화 및 분석 도구로 사용되었습니다.
-  <img src="https://img.shields.io/badge/-ReactJs-61DAFB?style=for-the-badge&logo=react&logoColor=white">: 프론트엔드 개발에 사용되었습니다.
