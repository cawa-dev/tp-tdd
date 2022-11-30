package fr.esgi.cleancode.service;

import fr.esgi.cleancode.model.DrivingLicence;

public class DrivingLicenceSave {

    public Boolean save(DrivingLicence drivingLicence) {
        if(drivingLicence.getDriverSocialSecurityNumber() == null){
            return false;
        }
        return true;
    }
}
