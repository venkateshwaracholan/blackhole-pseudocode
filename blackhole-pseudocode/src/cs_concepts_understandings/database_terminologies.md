single node ACID

Atomicity: either all of the transaction is committed or none.

Consistency: ensures transaction scan bring database from one valid state to another. transactions failing will be rolled back to the consistent states. 
validations: constraints, cascades, triggers, referential integrity are checked

Isolation: during concurrency, transactions accessing the same data are isolated to each other, the changes are not reflected until it is committed. 
isolation ensures database states as if they are executed sequentially

durability: durability ensures once a transaction is committed it will remain consistent even during system failure.


in distributed system, when network Partition failure happens(communication link between nodes fails)
either C or A has to be sacrificed

if we choose C, availability of the nodes of majority(master/leader) is still there, but the availability of minority nodes suffer losing CAP availability.

if we choose A, all nodes are available but data is not consistent.

a CA can exist only in a single node.

partition failures wont occur if there are network redundancies, but CAP is valid only when Network partition fails, otherwise both consistency and availability is achieved.

latency introduces lag in making system consistent across nodes.

BASE for distributed system - AP:

basically available: the system guarantee CAP availability, but data might be inconsistent or old data.

soft state: since the system is eventually consistent, the data is always a soft state that can be updated even without further updated changing its value 
on path to becoming consistent.

eventual consistency: the updates will not be reflected in all the nodes immediately,  but will be eventually updated and old values might be returned until then.


mysql master slave replication lags may make it look like eventually consistent, but it is not. its consistent(valid) and atomic with delays, whereas in eventual 
consistency inconsistent data like  (amt debited being reflected and not the credited) could be partially updated.


cassandra, you can write in all the nodes including replication nodes which makes it highly available. cassandra handles concurrency using timestamp and the last 
write wins, not sure what happens if both notes deceive different updates at the same timestamp which is highly unlikely.


MVCC: 

Locking Granularity:

B- tree indexes:

Transactions:

Hash Indexes:

Geo Spatial Indexes:

Geo spatial data types:

clustered indexes:

Data Caches:

Index caches:

Compressed data:
