// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package ch.unibas.medizin.osce.domain;

import java.lang.String;

privileged aspect Bankaccount_Roo_JavaBean {
    
    public String Bankaccount.getBankName() {
        return this.bankName;
    }
    
    public void Bankaccount.setBankName(String bankName) {
        this.bankName = bankName;
    }
    
    public String Bankaccount.getIBAN() {
        return this.IBAN;
    }
    
    public void Bankaccount.setIBAN(String IBAN) {
        this.IBAN = IBAN;
    }
    
    public String Bankaccount.getBIC() {
        return this.BIC;
    }
    
    public void Bankaccount.setBIC(String BIC) {
        this.BIC = BIC;
    }
    
}
