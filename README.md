## User Story
A global publishing company that publishes books and journals wants to develop a service to

watermark their documents. Book publications include topics in business, science and media. Journals

don’t include any specific topics. A document (books, journals) has a title, author and a watermark

property. An empty watermark property indicates that the document has not been watermarked yet.

The watermark service has to be asynchronous. For a given content document the service should

return a ticket, which can be used to poll the status of processing. If the watermarking is finished the

document can be retrieved with the ticket. The watermark of a book or a journal is identified by

setting the watermark property of the object. For a book the watermark includes the properties

content, title, author and topic. The journal watermark includes the content, title and author.


## How to run

First you need Java 8  and  can run the application using

mvn spring-boot:run Or you can build the JAR file with

mvn clean package and run the JAR

java -jar target/watermark-provider-0.0.1-SNAPSHOT.jar

## Shipping Easily So lets Dockerize
If you want to build docker image the steps are below

docker build .

docker images //list the images id

docker tag efc63e8b44a8(custom value, image id) baranipek/watermark-provider

docker run -p 8080:8080 baranipek/watermark-provider


##  API EndPoints

  1.You can reach swagger ui via http://localhost:8080/swagger-ui.html and show and list the endpoints
  also dummy inputs are ready by swagger

  2.If you don't want to use swagger lets first create watermark
  
  curl -X POST --header "Content-Type: application/json" --header "Accept: */*" -d "{
    \"author\": \"Stephen\",
    \"title\": \"Marketing Tricks\",
    \"topic\": \"Business\"
  }" "http://localhost:8080/watermark"

  Response: 3 (ticketId)

  2. After 3 seconds Lets see the process of the document.

  curl -X GET --header "Accept: */*" "http://localhost:8080/watermark/3"

  Response Body

    {
      "timestamp": 1492934625341,
      "status": 404,
      "error": "Not Found",
      "exception": "com.watermark.exception.WaterMarkNotCompletedException",
      "message": "Watermark still processing",
      "path": "/watermark/3"
    }


  3. What about after ten seconds.
  
  curl -X GET --header "Accept: */*" "http://localhost:8080/watermark/3"
  
  Response Body

  {content:"book",title:"Marketing Tricks",author:"Stephen",topic:Business}

  ##  Database Console
  if you wish to enable the h2 database console within your app.Once you add this, H2 database console
  will be available at http://localhost:8080/h2-console

ENJOY!
