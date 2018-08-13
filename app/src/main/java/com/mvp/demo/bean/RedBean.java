package com.mvp.demo.bean;

import java.util.List;

/**
 * @author Wang Yi Bo
 * @date 2018/5/24
 * 作用:
 */

public class RedBean {


    /**
     * ReturnStatus : 0
     * ReturnReason : OK
     * Remarks : 成功
     * Total : 1
     * ReturnMessage : [{"AssetStyles":[{"Font":"奖券","Color":"ff4a4a"}],"KeyId":158223,"YuPenProductId":"1","ProductId":11,"DDH":"DDH-f374d5db-9dbf-488a-976b-f8b87a161e68","ProductName":"年年余","LoanMoney":1000,"LoanRate":"11.50%","TrueRate":0.115,"LoanTermId":2,"LoanTerm":12,"BuyTime":"2018-01-26 07:27:16","StartTime":"2018-01-26 07:27:16","EndTime":"2019-01-26 07:27:16","WYBM":"150456","RedType":null,"RedMoney":null,"IsTransfer":false,"Discounts":null,"InvestmentProgress":0,"TransferBuyMoney":null,"DateTimeNow":"2018-06-19 12:55:02","RedList":[{"RedType":12,"RedMoney":0.001,"RedTypeName":"加息券"}],"Transfer":null,"State":"0003","AllTrueRate":"","ApiPdfState":null},{"AssetStyles":[{"Font":"奖券","Color":"ff4a4a"}],"KeyId":155220,"YuPenProductId":"1","ProductId":19,"DDH":"DDH-0848e225-96ef-4f41-8f24-4eb7c8fc0eab","ProductName":"月月盈","LoanMoney":1000,"LoanRate":"10.80%","TrueRate":0.108,"LoanTermId":2,"LoanTerm":12,"BuyTime":"2018-01-16 21:05:13","StartTime":"2018-01-16 21:05:13","EndTime":"2019-01-16 21:05:13","WYBM":"150455","RedType":null,"RedMoney":null,"IsTransfer":false,"Discounts":null,"InvestmentProgress":0,"TransferBuyMoney":null,"DateTimeNow":"2018-06-19 12:55:02","RedList":[{"RedType":12,"RedMoney":0.004,"RedTypeName":"加息券"}],"Transfer":null,"State":"0003","AllTrueRate":"","ApiPdfState":null},{"AssetStyles":[],"KeyId":155214,"YuPenProductId":"1","ProductId":20,"DDH":"DDH-d5902e29-6744-4207-9d8f-6c93f0c0f83d","ProductName":"季度红","LoanMoney":20000,"LoanRate":"7%","TrueRate":0.035,"LoanTermId":2,"LoanTerm":6,"BuyTime":"2018-01-16 20:26:50","StartTime":"2018-01-16 20:26:50","EndTime":"2018-07-16 20:26:50","WYBM":null,"RedType":null,"RedMoney":null,"IsTransfer":false,"Discounts":null,"InvestmentProgress":0,"TransferBuyMoney":null,"DateTimeNow":"2018-06-19 12:55:02","RedList":[],"Transfer":null,"State":"0003","AllTrueRate":"0.0150/0.0350/0.0600/0.1000","ApiPdfState":null},{"AssetStyles":[{"Font":"红包","Color":"ff4a4a"}],"KeyId":149184,"YuPenProductId":"1","ProductId":20,"DDH":"DDH-d6523bdd-29a1-4e5b-8685-3490b14d2d2d","ProductName":"季度红","LoanMoney":5000,"LoanRate":"7%","TrueRate":0.035,"LoanTermId":2,"LoanTerm":6,"BuyTime":"2017-12-27 19:19:29","StartTime":"2017-12-27 19:19:29","EndTime":"2018-06-27 19:19:29","WYBM":"136825","RedType":null,"RedMoney":null,"IsTransfer":false,"Discounts":null,"InvestmentProgress":0,"TransferBuyMoney":null,"DateTimeNow":"2018-06-19 12:55:02","RedList":[{"RedType":2,"RedMoney":2,"RedTypeName":"返现红包"}],"Transfer":null,"State":"0003","AllTrueRate":"0.0150/0.0350/0.0600/0.1000","ApiPdfState":null},{"AssetStyles":[{"Font":"已预约","Color":"ff4a4a"}],"KeyId":95680,"YuPenProductId":"1","ProductId":20,"DDH":"DDH-e7c7bc89-49b4-482c-95a2-c7d305a81512","ProductName":"季度红","LoanMoney":20000,"LoanRate":"10%","TrueRate":0.1,"LoanTermId":2,"LoanTerm":12,"BuyTime":"2017-06-15 12:37:16","StartTime":"2017-06-15 12:37:16","EndTime":"2018-06-15 12:37:16","WYBM":null,"RedType":null,"RedMoney":null,"IsTransfer":false,"Discounts":null,"InvestmentProgress":2,"TransferBuyMoney":null,"DateTimeNow":"2018-06-19 12:55:02","RedList":[],"Transfer":{"ApplyTime":"2018-06-15 02:00:19","ExpectedReturn":22000,"Term":12,"KeyId":3018409},"State":"0005","AllTrueRate":"0.0150/0.0350/0.0600/0.1000","ApiPdfState":null}]
     */

