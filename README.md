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
        
        Brokers are Kafka servers which contains multiple Topic and its associate patitions.
        As Kafka is a distributed system all the Brokers are grouped under a specific Cluster called 
        Kafka Cluster.
        
        - Given Borker will have only few Topics but not all topics. Means Topics in Kafka is distributed across
          multiple Brokers which ensures data replication (Copy of same data of one Broker is available in another Broker as well.)
          This nature of the Kafka design will ensure data won't be lost in case of any one of the Broker failure.
          
        - Each Broker in a Cluser is assigned with BrokerID which is Integer value.
        
        - Its always best practice to have min of 3 Broker cluster.
        
        - If a process is able to connect any Broker in Cluster it can able to connect/access the entire Broker in a Cluster.
        
        Ex - Kafka Cluster Broker distribution of Topics
        
        Broker 1.          Broker2.             Broker3
        
        Topic-A            Topic-A              Topic-A
        Partition-1.       Partition-0          Partition-2
        
        Topic-A            Topic-A              Topic-A
        Partition-2(ISR)   Partition-1(ISR)     Partition-0 (ISR)
        
        In above example We have a Topic-A with 3 partition and each partition is having another copy in another Broker.
        This process of having copy of a Topic's partition is called **Replication**.
        
        Here Topic-A has another copy of its partition in another Broker so its replication factor is 2.
        
        In Kafka for a given Topic Partition replication factor must be greater than 1 ideally it advices to
        have replication factor as 2 or 3 for given topic partition. 
        
        Any given point of time only ONE Broker can be leader for a given Partition and only 
        that Broker(leader) can receive and Serve the data. The same data will be syncronized with
        other partition (Replica Partition - ISR- In Sync Replica) in another Broker behind the scene automatically.
        
        If a leader Broker goes down then replication Partition in other Broker will become Leader.
        
        
        
        
