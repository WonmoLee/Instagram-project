# 스프링 부트 JPA, MySQL, Security 인스타그램



### 의존성 

![blog](https://postfiles.pstatic.net/MjAyMDA4MjBfMTM5/MDAxNTk3ODgzNDIyMDYz.wZ5P4Ig9VYkJJLswXvdtkqY22qzxXBQjxrFGCIxQ33kg.X-uXGvkC2bYaAvyVxhxjPQsj61XGB1fa9iZ8UcQPRigg.PNG.getinthere/Screenshot_42.png?type=w773)



![blog](<https://postfiles.pstatic.net/MjAyMDA4MjRfMTA2/MDAxNTk4MjMyNzA1OTgz.BQb6a4IR5Q22aWTKnwII-hIaHZx_N7-htey0g0Tp3ykg.5waKQmAt8kPgPKodIC_pfsP-4We7tYdrm3HKgbYOrlsg.JPEG.aryeong2/ERD_%EB%8B%A4%EC%9D%B4%EC%96%B4%EA%B7%B8%EB%9E%A8_(1).jpg?type=w966>)





### 사용자 생성 및 권한 주기 및 DB 생성

- create user 'insta'@'%' identified by 'bitc5600';
- GRANT ALL PRIVILEGES ON 별.별 TO 'insta'@'%';
- create database insta;
- use insta;





### MySQL 한글 설정(my.ini)

```ini
[client]
default-character-set=utf8

[mysql]
default-character-set=utf8

[mysqld]
collation-server = utf8_unicode_ci
init-connect='SET NAMES utf8'
init_connect='SET collation_connection = utf8_general_ci'
character-set-server=utf8
```





### application.yml 설정

``` yaml
server:
  port: 8080
  servlet:
    context-path: /
    encoding:
      charset: UTF-8
      enabled: true
      force: true
    
spring:    
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/insta?serverTimezone=Asia/Seoul&useSSL=false&allowPublicKeyRetrieval=true
    username: insta
    password: bitc5600
    
  jpa:
    open-in-view: true
    hibernate:
      ddl-auto: create
      naming:
        physical-strategy: org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl
      use-new-id-generator-mappings: false
    show-sql: true
      
  servlet:
    multipart:
      enabled: true
      max-file-size: 2MB
      
  security:
    user:
      name: test
      password: 1234   

test:
  secret: 이원모

file:
  path: C:/src/SpringBoot-Study/insta/src/main/resources/upload/
```

