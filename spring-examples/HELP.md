# Kafka Producer-Consumer Polling exaple

This code sample contains example configuration for Kafka Producer and Consumer.It has implementation of following use cases.

  - A Producer which is exposed via a REST GET endpoint.
  - When GET endpoint is called controller will created 100 User events and call Producer.
  - Producer will publish those 100 User events to configured kafka topic.
  
  - A Consumer is configured with polling intervel of 2 mins with max Event consumption count of 50.
  - This consumer will poll evet 2 min intervel and fetch 50 events each time.



