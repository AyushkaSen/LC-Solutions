SELECT email
FROM Person
Group by email
Having count(id)>1;