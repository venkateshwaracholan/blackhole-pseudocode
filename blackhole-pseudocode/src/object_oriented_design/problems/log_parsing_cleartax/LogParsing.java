/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package object_oriented_design.log_parsing_cleartax;

/**
 *
 * @author vshanmugham
 */

import java.io.*;  
import java.util.*;

class LogObject{
  private String id, method, url, masked_url;
  private int timestamp, response_time, response_code;
  
  LogObject(int timestamp,  String url, String method, int response_time, int response_code){
    this.timestamp = timestamp;
    this.method = method;
    this.url = url;
    this.response_time = response_time;
    this.response_code = response_code;
    assignMastedUrl();
    assignId();
  }
  
  public void assignMastedUrl(){
    this.masked_url = url.replaceAll("[0-9]+", "{id}");
  }
  
  public void assignId(){
    this.id = new StringBuilder(this.method).append("_").append(this.masked_url).toString();
  }
  
  public boolean equals(Object o){
    if(this==o){
      return true;
    }
    if(o==null || !o.getClass().equals(this.getClass())){
      return false;
    }
    LogObject l = (LogObject)o;
    return this.id.equals(l.id);
  }
  
  public int hashCode(){
    return this.id.hashCode();
  }
  
  public String getMaskedUrl(){
    return this.masked_url;
  }
  public String getMethod(){
    return this.method;
  }
  
  public int getresponseTime(){
    return this.response_time;
  }

}

class AggregateObject{
  int minTime, maxTime, totalTime;
  
  AggregateObject(int time){
    this.minTime = time;
    this.maxTime = time;
    this.totalTime = time;
  }
  
  public int getMintime(){
    return this.minTime;
  }
  public int getMaxtime(){
    return this.maxTime;
  }
  public int getTotaltime(){
    return this.totalTime;
  }
  
  public void updateAggregates(int time){
    if(time<minTime){
      minTime = time;
    }
    if(time>maxTime){
      maxTime = time;
    }
    totalTime+=time;
  }
}

public class LogParsing {
  int k = 5;
  Map<LogObject, Integer> logFreqMap= new HashMap();
  Map<LogObject, AggregateObject> aggregateMap = new HashMap();
  Queue<LogObject> minHeap = new PriorityQueue<>((a,b) -> logFreqMap.get(a)==logFreqMap.get(b) ? b.getMaskedUrl().compareTo(a.getMaskedUrl()) : logFreqMap.get(a)-logFreqMap.get(b));
  List<LogObject> topResult= new ArrayList();
  //List<LogObject> aggResult= new ArrayList();
  
  public void populateFreqMap(LogObject l){
    logFreqMap.put(l, logFreqMap.getOrDefault(l, 0)+1);
  }
  
  public void populateAggMap(LogObject l){
    if(aggregateMap.containsKey(l)){
      AggregateObject a = aggregateMap.get(l);
      a.updateAggregates(l.getresponseTime());
    }else{
      aggregateMap.put(l, new AggregateObject(l.getresponseTime()));
    }
    
  }
  
  // 1 2
  // 5
  
// 11 12 13 14 15  
  
  public void constructHeap(){
    for(LogObject l: logFreqMap.keySet()){
      minHeap.add(l);
      if(minHeap.size()>k){
        minHeap.poll();
      }
    }int temp = 0;
    while(minHeap.size()>0 && temp<k){
      topResult.add(minHeap.poll());
      temp++;
    }
    Collections.reverse(topResult);
  }
  
  public void readInputCsv(String path) throws IOException{
     String line = "";
     String splitBy = ",";
     BufferedReader br = new BufferedReader(new FileReader(path));
     String headerLine = br.readLine();
     while ((line = br.readLine()) != null){  
        String[] log = line.split(splitBy);
//        System.out.println("Log [timestamp=" + log[0] + ", url=" + log[1] + ", method=" + log[2] + ", response_time=" + log[3] + ", response_code= " + log[4]);  
        LogObject l = new LogObject(Integer.parseInt(log[0]),log[1],log[2],Integer.parseInt(log[3]),Integer.parseInt(log[4]));
        populateFreqMap(l);
        populateAggMap(l);
     }
  }
  
  public void writeTopThroughPut(String outFilePath)throws IOException{
    FileWriter csvWriter = new FileWriter(outFilePath);
    csvWriter.append("Method");
    csvWriter.append(",");
    csvWriter.append("Url");
    csvWriter.append(",");
    csvWriter.append("Frequency");
    csvWriter.append("\n");
    
    for (LogObject l: topResult) {
      String data = new StringBuilder(l.getMethod()).append(",").append(l.getMaskedUrl()).append(",").append(logFreqMap.get(l).toString()).toString();
      System.out.println(data);
      csvWriter.append(data);
      csvWriter.append("\n");
    }
   
    
  }
  
  public void writeAggregates(String outFilePath)throws IOException{
    FileWriter csvWriter = new FileWriter(outFilePath);
    csvWriter.append("Method");
    csvWriter.append(",");
    csvWriter.append("Url");
    csvWriter.append(",");
    csvWriter.append("Min time");
    csvWriter.append(",");
    csvWriter.append("Max time");
    csvWriter.append(",");
    csvWriter.append("Avg time");
    csvWriter.append("\n");
    
    for (LogObject l: aggregateMap.keySet()) {
      AggregateObject a = aggregateMap.get(l);
      StringBuilder sb = new StringBuilder(l.getMethod()).append(",").append(l.getMaskedUrl()).append(",");
      sb.append(a.getMintime()).append(",");
      sb.append(a.getMintime()).append(",");
      int avgTime = a.getTotaltime()/logFreqMap.get(l);
      sb.append(avgTime).append(",");
      String data = sb.toString();
      System.out.println(data);
      csvWriter.append(data);
      csvWriter.append("\n");
    }
    
  }
  
  public static void main(String args[]){
    
    String inFilePath = "/C:\\Users\\venka\\OneDrive\\Documents\\GitHub\\blackhole-pseudocode\\blackhole-pseudocode\\src\\object_oriented_design\\log_parsing_cleartax\\data.csv";
    String outTopPath =  "./outTopData.csv";
    String outAggPath =  "./outAggData.csv";
    LogParsing parser = new LogParsing(); 
    try   
    { 
      parser.readInputCsv(inFilePath);
      parser.constructHeap();
      parser.writeTopThroughPut(outTopPath);
      System.out.println("\n");
      parser.writeAggregates(outAggPath);
      //System.out.println("L");
    }
    catch (IOException e)   
    {  
      e.printStackTrace();  
    } 
  }
}
