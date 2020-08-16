//프로그래머스
//아픈 동물 찾기

select ANIMAL_ID, NAME
from ANIMAL_INS
where INTAKE_CONDITION = 'Sick'
order by ANIMAL_ID