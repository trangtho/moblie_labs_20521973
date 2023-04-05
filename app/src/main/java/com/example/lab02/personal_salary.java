package com.example.lab02;

class personal_salary {
    private String fullName;
    private int grossSalary;

    public personal_salary(){
    }

    public personal_salary(String fullName, int grossSalary){
        this.fullName=fullName;
        this.grossSalary=grossSalary;
    }

    public double netSalary(int gross){
        double temp = gross*(1-0.105);
        if(temp <= 11000000 )
            return temp;
        return 11000 + (temp-11000000) * (1-0.05);
    }

}

