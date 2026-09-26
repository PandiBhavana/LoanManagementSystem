package com.loanmanagement.service.impl;

import com.loanmanagement.dao.LoanApplicationDao;
import com.loanmanagement.dao.LoanTypeDao;
import com.loanmanagement.dao.UserDao;
import com.loanmanagement.dao.impl.LoanApplicationDaoImpl;
import com.loanmanagement.dao.impl.LoanTypeDaoImpl;
import com.loanmanagement.dao.impl.UserDaoImpl;
import com.loanmanagement.model.LoanApplication;
import com.loanmanagement.model.LoanType;
import com.loanmanagement.model.User;
import com.loanmanagement.service.LoanApplicationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoanApplicationServiceImpl implements LoanApplicationService {

    private static final Logger logger =
            LoggerFactory.getLogger(LoanApplicationServiceImpl.class);

    private LoanApplicationDao loanApplicationDao =
            new LoanApplicationDaoImpl();

    private LoanTypeDao loanTypeDao =
            new LoanTypeDaoImpl();
    private UserDao userDao = new UserDaoImpl();
    @Override
    public void addApplication(LoanApplication application) {
        LoanType loanType =
                loanTypeDao.getLoanTypeById(application.getLoanTypeId());

        if (loanType == null) {
            throw new IllegalArgumentException("Loan type not found");
        }

        if (!"ACTIVE".equals(loanType.getStatus())) {
            throw new IllegalArgumentException(
                    "Loan type is not available");
        }

        if (application.getRequestedAmount() < loanType.getMinAmount()
                || application.getRequestedAmount() > loanType.getMaxAmount()) {

            throw new IllegalArgumentException(
                    "Requested amount is outside the allowed loan amount range");
        }

        if (application.getTenureMonths() <= 0
                || application.getTenureMonths() > loanType.getMaxTenureMonths()) {

            throw new IllegalArgumentException(
                    "Invalid loan tenure");
        }

        application.setStatus("PENDING");

        loanApplicationDao.addLoanApplication(application);

        logger.info("Loan application added successfully");
    }

    @Override
    public LoanApplication getApplicationById(int applicationId) {
        return loanApplicationDao.getLoanApplicationById(applicationId);

    }

    @Override
    public void updateApplication(LoanApplication application) {
        LoanApplication existingApplication =
                loanApplicationDao.getLoanApplicationById(
                        application.getApplicationId());

        if (existingApplication == null) {
            throw new IllegalArgumentException("Application not found");
        }

        if (!"PENDING".equals(existingApplication.getStatus())) {
            throw new IllegalArgumentException(
                    "Only pending applications can be updated");
        }
        if (!"APPROVED".equals(application.getStatus())
                && !"REJECTED".equals(application.getStatus())) {

            throw new IllegalArgumentException(
                    "Application can only be approved or rejected");
        }
        User reviewer = userDao.getUserById(application.getReviewedBy());

        if (reviewer == null) {
            throw new IllegalArgumentException("Reviewer not found");
        }


        if (!"LOAN_OFFICER".equals(reviewer.getRole())) {
            throw new IllegalArgumentException(
                    "Only Loan Officer can approve or reject application");
        }


        loanApplicationDao.updateLoanApplication(application);
        logger.info("Loan application updated successfully");
    }

    @Override
    public void deleteApplication(int applicationId) {

        loanApplicationDao.deleteLoanApplication(applicationId);
    }
}