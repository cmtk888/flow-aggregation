package com.colemantung.app;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class AggregatedFlow {
    private static AggregatedFlow instance = null;

    public Map<Integer, List<HourNode>> hourlyMap;

    private AggregatedFlow() {
        hourlyMap = new HashMap<>();
    }

    public static AggregatedFlow getInstance()
    {
        if (instance == null)
            instance = new AggregatedFlow();

        return instance;
    }

    public String printHour(int hour) {
        List<HourNode> nodes = hourlyMap.get(hour);
        if (nodes == null) return "No Hourly node was added";

        String str = "";
        for (HourNode n : nodes) {
            str += "Hour " + n.hour + " -- bytes_rx: " + n.bytes_rx + "\n";
        }
        return str;
    }
}