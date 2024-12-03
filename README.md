### 📄 개요

- 프로젝트 이름 : Son7shop
- 프로젝트 기간 : 24.5.03 ~
- 프로젝트 설명 :  이 프로젝트는 대용량 트래픽을 효과적으로 처리하기 위해 기존 Monolithic 아키텍처를 Microservice 아키텍처로 분리하고, 성능 최적화를 다양한 기법을 적용한 시스템 개선 프로젝트입니다.

  

  ### 🏢 아키텍처
![arcit](https://github.com/user-attachments/assets/83d2982f-9364-40a7-b2b3-a9a79e548584)


### ⚙개발환경

- <img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=OpenJDK&logoColor=white"> : 주 언어로 사용되었습니다.
- <img src="https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=Spring&logoColor=white"> : 프로젝트의 백엔드 개발에 사용되었습니다
- - <img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white"> : 프로젝트의 메인 프레임워크로 사용되었습니다.
- - <img src="https://img.shields.io/badge/Spring Security-6DB33F?style=for-the-badge&logo=Spring Security&logoColor=white"> : 보안(JWT기반 로그인) 기능을 구현하는 데 사용되었습니다.
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

 ### ⚙ 개선 및 트러블슈팅                  
1. Monolithic에서 Microservice로 아키텍처 전환
기존의 모놀리식(Monolithic) 아키텍처는 시스템의 모든 기능이 하나의 단위로 구성되어 있어 대규모 트래픽을 처리하는 데 한계가 있었습니다. 이에 따라 시스템을 여러 개의 독립적인 Microservice로 분리하여 각 서비스가 독립적으로 운영되도록 개선했습니다.
이를 통해 서비스 간의 의존성을 최소화하고, 각 서비스를 독립적으로 확장하거나 배포할 수 있는 유연성을 확보했습니다.
![ddd](https://github.com/user-attachments/assets/790e2716-30e7-4e67-bece-dda709569cb8)


2. 동시성 제어 기법 적용
대량의 요청을 처리하기 위해 동시성 제어 기법을 적극적으로 활용했습니다. 여러 사용자가 동시에 서비스를 요청할 때 발생할 수 있는 데이터 충돌을 방지하기 위해 비관적 락(Pessimistic Lock)을 적용하여 데이터 일관성을 보장.
   첫번쨰 그래프는 redission에서 제공하는 distribution lock을 적용 했을떄 오차 그래프이다. 20개 쓰레드까지는 오차가 없었지만 20보다 많은 쓰레드가 전송되면 오차가 발생한다. 이전 락 방식을 변경하여 성능 개선을 하였다. 2번째 그래프는 jpa에서 제공하는 Lock 기능을 적용했을 떄 오차 그래프이다. 2000개 쓰레드까지 처리하였다.
<img src="https://github.com/user-attachments/assets/941c9d93-9830-4e66-a4bd-4de70d801258" alt="c"/>
<img src="https://github.com/user-attachments/assets/1a4ab341-aab1-4954-ba89-d6eece40813e" alt="b"/>


3. 캐싱을 통한 성능 최적화<br>
트래픽 증가에 따른 성능 문제를 해결하기 위해 캐싱(Caching) 기법을 적용하여 자주 조회되는 데이터를 Redis 저장소에 저장하고, 데이터베이스 조회를 최소화.이를 통해 응답 시간을 단축시키고, 서버 부하를 줄여 전체 시스템의 성능을 개선.

![응답속도1](https://github.com/user-attachments/assets/d6e5df6c-e14d-4b97-8977-7898f0a577f2) ![응답속도2](https://github.com/user-attachments/assets/2b45528b-2b86-43ec-92eb-e1da408c26e9)

###  ⚙ 최근 작업 
사용자의 상품 주문을 시뮬레이션 하기 위해 카카오 결제 모듈을 추가를 했었다.하지만 MSA 환경에 따른 주문 로직과 결제 로직간에 Transaction 문제 발생. 분산 트랜스액션으로 해결 예정. Saga Pattern에서 Choreography 방식을 채택하여 microservice간에 transaction들이 rollback 기능하게 끔 개발해야한다. 일단 일시적으로 주문 로직과 결제 로직을 별개의 트랜스액션이 되도록 분리를 해놓았다.   

 **결제 모듈 API Sequence Diagram**--------------------------------------------**Choreography Saga 패턴**                                                                                                                                                                                                   
 
<img src="https://github.com/user-attachments/assets/cc3468b3-b3de-427c-873b-52cce8bdbae9" alt="Kakao Payment API Sequence Diagram" width="400"/><img src="https://github.com/user-attachments/assets/f8518f3e-2e89-4876-afa6-226f9ce5e94e" alt="Kakao Payment API Sequence Diagram" width="400"/>


  


  ### 🎥 시연영상
   https://github.com/user-attachments/assets/ebea23b6-5fa9-4b9b-a947-65d6853919e8


