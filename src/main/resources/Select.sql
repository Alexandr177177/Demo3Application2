SELECT name, telephon, education.degree
FROM Employee
JOIN Education On employee.id = education.id_education
WHERE Employee.id=1;