

System Design Terms:

functional requirements - define system behaviour or ser of operations, APIs
Non functioanal requirements - define system qualities like scalability, perfomance, availability, consistency, durability, maintainability, security, testability, hardware cost etc

Authentication - verifies the identity of a user or service.
Authorization  - determines their access rights. basically permissions they have.
VIP - virtual IP, symbolic hostname, dns entries
Load Balancer LB - routes requests across number of servers
Front end web service - initial processing of requests like validation, authernication etc
TLS - protocal that aims to provide privacy and data integrity, decrypts requests and pass unencrypted request to downstream and also encrypt responses while sending themback.
       TLS on LB is expensive. Termination usually handled by sep proxy that runs as a process on frontend hosts.
Server side encryption - encrypts messages, stored in encrypte form and frontend service decrypts messages when sending to consumer
caching  - cache ifo like queue metadata,auth user data, most actively used queues to avoid db call
Rate limiting(throttling) - process of limiting number of requests that u can submit to APIs at given amount of time, avoids request overwhelings ddos
                            leaky bucket algorithm
request dispatching - call services though http requests, metadata service, backend service. 
                        islotate requests to diff services, one service failure should not affect other, bulk head, circuit breaker helps
request deduplication - when response to successful request fails to reach client this happens, 
                        matters for atmost once, exactly once delivery strategies, not to orry for atleast once strategy
                        caching request ids can help to void duplication
usage data collection - gather error and usage info for debugging and audits, like datadog