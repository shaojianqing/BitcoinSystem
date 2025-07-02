package sjq.bitcoin.storage.domain;

import java.sql.Timestamp;

public class TransactionAddress {

    private Long id;

    private String transactionHash;

    private Long transactionOutputIndex;

    private String address;

    private String addressType;

    private Long coinValue;

    private Boolean spendStatus;

    private Timestamp createTime;

    private Timestamp modifyTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTransactionHash() {
        return transactionHash;
    }

    public void setTransactionHash(String transactionHash) {
        this.transactionHash = transactionHash;
    }

    public Long getTransactionOutputIndex() {
        return transactionOutputIndex;
    }

    public void setTransactionOutputIndex(Long transactionOutputIndex) {
        this.transactionOutputIndex = transactionOutputIndex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddressType() {
        return addressType;
    }

    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }

    public Long getCoinValue() {
        return coinValue;
    }

    public void setCoinValue(Long coinValue) {
        this.coinValue = coinValue;
    }

    public Boolean getSpendStatus() {
        return spendStatus;
    }

    public void setSpendStatus(Boolean spendStatus) {
        this.spendStatus = spendStatus;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Timestamp modifyTime) {
        this.modifyTime = modifyTime;
    }
}
