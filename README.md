# Ejercicio 3_2 4 - Médico
## DM2 DEIN 2024-2025
### Alesandro Quirós Gobbato

Esta es una aplicación hecha con JavaFX y JasperReports que muestra un formulario médico y genera un informe a partir de los datos introducidos.

JasperReport está compilado en 7.0.1. La ejecución funciona en VSCode.

#### Estructura

La estructura del proyecto es la siguiente:
- `src > main`:
    - `java > com.alesandro.ejercicio3_24`:
        - `MedicoApplicacion.java`: Clase que lanza la aplicación
        - `controller`:
          - `MedicoController.java`: Clase que controla los eventos de la ventana principal de la aplicación
    - `resources > com.alesandro.ejercicio3_24`:
        - `fxml`:
          - `FormularioMedico.fxml`: Ventana principal de la aplicación
        - `images`: Carpeta que contiene las imágenes de la aplicación
        - `reports`:
          - `InformeMedico.jasper`: Fichero del informe de JasperReport de la aplicación
          - `InformeMedico.jrxml`: Fichero para la edición del informe de JasperReport en JasperStudio de la aplicación
