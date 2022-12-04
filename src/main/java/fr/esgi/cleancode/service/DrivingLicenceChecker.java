package fr.esgi.cleancode.service;

import fr.esgi.cleancode.exception.InvalidDriverSocialSecurityNumberException;

public class DrivingLicenceChecker {

    public boolean checkSocialSecurityNumberValidity(String securitySocialNumber) {
        checkIfSocialSecurityNumberIsNull(securitySocialNumber);
        checkIfSocialSecurityNumberContainsOnlyNumbers(securitySocialNumber);
        checkIfSocialSecurityNumberContainsFifteenNumbers(securitySocialNumber);

        return true;
    }

    public boolean checkIfSocialSecurityNumberIsNull(String socialSecurityNumber) {
        if (socialSecurityNumber == null) throw new InvalidDriverSocialSecurityNumberException("The Social Security Number is required !");
        return true;
    }

    public boolean checkIfSocialSecurityNumberContainsOnlyNumbers(String socialSecurityNumberGiven) {
        char[] chars = socialSecurityNumberGiven.toCharArray();
        for (char c : chars) {
            if (!Character.isDigit(c)) {
                throw new InvalidDriverSocialSecurityNumberException("The Social Security Number need to contain only number : " + socialSecurityNumberGiven + " !");
            }
        }
        return true;
    }

    public boolean checkIfSocialSecurityNumberContainsFifteenNumbers(String securitySocialNumber) {
        if (securitySocialNumber.length() > 15) throw new InvalidDriverSocialSecurityNumberException("The Social Security Number should contains only fifteen numbers !");
        return true;
    }
}
