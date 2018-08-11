package com.zhiyun.dto;

/**
 * @Auther: sunyuntao
 * @Date: 2018/08/11 11:29
 * @Description:
 */
public class BaseUserInfoDto {

    private AccountDto account;

    private PersonalAuthDto personalAuth;

    private LegalPersonDto  legalPerson;

    private EnterpriseAuthDto enterpriseAuth;

    public AccountDto getAccount() {
        return account;
    }

    public void setAccount(AccountDto account) {
        this.account = account;
    }

    public PersonalAuthDto getPersonalAuth() {
        return personalAuth;
    }

    public void setPersonalAuth(PersonalAuthDto personalAuth) {
        this.personalAuth = personalAuth;
    }

    public LegalPersonDto getLegalPerson() {
        return legalPerson;
    }

    public void setLegalPerson(LegalPersonDto legalPerson) {
        this.legalPerson = legalPerson;
    }

    public EnterpriseAuthDto getEnterpriseAuth() {
        return enterpriseAuth;
    }

    public void setEnterpriseAuth(EnterpriseAuthDto enterpriseAuth) {
        this.enterpriseAuth = enterpriseAuth;
    }
}
