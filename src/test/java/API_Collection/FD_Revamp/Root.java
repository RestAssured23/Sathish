package FD_Revamp;

import java.util.ArrayList;

public class Root {

    public String holdingProfileId;
    public String schemeId;
    public String companyId;
    public int amount;
    public int tenure;
    public String tenureUnit;
    public String userBankId;
    public String payoutMode;
    public String renewalType;
    public ArrayList<NomineeDetail> nomineeDetails;

    public String getHoldingProfileId() {
        return holdingProfileId;
    }

    public void setHoldingProfileId(String holdingProfileId) {
        this.holdingProfileId = holdingProfileId;
    }

    public String getSchemeId() {
        return schemeId;
    }

    public void setSchemeId(String schemeId) {
        this.schemeId = schemeId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getTenure() {
        return tenure;
    }

    public void setTenure(int tenure) {
        this.tenure = tenure;
    }

    public String getTenureUnit() {
        return tenureUnit;
    }

    public void setTenureUnit(String tenureUnit) {
        this.tenureUnit = tenureUnit;
    }

    public String getUserBankId() {
        return userBankId;
    }

    public void setUserBankId(String userBankId) {
        this.userBankId = userBankId;
    }

    public String getPayoutMode() {
        return payoutMode;
    }

    public void setPayoutMode(String payoutMode) {
        this.payoutMode = payoutMode;
    }

    public String getRenewalType() {
        return renewalType;
    }

    public void setRenewalType(String renewalType) {
        this.renewalType = renewalType;
    }

    public ArrayList<NomineeDetail> getNomineeDetails() {
        return nomineeDetails;
    }

    public void setNomineeDetails(ArrayList<NomineeDetail> nomineeDetails) {
        this.nomineeDetails = nomineeDetails;
    }

}
