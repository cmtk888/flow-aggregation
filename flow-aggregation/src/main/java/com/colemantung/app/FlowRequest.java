package com.colemantung.app;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class FlowRequest {
    String srcApp;
    String destApp;
    String vpcId;
    int bytesTx;
    int bytesRx;
    int hour;

    public FlowRequest() {
    }

    public FlowRequest(String srcApp, String destApp, String vpcId,
                       int bytesTx, int bytesRx, int hour) {
        this.srcApp = srcApp;
        this.destApp = destApp;
        this.vpcId = vpcId;
        this.bytesTx = bytesTx;
        this.bytesRx = bytesRx;
        this.hour = hour;
    }

    public String getSrcApp() {
        return srcApp;
    }

    public void setSrcApp(String srcApp) {
        this.srcApp = srcApp;
    }

    public String getDestApp() {
        return destApp;
    }

    public void setDestApp(String destApp) {
        this.destApp = destApp;
    }

    public String getVpcId() {
        return vpcId;
    }

    public void setVpcId(String vpcId) {
        this.vpcId = vpcId;
    }

    public int getBytesTx() {
        return bytesTx;
    }

    public void setBytesTx(int bytesTx) {
        this.bytesTx = bytesTx;
    }

    public int getBytesRx() {
        return bytesRx;
    }

    public void setBytesRx(int bytesRx) {
        this.bytesRx = bytesRx;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    @Override
    public String toString() {
        return "src_app: " + srcApp
                + ", dest_app: " + destApp
                + ", vpc_id: " + vpcId
                + ", bytes_tx: " + bytesTx
                + ", bytes_rx: " + bytesRx
                + ", hour: " + hour
                + "\n";
    }
}
