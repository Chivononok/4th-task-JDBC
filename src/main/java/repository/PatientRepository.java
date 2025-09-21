package repository;

import entity.Patient;

import java.util.List;

public interface PatientRepository {

    List<Patient> getAllPatient();

    Patient findById(Long id);
    double getAveragePatientAge();
    void deletePatientById(Long id);
    List<Patient> getAllPatientByChamber(Integer chamber);
    List<Patient> getAllPatientByDiagnosAndAge(String diagnos, Integer age);
    List<Patient> getAllPatientElderAge(Integer age);
    int getCountPatient();
    void addPatient(Patient patient);
}
