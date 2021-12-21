# RESTaurant
**Continuous Delivering the most fresh Micro-Services in town**
---

## Responsibility for each microservice
- Order > Amin + Youri
- Delivery > Amin + Youri
- Stock > Richard
- Menu  > Daan
- Auth  > Hugo

## Development
With the following commands the development setup can be ran.  
`cd development`  
`docker-compose up`  
  
When activly developing on one micro service the app for that service can be started via the idea instead of the compose file. How to setup?  
1. Edit configuration  
2. Choose the configuration that runs the docker-compose.  
3. enter the names of all services except the container under active development.  
4. Now the service under active development can be ran manually from Intellij via the application entry point.  

## Branching
Branch names should follow this convention:  
  
Feature branch: `microservicename/featurename`  
Main branch per service: `microservicename`  
Main branch: `main`  
  
Merge to main with a pull request via github. After merging make sure the code on main works properly.  

