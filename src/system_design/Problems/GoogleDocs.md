
solution document
https://conclave-team.github.io/conclave-site/


collabedit is a subset of the problem

Google docs is better


create a doc - uniqueid
continuous saving on end of typing
permissions - view,edit, comment
more than 2 ppl can edit at the same time
sharing
highlight who typed what
publish changes immediately to all users
revision history



sockets
redis(?) and later to more permanent store nosql,
store current state and changes - where?
need to store who typed what and where
use a queue to publish



aaaaabbbbaaaa
v    s   v


user, line number, column order(incremental with locking), text
send only the line numbers changing


operations:
insert - text, user, order
update - old_text,new_text, user, order
delete - line number, user, order


as
asdas
dfdd
asdad
gfgfg
fgf





 


