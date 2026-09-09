SELECT E.name AS EMPLOYEE
FROM Employee as E
JOIN Employee m ON e.managerID = m.id
WHERE E.salary>m.salary;