create table quiz
(
   id integer not null UNIQUE,
   name varchar(255) not null,
   description varchar(255), 
   primary key(id)
);

create table quiz_ques
(
   id integer not null UNIQUE,
   name varchar(255) not null,
   options varchar(255), 
   correct_option varchar(255),
   quiz integer,
   points integer,
   primary key(id),
   foreign key(quiz) referencs quiz(id)
);

