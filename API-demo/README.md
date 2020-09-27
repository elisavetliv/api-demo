# Clearing Cost API: A solution based on Elasticsearch

### 1. Purpose
Clearing cost of a credit card is the fee that a bank applies to process a banking exchange. The clearing cost matrix is user-defined and is stored in Elasticsearch as the following matrix:
|Country|Cost (euros)   |
|---|---|
|  DK | 12  | 
|  GR | 13  |
|  UK | 15  | 


So given a credit card number, the API must return the card's country and clearing cost

Th Clearing Cost API provides the mechanism that:

* edits, deletes and gets clearing cost data (see paragraph 3)
* returns a credit card's country and clearing cost (see paragraph 4)

All HTTP requests are commited with basic authentication. The user is **api_tester** and password **test**.


### 2. Requirements
* Elasticsearch. **After installation make sure to create an index named clearing-cost**
* JDK 1.8
* Eclipse or STS 
* Postman

### 3. Opearations on the Clearing Cost Matrix with examples
* **Add clearing cost = 12 for country Denmark**
```
curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X POST http://localhost:8080/clearing-cost -d'{"country":"DK","cost":"12"}' -u api_tester:test
```

* **Get clearing cost of Denmark**
```
curl -i -X GET http://localhost:8080/clearing-cost/DK -u api_tester:test
```

* **Change clearing cost of Denmark to 15**
```
curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X PUT http://localhost:8080/clearing-cost -d '{"country":"DK","cost":"15"}' -u api_tester:test
```

* **Add clearing cost = 8 for country Greece**
```
curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X POST http://localhost:8080/clearing-cost -d'{"country":"GR","cost":"18"}' -u api_tester:test
```

* **Delete clearing cost of Greece**
```
curl -i -X DELETE http://localhost:8080/clearing-cost/GR -u api_tester:test
```

### 4. Get clearing cost of a credit card with example
Let's say that the user wants to get clearing cost of credit card with number **4571 7360 1234 5678**:
```
curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X POST http://localhost:8080/payment-cards-cost -d '{"card_number":"4571 7360 1234 5678"}' -u api_tester:test
```

Then the user should expect the answer:

        {
                "country":"DK",
                "cost":"15"
        }


