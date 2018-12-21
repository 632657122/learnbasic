AMQP 如何实现通信的
- 建立连接Connection。由producer和consumer创建连接，连接到broker的物理节点上。 
- 建立消息Channel。Channel是建立在Connection之上的，一个Connection可以建立多个Channel。
        producer连接Virtual Host 建立Channel，Consumer连接到相应的queue上建立Channel。 
- 发送消息。由Producer发送消息到Broker中的exchange中。 
- 路由转发。exchange收到消息后，根据一定的路由策略，将消息转发到相应的queue中去。 
- 消息接收。Consumer会监听相应的queue，一旦queue中有可以消费的消息，queue就将消息发送给
        Consumer端。 
- 消息确认。当Consumer完成某一条消息的处理之后，需要发送一条ACK消息给对应的Queue。
       Queue收到ACK信息后，才会认为消息处理成功，并将消息从Queue中移除；如果在对应的Channel断开后，
       Queue没有收到这条消息的ACK信息，该消息将被发送给另外的Channel。至此一个消息的发送接收流程走完
       了。消息的确认机制提高了通信的可靠性。