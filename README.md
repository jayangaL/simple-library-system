# simple-library-system
# RESTful API for library management 
 steps to get project running
 1. ./mvnw clean package -DskipTests
 2. cp target/simple-library-system-0.0.1-SNAPSHOT.jar src/main/docker
 3. cd src/main/docker
 4. docker build -t my-local-repo/myservice:latest .
 5. docker-compose up
 6. navigate to http://localhost:8082/swagger-ui/index.html API documentation
 7. please navigate to the folder screen-shot-guide for more clarity
