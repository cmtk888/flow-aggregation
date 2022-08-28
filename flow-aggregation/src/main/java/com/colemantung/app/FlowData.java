package com.colemantung.app;

import java.util.*;

class SrcAppNode {
    String name;
    Map<String, DestAppNode> destApps;
    public SrcAppNode (FlowRequest request) {
        this.name = request.getSrcApp();
        destApps = new HashMap<>();
        destApps.put(request.getDestApp(), new DestAppNode(request));
    }

    public void update (FlowRequest request) {
        DestAppNode node = destApps.get(request.getDestApp());
        if (node == null) {
            destApps.put(request.getDestApp(), new DestAppNode(request));
        } else {
            node.update(request);
        }
    }
}

class DestAppNode {
    String name;
    Map<String, VpcIdNode> vpcIds;
    public DestAppNode (FlowRequest request) {
        this.name = request.getDestApp();
        vpcIds = new HashMap<>();
        vpcIds.put(request.getVpcId(), new VpcIdNode(request));
    }

    public void update (FlowRequest request) {
        VpcIdNode node = vpcIds.get(request.getVpcId());
        if (node == null) {
            vpcIds.put(request.getVpcId(), new VpcIdNode(request));
        } else {
            node.update(request);
        }
    }
}

class VpcIdNode {
    String id;
    Map<Integer, HourNode> hours;
    public VpcIdNode (FlowRequest request) {
        this.id = request.getVpcId();
        hours = new HashMap<>();
        hours.put(request.getHour(), new HourNode(request));
    }

    public void update (FlowRequest request) {
        HourNode node = hours.get(request.getHour());
        if (node == null) {
            hours.put(request.getHour(), new HourNode(request));
        } else {
            node.update(request);
        }
    }
}

class HourNode {
    int hour;
    int bytes_tx;
    int bytes_rx;
    public HourNode (FlowRequest request) {
        this.hour = request.getHour();
        this.bytes_tx = request.bytesTx;
        this.bytes_rx = request.bytesRx;
        aggregateFlow();
    }

    public void update (FlowRequest request) {
        if (hour != request.getHour()) {
            // throw exception
            return;
        }
        this.bytes_tx += request.getBytesTx();
        this.bytes_rx += request.getBytesRx();;
    }

    public void aggregateFlow () {
        AggregatedFlow aggregatedFlow = AggregatedFlow.getInstance();
        List<HourNode> hourNodes = aggregatedFlow.hourlyMap.get(this.hour);
        // Need to update
        if (hourNodes == null) {
            hourNodes = new LinkedList<HourNode>();
            hourNodes.add(this);
            aggregatedFlow.hourlyMap.put(this.hour, hourNodes);
        } else {
            hourNodes.add(this);
            aggregatedFlow.hourlyMap.put(this.hour, hourNodes);
        }
    }
}

public class FlowData {
    Map<String, SrcAppNode> srcApps;
    AggregatedFlow aggregatedFlow;

    private static FlowData instance = null;

    private FlowData() {
        this.srcApps = new HashMap<>();
    }

    public static FlowData getInstance()
    {
        if (instance == null)
            instance = new FlowData();

        return instance;
    }

    public void addRecord(FlowRequest request) {
        SrcAppNode node = srcApps.get(request.srcApp);
        if (node == null) {
            srcApps.put(request.getSrcApp(), new SrcAppNode(request));
        } else {
            node.update(request);
        }
    }
}
