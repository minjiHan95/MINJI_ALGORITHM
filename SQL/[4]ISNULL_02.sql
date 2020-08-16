//프로그래머스
//이름이 있는 동물의 아이디

select ANIMAL_ID
from ANIMAL_INS
where NAME is Not NULL
order by ANIMAL_ID;