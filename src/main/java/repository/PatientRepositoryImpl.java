package repository;

import config.JdbcConnection;
import entity.Patient;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientRepositoryImpl implements PatientRepository {
    private final String GET_ALL_PATIENTS = "SELECT * FROM hospitalsch.\"Patient\"";
    private final String GET_PATIENT_BY_ID = "SELECT * FROM hospitalsch.\"Patient\" where hospitalsch.\"Patient\".id=?";
    private final String DELETE_PATIENT_BY_ID = "delete from hospitalsch.\"Patient\" where hospitalsch.\"Patient\".id=?";
    private final String GET_ALL_PATIENT_BY_CHAMBER = "select * from hospitalsch.\"Patient\" where hospitalsch.\"Patient\".chember=?";
    private final String GET_ALL_PATIENT_BY_DIAGNOS_AND_AGE="select * from hospitalsch.\"Patient\" where hospitalsch.\"Patient\".diagnos=? and hospitalsch.\"Patient\".age>?";
    private final String GET_ALL_PATIENT_BY_AGE_DESC = "SELECT * FROM hospitalsch.\"Patient\" where hospitalsch.\"Patient\".age>? order by age DESC";
    private final String GET_AVG_AGE = "select avg(age) from hospitalsch.\"Patient\"";
    private final String GET_COUNT_PATIENT = "select count(*) from hospitalsch.\"Patient\"";
    private final String ADD_PATIENT="insert into hospitalsch.\"Patient\" (id, \"name\", surname, diagnos, chamber, age) values(?,?,?,?,?,?)";
    private final String GET_MAX_ID="select max(id) from hospitalsch.\"Patient\"";
    private final JdbcConnection jdbcConnection;

    public PatientRepositoryImpl(JdbcConnection jdbcConnection) {
        this.jdbcConnection = jdbcConnection;
    }


    @Override
    public List<Patient> getAllPatient() {
        List<Patient> patients = new ArrayList<>();
        try (Connection connection = jdbcConnection.getConnection();){
            Statement statement = connection.createStatement();
            statement.executeQuery(GET_ALL_PATIENTS);
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()){
                String id = resultSet.getString(1);
                String name = resultSet.getString(2);
                String surname = resultSet.getString("surname");
                String diagnos = resultSet.getString("diagnos");
                String chamber = resultSet.getString("chamber");
                String age = resultSet.getString("age");

                Patient patient = new Patient(Long.valueOf(id), name, surname, diagnos, Integer.parseInt(chamber),
                        Integer.parseInt(age));
                patients.add(patient);
            }
            return patients;
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Patient findById(Long id) {
        try(Connection connection = jdbcConnection.getConnection();){
            PreparedStatement preparedStatement = connection.prepareStatement(GET_PATIENT_BY_ID);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet==null){
                return new Patient();
            }
            resultSet.next();
            String name = resultSet.getString(2);
            String surname = resultSet.getString("surname");
            String diagnos = resultSet.getString("diagnos");
            String chamber = resultSet.getString("chamber");
            String age = resultSet.getString("age");

            Patient patient = new Patient(id, name, surname, diagnos, Integer.parseInt(chamber),
                    Integer.parseInt(age));
            return patient;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void deletePatientById(Long id){
        try(Connection connection = jdbcConnection.getConnection();){
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PATIENT_BY_ID);
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public List<Patient> getAllPatientByChamber(Integer chamber){
        List<Patient> patients = new ArrayList<>();
        try (Connection connection = jdbcConnection.getConnection();){
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_PATIENT_BY_CHAMBER);
            preparedStatement.setInt(1, chamber);
            preparedStatement.executeQuery();

            ResultSet resultSet = preparedStatement.getResultSet();
            while (resultSet.next()){
                String id = resultSet.getString(1);
                String name = resultSet.getString(2);
                String surname = resultSet.getString("surname");
                String diagnos = resultSet.getString("diagnos");
                String age = resultSet.getString("age");

                Patient patient = new Patient(Long.valueOf(id), name, surname, diagnos, chamber,
                        Integer.parseInt(age));
                patients.add(patient);
            }
            return patients;
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public List<Patient> getAllPatientByDiagnosAndAge(String diagnos, Integer age){
        List<Patient> patients = new ArrayList<>();
        try (Connection connection = jdbcConnection.getConnection();){
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_PATIENT_BY_DIAGNOS_AND_AGE);
            preparedStatement.setString(1, diagnos);
            preparedStatement.setInt(2, age);
            preparedStatement.executeQuery();

            ResultSet resultSet = preparedStatement.getResultSet();
            while (resultSet.next()){
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                String chamber = resultSet.getString("chamber");
                String age1 = resultSet.getString("age");
                Patient patient = new Patient(Long.valueOf(id), name, surname, diagnos,Integer.parseInt(chamber),
                        Integer.parseInt(age1));
                patients.add(patient);
            }
            return patients;
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public List<Patient> getAllPatientElderAge(Integer age){
        List<Patient> patients = new ArrayList<>();
        try (Connection connection = jdbcConnection.getConnection();){
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_PATIENT_BY_AGE_DESC);
            preparedStatement.setInt(1, age);
            preparedStatement.executeQuery();

            ResultSet resultSet = preparedStatement.getResultSet();
            while (resultSet.next()){
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                String chamber = resultSet.getString("chamber");
                String diagnos = resultSet.getString("diagnos");
                String age1 = resultSet.getString("age");

                Patient patient = new Patient(Long.valueOf(id), name, surname, diagnos,Integer.parseInt(chamber),
                        Integer.parseInt(age1));
                patients.add(patient);
            }
            return patients;
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public double getAveragePatientAge(){
        try (Connection connection = jdbcConnection.getConnection();){
            PreparedStatement preparedStatement = connection.prepareStatement(GET_AVG_AGE);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.getResultSet();
            resultSet.next();
            double avg = resultSet.getDouble(1);
            return avg;
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public int getCountPatient(){
        try (Connection connection = jdbcConnection.getConnection();){
            PreparedStatement preparedStatement = connection.prepareStatement(GET_COUNT_PATIENT);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.getResultSet();
            resultSet.next();
            int count = resultSet.getInt(1);
            return count;
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void addPatient(Patient patient){
        try (Connection connection = jdbcConnection.getConnection();){
            PreparedStatement preparedStatementM = connection.prepareStatement(GET_MAX_ID);
            preparedStatementM.executeQuery();
            ResultSet resultSet = preparedStatementM.getResultSet();
            resultSet.next();
            var maxId = resultSet.getLong(1);

            PreparedStatement preparedStatement = connection.prepareStatement(ADD_PATIENT);
            preparedStatement.setLong(1, ++maxId);
            preparedStatement.setString(2, patient.getName());
            preparedStatement.setString(3, patient.getSurname());
            preparedStatement.setString(4, patient.getDiagnos());
            preparedStatement.setInt(5, patient.getChamber());
            preparedStatement.setInt(6, patient.getAge());
            preparedStatement.execute();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
