select * from student;

select * from faculty;

select s.name from student as s;

select age, name, count(name) from student
WHERE age > 21
  and age < 23
GROUP BY age, name;

select * from student
ORDER BY age;

select * from student
where name like '%е%';