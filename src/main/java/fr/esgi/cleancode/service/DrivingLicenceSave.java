package fr.esgi.cleancode.service;

import fr.esgi.cleancode.model.DrivingLicence;

class DrivingLicenceSave {

    public void save(DrivingLicence drivingLicence) {
        checkIfSocialSecurityNumberIsNull(drivingLicence.getDriverSocialSecurityNumber());
    }

    Boolean checkIfSocialSecurityNumberIsNull(String socialSecurityNumber){
        if(socialSecurityNumber == null){
            return false;
        }
        return true;
    }

    Boolean checkifSocialSecurityNumberContainsNumber(String socialSecurityNumberGiven) {
        char[] chars = socialSecurityNumberGiven.toCharArray();
        for(char c : chars){
            if(!Character.isDigit(c)){
                return false;
            }
        }
        return true;
    }
}
