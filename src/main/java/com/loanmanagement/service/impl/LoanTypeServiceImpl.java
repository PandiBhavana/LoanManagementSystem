package com.loanmanagement.service.impl;

import com.loanmanagement.dao.LoanTypeDao;
import com.loanmanagement.dao.impl.LoanTypeDaoImpl;
import com.loanmanagement.model.LoanType;
import com.loanmanagement.service.LoanTypeService;

public class LoanTypeServiceImpl implements LoanTypeService {

    private LoanTypeDao loanTypeDao = new LoanTypeDaoImpl();

    @Override
    public void addLoanType(LoanType loanType) {
        if (loanType.getMinAmount() < 0) {
            throw new IllegalArgumentException(
                    "Minimum amount cannot be negative");
        }

        if (loanType.getMaxAmount() <= 0) {
            throw new IllegalArgumentException(
                    "Maximum amount must be greater than zero");
        }

        if (loanType.getMinAmount() > loanType.getMaxAmount()) {
            throw new IllegalArgumentException(
                    "Minimum amount cannot be greater than maximum amount");
        }

        if (loanType.getInterestRate() < 0) {
            throw new IllegalArgumentException(
                    "Interest rate cannot be negative");
        }

        if (loanType.getMaxTenureMonths() <= 0) {
            throw new IllegalArgumentException(
                    "Maximum tenure must be greater than zero");
        }

        loanTypeDao.addLoanType(loanType);
    }

    @Override
    public LoanType getLoanTypeById(int loanTypeId) {
        return loanTypeDao.getLoanTypeById(loanTypeId);
    }

    @Override
    public void updateLoanType(LoanType loanType) {
        if (loanType.getMinAmount() < 0) {
            throw new IllegalArgumentException(
                    "Minimum amount cannot be negative");
        }

        if (loanType.getMaxAmount() <= 0) {
            throw new IllegalArgumentException(
                    "Maximum amount must be greater than zero");
        }

        if (loanType.getMinAmount() > loanType.getMaxAmount()) {
            throw new IllegalArgumentException(
                    "Minimum amount cannot be greater than maximum amount");
        }

        if (loanType.getInterestRate() < 0) {
            throw new IllegalArgumentException(
                    "Interest rate cannot be negative");
        }

        if (loanType.getMaxTenureMonths() <= 0) {
            throw new IllegalArgumentException(
                    "Maximum tenure must be greater than zero");
        }

        loanTypeDao.updateLoanType(loanType);
    }

    @Override
    public void deleteLoanType(int loanTypeId) {
        loanTypeDao.deleteLoanType(loanTypeId);
    }
}
