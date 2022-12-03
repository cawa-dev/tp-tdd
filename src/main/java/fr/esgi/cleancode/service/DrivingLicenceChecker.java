package fr.esgi.cleancode.service;

import fr.esgi.cleancode.exception.InvalidDriverSocialSecurityNumberException;

class DrivingLicenceChecker {

    public Boolean checkSocialSecurityNumberValidity(String securitySocialNumber) {
        if (!checkIfSocialSecurityNumberIsNull(securitySocialNumber) ||
                !checkIfSocialSecurityNumberContainsOnlyNumbers(securitySocialNumber) ||
                !checkIfSocialSecurityNumberContainsFifteenNumbers(securitySocialNumber)) {
            throw new InvalidDriverSocialSecurityNumberException("Security social number " + securitySocialNumber
                    + " is invalid");
        }
        return true;
    }

    Boolean checkIfSocialSecurityNumberIsNull(String socialSecurityNumber) {
        if (socialSecurityNumber == null) {
            return false;
        }
        return true;
    }

    Boolean checkIfSocialSecurityNumberContainsOnlyNumbers(String socialSecurityNumberGiven) {
        char[] chars = socialSecurityNumberGiven.toCharArray();
        for (char c : chars) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    Boolean checkIfSocialSecurityNumberContainsFifteenNumbers(String securitySocialNumber) {
        if (securitySocialNumber.length() == 15) {
            return true;
        }
        return false;
    }
}
