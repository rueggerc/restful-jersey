@echo off

rem File
curl -H "Content-Type: application/json" -X POST http://localhost:8080/restful-jersey/rest/books/book -d "@data/newBook1.json"

rem Inline
curl -i -H "Content-Type: application/json" -d "{\"title\":\"MyBook\", \"author\":\"Joe Smith\", \"numberOfPages\":\"42\"}" ^
     -X POST http://localhost:8080/restful-jersey/rest/books/book



