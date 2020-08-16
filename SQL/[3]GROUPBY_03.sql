//프로그래머스
//입양 시각 구하기(1)

select HOUR(DATETIME) as HOUR, count(DATETIME) as COUNT
from ANIMAL_OUTS
group by HOUR
having HOUR>=09 and HOUR<=19
order by HOUR;