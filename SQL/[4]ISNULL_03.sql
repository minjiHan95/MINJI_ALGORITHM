//프로그래머스
//NULL 처리하기

select ANIMAL_TYPE, IFNULL(NAME, 'No name'), SEX_UPON_INTAKE
from ANIMAL_INS
order by ANIMAL_ID