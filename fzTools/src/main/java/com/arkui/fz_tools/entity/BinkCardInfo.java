package com.arkui.fz_tools.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by 84658 on 2017/10/24.
 */

public class BinkCardInfo {

    /**
     * BankCode : BindCardQuery
     * TradeDate : 20171024
     * RetCode : S0001
     * Status : S
     * PartnerId : 200001300057
     * MerUserId : 10
     * InputCharset : UTF-8
     * SignType : RSA
     * AcceptStatus : S
     * BindingCards : [{"bankName":"工商银行","bindingStatus":"1","bkAcctTp":"01","cardBegin":"622203","cardEnd":"2188","mobNo":"18*******95","modifyTime":"20171024104659"}]
     * TradeTime : 110549
     * TrxId : 201710241105591600
     * Sign : Hasd3WPxbAM4efaxfyM3PxG78csDrCXTzY8j5ZhFBI5LAI4VtAx8P5TqN2d7lcyynFMega/cLpckhcWh7xdyqdoTp4x7e164gAorf+/xhd31iqfl2QP2ZIjKYHAovrR150zF+Yg8NdF58O7/2ITARpzKYgkctQQDd78xHXZVu24=
     * RetMsg : 处理成功
     */

    @SerializedName("BankCode")
    private String BankCode;
    @SerializedName("TradeDate")
    private String TradeDate;
    @SerializedName("RetCode")
    private String RetCode;
    @SerializedName("Status")
    private String Status;
    @SerializedName("PartnerId")
    private String PartnerId;
    @SerializedName("MerUserId")
    private String MerUserId;
    @SerializedName("InputCharset")
    private String InputCharset;
    @SerializedName("SignType")
    private String SignType;
    @SerializedName("AcceptStatus")
    private String AcceptStatus;
    @SerializedName("TradeTime")
    private String TradeTime;
    @SerializedName("TrxId")
    private String TrxId;
    @SerializedName("Sign")
    private String Sign;
    @SerializedName("RetMsg")
    private String RetMsg;
    @SerializedName("BindingCards")
    private List<BindingCardsBean> BindingCards;

    public String getBankCode() {
        return BankCode;
    }

    public void setBankCode(String BankCode) {
        this.BankCode = BankCode;
    }

    public String getTradeDate() {
        return TradeDate;
    }

    public void setTradeDate(String TradeDate) {
        this.TradeDate = TradeDate;
    }

    public String getRetCode() {
        return RetCode;
    }

    public void setRetCode(String RetCode) {
        this.RetCode = RetCode;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public String getPartnerId() {
        return PartnerId;
    }

    public void setPartnerId(String PartnerId) {
        this.PartnerId = PartnerId;
    }

    public String getMerUserId() {
        return MerUserId;
    }

    public void setMerUserId(String MerUserId) {
        this.MerUserId = MerUserId;
    }

    public String getInputCharset() {
        return InputCharset;
    }

    public void setInputCharset(String InputCharset) {
        this.InputCharset = InputCharset;
    }

    public String getSignType() {
        return SignType;
    }

    public void setSignType(String SignType) {
        this.SignType = SignType;
    }

    public String getAcceptStatus() {
        return AcceptStatus;
    }

    public void setAcceptStatus(String AcceptStatus) {
        this.AcceptStatus = AcceptStatus;
    }

    public String getTradeTime() {
        return TradeTime;
    }

    public void setTradeTime(String TradeTime) {
        this.TradeTime = TradeTime;
    }

    public String getTrxId() {
        return TrxId;
    }

    public void setTrxId(String TrxId) {
        this.TrxId = TrxId;
    }

    public String getSign() {
        return Sign;
    }

    public void setSign(String Sign) {
        this.Sign = Sign;
    }

    public String getRetMsg() {
        return RetMsg;
    }

    public void setRetMsg(String RetMsg) {
        this.RetMsg = RetMsg;
    }

    public List<BindingCardsBean> getBindingCards() {
        return BindingCards;
    }

    public void setBindingCards(List<BindingCardsBean> BindingCards) {
        this.BindingCards = BindingCards;
    }

    public static class BindingCardsBean {
        /**
         * bankName : 工商银行
         * bindingStatus : 1
         * bkAcctTp : 01
         * cardBegin : 622203
         * cardEnd : 2188
         * mobNo : 18*******95
         * modifyTime : 20171024104659
         */

        @SerializedName("bankName")
        private String bankName;
        @SerializedName("bindingStatus")
        private String bindingStatus;
        @SerializedName("bkAcctTp")
        private String bkAcctTp;
        @SerializedName("cardBegin")
        private String cardBegin;
        @SerializedName("cardEnd")
        private String cardEnd;
        @SerializedName("mobNo")
        private String mobNo;
        @SerializedName("modifyTime")
        private String modifyTime;

        public String getBankName() {
            return bankName;
        }

        public void setBankName(String bankName) {
            this.bankName = bankName;
        }

        public String getBindingStatus() {
            return bindingStatus;
        }

        public void setBindingStatus(String bindingStatus) {
            this.bindingStatus = bindingStatus;
        }

        public String getBkAcctTp() {
            return bkAcctTp;
        }

        public void setBkAcctTp(String bkAcctTp) {
            this.bkAcctTp = bkAcctTp;
        }

        public String getCardBegin() {
            return cardBegin;
        }

        public void setCardBegin(String cardBegin) {
            this.cardBegin = cardBegin;
        }

        public String getCardEnd() {
            return cardEnd;
        }

        public void setCardEnd(String cardEnd) {
            this.cardEnd = cardEnd;
        }

        public String getMobNo() {
            return mobNo;
        }

        public void setMobNo(String mobNo) {
            this.mobNo = mobNo;
        }

        public String getModifyTime() {
            return modifyTime;
        }

        public void setModifyTime(String modifyTime) {
            this.modifyTime = modifyTime;
        }
    }
}
