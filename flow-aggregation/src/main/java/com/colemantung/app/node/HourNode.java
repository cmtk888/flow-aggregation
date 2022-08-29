package com.colemantung.app.node;

import com.colemantung.app.AggregatedFlow;
import com.colemantung.app.FlowRequest;

import java.util.LinkedList;
import java.util.List;

public class HourNode {
    int hour;
    int bytes_tx;
    int bytes_rx;
    // Parent Node
    VpcIdNode parentNode;

    public HourNode(FlowRequest request, VpcIdNode parentNode) {
        this.hour = request.getHour();
        this.bytes_tx = request.getBytesTx();
        this.bytes_rx = request.getBytesRx();
        this.parentNode = parentNode;
        aggregateFlow();
    }

    public void update(FlowRequest request) {
        if (hour != request.getHour()) {
            // throw exception
            return;
        }
        this.bytes_tx += request.getBytesTx();
        this.bytes_rx += request.getBytesRx();
    }

    public void aggregateFlow() {
        AggregatedFlow aggregatedFlow = AggregatedFlow.getInstance();

        List<HourNode> hourNodes = aggregatedFlow.hourlyMap.get(this.hour);
        if (hourNodes == null) {
            hourNodes = new LinkedList<HourNode>();
        }

        hourNodes.add(this);
        aggregatedFlow.hourlyMap.put(this.hour, hourNodes);
    }

    public VpcIdNode getParentNode() {
        return parentNode;
    }
    public int getHour() {
        return hour;
    }

    public int getBytesTx() {
        return bytes_tx;
    }

    public int getBytesRx() {
        return bytes_rx;
    }
}
