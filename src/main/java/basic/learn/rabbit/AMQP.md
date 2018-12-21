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
       
       生产者（Producer）：向Exchange发布消息的应用。 
       消费者（Consumer）：从消息队列中消费消息的应用。 
       消息队列（Message Queue）：服务器组件，用于保存消息，直到发送给消费者。
       消息（Message）：传输的内容。 
       交换器（exchange）：路由组件，接收Producer发送的消息，并将消息路由转发给消息队列。
       虚拟主机（Virtual Host）: 一批交换器，消息队列和相关对象。虚拟主机是共享相同身份认证和加密环境的独立服务器域。
       Broker ：AMQP的服务端称为Broker。
       连接（Connection）:一个网络连接，比如TCP/IP套接字连接。
       信道（Channel）：多路复用连接中的一条独立的双向数据流通道，为会话提供物理传输介质。 
       绑定器（Binding）：消息队列和交换器直接的关联