package com.loanmanagement.service;

import com.loanmanagement.model.LoanApplication;

public interface LoanApplicationService {
    void addApplication(LoanApplication application);

    LoanApplication getApplicationById(int applicationId);

    void updateApplication(LoanApplication application);

    void deleteApplication(int applicationId);
}
