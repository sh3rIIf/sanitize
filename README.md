#### PRODUCTION DEPLOYMENT
1. Add docker build file to containerise the app
2. Add docker-compose file to repo
3. Add a job to ship container to registry(see point 7 under WHAT CAN BE IMPROVED)
4. Use docker-compose file to deploy anywhere. Docker daemon also allows remote deployment which makes it VERY easy to deploy anytime, anywhere and with very little downtime

#### PROBLEM
A simple replace on the unsanitized string will not work for all scenarios. For example words within words(OWN in BROWN). We could use regex to get around that problem but there is still a problem when the string in the database contains multiple words. I managed to get around most of the problems by getting the index of a word in the unsanitized string and adding it to a custom word map. A seperate loop will then go through the word map replacing strings in the unsanitized string. However, the drawback of this method is that words within words is still a problem because we allow the string in the database to be made up of multiple words.  
Some examples to explain the problem:  
Words to repace: BEGIN, MIDDLE, END, BROWN, OWN  
Unsanitized string = BEGIN this is a MIDDLE problem MIDDLE string with the word BROWN END  
Expected result = ***** this is a ****** problem ****** string with the word ***** ***  
The endpoint will provide the expected result but if we change BROWN to BROWNED only BROWN will be replaced leaving *****ED when the word should not have been replaced at all  

#### WHAT CAN BE IMPROVED
1. Caching - By caching the database table performance can be increased. Each time a string is sanitized the entire table has to be fetched from the database. Caching the table will negate the need to make use of the database for costly full table scans. The cache can be updated during modify operations on the table.
2. Encrypt secrets on properties file - Spring has built in functionality to encrypt secrets in properties file with use of ENC()
3. Validation on endpoints - Add validation to request objects
4. Authentication on endpoints
5. Security and vulnerability scanning - Adding OWASP to build script is easy and can generate a report for any issues during build
6. Sonar - Add jacoco and OWASP reports to Sonar
7. Add CI/CD scripts - for example when using Jenkins we can create pipeline scripts which can be maintained within the repo: CI, Build, Ship, Deploy
8. Containerisation - A docker build file can be added and maintained within the repo
9. Test containers - by using test containers we negate the need for hosting anything for integration testing eg database, redis caching, mq, etc

#### CODING
The coding standard creates a predictable model that makes it easy to estimate development effort. New endpoint? Simple, we know we need a controller, request and response objects, a service layer and a repo layer if database integration is required. If data manipulation is required eg masking or encrypting values then we can just add a repo adaptor layer before the actual repo layer. Third party integration? Just add an adaptor layer that can be used by the service.  
There is a code coverage report ./target/site/jacoco/index.html after a maven install

#### TECH STACK
For any external infrastructure requirements I have added a docker-compose file under the docker directory to allow for easy startup of a MS SQL database. If caching is introduced we can just add a redis container