    private String ReturnStatus;
    private String ReturnReason;
    private String Remarks;
    private String Total;
    private List<ReturnMessageBean> ReturnMessage;

    public String getReturnStatus() {
        return ReturnStatus;
    }

    public void setReturnStatus(String ReturnStatus) {
        this.ReturnStatus = ReturnStatus;
    }

    public String getReturnReason() {
        return ReturnReason;
    }

    public void setReturnReason(String ReturnReason) {
        this.ReturnReason = ReturnReason;
    }

    public String getRemarks() {
        return Remarks;
    }

    public void setRemarks(String Remarks) {
        this.Remarks = Remarks;
    }

    public String getTotal() {
        return Total;
    }

    public void setTotal(String Total) {
        this.Total = Total;
    }

    public List<ReturnMessageBean> getReturnMessage() {
        return ReturnMessage;
    }

    public void setReturnMessage(List<ReturnMessageBean> ReturnMessage) {
        this.ReturnMessage = ReturnMessage;
    }

    public static class ReturnMessageBean {
        /**
         * AssetStyles : [{"Font":"奖券","Color":"ff4a4a"}]
         * KeyId : 158223
         * YuPenProductId : 1
         * ProductId : 11
         * DDH : DDH-f374d5db-9dbf-488a-976b-f8b87a161e68
         * ProductName : 年年余
         * LoanMoney : 1000.0
         * LoanRate : 11.50%
         * TrueRate : 0.115
         * LoanTermId : 2
         * LoanTerm : 12
         * BuyTime : 2018-01-26 07:27:16
         * StartTime : 2018-01-26 07:27:16
         * EndTime : 2019-01-26 07:27:16
         * WYBM : 150456
         * RedType : null
         * RedMoney : null
         * IsTransfer : false
         * Discounts : null
         * InvestmentProgress : 0
         * TransferBuyMoney : null
         * DateTimeNow : 2018-06-19 12:55:02
         * RedList : [{"RedType":12,"RedMoney":0.001,"RedTypeName":"加息券"}]
         * Transfer : null
         * State : 0003
         * AllTrueRate :
         * ApiPdfState : null
         */

        private int KeyId;
        private String YuPenProductId;
        private int ProductId;
        private String DDH;
        private String ProductName;
        private double LoanMoney;
        private String LoanRate;
        private double TrueRate;
        private int LoanTermId;
        private int LoanTerm;
        private String BuyTime;
        private String StartTime;
        private String EndTime;
        private String WYBM;
        private Object RedType;
        private Object RedMoney;
        private boolean IsTransfer;
        private Object Discounts;
        private int InvestmentProgress;
        private Object TransferBuyMoney;
        private String DateTimeNow;
        private Object Transfer;
        private String State;
        private String AllTrueRate;
        private Object ApiPdfState;
        private List<AssetStylesBean> AssetStyles;
        private List<RedListBean> RedList;

        public int getKeyId() {
            return KeyId;
        }

        public void setKeyId(int KeyId) {
            this.KeyId = KeyId;
        }

        public String getYuPenProductId() {
            return YuPenProductId;
        }

        public void setYuPenProductId(String YuPenProductId) {
            this.YuPenProductId = YuPenProductId;
        }

        public int getProductId() {
            return ProductId;
        }

        public void setProductId(int ProductId) {
            this.ProductId = ProductId;
        }

        public String getDDH() {
            return DDH;
        }

        public void setDDH(String DDH) {
            this.DDH = DDH;
        }

        public String getProductName() {
            return ProductName;
        }

        public void setProductName(String ProductName) {
            this.ProductName = ProductName;
        }

        public double getLoanMoney() {
            return LoanMoney;
        }

        public void setLoanMoney(double LoanMoney) {
            this.LoanMoney = LoanMoney;
        }

