//프로그래머스
//입양 시각 구하기(2)

/*
Left Join 이용해서 풀기

첫번째테이블이름 : TEMP (Union 으로 0~23 시간을 가진 Table을 생성)
SELECT TEMP.HOUR, IFNULL(OUTS.COUNT, 0) AS COUNT
FROM 
    (
        SELECT 0 AS HOUR
        Union SELECT 1
        ...
        Union SELECT 23
    ) AS TEMP

LEFT JOIN 

두번째테이블이름 : OUTS
    (
        SELECT HOUR(DATETIME) as HOUR, COUNT(*) as COUNT
        FROM ANIMAL_OUTS
        Group by HOUR
    ) AS OUTS

ON TEMP.HOUR = OUTS.HOUR

*/

select temp.hour, ifnull(outs.COUNT, 0) as count
from(
    select 0 as hour
    Union SELECT 1
    Union SELECT 2
    Union SELECT 3
    Union SELECT 4
    Union SELECT 5
    Union SELECT 6
    Union SELECT 7
    Union SELECT 8
    Union SELECT 9
    Union SELECT 10
    Union SELECT 11
    Union SELECT 12
    Union SELECT 13
    Union SELECT 14
    Union SELECT 15
    Union SELECT 16
    Union SELECT 17
    Union SELECT 18
    Union SELECT 19
    Union SELECT 20
    Union SELECT 21
    Union SELECT 22
    Union SELECT 23
    ) as temp
left join (
    select hour(DATETIME) as HOUR, count(*) as COUNT
    from ANIMAL_OUTS
    group by HOUR
    ) as outs
on temp.hour = outs.hour;