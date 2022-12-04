package fr.esgi.cleancode.service;

import fr.esgi.cleancode.exception.InvalidDriverSocialSecurityNumberException;

public class DrivingLicenceChecker {

    protected Boolean checkSocialSecurityNumberValidity(String securitySocialNumber) {
        checkIfSocialSecurityNumberIsNull(securitySocialNumber);
        checkIfSocialSecurityNumberContainsOnlyNumbers(securitySocialNumber);
        checkIfSocialSecurityNumberContainsFifteenNumbers(securitySocialNumber);

        return true;
    }

    protected boolean checkIfSocialSecurityNumberIsNull(String socialSecurityNumber) {
        if (socialSecurityNumber == null)
            throw new InvalidDriverSocialSecurityNumberException("The Social Security Number is required !");
        return true;
    }

    protected boolean checkIfSocialSecurityNumberContainsOnlyNumbers(String socialSecurityNumberGiven) {
        char[] chars = socialSecurityNumberGiven.toCharArray();
        for (char c : chars) {
            if (!Character.isDigit(c)) {
                throw new InvalidDriverSocialSecurityNumberException("The Social Security Number need to contain only number : " + socialSecurityNumberGiven + " !");
            }
        }
        return true;
    }

    protected boolean checkIfSocialSecurityNumberContainsFifteenNumbers(String securitySocialNumber) {
        if (securitySocialNumber.length() != 15)
            throw new InvalidDriverSocialSecurityNumberException("The Social Security Number should contains fifteen numbers exactly !");
        return true;
    }
}
