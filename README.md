# kafka

# What is Kafka

Kafka is a **distibuted** **fault tolerent** highly efficient message/data/event processing platform.

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
        
   **Producer**
   
        Producer is a component which sends/writes data into kafka topic-partition. It's client/Publisher which writes 
        data into a Topic.
        
        When a Producer/Publisher/Client connects to a Kafka and send's its data/message Kafka infrastructur
        will know in which given data needs to be written and wite the data into specific leader topic partition.
        
        Idellay each producer will send the data with a **Key** associated with it. For the first
        message Kafka will place the message in a Leader topic partition then subsequent
        messages will be automatically will go the same topic partition by default. 
        
        Similarly when a data is written to a leader partition the it will be automatically
        replicated to its relica partitin as well by the kafka.
        
        When there is no key is mention in a given Message then Kafka will use round robin
        method to identify to which Topic partition that data needs to be sent.
        
        When Producer/Publisher writes any message to Kafka it will receive acknowledgement.
        based on Producer/Publisher prefernce.
        
        There are 3 modes of acknowledgements possible in kafka. A procuder can choose any
        one of them while sending data to Kafka.
        
         - acks=0 Producer won't wait for acknowledgement in this mode. So there is a 
                  possibility of a data loss.
             
         - acks=1 Producer will wait for acknowledgement from Leader Topic partition.
                  In this mode partial data loss is possible in case a Broker fails and replica
                  didn't get the written data.
                  
         - acks=2 Producer will wait for acknowledgement from both Leader and all replicas.
                  In this case no data loss is possible.
                  
  **Consumer**
  
     Consumer is process/application which connects to a Kafka topic and read data from a specicific
     topic.
     
     Any consuming application upon connecting to Kafka will know which broker topic to be read.
     
     In case of Broker failure Consumers will know how to recover using Consumer Offset values.
     
     Any Consumer will data in any given topic in Offset order only. Which mean in which
     order those messages are published in the same order only a Consumer can be able to read.
     
     A same Consumer can able to read multiple Partition data at the same time. 
     
     Consumers are grouped together based on the data/partition in which its reading data
     and a Consumer Group can be formed.
     
     If we have too many Consumers in a group which read data from a specific topic then
     few of the Consumers will be inactive.
     
     Consumers (in a ConsumerGroup) <= Total Number of Topic-Partition.
     
  **Consumer OffSet**
  
     Kafka will trake each Consumer Group's Consumers in which OffSet data they are reading. This information
     is stored or commited live in a Kafka Topic name __consumer__offsets.Using this reference Consumers can
     able to recover its reading operation in case of any Broker failure while reading data from a Topic.
     
     Commiting data into this consumer_offset is taken care by Kafka based on some configuration
     called Delivery Semantics.
     
     Thera are 3 types of Delivery Semantics Configuration is possible.
     
       - Atmost Once --> Offsets are committed as soon as message is received by Consumer.
                         In this case in case during message processing if any error then that
                         message will be lost.
                         
       - Atleast Once --> Offsets are committed once message is processed successfully.
                          In case any error during processing Consumer can read the message again
                          and reprocess.
                          Downside of this approach is there is a possibility of duplicate message
                          processing.
                          
       - Exactly Once --> This can be achieved for Kafka -> Kafka workflows.
       
  **Broker Discovery**
  
    In a Kafka Cluser each and every Kafka Broker will have information about each and every broker and its
    Topics and its associated partition information. Any Broker in a Kafka Cluster will have this information
    of other Brokers data as meta-data.
    
    With this meta-data any Kafka Client/Consumer app by connecting to a one Broker can able to get the
    information about all other Brokers in the Cluster using this meta-data. This process is referred as
    Broker Discovery.
    
    Due to this reason any Kafka Broker is called a bootstrap server.
    
 **Zookeeper**
 
    Zookeeper manages all the Brokers in a Cluster. It will holds all the meta-data like information of 
    the Broker and its aprtitions.
    
    It will send notifications to Kafka infra whenever a Broker is provisined and Topic or Partition
    created,deleted or updated etc.
    
    Zookeeper is also distributed in nature as it has a Leader and one are more followers.
    Leader will take responsiblity of all Write kind of operations whereas followers
    will take care of Read operation of its associated Brokers and its topics.
    
    To run Kafka we must run ZooKeeper as well without it Kafka won't start.

In a Kafka Cluster when we have replication factor of N (4 Brokers), Producer and Consumers can
tolerate N-1 broker(max of 3 Brokers) being down for any reason. This ensuers high availability.

# Kafka Local Setup and Running locally.

To down load latest version of Kafka follow this link.

https://kafka.apache.org/downloads

To start Kafka locally make sure your executing following commands from Kafka bin folder or
providing bin folder directory before the actual commad.

**Before installing Kafka your local system must have Java installed**


 - Start Zookeeper first. 
 ```
    bin/zookeeper-server-start.sh config/zookeeper.properties
    
 ```
    
 - If Zookeeper started successfully then start Kafka Server/Broker in a sepearate window.
 
 ```
    bin/kafka-server-start config/server.properties
    
 ```
 - Create a topic named user-reg-events

```
    bin/kafka-topics.sh --create --topic user-reg-events --bootstrap-server localhost:9092
    
```

- To display properties of the created topic. 

```
   bin/kafka-topics.sh --describe --topic user-reg-events --bootstrap-server localhost:9092
   
   Following info will get printed. By default Kafka created a topic with 1 partition and 1 replica as we are running on this command on
   single broker so far.
   
   Topic: user-reg-events	TopicId: ui1BhWvsSkGMRFr0mYekqg	PartitionCount: 1	ReplicationFactor: 1	Configs: segment.bytes=1073741824
	 Topic: user-reg-events	Partition: 0	Leader: 0	Replicas: 0	Isr: 0
   
```

 - To list all the topics in a broker.

```
  bin/kafka-topics.sh --list --topic user-reg-events --bootstrap-server localhost:9092
  
```
    
     
     
     
     
     
     
     
     
         
         
        
        
        
        
