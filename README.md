# RabbitMQ client Shutdown POC

Simple POC to show the shutdown behaviour while RabbitMQ client.

## Setup

- Start a clean instance of RabbitMQ. `docker-compose.yml` is provided to facilitate this; use `docker-compose up -d`.

## Replication

`Send.java` will publish 20 messages to RabbitMQ queue.  
`Recv.java` will consume message, wait 10 seconds then acknowledge.

1. Login to rabbitmq at http://localhost:15672. Username/password: `guest`
2. Navigate to http://localhost:15672/#/queues/%2F/sac_queue to view queue.
3. Run `Send.java` to populate messages in queue
4. Start 2 instances of `Recv.java`
    * You may need to allow multiple instances in your IDE. Example IntelliJ:![img.png](img.png) 
5. View application logs.
6. Notice how only one instance processes messages.
6. Gracefully shutdown instance processing messages.
    * IDE shutdown button. Alternative on linux: `ps aux | grep receiver` to get process id. `kill -INT <process_id>`
7. Notice how second instance does not start processing messages until first instance is closed.

Message order is maintained. No bug detected.
