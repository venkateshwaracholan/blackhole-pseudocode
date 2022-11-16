/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package design_patterns.creational;

/**
 *
 * @author vshanmugham
 */

/*
factory pattern with zoho reports example

core idea: provide type to get an object

*/

public class Factory {
  public static void main(String args[]){
    Reports r = new Reports();
    r.generateChart(ViewType.CHART);
    r.generateChart(ViewType.PIVOT);
    r.generateChart(ViewType.SUMMARY);
    r.generateChart(ViewType.TABULAR);
  }
}

interface View{
  void drawChart();
}

enum ViewType{
  CHART,
  PIVOT,
  SUMMARY,
  TABULAR
}

class ChartView implements View{
  public void drawChart(){
    System.out.println("Chart View");
  }
}
class SummaryView implements View{
  public void drawChart(){
    System.out.println("Summary View");
  }
}
class TabularView implements View{
  public void drawChart(){
    System.out.println("Tabular View");
  }
}
class PivotView implements View{
  public void drawChart(){
    System.out.println("Pivot View");
  }
}

// composition class for view interface
class Reports{
  public void generateChart(ViewType type){
    final View view = ViewFactory.createView(type);
    view.drawChart();
    
  }
  
}

// factory class for creating view
class ViewFactory{
  public static View createView(ViewType type){
    View view;
    switch(type){
      case CHART: view = new ChartView();break;
      case PIVOT: view = new PivotView();break;
      case SUMMARY: view = new SummaryView();break;
      default: view = new TabularView();break;
    }
    return view;
  }
}


