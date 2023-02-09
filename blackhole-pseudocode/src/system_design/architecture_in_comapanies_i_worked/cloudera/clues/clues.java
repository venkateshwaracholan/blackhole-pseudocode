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
architecture of clues piepline

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
    
    spark classifier - consumes data from kafka clues raw topic and filters textual info with regex to gather url patterns for kb, community aricles, jiras, 
                    configs, log lines, stack traces, etc
                    uses Ml models to extract important terms from the clue, compare terms with labelled tersm tsv and add weitage to those terms
                    also extract kbids, community article ids jiraids and push the processed clue record to kakfa clues topic
                spark submit on snow nodes, spark jobs are executed in other nodes, reads and writes intermediate data in HDFS parquet files
                finally sends terms data to kafka
    
    clue record:   caseid, meta, textual info, typoe, terms and weight (hbase,3)
    
    
    case clues persister:
        
    
    
    Ml Models: from seeds team, updated into HDFS
    
    drift chatbot
    slack knowledge discovery
    just in time support

    
    
    sdfc -> case: cdm fields, desc and comment(clue) -> regex and filter -> persons(ml), sentences(ml extract) -> categorizer (coe, customer) -> formatted sentences join formatted persons
    
    
    Hbase:
    
    Kite:
    
    Solr:
    
    
    deployments:
    salt states
    salt pillar
    Jenkins
    kubernetes + docker
    docker images
    ise kubernetes depoyments scripts + jinjatemplates + SLS salt state file
    
    datadog + statsd client
    
    github
    
    When case related data gets updated in sfdc, we run a nifi minutely batch poller to get the case updated for the 1 minute time range. 
    And pushes to kafka so that the classifier can consume and process further. This generally takes a few minutes to pass all the services layers 
    and get indexed in solr. But if there is load in any of the layers, it can take longer. 
    
    */
}
