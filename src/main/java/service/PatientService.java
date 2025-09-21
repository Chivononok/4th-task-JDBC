package service;

import config.PostgreConnection;
import entity.Patient;
import repository.PatientRepository;
import repository.PatientRepositoryImpl;

import java.util.List;
import java.util.stream.Collectors;

public class PatientService {
    private final PatientRepository patientRepository;

    public PatientService(){
        this.patientRepository = new PatientRepositoryImpl(new PostgreConnection());
    }
    public List<Patient> getPatientsByChamber(int chamber){
        List<Patient> patients = patientRepository.getAllPatient();
        return patients.stream()
                .filter(patient -> patient.getChamber().equals(chamber))
                .collect(Collectors.toList());
    }

    public List<Patient> getPatients(){
        List<Patient> patients = patientRepository.getAllPatient();
        return patients;
    }


    public double getAvgAge(){
        double avg = patientRepository.getAveragePatientAge();
        return avg;
    }

    public List<Patient> getAllPatientByDiagnosAndAge(String diagnos, Integer age){
        List<Patient> patients = patientRepository.getAllPatientByDiagnosAndAge(diagnos, age);
        return patients;
    }

    public List<Patient> getAllPatientElderAge(Integer age){
        List<Patient> patients = patientRepository.getAllPatientElderAge(age);
        return patients;
    }

    public int getCountPatient(){
        return patientRepository.getCountPatient();
    }

    public void addPatient(String name, String surname, String diagnos, Integer chamber, Integer age){
        Patient patient = new Patient();
        patient.setName(name);
        patient.setSurname(surname);
        patient.setDiagnos(diagnos);
        patient.setAge(age);
        patient.setChamber(chamber);
        patientRepository.addPatient(patient);
    }

    public void deletePatientById(Long id){
        patientRepository.deletePatientById(id);
    }
}
