package fr.esgi.cleancode.service;

import fr.esgi.cleancode.exception.InvalidAvailablesPointsException;
import fr.esgi.cleancode.exception.InvalidDriverSocialSecurityNumberException;

public final class DrivingLicenceCheckerService {

    public void checkValidity(String securitySocialNumber)
            throws InvalidDriverSocialSecurityNumberException {
        checkNullability(securitySocialNumber);
        checkContainsOnlyNumbers(securitySocialNumber);
        checkLength(securitySocialNumber);
    }

    public Boolean checkNullability(String socialSecurityNumber) {
        if (socialSecurityNumber == null)
            throw new InvalidDriverSocialSecurityNumberException("The Social Security Number is required is should not be null !");
        return true;
    }

    public boolean checkContainsOnlyNumbers(String socialSecurityNumberGiven) {
        char[] chars = socialSecurityNumberGiven.toCharArray();
        for (char c : chars) {
            if (!Character.isDigit(c)) {
                throw new InvalidDriverSocialSecurityNumberException("The Social Security Number need to contain only number : " + socialSecurityNumberGiven + " !");
            }
        }
        return true;
    }

    public boolean checkLength(String securitySocialNumber) {
        if (securitySocialNumber.length() != 15)
            throw new InvalidDriverSocialSecurityNumberException("The Social Security Number should contains fifteen numbers exactly !");
        return true;
    }

    public void checkAvailablePoints(int sourceAvailablePoints) throws InvalidAvailablesPointsException {
        if (sourceAvailablePoints != 12) {
            throw new InvalidAvailablesPointsException("You cannot create an Driving Licence with : " + sourceAvailablePoints + " points");
        }
    }
}
