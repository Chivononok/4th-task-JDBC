import entity.Patient;
import service.PatientService;

import java.util.List;

public class Main {
    public static void main(String[] args){
        PatientService patientService = new PatientService();
        List<Patient> patients;

        System.out.println("=== Все пациенты ===");
        patients = patientService.getPatients();
        for (Patient pat: patients) {
            System.out.println(pat);
        }
        System.out.println("====================");

        System.out.println("=== Все пациенты из палаты 12 ===");
        patients = patientService.getPatientsByChamber(12);
        for (Patient pat: patients) {
            System.out.println(pat);
        }
        System.out.println("=================================");

        System.out.println("=== Все пациенты с простудой старше 30 ===");
        patients = patientService.getAllPatientByDiagnosAndAge("простуда", 30);
        for (Patient pat: patients) {
            System.out.println(pat);
        }
        System.out.println("=========================================");

        System.out.println("=== Все пациенты старше 20 с сортировкой по убыванию ===");
        patients = patientService.getAllPatientElderAge(20);
        for (Patient pat: patients) {
            System.out.println(pat);
        }
        System.out.println("=========================================");

        double avg = patientService.getAvgAge();
        System.out.println("Средний возраст пациентов: " + avg);

        int count = patientService.getCountPatient();
        System.out.println("Всего пациентов: " + count);

        //patientService.addPatient("имя", "фамилия", "диагноз", 12, 33);
        //patientService.deletePatientById(6L);
    }
}