        public String getLoanRate() {
            return LoanRate;
        }

        public void setLoanRate(String LoanRate) {
            this.LoanRate = LoanRate;
        }

        public double getTrueRate() {
            return TrueRate;
        }

        public void setTrueRate(double TrueRate) {
            this.TrueRate = TrueRate;
        }

        public int getLoanTermId() {
            return LoanTermId;
        }

        public void setLoanTermId(int LoanTermId) {
            this.LoanTermId = LoanTermId;
        }

        public int getLoanTerm() {
            return LoanTerm;
        }

        public void setLoanTerm(int LoanTerm) {
            this.LoanTerm = LoanTerm;
        }

        public String getBuyTime() {
            return BuyTime;
        }

        public void setBuyTime(String BuyTime) {
            this.BuyTime = BuyTime;
        }

        public String getStartTime() {
            return StartTime;
        }

        public void setStartTime(String StartTime) {
            this.StartTime = StartTime;
        }

        public String getEndTime() {
            return EndTime;
        }

        public void setEndTime(String EndTime) {
            this.EndTime = EndTime;
        }

        public String getWYBM() {
            return WYBM;
        }

        public void setWYBM(String WYBM) {
            this.WYBM = WYBM;
        }

        public Object getRedType() {
            return RedType;
        }

        public void setRedType(Object RedType) {
            this.RedType = RedType;
        }

        public Object getRedMoney() {
            return RedMoney;
        }

        public void setRedMoney(Object RedMoney) {
            this.RedMoney = RedMoney;
        }

        public boolean isIsTransfer() {
            return IsTransfer;
        }

        public void setIsTransfer(boolean IsTransfer) {
            this.IsTransfer = IsTransfer;
        }

        public Object getDiscounts() {
            return Discounts;
        }

        public void setDiscounts(Object Discounts) {
            this.Discounts = Discounts;
        }

        public int getInvestmentProgress() {
            return InvestmentProgress;
        }

        public void setInvestmentProgress(int InvestmentProgress) {
            this.InvestmentProgress = InvestmentProgress;
        }

        public Object getTransferBuyMoney() {
            return TransferBuyMoney;
        }

        public void setTransferBuyMoney(Object TransferBuyMoney) {
            this.TransferBuyMoney = TransferBuyMoney;
        }

        public String getDateTimeNow() {
            return DateTimeNow;
        }

        public void setDateTimeNow(String DateTimeNow) {
            this.DateTimeNow = DateTimeNow;
        }

        public Object getTransfer() {
            return Transfer;
        }

        public void setTransfer(Object Transfer) {
            this.Transfer = Transfer;
        }

        public String getState() {
            return State;
        }

        public void setState(String State) {
            this.State = State;
        }

        public String getAllTrueRate() {
            return AllTrueRate;
        }

        public void setAllTrueRate(String AllTrueRate) {
            this.AllTrueRate = AllTrueRate;
        }

        public Object getApiPdfState() {
            return ApiPdfState;
        }

        public void setApiPdfState(Object ApiPdfState) {
            this.ApiPdfState = ApiPdfState;
        }

        public List<AssetStylesBean> getAssetStyles() {
            return AssetStyles;
        }

        public void setAssetStyles(List<AssetStylesBean> AssetStyles) {
            this.AssetStyles = AssetStyles;
        }

        public List<RedListBean> getRedList() {
            return RedList;
        }

        public void setRedList(List<RedListBean> RedList) {
            this.RedList = RedList;
        }

        public static class AssetStylesBean {
            /**
             * Font : 奖券
             * Color : ff4a4a
             */

            private String Font;
            private String Color;

            public String getFont() {
                return Font;
            }

            public void setFont(String Font) {
                this.Font = Font;
            }

            public String getColor() {
                return Color;
            }

            public void setColor(String Color) {
                this.Color = Color;
            }
        }

        public static class RedListBean {
            /**
             * RedType : 12
             * RedMoney : 0.001
             * RedTypeName : 加息券
             */

            private int RedType;
            private double RedMoney;
            private String RedTypeName;

            public int getRedType() {
                return RedType;
            }

            public void setRedType(int RedType) {
                this.RedType = RedType;
            }

            public double getRedMoney() {
                return RedMoney;
            }

            public void setRedMoney(double RedMoney) {
                this.RedMoney = RedMoney;
            }

            public String getRedTypeName() {
                return RedTypeName;
            }

            public void setRedTypeName(String RedTypeName) {
                this.RedTypeName = RedTypeName;
            }
        }
    }
}
