package pl.pollub.mw.pai_lab01;
import java.io.Serializable;


public class LoanBean implements Serializable{
    
    // 
    private double quota_credit = 1000;
    private double yearly_percent = 10;
    private int installments_number = 10;

    public double getQuota_credit() {
        return quota_credit;
    }

    public void setQuota_credit(double quota_credit) {
        this.quota_credit = quota_credit;
    }

    public double getYearly_percent() {
        return yearly_percent;
    }

    public void setYearly_percent(double yearly_percent) {
        this.yearly_percent = yearly_percent;
    }

    public int getInstallments_number() {
        return installments_number;
    }

    public void setInstallments_number(int installments_number) {
        this.installments_number = installments_number;
    }
    
    
    public double getLoan(){
        return 0.0;
    }
    
}
