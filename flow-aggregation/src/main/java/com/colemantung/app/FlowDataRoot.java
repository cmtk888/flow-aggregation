package com.colemantung.app;

import com.colemantung.app.node.SrcAppNode;

import java.util.*;

// The root of data tree implemented in singleton
public class FlowDataRoot {
    Map<String, SrcAppNode> srcApps;

    private static FlowDataRoot instance = null;

    private FlowDataRoot() {
        this.srcApps = new HashMap<>();
    }

    public static FlowDataRoot getInstance() {
        if (instance == null)
            instance = new FlowDataRoot();
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
