
package system_design.aws;


public class Aws {
  
}


/*
route 53
config
domain regex - elb mapping

elb vs alb

elb is classic default
alb is better with code

target groups - list of instances

abl can route to multiple target grps with trafffic percentages
within target grp round robin and least outstanding requests


simple alb


route 53 to internally route to db ip to avoid redeployment for config
master slave
DR

cloud watch



haproxy nodejs
nodejs instances 
becoz it is not migrated 





nginx as rever proxy for multiple passgenger services on ports



deployement

deploy in cron, run pre db migration 
detach half instances from target grps
deploy ember and keep ready, not activate
deploy in half instances
attach first half
activate frontend
detach second half
deploy second half
attach second half
run post migration




bluegreen deployment, kuberetes level
segragated by stacks or target gps
and incready traffci percentage from 10



cloud watch, zookeeper


*/


