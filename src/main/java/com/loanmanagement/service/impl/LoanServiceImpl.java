package com.loanmanagement.service.impl;

import com.loanmanagement.dao.LoanApplicationDao;
import com.loanmanagement.dao.LoanDao;
import com.loanmanagement.dao.LoanTypeDao;
import com.loanmanagement.dao.impl.LoanApplicationDaoImpl;
import com.loanmanagement.dao.impl.LoanDaoImpl;
import com.loanmanagement.dao.impl.LoanTypeDaoImpl;
import com.loanmanagement.model.Loan;
import com.loanmanagement.model.LoanApplication;
import com.loanmanagement.model.LoanType;
import com.loanmanagement.service.LoanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoanServiceImpl implements LoanService {
    private static final Logger logger =
            LoggerFactory.getLogger(LoanServiceImpl.class);
    private LoanDao loanDao = new LoanDaoImpl();
    private LoanApplicationDao loanApplicationDao =
            new LoanApplicationDaoImpl();
   private LoanTypeDao loanTypeDao=new LoanTypeDaoImpl();
    @Override
    public void addLoan(Loan loan) {

        LoanApplication application =
                loanApplicationDao.getLoanApplicationById(
                        loan.getApplicationId());

        if (application == null) {
            throw new IllegalArgumentException(
                    "Loan application not found");
        }

        if (!"APPROVED".equals(application.getStatus())) {
            throw new IllegalArgumentException(
                    "Loan can be created only from an approved application");
        }

        LoanType loanType =
                loanTypeDao.getLoanTypeById(application.getLoanTypeId());

        if (loanType == null) {
            throw new IllegalArgumentException("Loan type not found");
        }
        loan.setCustomerId(application.getCustomerId());
        loan.setLoanTypeId(application.getLoanTypeId());
        loan.setPrincipalAmount(application.getRequestedAmount());
        loan.setTenureMonths(application.getTenureMonths());
        loan.setInterestRate(loanType.getInterestRate());
        loan.setTotalPayable(loan.getPrincipalAmount());
        loan.setOutstandingAmount(loan.getPrincipalAmount());
        loan.setStatus("ACTIVE");

        loanDao.addLoan(loan);

        logger.info("Loan added successfully");
    }

    @Override
    public Loan getLoanById(int loanId) {

        return loanDao.getLoanById(loanId);
    }

    @Override
    public void updateLoan(Loan loan) {
        loanDao.updateLoan(loan);
    }

    @Override
    public void deleteLoan(int loanId) {
        loanDao.deleteLoan(loanId);
    }
    }

