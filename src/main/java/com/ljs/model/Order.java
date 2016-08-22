package com.ljs.model;

import javax.persistence.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by lala on 16/8/20.
 */
@Entity
@Table(name = "papa_sdk_order")
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "product_id")
    private String productId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "app_order_id")
    private String appOrderId;

    @Column(name = "pay_type")
    private int payType;

    @Column(name = "openuid")
    private String openuid;

    @Column(name = "order_id")
    private String orderId;

    @Column
    private long time;

    @Column
    private String qudao;

    @Column(name = "pay_amount")
    private String payAmount;

    @Column(name = "game_amount")
    private String gameAmount;

    @Column(name = "order_status")
    private int orderStatus;

    @Column(name = "action")
    private String action;

    @Column(name = "wallet_type")
    private String walletType;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getAppOrderId() {
        return appOrderId;
    }

    public void setAppOrderId(String appOrderId) {
        this.appOrderId = appOrderId;
    }

    public int getPayType() {
        return payType;
    }

    public void setPayType(int payType) {
        this.payType = payType;
    }

    public String getOpenuid() {
        return openuid;
    }

    public void setOpenuid(String openuid) {
        this.openuid = openuid;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Date getTime() {
        return new Date(time*1000);
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getQudao() {
        return qudao;
    }

    public void setQudao(String qudao) {
        this.qudao = qudao;
    }

    public String getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(String payAmount) {
        this.payAmount = payAmount;
    }

    public String getGameAmount() {
        return gameAmount;
    }

    public void setGameAmount(String gameAmount) {
        this.gameAmount = gameAmount;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getWalletType() {
        return walletType;
    }

    public void setWalletType(String walletType) {
        this.walletType = walletType;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", appOrderId='" + appOrderId + '\'' +
                ", payType=" + payType +
                ", openuid='" + openuid + '\'' +
                ", orderId='" + orderId + '\'' +
                ", time=" + time +
                ", qudao='" + qudao + '\'' +
                ", payAmount='" + payAmount + '\'' +
                ", gameAmount='" + gameAmount + '\'' +
                ", orderStatus=" + orderStatus +
                ", action='" + action + '\'' +
                ", walletType='" + walletType + '\'' +
                '}';
    }
}
