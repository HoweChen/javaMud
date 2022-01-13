# Test block api

GET http://localhost:8080/block

will get "Pass"

# Add flow rule

POST http://localhost:8080/rule/add

Content-Type: application/json

```json
{
  "resource": "GET:/block",
  "controlBehavior": 0,
  "count": 0,
  "grade": 1,
  "limitApp": "default",
  "strategy": 0
}
```

will get 200 status code

# Test block api

GET http://localhost:8080/block

will get "Blocked"