


1. multiple choice
2. fill in the blanks
3. short answers
4. true/false

1. question - 500 varchar

answers
1. number - tiny int
2. string - 100 varchar
3. string - 500 varchar
4. number - tiny int


Tables:
Questions
Answers
Students

Question has one answer - to store

Questions
id question-varchar(500)

Students
id name 

Answers
id question_id student_id answer_type_id answer_type


Answers
id question_id student_id sting_col


mq
id tiny_int

blanks
id varchar(100)

short
id varchar(500)

truefalse
id boolean/tiny_int

long_answer
id varchar(1000)
