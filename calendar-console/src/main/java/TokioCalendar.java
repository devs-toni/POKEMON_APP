import domain.Event;
import domain.Task;
import domain.User.UserType;
import domain.User;
import domain.exception.LimitSizeException;
import domain.exception.UserDoesNotExistException;
import util.FileUtils;
import util.InputUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;
import java.util.Properties;

import static util.Constants.FILE_PATH;
import static util.Constants.PROPS_PATH;

public class TokioCalendar {

    private ArrayList<User> users;
    private User currentUser;
    private boolean exit;
    Properties properties;

    public TokioCalendar() {
        loadData();
        exit = false;

    }


    private void showMenu() {
        if (currentUser != null) {
            System.out.println("SESIÓN INICIADA: " + currentUser.getUsername());
        }
        System.out.println("Calendario TokioSchool v1.0");
        System.out.println("0. Registrar Usuario");
        System.out.println("1. Iniciar Sesion");
        System.out.println("2. Registrar evento");
        System.out.println("3. Registrar tarea");
        System.out.println("4. Ver eventos");
        System.out.println("5. Ver tares");
        System.out.println("6. Modificar tarea");
        System.out.println("7. Salir");
        System.out.println("8. Opciones");
        System.out.println("Seleccione una opción: ");
        String choice = InputUtils.inputText("Elige una opción");

        switch (choice) {
            case "0":
                registerUser();
                break;
            case "1":
                String username = InputUtils.inputMandatoryText("Introduce el nombre de usuario.");
                String password = InputUtils.inputMandatoryText("Introduce la contraseña.");

                try {
                    if (loginUser(username, password)) {
                        System.out.println("Sesion iniciada correctamente.");
                    }
                } catch (UserDoesNotExistException udnee) {
                    System.out.println(udnee.getMessage());
                }
                break;
            case "2":
                if (hasSesion()) {
                    addEvent();
                }
                break;
            case "3":
                if (hasSesion()) {
                    addTask();
                }
                break;
            case "4":
                if (hasSesion()) {
                    viewEvents();
                }

                break;
            case "5":
                if (hasSesion()) {
                    viewTasks();
                }
                break;
            case "6":
                if (hasSesion()) {
                    modifyTask();
                }
                break;

            case "7":
                exit = true;
                break;
            case "8":
                setOption();
                break;
        }

    }

    private void setOption() {

        //TODO Mostrar opciones y sus valores
        try {
            String option = InputUtils.inputMandatoryText("Introduce la opción: ");
            String value = InputUtils.inputMandatoryText("Introduce el valor: ");
            properties.setProperty(option, value);
            properties.store(new FileOutputStream(PROPS_PATH), "Parametros de aplicacion");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private void modifyTask() {
        String name = InputUtils.inputText("Introduce el nombre de la tarea a modificar.");
        Map<String, Task> tasks = currentUser.getTaskMap();

        if (!tasks.containsKey(name)) {
            System.out.println("No se ha encontrado la tarea. Vuelve a intentarlo");
            return;
        }

        Task task = tasks.get(name);

        String description = InputUtils.inputText("Introduce la nueva descripcion.");
        int duration = InputUtils.inputIntNumber("Introduce la nueva duración.");
        task.setDescription(description);
        task.setDuration(duration);
        System.out.println("Tarea modificada correctamente.");
        saveData();

    }


    private boolean hasSesion() {
        if (currentUser == null) {
            System.out.println("Debes iniciar sesión para continuar");
            InputUtils.pressEnterToContinue();

            return false;
        }
        return true;
    }

    private void registerUser() {
        String username = InputUtils.inputMandatoryText("Introduce el nombre de usuario");
        String password = InputUtils.inputMandatoryText("Introduce la contraseña");
        String email = InputUtils.inputMandatoryText("Introduce el email");

        for (UserType type : UserType.values()) {
            System.out.println(type.ordinal() + " - " + type.name());
        }

        int type = InputUtils.inputIntNumber("Introduce el tipo:");

        User user = new User(username, password, email, User.UserType.values()[type]);

        users.add(user);
        saveData();


        System.out.println("Usuario registrado correctamente");
        InputUtils.pressEnterToContinue();
    }

    private boolean loginUser(String username, String password) throws UserDoesNotExistException {
        User loginUser = new User(username, password);
        currentUser = users.stream()
                .filter(user -> user.equals(loginUser))
                .findFirst().orElseThrow(() -> new UserDoesNotExistException("Usuario/Contraseña incorrectos"));

        return true;
    }

    public void addEvent() {
        String name = InputUtils.inputMandatoryText("Introduce el nombre");
        String description = InputUtils.inputMandatoryText("Introduce la descripcion");
        String location = InputUtils.inputMandatoryText("Introduce la ubicacion");
        LocalDateTime dateTime = LocalDateTime.now();

        domain.Event event = new Event(name, description, location, dateTime, 5, currentUser);
        try {
            currentUser.addEvent(event);
            System.out.println("Evento creado satisfactoriamente");
        } catch (LimitSizeException lse) {
            System.out.println("No puedes almacenar mas eventos.");
        }
        saveData();


        InputUtils.pressEnterToContinue();
    }

    public void addTask() {
        String name = InputUtils.inputMandatoryText("Introduce el nombre");
        String description = InputUtils.inputMandatoryText("Introduce la descripcion");
        int minutes = InputUtils.inputIntNumber("Introduce la duracion");

        Task task = new Task(name, description, 5, currentUser);
        try {
            currentUser.addTask(task);
            System.out.println("Tarea guardada satisfactoriamente");
        } catch (LimitSizeException lse) {
            System.out.println("No puedes almacenar más tareas");
            lse.printStackTrace();
        }
        saveData();


        InputUtils.pressEnterToContinue();
    }

    private void viewEvents() {
        currentUser.getEventsSet()
                .forEach(System.out::println);

        InputUtils.pressEnterToContinue();
    }

    private void viewTasks() {
        currentUser.getTasksSet()
                .forEach(System.out::println);

        InputUtils.pressEnterToContinue();
    }

    public void launch() {
        while (!exit) {
            showMenu();
        }
    }

    private void saveData() {
        try {
            FileUtils.saveData(users, FILE_PATH);
        } catch (IOException ioe) {
            System.out.println("Se ha producido un error al guardar la información");
        }
    }

    @SuppressWarnings("unchecked")
    private void loadData() {
        try {
            properties = new Properties();
            properties.load(new FileInputStream((PROPS_PATH)));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }


        //Es la primera ejecucion del programa
        if (Files.notExists(Paths.get(FILE_PATH))) {
            users = new ArrayList<>();
            return;
        }

        try {
            users = (ArrayList<User>) FileUtils.loadFile(FILE_PATH);
        } catch (FileNotFoundException fnfe) {
            System.out.println("No se ha podido encontrar el archivo");
            fnfe.printStackTrace();
        } catch (IOException ioe) {
            System.out.println("No se ha podido leer el ficher");
            ioe.printStackTrace();
        } catch (ClassNotFoundException cnfe) {
            System.out.println("No se ha podido cargar la información. El formato no es correcto");
            cnfe.printStackTrace();
        }

    }
}
