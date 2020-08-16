//프로그래머스
//동명 동물 수 찾기

select NAME, count(Name) as COUNT
from ANIMAL_INS
group by NAME
having COUNT >=2
order by NAME