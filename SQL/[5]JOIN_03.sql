//프로그래머스
//오랜 기간 보호한 동물(1)

select A.NAME, A.DATETIME
from ANIMAL_INS AS A
left join ANIMAL_OUTS AS B
on A.ANIMAL_ID = B.ANIMAL_ID
where B.ANIMAL_ID is NULL
order by A.DATETIME
limit 3