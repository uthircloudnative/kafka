# kafka

# What is Kafka

# Why Kafka

  - Its supports/provides distributed , resilient and fault tolerent data exchange platform.
  - Ability to to scale Horizontaly when we need to handle huge volume of data events.
  - Handles millions of messages/events per second.
  - Provide a highperformance real time messaging system.

# Where Kafka used / Usecases.

   - Messageing systems like MQ.
   - Real time Activity tracking.
   - Gather metrics from various sources from different locations.
   - Application log gatherings.
   - Processing real time data (Stream processing).
   - Create a decoupled systems by reducing system dependecies.
   - Integrate with various technologies like Spark, Flink, Hadoop and other Big Data technologies.
   
# Real World Use Cases
 
   - **LinkedIn** uses Kafka to prevent spam, collect user interactions. Kafka was invented by  **LinkedIn** and its opensourced.
   - <b>Netflix</b> uses kafka to apply recommendations in real-time while users watching its shows.
   - **Uber** uses kaka to gather user,taxi and other trip data in real time.
   
# Components of Kafka

  **Topics** 
  
    Topic is a stream of data. In Kafka publisher (A component/service sends message/data) will write/send 
    its data into a topic. 
    Subscriber (A componenet/service) will read the data from the topic. 
    
    Topic is kind of channel in which stream of data is written and read by participating 
    Publisher and Subscriber components.
    
      - In SQL terms we can consider a Topic is equivalent to a table. 
        In Kafka we can have any number of Topic where its kind of a medium for the data.
      - A tpoic is identified by its name.
      - **Each topics are split by number of partitions **
      - Every partition is ordered like Partition0, Partition1,Partition2 ., etc.
      - Every message sent will be written in any one of the given topic's partition. 
      - Each message will assigned with a incremental id called **offset**. 
        Visibility of the Offset is specific to given partition only.
      - Any message written in a partition is immutable in nature. Once written it can't be modified.
      - Data is writted/assigned to any partition in a topic unless a Key is provided.
      
   **Broker**
        
