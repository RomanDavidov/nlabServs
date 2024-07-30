package com.example.nlabservs;

public class Companies {

    public String getCompany_ext_name() {
        return company_ext_name;
    }

    public void setCompany_ext_name(String company_ext_name) {
        this.company_ext_name = company_ext_name;
    }

    private String company_ext_name;

    public int getCompany_ext_id() {
        return company_ext_id;
    }

    public void setCompany_ext_id(int company_ext_id) {
        this.company_ext_id = company_ext_id;
    }

    private int company_ext_id;

    public Companies(/*int company_ext_id,*/String company_ext_name) {
        //this.company_ext_id = company_ext_id;
        this.company_ext_name = company_ext_name;
    }
}
