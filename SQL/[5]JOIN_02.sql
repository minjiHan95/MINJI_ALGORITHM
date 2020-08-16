//프로그래머스
//있었는데요 없었습니다

//서브쿼리
select ANIMAL_ID, NAME
from ANIMAL_INS AS A
where ANIMAL_ID in (
    select ANIMAL_ID
    from ANIMAL_OUTS As B
    where A.DATETIME >= B.DATETIME
)
order by A.DATETIME

//Join
select A.ANIMAL_ID, A.NAME
from ANIMAL_INS AS A
left join ANIMAL_OUTS AS B
on A.ANIMAL_ID = B.ANIMAL_ID
where A.DATETIME >= B.DATETIME
order by A.DATETIME