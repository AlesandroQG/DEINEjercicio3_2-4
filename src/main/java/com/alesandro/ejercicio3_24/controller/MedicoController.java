package com.alesandro.ejercicio3_24.controller;

import com.alesandro.ejercicio3_24.MedicoApplication;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

/**
 * Clase controladora de la vista principal de la aplicación
 */
public class MedicoController implements Initializable {
    /**
     * Mapa con los datos a cargar en el informe
     */
    private HashMap<String, Object> parameters;

    @FXML // fx:id="codMedico"
    private TextField codMedico; // Value injected by FXMLLoader

    @FXML // fx:id="direccionPaciente"
    private TextField direccionPaciente; // Value injected by FXMLLoader

    @FXML // fx:id="especialidadMedico"
    private TextField especialidadMedico; // Value injected by FXMLLoader

    @FXML // fx:id="nombreMedico"
    private TextField nombreMedico; // Value injected by FXMLLoader

    @FXML // fx:id="nombrePaciente"
    private TextField nombrePaciente; // Value injected by FXMLLoader

    @FXML // fx:id="numPaciente"
    private TextField numPaciente; // Value injected by FXMLLoader

    @FXML // fx:id="tratamiento"
    private TextArea tratamiento; // Value injected by FXMLLoader

    /**
     * Función que se ejecuta cuando se inicia la ventana
     *
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        parameters = new HashMap<String, Object>();
        tratamiento.setText("Tomar xxx cada 8 días durante 3 semanas\nTomar yyy cada 8 días durante 3 semanas\nTomar zzz cada 8 días durante 3 semanas");
    }

    /**
     * Función que se ejecuta al pulsar el botón "Generar informe". Valida y muestra el formulario
     *
     * @param event evento del usuario
     */
    @FXML
    void generarInforme(ActionEvent event) {
        String error = validar();
        if (error.isEmpty()) {
            lanzarInforme();
        } else {
            mostrarAlerta(error);
        }
    }

    /**
     * Función que valida el formulario
     *
     * @return mensaje de error
     */
    public String validar() {
        String error = "";
        // Número Paciente
        if (numPaciente.getText().isEmpty()) {
            error += "El campo de número del paciente no puede estar vacío\n";
        } else {
            if (validateInt(numPaciente.getText())) {
                parameters.put("numPaciente", numPaciente.getText());
            } else {
                error += "El campo de número del paciente tiene que ser numérico\n";
            }
        }
        // Nombre Paciente
        if (nombrePaciente.getText().isEmpty()) {
            error += "El campo de nombre del paciente no puede estar vacío\n";
        } else {
            parameters.put("nombrePaciente", nombrePaciente.getText());
        }
        // Dirección Paciente
        if (direccionPaciente.getText().isEmpty()) {
            error += "El campo de dirección del paciente no puede estar vacío\n";
        } else {
            parameters.put("direccionPaciente", direccionPaciente.getText());
        }
        // Código Médico
        if (codMedico.getText().isEmpty()) {
            error += "El campo de código médico del no puede estar vacío\n";
        } else {
            if (validateInt(codMedico.getText())) {
                parameters.put("codMedico", codMedico.getText());
            } else {
                error += "El campo de código médico del tiene que ser numérico\n";
            }
        }
        // Nombre Médico
        if (nombreMedico.getText().isEmpty()) {
            error += "El campo de nombre del médico no puede estar vacío\n";
        } else {
            parameters.put("nombreMedico", nombreMedico.getText());
        }
        // Especialidad Médico
        if (especialidadMedico.getText().isEmpty()) {
            error += "El campo de especialidad del médico no puede estar vacío\n";
        } else {
            parameters.put("especialidadMedico", especialidadMedico.getText());
        }
        // Tratamiento
        if (tratamiento.getText().isEmpty()) {
            error += "El campo de tratamiento no puede estar vacío\n";
        } else {
            parameters.put("tratamiento", tratamiento.getText());
        }
        return error;
    }

    /**
     * Función que valida un integer
     *
     * @param valor string a validar
     * @return true/false
     */
    public boolean validateInt(String valor) {
        try {
            Integer.parseInt(valor);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Función que carga y lanza el informe de JasperReport
     */
    public void lanzarInforme() {
        try {
            JasperReport report = (JasperReport) JRLoader.loadObject(MedicoApplication.class.getResource("reports/InformeMedico.jasper")); // Obtener el fichero del informe
            JasperPrint jprint = JasperFillManager.fillReport(report, parameters, new JREmptyDataSource()); // Cargar el informe con las personas
            JasperViewer viewer = new JasperViewer(jprint, false); // Instanciar la vista del informe para mostrar el informe
            viewer.setVisible(true); // Mostrar el informe al usuario
        } catch (JRException e) {
            System.err.println(e.getMessage());
            mostrarAlerta("Ha ocurrido un error cargando el informe");
        }
    }

    /**
     * Función que se ejecuta al pulsar el botón "Limpiar". Limpia el formulario
     *
     * @param event evento del usuario
     */
    @FXML
    void limpiar(ActionEvent event) {
        parameters = new HashMap<String, Object>();
        numPaciente.setText("");
        nombrePaciente.setText("");
        direccionPaciente.setText("");
        codMedico.setText("");
        nombreMedico.setText("");
        especialidadMedico.setText("");
        tratamiento.setText("");
    }

    /**
     * Función que se ejecuta al pulsar el botón "Salir". Cierra la aplicación
     *
     * @param event evento del usuario
     */
    @FXML
    void salir(ActionEvent event) {
        Platform.exit();
    }

    /**
     * Función que muestra un mensaje de alerta al usuario
     *
     * @param mensaje de error a mostrar
     */
    public void mostrarAlerta(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setHeaderText(null);
        alerta.setTitle("Error");
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}