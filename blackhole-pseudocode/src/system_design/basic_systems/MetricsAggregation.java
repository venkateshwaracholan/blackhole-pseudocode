/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package system_design.basic_systems;

/**
 *
 * @author user
 * 1. What Are Metrics? (Basics)

    A metric is a time-series value representing something measurable.

    Types you should know:

    Counter – monotonically increasing (requests served)

    Gauge – current value (CPU %, memory)

    Histogram – distribution of latencies (p50/p95/p99)

    Set/Uniq – unique items (unique users)

    A time series is:

    metricName + labels → values over time


    Example:

    metric: request_count
    labels: {service=payments, region=us}
    values: [12 at t=1s], [15 at t=2s] ...
 */
public class MetricsAggregation {
    
}
