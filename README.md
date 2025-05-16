"# event-driven-microservices" 
Follow below steps to run kafka on your Windows machine.Also I am using java version "24.0.1" 2025-04-15.

1 - Start Zookeeper.
C:\kafka>.\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties

2 - Start Kafka Server.
.\bin\windows\kafka-server-start.bat .\config\server.properties

3 - Create Topic.
C:\kafka>bin\windows\kafka-topics.bat --create --topic order.created --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1
C:\kafka>bin\windows\kafka-topics.bat --create --topic payment.success --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1
C:\kafka>bin\windows\kafka-topics.bat --create --topic payment.failed --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1

4 - List Topics
C:\kafka>bin\windows\kafka-topics.bat --list --bootstrap-server localhost:9092
