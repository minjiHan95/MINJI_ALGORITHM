//프로그래머스
//보호소에서 중성화한 동물

select A.ANIMAL_ID, A.ANIMAL_TYPE, A.NAME
from ANIMAL_INS AS A
left join ANIMAL_OUTS As B
on A.ANIMAL_ID = B.ANIMAL_ID
where 
    (A.SEX_UPON_INTAKE = 'Intact Female' and B.SEX_UPON_OUTCOME = 'Spayed Female') or 
    (A.SEX_UPON_INTAKE = 'Intact Male' and B.SEX_UPON_OUTCOME = 'Neutered Male')
order by A.ANIMAL_ID