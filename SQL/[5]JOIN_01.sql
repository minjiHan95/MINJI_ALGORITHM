//프로그래머스
//없어진 기록 찾기

select A.ANIMAL_ID, A.NAME
from ANIMAL_OUTS as A
left join ANIMAL_INS as B
on A.ANIMAL_ID = B.ANIMAL_ID
where B.ANIMAL_ID is Null