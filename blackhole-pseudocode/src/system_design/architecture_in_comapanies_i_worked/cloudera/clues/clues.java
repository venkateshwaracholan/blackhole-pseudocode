/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package system_design.architecture_in_comapanies_i_worked.cloudera.clues;

/**
 *
 * @author venka
 */

/*
architecture of clues piepline - ownership

*/

public class clues {
    /*
    
    components: salesforce, nifi, kafka, spark classifier stream processor(scala + ML), Ml models and labelelled terms tsv in HDFS, kafka, clues persister, hbase, solr, clues API
    
    salesforce: data source, case data - case title, desc, CDM fields, comments, attachments, other meta data
    
    Nifi: 4 nifi processors
        salesforcebatch poller, salesforce retriever, caseclues retriever, kafka publisher
        each has an out put queue except kasfka publisher
    
    case title, desc, cdm fields, comments
    cdm fields: issue clarification, solution provided, problems statement, etc
    
    salesforce batch poller - polls sf every minute to fetch sfdcid for new cases or updated ones and passes Sfids to queue, we use A TIMESTAMP checkpoint for
                            all data that has been successfully sent to queue, and poll and read data between now and last successful timestamp and update it if success
    salesforce retriever - retrieves case details from sfdcid and pushes id,type  to queue type: case, comment, id resp caseId and comment ID, 
                            for every case there are many comments, so outputs many records for every sfdc ID
    caseclues retriever - retrieves info based on type, for case, it gathers case title, desc, 6 cdm fields, puts one entry for each in next queue,
                            so case clues retriever creates more records for every record
    kafka publisher - publishes to the topic, raw clues in kafka
    
    1 raw clue is caseid, type, textual info(can be title, desc, CDm fields, comments), other meta
    
    sdfc -> case: cdm fields, desc and comment(raw clue) -> regex and filter -> persons(ml), sentences(ml extract) -> categorizer (coe, customer) -> formatted sentences join formatted persons
    
    spark classifier - consumes batch of data from kafka clues raw topic and filters textual info with regex to gather url patterns for kb, community aricles, jiras, 
                    configs, log lines, stack traces, etc
                    uses Ml models to extract important terms from the clue, compare terms with labelled tersm tsv and add weitage to those terms
                    also extract kbids, community article ids jiraids and push the processed clue record to kakfa clues topic
                spark submit on snow nodes, spark jobs are executed in other nodes, reads and writes intermediate data in HDFS parquet files
                finally sends clue record to kafka
    
    Ml Models: from seeds team, updated into HDFS
    
    clue record:   caseid, meta, textual info, typoe, terms and weight (hbase,3)
    
    
    case clues persister: spring app + maven + docker
        consumes a batch(usually 500) of clue records from clues kafka topic and
        merges all clues for single case ID and stores into hbase, replaces certain info and accumulates info like terms, loglines, configs, stack traces, kbids, jira ids,
        community article ids etc
        pushes case records to solr for searching, so creates a searchable technical contect stripped of unwanted terms, logs, configs, stack traces, kb, community and jiras etc
        runs a background job to get kbs and jiras and community articles from impala and put index into solr
        for kbs, community articles and jiras, we accumulate a merged terms from cases referencing the articles url ans use that as searchable tech content, we also
        index body, desc, summary, and number of referencing cases, views, likes, impressions etc for leveraging it in search
        since batching,  we gather kbs,jiras, community of all uniq cases in batch and index them with updated info but we have a limit of max associations to 100
        as possibility of an article refernced in 100 cases is very rare
        
    impala:
        Apache Impala is an open source massively parallel processing (MPP) SQL query engine for data stored in a computer cluster running Apache Hadoop
        Impala brings scalable parallel database technology to Hadoop, enabling users to issue low-latency SQL queries to data stored in HDFS and Apache 
        HBase without requiring data movement or transformation. Impala is integrated with Hadoop to use the same file and data formats, metadata, security 
        and resource management frameworks used by MapReduce, Apache Hive, Apache Pig and other Hadoop software.
        
    clues API: 
        queries solr, to get, similar cases, kbs, community articles, jiras for a case number for customer operations engineers to use
        we fetch case details like terms, configs, loglines, stack traces from hbase and then construct a solr query with teh data 
        and search against technical_content over all the entities living in solr and filtering entities based on the APIs
        for similar kbs, community and jira, we get terms accumated from referencing cases in the search and also search in the body of such entities
        we aslo boost certains parameter we are interested in , like views, likes, number of case references
        
    
    Hbase: we store case data with terms and , case-entity relationships like kbid, jiraid, community article id, list of cases refrencing it
            
    Kite: we use kite model with AVRO, arow based format to store data in hbase and store data in binary compressed format
    
    Solr: we use solr for searching similar entities based on the terms, solr should be backed by a durable database
        as changes to solr schema or upgrades in major version requires reindexing, so reindexing scripts are exposed via API to
        reindex entity information from hbase to solr, ths is rare though 
    
    
    deployments: olson, staging and prod
    salt states - we store all configurations and properties in salt states
    salt pillar - we store all credentials in salt pillar
    Jenkins - one click deployments with scripts, builds and deployments in jenkins for each env 
    kubernetes + docker
    docker images 
    ise kubernetes depoyments scripts + jinjatemplates + SLS salt state file
    
    monitoring
    datadog + statsd client
    
    github
    
    When case related data gets updated in sfdc, we run a nifi minutely batch poller to get the case updated for the 1 minute time range. 
    And pushes to kafka so that the classifier can consume and process further. This generally takes a few minutes to pass all the services layers 
    and get indexed in solr. But if there is load in any of the layers, it can take longer. 
    
    CTX Integration
        clues entities APIs are consumed by CTX - cloudera troubleshooting experience service
    CSH integration
        clues entities APIs are consumed by CSH - cloudera troubleshooting experience service
    
    services accessing data from clues solr collections
        drift chatbot
        slack knowledge discovery
        just in time support
    
    
    tasks
        set up nifi processors
        adding kbs, community and jiras, craeted hbase tables using kite, 
        scheduled job to load kbs, community and jiras from impala
        reindexing scripts for solr upgrades and schema changes
        pagination, filtering, solr query boosting
        staging refreshes
        version upgrades for log4j vulnerability
        added ssl with csr, .p12
    */
}
