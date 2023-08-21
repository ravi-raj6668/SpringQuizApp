--drop table question if exist;

create table question(
id serial not null primary key,
question_title varchar(250),
option1 varchar(250),
option2 varchar(250),
option3 varchar(250),
option4 varchar(250),
difficulty_level varchar(250),
right_answer varchar(250),
category varchar(250));


select * from question q ;


--used in primary key error such as getting duplicate value 
SELECT setval('question_id_seq', (SELECT MAX(id) FROM question q)+1);
--
INSERT INTO question(id,category,difficulty_level,option1,option2,option3,option4,question_title,right_answer) VALUES (1,'JAVA','Easy','class','interface','extends','implements','Which Java keyword is used to create a subclass?','extends');
INSERT INTO question(id,category,difficulty_level,option1,option2,option3,option4,question_title,right_answer) VALUES (2,'Java','Easy','4','5','6','Compile error','What is the output of the following Java code snippet?
int x = 5;
System.out.println(x++);','5');
INSERT INTO question(id,category,difficulty_level,option1,option2,option3,option4,question_title,right_answer) VALUES (3,'Java','Easy','TRUE','FALSE','0',NULL,'In Java, what is the default value of an uninitialized boolean variable?','FALSE');
INSERT INTO question(id,category,difficulty_level,option1,option2,option3,option4,question_title,right_answer) VALUES (4,'Java','Easy','try','throw','catch','finally','Which Java keyword is used to explicitly throw an exception?','throw');
INSERT INTO question(id,category,difficulty_level,option1,option2,option3,option4,question_title,right_answer) VALUES (5,'Java','Easy','It indicates that a variable is constant.','It indicates that a method can be accessed without creating an instance of the class.','It indicates that a class cannot be extended.','It indicates that a variable is of primitive type.','What does the ''static'' keyword mean in Java?','It indicates that a method can be accessed without creating an instance of the class.');
INSERT INTO question(id,category,difficulty_level,option1,option2,option3,option4,question_title,right_answer) VALUES (6,'Java','Easy','constant int x = 5;','final int x = 5;','static int x = 5;','readonly int x = 5;','What is the correct way to declare a constant variable in Java?','final int x = 5;');
INSERT INTO question(id,category,difficulty_level,option1,option2,option3,option4,question_title,right_answer) VALUES (7,'Java','Easy','for loop','while loop','do-while loop','switch loop','Which loop in Java allows the code to be executed at least once?','do-while loop');
INSERT INTO question(id,category,difficulty_level,option1,option2,option3,option4,question_title,right_answer) VALUES (8,'Java','Easy','To terminate a loop or switch statement and transfer control to the next statement.','To skip the rest of the code in a loop and move to the next iteration.','To define a label for a loop or switch statement.','To check a condition and execute a block of code repeatedly.','What is the purpose of the ''break'' statement in Java?','To terminate a loop or switch statement and transfer control to the next statement.');
INSERT INTO question(id,category,difficulty_level,option1,option2,option3,option4,question_title,right_answer) VALUES (9,'Java','Easy','+','-','*','/','Which Java operator is used to concatenate two strings?','+');
INSERT INTO question(id,category,difficulty_level,option1,option2,option3,option4,question_title,right_answer) VALUES (10,'Java','Easy','HashMap','ArrayList','LinkedList','HashSet','In Java, which collection class provides an implementation of a dynamic array?','ArrayList');
INSERT INTO question(id,category,difficulty_level,option1,option2,option3,option4,question_title,right_answer) VALUES (11,'Python','Easy','count()','size()','length()','len()','Which Python function is used to calculate the length of a list?','len()');
INSERT INTO question(id,category,difficulty_level,option1,option2,option3,option4,question_title,right_answer) VALUES (12,'Python','Easy','[1, 2, 3]','[1, 2, 3, 4]','[4, 3, 2, 1]','Error','What is the output of the following Python code snippet?
x = [1, 2, 3]
x.append(4)
print(x)','[1, 2, 3, 4]');
INSERT INTO question(id,category,difficulty_level,option1,option2,option3,option4,question_title,right_answer) VALUES (13,'Python','Easy','break','continue','pass','return','Which Python statement is used to exit from a loop prematurely?','break');
INSERT INTO question(id,category,difficulty_level,option1,option2,option3,option4,question_title,right_answer) VALUES (14,'Python','Easy','To generate a random number within a given range.','To iterate over a sequence of numbers.','To sort a list in ascending order.','To calculate the length of a string.','What is the purpose of the ''range()'' function in Python?','To iterate over a sequence of numbers.');
INSERT INTO question(id,category,difficulty_level,option1,option2,option3,option4,question_title,right_answer) VALUES (15,'Python','Easy','int','float','str','list','In Python, which data type is mutable?','list');
INSERT INTO question(id,category,difficulty_level,option1,option2,option3,option4,question_title,right_answer) VALUES (16,'Python','Easy','datetime','math','os','sys','Which Python module is used for working with dates and times?','datetime');



create table quiz(
	id serial NOT NULL primary key,
    quiz_title varchar(255) DEFAULT NULL
);


CREATE TABLE quiz_questions (
    quiz_id SERIAL NOT NULL,
    questions_id SERIAL NOT NULL,
    primary key (quiz_id, questions_id),
    constraint quiz
    FOREIGN KEY (quiz_id) REFERENCES quiz (id),
    constraint question
    FOREIGN KEY (questions_id) REFERENCES question (id)
);
