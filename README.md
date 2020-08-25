# 스프링 부트 JPA, MySQL, Security 인스타그램



### 의존성 

![image-20200820094651361](C:\Users\admin\AppData\Roaming\Typora\typora-user-images\image-20200820094651361.png)





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

