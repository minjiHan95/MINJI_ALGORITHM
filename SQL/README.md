# DATABASE


* [SELECT 문법](#SELECT-문법)
* [패턴 매칭](#패턴-매칭)
* [JOIN](#JOIN)
* [UNION](#UNION)
* [서브쿼리](#서브쿼리)
* [문자열 함수](#문자열-함수)
* [수학 함수](#수학-함수)
* [제약 조건](#제약-조건)


***

**Reservation 테이블**

| ID 	|  Name  	| ReserveDate 	| RoomNum 	|
|:--:	|:------:	|:-----------:	|:-------:	|
|  1 	| 홍길동 	|  2016-01-05 	|   2014  	|
|  2 	| 임꺽정 	|  2016-02-12 	|   918   	|
|  3 	| 장길산 	|  2016-01-16 	|   1208  	|
|  4 	| 홍길동 	|  2016-03-17 	|   504   	|

<br>

**Customer 테이블**
| ID 	|  Name  	| Age 	| Address 	|
|:--:	|:------:	|:---:	|:-------:	|
|  1 	| 홍길동 	|  17 	|   서울  	|
|  2 	| 임꺽정 	|  11 	|   인천  	|
|  3 	| 장길산 	|  13 	|   서울  	|
|  4 	| 전우치 	|  17 	|   수원  	|
<br>

***

### **SELECT 문법**

**SELECT 문법**

```SQL
SELECT 필드이름

FROM 테이블이름

WHERE 조건
```
이러한 WHERE 절에는 여러 개의 조건을 같이 명시할 수도 있다. 이때 여러 개의 조건은 `AND나 OR 연산자`를 이용하여 연결한다.

<br>

**중복되는 값 제거**

만약 같은 필드에 중복되는 값을 가지는 레코드가 있다면, DISTINCT 키워드를 사용하여 그 값이 한 번만 선택되도록 설정

```SQL
SELECT DISTINCT Name

FROM Reservation;
```
<br>

**선택한 결과의 정렬**

SELECT 문으로 선택한 결과를 ORDER BY 절을 사용하여 정렬

ORDER BY 절의 기본 설정은 오름차순이며, `ASC` 키워드를 사용하여 직접 오름차순을 명시할 수도 있다. 내림차순으로 정렬하고자 할 때는 맨 마지막에 `DESC` 키워드를 추가한다.

대소문자까지 구분하여 정렬하고 싶을 때는 `ORDER BY BINARY` 절을 사용

```SQL
SELECT *

FROM Reservation

ORDER BY ReserveDate DESC, RoomNum ASC;
```
<br>

**별칭(alias)을 이용한 처리**

테이블과 필드에 임시로 별칭(alias)을 부여하고, 해당 별칭을 SELECT 문에서 사용

```Markdown
1. 
SELECT 필드이름 AS 별칭

FROM 테이블이름;

2. 
SELECT 필드이름

FROM 테이블이름 AS 별칭;
```

```SQL
SELECT ReserveDate, CONCAT(RoomNum, " : ", Name) AS ReserveInfo

FROM Reservation;
```
<br>

***

### **패턴 매칭**

데이터의 `특정 패턴을 검색`하기 위해 다음과 같은 패턴 매칭 연산자를 제공한다. 또한, 임의의 `문자나 문자열을 대체`하기 위해서 와일드카드(wildcard) 문자를 사용할 수도 있다.

<br>

**LIKE**

`LIKE 연산자` : 특정 패턴을 포함하는 데이터만을 검색하기 위해 사용

```SQL
SELECT * FROM Reservation

WHERE Name LIKE '장%';
```

`NOT LIKE 연산자` : 특정 패턴을 포함하지 않는 데이터를 검색하고 싶을 때 사용

```SQL
SELECT * FROM Reservation

WHERE Name NOT LIKE '장%';
```
<br>

**와일드카드(wildcard)**

와일드카드(wildcard)란 문자열 내에서 임의의 문자나 문자열을 대체하기 위해 사용되는 기호를 의미한다. MySQL에서 사용할 수 있는 와일드카드 문자는 다음과 같다.

|문자|설명|
|---|---------------------------|
| % | 0개 이상의 문자를 대체함. |
| _ | 1개의 문자를 대체함.      |


```SQL
SELECT * FROM Reservation

WHERE RoomNum LIKE '20__';
```
<br>

**REGEXP**

LIKE 연산자보다 더욱 복잡한 패턴을 검색하고 싶을 때는 REGEXP 연산자를 사용한다. REGEXP 연산자는 정규 표현식을 토대로 하는 패턴 매칭 연산을 제공한다. REGEXP 연산자와 함께 사용할 수 있는 패턴은 다음과 같다.

|연산자|패턴|
|:------:|----------------------------------------------------|
|    .   | 줄 바꿈 문자(\n)를 제외한 임의의 한 문자를 의미함. |
|    *   | 해당 문자 패턴이 0번 이상 반복됨.                  |
|    +   | 해당 문자 패턴이 1번 이상 반복됨.                  |
|    ^   | 문자열의 처음을 의미함.                            |
|    $   | 문자열의 끝을 의미함.                              |
|   \|   | 선택을 의미함.(OR)                                 |
|  [...] | 괄호([]) 안에 있는 어떠한 문자를 의미함.           |
| [^...] | 괄호([]) 안에 있지 않은 어떠한 문자를 의미함.      |
|   {n}  | 반복되는 횟수를 지정함.                            |
|  {m,n} | 반복되는 횟수의 최솟값과 최댓값을 지정함.          |

<br>

Name 필드의 값이 '홍'으로 시작하거나, '산'으로 끝나는 레코드를 선택하는 예제

 
```SQL
SELECT * FROM Reservation

WHERE Name REGEXP '^홍|산$';
```
<br>
 
***

### **JOIN**

JOIN은 데이터베이스 내의 여러 테이블에서 가져온 레코드를 조합하여 하나의 테이블이나 결과 집합으로 표현해 준다. JOIN은 보통 SELECT 문과 함께 자주 사용된다.

<br>

**INNER JOIN**

INNER JOIN은 ON 절과 함께 사용되며, ON 절의 조건을 만족하는 데이터만을 가져온다.

```SQL
1. 
SELECT *

FROM Reservation

INNER JOIN Customer

ON Reservation.Name = Customer.Name;


2. 
SELECT *

FROM Reservation

JOIN Customer

ON Reservation.Name = Customer.Name;
```
<br>

**LEFT JOIN**

LEFT JOIN은 첫 번째 테이블을 기준으로, 두 번째 테이블을 조합하는 JOIN이다. 

```Markdown
이때 ON 절의 조건을 만족하지 않는 경우에,
* 첫 번째 테이블의 필드 값은 그대로
* 두 번째 테이블의 필드 값은 모두 NULL로 표시된다.
```
```SQL
SELECT *

FROM Reservation

LEFT JOIN Customer

ON Reservation.Name = Customer.Name

WHERE ReserveDate > '2016-02-01';
```

위의 예제에서 두 개의 Name 값이 일치하면, INNER JOIN과 같이 두 테이블의 모든 필드를 그대로 가져온다. 하지만 `두 개의 Name 값이 일치하지 않는 경우`에는 Customer 테이블의 `모든 필드를 NULL`로 표시된다.

<details><summary>예제</summary>

```SQL
-- LEFT JOIN

SELECT *
FROM Reservation AS A
LEFT JOIN Customer AS B
ON A.Name = B.ID;
```

| ID 	|  Name  	| ReserveDate 	| RoomNum 	|  ID  	| Name 	|  Age 	| Address 	|
|:--:	|:------:	|:-----------:	|:-------:	|:----:	|:----:	|:----:	|:-------:	|
|  1 	| 홍길동 	|  2016-01-05 	|   2014  	| NULL 	| NULL 	| NULL 	|   NULL  	|
|  2 	| 임꺽정 	|  2016-02-12 	|   918   	| NULL 	| NULL 	| NULL 	|   NULL  	|
|  3 	| 장길산 	|  2016-01-16 	|   1208  	| NULL 	| NULL 	| NULL 	|   NULL  	|
|  4 	| 홍길동 	|  2016-03-17 	|   504   	| NULL 	| NULL 	| NULL 	|   NULL  	|



```SQL
-- INNER JOIN

SELECT *
FROM Reservation AS A
INNER JOIN Customer AS B
ON A.Name = B.ID;
```
0 row(s) in set

</details>
<br>

***

### **UNION**

**UNION**

여러 개의 SELECT 문의 결과를 하나의 테이블이나 결과 집합으로 표현할 때 사용한다. 이때 각각의 SELECT 문으로 선택된 `필드의 개수와 타입`은 모두 같아야 하며, `필드의 순서` 또한 같아야 한다.

```SQL
SELECT Name

FROM Reservation

UNION

SELECT Name

FROM Customer;
```

이때 두 SELECT 문의 결과에서 중복된 레코드인 '홍길동'은 한 번만 표시

<br>

**UNION ALL**

위의 예제처럼 UNION은 DISTINCT 키워드를 따로 명시하지 않아도 기본적으로 중복되는 레코드를 제거한다. 따라서 이렇게 `중복되는 레코드까지 모두 출력`하고 싶다면, `ALL 키워드`를 사용해야 한다.

```SQL
SELECT Name

FROM Reservation

UNION ALL 

SELECT Name

FROM Customer;
```
이때 두 SELECT 문의 결과는 중복된 레코드까지 모두 표시

<br>

***

### **서브쿼리**
서브쿼리(subquery)란 다른 쿼리 내부에 포함되어 있는 SELETE 문을 의미한다. 서브쿼리를 포함하고 있는 쿼리를 외부쿼리(outer query)라고 부르며, 서브쿼리는 내부쿼리(inner query)라고도 부른다.

<br>

**서브쿼리 사용시 주의사항**

1. 서브쿼리를 괄호(())로 감싸서 사용한다.
2. 서브쿼리는 단일 행 또는 복수 행 비교 연산자와 함께 사용 가능하다.
3. 서브쿼리에서는 ORDER BY 를 사용하지 못한다.

<br>

**서브쿼리가 사용 가능한 곳**

1. SELECT 절
2. FROM 절
3. WHERE 절
4. HAVING 절
5. ORDER BY 절
6. INSERT 문의 VALUES 절
7. UPDATE 문의 SET 절

이러한 서브쿼리는 또 다시 다른 서브쿼리 안에 포함될 수 있다.

<br>

**다중 행 서브쿼리**

서브쿼리의 결과가 2건 이상 반환될 수 있다면 반드시 다중 행 비교 연산자(IN, ALL, ANY, SOME)와 함께 사용해야 한다.

| 연산자  	|  설명                                                                        	|
|---------	|------------------------------------------------------------------------------	|
|  IN     	|  서브쿼리의 결과에 존재하는 임의의 값과 동일한 조건을 의미한다.              	|
|  ALL    	|  서브쿼리의 결과에 존재하는 모든 값을 만족하는 조건을 의미한다.              	|
|  ANY    	|  서브쿼리의 결과에 존재하는 어느 하나의 값이라도 만족하는 조건을 의미한다.   	|
|  EXISTS 	|  서브쿼리의 결과를 만족하는 값이 존재하는지 여부를 확인하는 조건을 의미한다. 	|

<br>

```SQL
//주소가 서울인 고객이 예약한 예약 정보만을 선택하는 예제

SELECT ID, ReserveDate, RoomNum

FROM Reservation

WHERE Name IN (

    SELECT Name

    FROM Customer

    WHERE Address = '서울'
    );
```

<br>

**서브쿼리의 장점**

1. 서브쿼리는 쿼리를 구조화시키므로, 쿼리의 각 부분을 명확히 구분할 수 있다.
2. 서브쿼리는 복잡한 JOIN이나 UNION과 같은 동작을 수행할 수 있는 또 다른 방법을 제공한다.
3. 서브쿼리는 복잡한 JOIN이나 UNION 보다 좀 더 읽기 편하다.

<br>

**FROM 절의 서브쿼리**

서브쿼리는 SELECT 문의 FROM 절에서도 사용할 수 있다. 이때 서브쿼리에 의해 선택된 결과 집합은 FROM 절에서 하나의 테이블로써 사용된다.

기본적으로 FROM 절에는 테이블 명이 오도록 되어있다. 그런데 서브쿼리가 FROM 절에 사용되면 동적으로 생성된 테이블인 것처럼 사용할 수 있다. 인라인 뷰는 SQL 문이 실행될 때만 임시적으로 생성되는 동적인 뷰이기 때문에 데이터베이스에 해당 정보가 저장되지 않습니다. 인라인 뷰는 동적으로 조인 방식을 사용하는 것과 같습니다

```SQL
SELECT Name, ReservedRoom

FROM (
    SELECT Name, ReserveDate, (RoomNum + 1) AS ReservedRoom

    FROM Reservation

    WHERE RoomNum > 1001) AS ReservationInfo;
```
 
SELECT 문의 FROM 절에서 사용되는 모든 테이블에는 이름이 필요하다. 따라서 FROM 절에서 사용되는 서브쿼리는 위의 문법처럼 반드시 이름을 정의해야 한다.

<br>

***

### **문자열 함수**

MySQL은 사용자의 편의를 위해 다양한 기능의 내장 함수를 미리 정의하여 제공한다. 그 중 대표적인 내장 함수의 종류는 `1. 문자열 함수, 2. 수학 함수, 3. 날짜와 시간 함수` 와 같다.

<br>

**문자열 길이** 

LENGTH() 함수는 전달받은 문자열의 길이를 반환한다.

```SQL
SELECT LENGTH('12345678');

--8
```

<br>

**문자열 결합**

CONCAT() 함수는 전달받은 문자열을 모두 결합하여 하나의 문자열로 반환한다. 만약 전달받은 문자열 중 `하나라도 NULL이 존재`하면, NULL을 반환한다.

```SQL
SELECT 
    CONCAT('Ora', 'cle Cor', 'poration'),   

    CONCAT('Oracle', NULL, 'Corporation');  

-- Oracle Corporation
-- NULL
```

<br>

**특정 문자열의 위치 검색**

LOCATE() 함수는 인수로 전달받은 문자열이 특정 문자열에서 처음으로 나타나는 위치를 찾아서, 해당 위치를 반환한다. 만약 전달받은 문자열이 특정 문자열 내에 존재하지 않으면 0을 반환한다.

*주의*

문자열의 첫 번째 문자의 인덱스를 언제나 `1부터 시작하여 계산`하므로, 주의를 기울여야 한다. 이때 `세 번째 인수`로 특정 문자열에서 전달받은 문자열을 찾기 시작할 `인덱스를 전달`할 수도 있다.

```SQL
SELECT 
    LOCATE('abc', 'ababcDEFabc'),      

    LOCATE('abc', 'ababcDEFabc', 4);    

-- 3
-- 9 
```

<br>

**문자열 추출**

LEFT() 함수는 전달받은 문자열의 왼쪽부터 명시한 개수만큼의 문자를 반환한다.

RIGHT() 함수는 전달받은 문자열의 오른쪽부터 명시한 개수만큼의 문자를 반환한다.

```SQL
SELECT 
    LEFT('MySQL PHP HTML Java', 5), 

    RIGHT('MySQL PHP HTML Java', 4);

-- MySQL
-- Java
```

<br>

**문자열 대소문자 변경**
LOWER() 함수는 전달받은 문자열의 문자를 모두 소문자로 변경한다.

UPPER() 함수는 전달받은 문자열의 문자를 모두 대문자로 변경한다.

```SQL
SELECT 
    LOWER('MySQL PHP HTML Java'), 

    UPPER('MySQL PHP HTML Java');

-- mysql php html java
-- MYSQL PHP HTML JAVA
```

<br>

**문자열 대체**

REPLACE() 함수는 전달받은 문자열에서 특정 문자열을 찾은 후에, 찾은 문자열을 대체 문자열로 교체한다.

```SQL
SELECT 
    REPLACE('MySQL', 'My', 'MS ');

-- MS SQL
```

<br>

**문자열 다듬기**

TRIM() 함수는 전달받은 문자열의 앞이나 뒤, 또는 양쪽 모두에 있는 특정 문자를 제거한다.

 
```Markdown
TRIM() 함수에서 사용할 수 있는 지정자는 다음과 같다.
1. BOTH : 전달받은 문자열의 양 끝에 존재하는 특정 문자를 제거함. (기본 설정)
2. LEADING : 전달받은 문자열 앞에 존재하는 특정 문자를 제거함.
3. TRAILING : 전달받은 문자열 뒤에 존재하는 특정 문자를 제거함.
```
 
만약 지정자를 명시하지 않으면, 자동으로 BOTH로 설정된다. 또한, 제거할 문자를 명시하지 않으면, 자동으로 공백을 제거하게 된다.

```SQL
SELECT 
    TRIM('   !!!MySQL PHP HTML Java!!!    '), 

    TRIM(LEADING '!' FROM '!!!MySQL PHP HTML Java!!!')

-- !!!MySQL PHP HTML Java!!!
-- MySQL PHP HTML Java!!!
```
<br>

***

### **수학 함수**

<br>

**올림과 내림**

FLOOR() 함수는 인수로 전달받은 값과 같거나 작은 수 중에서 가장 큰 정수를 반환한다.

CEIL() 함수는 반대로 인수로 전달받은 값과 같거나 큰 수 중에서 가장 작은 정수를 반환한다.

ROUND() 함수는 소수점에서의 반올림을 수행할 때 사용한다. 이때 두 번째 인수로 반올림할 소수점을 전달할 수도 있다.

```SQL
SELECT 
    FLOOR(10.95),
    FLOOR(11.01),
    FLOOR(-10.95),
    FLOOR(-11.01);

-- 10, 11, -11, -12

SELECT 
    CEIL(10.95),
    CEIL(11.01),
    CEIL(11),
    CEIL(-10.95),
    CEIL(-11.01);

-- 11, 12, 11, -10, -11

SELECT 
    ROUND(10.49),
    ROUND(10.5), 
    ROUND(-10.5), 
    ROUND(-10.49),
    ROUND(-10.49, 1),
    ROUND(-10.49, 2);

-- 10, 11, -11, -10, -10.5, -10.49

```

<br>

**지수와 로그**

SQRT() 함수는 전달받은 수의 제곱근 중 양수값을 반환한다.

POW() 함수는 전달받은 수의 거듭제곱을 반환한다. 첫 번째 인수로는 밑수를 전달하고, 두 번째 인수로는 지수를 전달한다.

EXP() 함수는 인수로 지수를 전달받아, e의 거듭제곱을 계산하여 반환한다.

LOG() 함수는 전달받은 수의 자연로그 값을 계산하여 반환한다.

```SQL
SELECT 
    SQRT(4), 

    POW(2, 3),

    EXP(3), 

    LOG(3);

-- 2
-- 8
-- 20.085536923187668
-- 1.0986122886681098
```

<br>

**삼각 함수**

SIN() 함수는 전달받은 수의 사인값을, COS() 함수는 코사인값을, TAN() 함수는 탄젠트값을 반환한다.

```SQL
SELECT 
    SIN(PI()/2), 

    COS(PI()), 

    TAN(PI()/4);

-- 1
-- -1
-- 0.9999999999999999
```

<br>

**기타 함수**

ABS(X) 함수는 전달받은 수의 절댓값을 반환한다.

RAND() 함수는 0.0보다 크거나 같고 1.0보다 작은 하나의 실수를 무작위로 생성하여 반환한다.

```SQL
SELECT 
    ABS(-3), 

    ROUND(RAND()*100, 0);

-- 3
-- 24
```

<br>

***

### **그룹 함수**

하나의 값이 아닌 값들의 `집합에 대해서 동작`한다. 자주 사용되는 대표적인 그룹 함수는 다음과 같다. 

1. COUNT()

2. MIN()과 MAX()

3. SUM()

4. AVG()

대부분의 그룹 함수는 NULL 값을 제외하고 동작한 결과를 반환한다.

<br>

**COUNT()**

COUNT() 함수는 선택된 필드에서 특정 조건을 만족하는 레코드의 총 개수를 반환한다. 만약 조건을 만족하는 레코드가 없다면, COUNT() 함수는 0을 반환한다. 이때 중복된 값을 제외하려면, 필드 이름 앞에 DISTINCT 키워드를 사용한다.

```SQL
SELECT COUNT(*) AS NumberOfRervation

FROM Reservation
```

| NumberOfRervation |
|:--------------:|
|       4      |

```SQL
SELECT COUNT(Name) AS NumberOfName 

FROM Reservation;
```

| NumberOfName |
|:--------------:|
|       4      |

```SQL
SELECT COUNT(DISTINCT Name) AS NumberOfName 

FROM Reservation;
```

| NumberOfName |
|:--------------:|
|       3      |

<br>

**MIN()과 MAX()**

MIN() 함수는 선택된 필드에 저장된 값 중 가장 작은 값을 반환한다.

MAX() 함수는 선택된 필드에 저장된 값 중 가장 큰 값을 반환한다.

```SQL
SELECT MIN(Age) AS MinAge

FROM Customer;
```
 
| MinAge |
|:--------------:|
|11|

```SQL
SELECT MAX(Name) AS MaxName

FROM Customer;
```

| MaxName |
|:--------------:|
|홍길동|

위의 예제처럼 MIN() 함수와 MAX() 함수는 `문자열 타입의 필드`에서도 최솟값과 최댓값을 구할 수 있다.

<br>

**SUM()**

SUM() 함수는 선택된 숫자 타입의 필드에 저장된 값의 총 합을 반환한다.

```SQL
SELECT SUM(Age) AS SumOfAge

FROM Customer;
```
 
| SumOfAge |
|:--------------:|
|58|

<br>

**AVG()**

AVG() 함수는 선택된 숫자 타입의 필드에 저장된 값의 평균값을 반환한다.

```SQL
SELECT AVG(Age) AS AverageOfAge

FROM Customer;
```

| AverageOfAge |
|:--------------:|
|14.5000|

<br>

**GROUP BY 절**

GROUP BY 절은 선택된 레코드의 집합을 필드의 값이나 표현식에 의해 그룹화한 결과 집합을 반환한다. 즉, GROUP BY 절은 하나의 그룹을 하나의 레코드로 반환하므로, 결과 집합의 크기를 줄여주는 역할을 한다.

이러한 GROUP BY 절은 SELECT 문에서만 사용할 수 있으며, 앞서 살펴 본 그룹 함수를 사용할 때 자주 같이 사용된다.

```SQL
SELECT 필드이름, 그룹함수(필드이름)

FROM 테이블이름

[WHERE 조건]

GROUP BY 필드이름;
```

```SQL
-- Customer 테이블에서 각 나이별 회원수를 구하는 예제

SELECT Age, COUNT(*) AS NumOfCustomer

FROM Customer

GROUP BY Age;
```

| age | 회원수 |
|:---:|:------:|
|  11 |    1   |
|  13 |    1   |
|  17 |    2   |

위의 예제처럼 GROUP BY 절에 사용된 필드의 이름은 `반드시 SELECT 문에도 같이 명시`되어야 합니다.

<br>

**HAVING 절**

HAVING 절은 SELECT 문의 WHERE 절처럼 `GROUP BY 절에 의해 반환되는 결과 집합의 조건을 설정`할 수 있게 해줍니다.

```SQL
SELECT 필드이름, 그룹함수(필드이름)

FROM 테이블이름

[WHERE 조건]

GROUP BY 필드이름

HAVING 조건;
```
 
```SQL
-- Customer 테이블에서 각 도시별 가장 나이가 많은 레코드 중 15살 이상의 레코드만을 선택하는 예제

SELECT Address, Name, MAX(Age) AS MaxAge

FROM Customer

GROUP BY Address

HAVING MaxAge > 15;
```

| address |  name  | maxAge |
|:-------:|:------:|:------:|
|   서울  | 홍길동 |   17   |
|   수원  | 전우치 |   17   |

<br>

***

### **제약 조건**
제약 조건(constraint)이란 데이터의 무결성을 지키기 위해 데이터를 입력받을 때 실행되는 검사 규칙을 의미힌다. 이러한 제약 조건은 `CREATE 문으로 테이블을 생성`할 때나, `ALTER 문으로 필드를 추가`할 때도 설정할 수도 있다.

CREATE TABLE 문에서 사용할 수 있는 제약 조건

 ``` Markdown
1. NOT NULL : 해당 필드는 NULL 값을 저장할 수 없게 됩니다.
2. UNIQUE : 해당 필드는 서로 다른 값을 가져야만 합니다.
3. PRIMARY KEY : 해당 필드가 NOT NULL과 UNIQUE 제약 조건의 특징을 모두 가지게 됩니다.
4. FOREIGN KEY : 하나의 테이블을 다른 테이블에 의존하게 만듭니다.
5. DEFAULT : 해당 필드의 기본값을 설정합니다.
 ```
<br>

***

<details><summary>출처</summary>

http://tcpschool.com/mysql/intro

kocw.net/home/search/kemView.do?kemId=1178046

https://futurists.tistory.com/17

https://mozi.tistory.com/233

</details>