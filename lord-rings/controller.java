package lordOfTheRings;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import keyboard.utilesTeclado;
import lordOfTheRings.misExcepciones.CharacterExistsException;
import lordOfTheRings.misUtiles.util;

import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class controller implements Initializable {

    //OBJETO JUEGO A TRAVÉS DEL CUAL REALIZARÉ LAS ACCIONES PRINCIPALES DEL JUEGO
    juego game = new juego();

    public void setGame(juego game) {
        this.game = game;
    }

    /**
     * Objetos de la interfaz gráfica . . .
     */

    /**
     * Parte de creación de personajes
     */

    @FXML
    private ComboBox<String> tipo_heroe_combo;
    @FXML
    private ComboBox<String> tipo_bestia_combo;

    @FXML
    private TextField nombre_heroe_field;
    @FXML
    private TextField nombre_bestia_field;
    @FXML
    private TextField vida_heroe_field;
    @FXML
    private TextField vida_bestia_field;
    @FXML
    private TextField armadura_heroe_field;
    @FXML
    private TextField armadura_bestia_field;
    @FXML

    private Label error_heroe_label;
    @FXML
    private Label error_bestia_label;
    @FXML
    private Label error_vida_heroe_label;
    @FXML
    private Label error_vida_bestia_label;
    @FXML
    private Label error_armadura_heroe_label;
    @FXML
    private Label error_armadura_bestia_label;

    @FXML
    private Button anadir_heroe;
    @FXML
    private Button anadir_bestia;

    /**
     * Parte de la edición de listas
     */

    @FXML
    private ListView<heroe> lista_heroes_field;
    @FXML
    private ListView<bestia> lista_bestias_field;

    @FXML
    private Button subir_heroe = new Button();
    @FXML
    private Button bajar_heroe = new Button();
    @FXML
    private Button eliminar_heroe = new Button();
    @FXML
    private Button editar_heroe = new Button();
    @FXML
    private Button subir_bestia = new Button();
    @FXML
    private Button bajar_bestia = new Button();
    @FXML
    private Button eliminar_bestia = new Button();
    @FXML
    private Button editar_bestia = new Button();

    /**
     * Parte de la lucha principal
     */

    @FXML
    private Button lucha;
    @FXML
    private Button empezar_batalla;
    @FXML
    private Button load;
    @FXML
    private Label error_botones;
    @FXML
    private Pane pane_lucha;
    @FXML
    private Button delete_saves;

    @FXML
    private TextArea fight_area;

    //BORDES EMPLEADOS
    Border bordeGenerico;
    BorderStroke bordeStrokeError = new BorderStroke(Paint.valueOf("red"), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT);
    Border bordeError = new Border(bordeStrokeError);

    //VARIABLES EMPLEADAS EN LA INTRODUCCIÓN DE DATOS
    boolean vidaHeroeCorrecta = false;
    boolean armaduraHeroeCorrecta = false;
    boolean vidaBestiaCorrecta = false;
    boolean armaduraBestiaCorrecta = false;

    public TextArea getFight_area() {
        return fight_area;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        combo_heroes();                                 //INICIAR COMBO DE HEROES
        combo_bestias();                                //INICIAR COMBO DE BESTIAS
        bordeGenerico = vida_heroe_field.getBorder();   //CAPTURA DEL BORDE GENÉRICO

        game.setEmpezarLucha(false);
        game.setCarga(true);

        fight_area.appendText("CARGAR// Con la partida vacía, carga la partida anterior . . .");
        fight_area.appendText("\nEMPEZAR BATALLA// Con al menos un ejercito creado, borra todos los datos . . .");
        fight_area.appendText("\nBORRAR AUTOGUARDADO// Borra el archivo que almacena los datos guardados . . .");

    }


    /**
     * Cuando pulsemos sobre los combo, y se seleccione una opción!!! Estos adoptaran el Borde genérico . . .
     * En caso de estar vacío, al pulsar en una opción desaparecerá el borde de error.
     *
     * @param event Evento de 'click'
     */

    @FXML
    void comboAction(ActionEvent event) {
        ComboBox c = (ComboBox) event.getSource();

        switch (c.getId()) {
            case "tipo_heroe_combo":
                if (tipo_heroe_combo.getSelectionModel().isEmpty()) tipo_heroe_combo.setBorder(bordeError);
                else tipo_heroe_combo.setBorder(bordeGenerico);
                break;
            case "tipo_bestia_combo":
                if (tipo_bestia_combo.getSelectionModel().isEmpty()) tipo_bestia_combo.setBorder(bordeError);
                else tipo_bestia_combo.setBorder(bordeGenerico);
                break;
        }
    }

    /**
     * Todas las acciones realizadas mediante botones, e identificadas mediante el FXID de cada botón
     *
     * @param event Evento 'click'
     */

    @FXML
    void action(ActionEvent event) {
        Button b = (Button) event.getSource();

        switch (b.getId()) {
            case "anadir_heroe":
                if (!game.isBotones_off()) {
                    pintar_campos_vacios(tipo_heroe_combo, nombre_heroe_field, vida_heroe_field, armadura_heroe_field); //SI AL PULSAR ESTAN VACÍOS SE PINTAN DE ROJO
                    Boolean nombreHeroeCorrecto = !verificar_campo_vacio(nombre_heroe_field); // . . . Y SE CAPTURA UNA BOOLEAN SOBRE EL NOMBRE
                    campo_numerico_correcto(vida_heroe_field); //SE VERIFICA QUE SEAN NUMÉRICOS
                    campo_numerico_correcto(armadura_heroe_field);

                    if (vidaHeroeCorrecta && armaduraHeroeCorrecta && nombreHeroeCorrecto &&
                            tipo_heroe_combo.getSelectionModel().getSelectedItem() != null) { //SI TODOS LOS DATOS SON CORRECTOS . . .
                        String nombre = nombre_heroe_field.getText(); //CAPTURA DEL NOMBRE
                        int vida = Integer.parseInt(vida_heroe_field.getText()); //CAPTURA DE LA VIDA
                        int armadura = Integer.parseInt(armadura_heroe_field.getText()); //CAPTURA DE LA ARMADURA
                        tipo tipoHeroe = tipo.valueOf(tipo_heroe_combo.getSelectionModel().getSelectedItem()); //CAPTURA DEL TIPO

                        try {
                            game.addCharacter(nombre, vida, armadura, tipoHeroe); //SE LLAMA A LA FUNCION AÑADIR PEROSNAJE (HEROE), PASANDOLE LOS DATOS INTRODUCIDOS
                        } catch (CharacterExistsException e) {
                            error_heroe_label.setText(e.getLocalizedMessage()); //SE RECOGE EXCEPCION DE PERSONAJE YA EXISTE - SE PINTA ETIQUETA CON ERROR
                        }
                        lista_heroes_field.setItems(FXCollections.observableArrayList(game.getListaHeroes())); //SE PINTA EN LA LISTVIEW DE HEROES, LA LISTA DE HEROES

                        //TODOS LOS CAMPOS SE REINICIAN
                        nombre_heroe_field.setText("");
                        tipo_heroe_combo.getSelectionModel().clearSelection();
                        tipo_heroe_combo.setBorder(bordeGenerico);
                        vida_heroe_field.setText("");
                        armadura_heroe_field.setText("");

                        game.setEmpezarLucha(true);
                        game.setCarga(false);

                        autoguardado();

                    } else if (verificar_campo_vacio(nombre_heroe_field) || verificar_campo_vacio(vida_heroe_field)
                            || verificar_campo_vacio(armadura_heroe_field) ||
                            tipo_heroe_combo.getSelectionModel().getSelectedItem() == null) { //SI ALGUNO DE LOS DATOS NO HA SIDO INTRODUCIDO . . .
                        error_heroe_label.setText("Todos los campos son obligatorios . . ."); //SE PINTA ETIQUETA CON ERROR
                    }
                } else {
                    error_botones.setText("Elija 'EMPEZAR BATALLA' o 'LUCHAR DE NUEVO . . .");
                }
                break;

            case "anadir_bestia":
                if (!game.isBotones_off()) {
                    pintar_campos_vacios(tipo_bestia_combo, nombre_bestia_field, vida_bestia_field, armadura_bestia_field); //SI AL PULSAR ESTAN VACÍOS SE PINTAN DE ROJO
                    Boolean nombreBestiaCorrecto = !verificar_campo_vacio(nombre_bestia_field);// . . . Y SE CAPTURA UNA BOOLEAN SOBRE EL NOMBRE
                    campo_numerico_correcto(vida_bestia_field); //SE VERIFICA QUE SEAN NUMÉRICOS
                    campo_numerico_correcto(armadura_bestia_field);
                    if (vidaBestiaCorrecta && armaduraBestiaCorrecta && nombreBestiaCorrecto &&
                            tipo_bestia_combo.getSelectionModel().getSelectedItem() != null) {      //SI TODOS LOS DATOS SON CORRECTOS . . .
                        String nombre = nombre_bestia_field.getText(); //SE CAPTURA EL NOMBRE

                        int vida = Integer.parseInt(vida_bestia_field.getText()); //SE CAPTURA LA VIDA
                        int armadura = Integer.parseInt(armadura_bestia_field.getText()); //SE CAPTURA LA ARMADURA
                        tipo tipoBestia = tipo.valueOf(tipo_bestia_combo.getSelectionModel().getSelectedItem()); //SE CAPTURA EL TIPO
                        try {
                            game.addCharacter(nombre, vida, armadura, tipoBestia); //SE LLAMA A LA FUNCIÓN AÑADIR PERSONAJE (BESTIA), CON LOS DATOS INTRODUCIDOS
                        } catch (CharacterExistsException e) {
                            error_bestia_label.setText(e.getLocalizedMessage());  //SI YA EXISTE SE PINTA UNA ETIQUETA CON EL ERROR
                        }
                        lista_bestias_field.setItems(FXCollections.observableArrayList(game.getListaBestias())); //SE PINTA EN LA LISTVIEW DE HEROES, LA LISTA DE HEROES

                        //SE REINICIAN TODOS LOS CAMPOS
                        nombre_bestia_field.setText("");
                        tipo_bestia_combo.getSelectionModel().clearSelection();
                        tipo_bestia_combo.setBorder(bordeGenerico);
                        vida_bestia_field.setText("");
                        armadura_bestia_field.setText("");

                        game.setEmpezarLucha(true);
                        game.setCarga(false);

                        autoguardado();

                    } else if (vida_bestia_field.getText().isEmpty() || armadura_bestia_field.getText().isEmpty()
                            || nombre_bestia_field.getText().isEmpty() ||
                            tipo_bestia_combo.getSelectionModel().getSelectedItem() == null) {  //SI ALGUNO DE LOS DATOS NO HA SIDO INTRODUCIDO . . .
                        error_bestia_label.setText("Todos los campos son obligatorios . . .");  //SE PINTA ETIQUETA CON ERROR
                    }
                } else {
                    error_botones.setText("Elija 'EMPEZAR BATALLA' o 'LUCHAR DE NUEVO . . .");
                }
                break;

            case "subir_heroe":
                if (!game.isBotones_off()) {
                    heroe auxSubirHeroe = lista_heroes_field.getSelectionModel().getSelectedItem(); //SE CAPTURA EL OBJETO SELECCIONADO
                    int subirHeroe = game.getListaHeroes().indexOf(auxSubirHeroe); //SE AVERIGUA LA POSICIÓN (X) DE LA LISTA EN LA QUE RESIDE ESTE OBJETO
                    if (subirHeroe > 0) {//SI ESTA POSICIÓN NO ES LA PRIMERA
                        heroe auxBajar = game.getListaHeroes().get(subirHeroe - 1); //SE OBTIENE EL OBJETO DE LA POSICIÓN ANTERIOR A X
                        game.getListaHeroes().set(subirHeroe - 1, auxSubirHeroe); //EL OBJETO AL QUE SE LE HA DICHO 'SUBIR', SUBE DE POSICIÓN EN LA LISTA
                        game.getListaHeroes().set(subirHeroe, auxBajar);  //Y EL OBJETO AL QUE SE LE HA ROBADO EL SITIO, BAJA DE POSICIÓN EN LA LISTA
                        lista_heroes_field.setItems(FXCollections.observableArrayList(game.getListaHeroes())); //SE MUESTREA LA LISTA
                        autoguardado();
                    }
                } else {
                    error_botones.setText("Elija 'EMPEZAR BATALLA' o 'LUCHAR DE NUEVO . . .");
                }
                break;

            case "bajar_heroe":
                if (!game.isBotones_off()) {
                    heroe auxBajarHeroe = lista_heroes_field.getSelectionModel().getSelectedItem(); //SE CAPTURA EL OBJETO SELECCIONADO
                    int bajarHeroe = game.getListaHeroes().indexOf(auxBajarHeroe);//SE AVERIGUA LA POSICION (X) DE LA LISTA EN LA QUE RESIDE ESTE OBJETO
                    if ((bajarHeroe >= 0) && (bajarHeroe < game.getListaHeroes().size() - 1)) { //SI ESTA POSICION NO ES LA ÚLTIMA . . . Y HAY UN OBJETO SELECCIONADO
                        heroe auxSubir = game.getListaHeroes().get(bajarHeroe + 1); //SE OBTIENE EL OBJETO DE LA POSICIÓN POSTERIOR A X
                        game.getListaHeroes().set(bajarHeroe + 1, auxBajarHeroe);//EL OBJETO SELECCIONADO, BAJA DE POSICIÓN EN LA LISTA
                        game.getListaHeroes().set(bajarHeroe, auxSubir);//Y EL OBJETO AL QUE SE LE HA ROBADO EL SITIO, SUBE DE POSICIÓN EN LA LISTA
                        lista_heroes_field.setItems(FXCollections.observableArrayList(game.getListaHeroes()));
                        autoguardado();
                    }
                } else {
                    error_botones.setText("Elija 'EMPEZAR BATALLA' o 'LUCHAR DE NUEVO . . .");
                }
                break;

            case "eliminar_heroe":
                if (!game.isBotones_off()) {
                    heroe auxEliminarHeroe = lista_heroes_field.getSelectionModel().getSelectedItem(); //SE CAPTURA EL OBJETO SELECCIONADO
                    game.getListaHeroes().remove(auxEliminarHeroe); // . . . Y SE ELIMINA DE LA LISTA
                    lista_heroes_field.setItems(FXCollections.observableArrayList(game.getListaHeroes())); //SE MUESTREA LA LISTA
                    autoguardado();

                    if (game.getListaHeroes().isEmpty() && game.getListaBestias().isEmpty()) {
                        game.setCarga(true);
                        game.setEmpezarLucha(false);
                    }
                } else {
                    error_botones.setText("Elija 'EMPEZAR BATALLA' o 'LUCHAR DE NUEVO . . .");
                }
                break;

            case "editar_heroe":
                if (!game.isBotones_off()) {
                    if (lista_heroes_field.getSelectionModel().getSelectedItem() != null) {
                        heroe modif = lista_heroes_field.getSelectionModel().getSelectedItem();

                        List<String> choices = new ArrayList<>();
                        choices.add("Nombre");
                        choices.add("Tipo");
                        choices.add("Vida");
                        choices.add("Armadura");
                        ChoiceDialog<String> opciones = new ChoiceDialog<>(null, choices);
                        opciones.setTitle("Editar ");
                        opciones.setHeaderText("Que opción deseas modificar");
                        opciones.setContentText("Elige una opción:");

                        while (true) {
                            Optional<String> result = opciones.showAndWait();
                            if (result.isEmpty()) break;
                            if (result.get().equalsIgnoreCase("nombre")) {
                                TextInputDialog opcionVida = new TextInputDialog("");
                                opcionVida.setTitle("Editar ");
                                opcionVida.setHeaderText("Editar nombre del heroe seleccionado");
                                opcionVida.setContentText("Introduce un nombre:");
                                Optional<String> nombre = opcionVida.showAndWait();
                                nombre.ifPresent(modif::setNombre);
                                lista_heroes_field.refresh();
                                autoguardado();
                            } else if (result.get().equalsIgnoreCase("tipo")) {
                                ChoiceDialog<tipo> opcionTipo = new ChoiceDialog<>(null, Arrays.stream(tipo.values()).filter(tipo -> tipo.getTipoPersonaje().equals("Heroe")).collect(Collectors.toList()));
                                opcionTipo.setTitle("Editar ");
                                opcionTipo.setHeaderText("Editar la raza del heroe seleccionado");
                                opcionTipo.setContentText("Elige una opción:");
                                Optional<tipo> tipo = opcionTipo.showAndWait();
                                tipo.ifPresent(modif::setType);
                                lista_heroes_field.refresh();
                                autoguardado();
                            } else if (result.get().equalsIgnoreCase("vida")) {
                                TextInputDialog opcionVida = new TextInputDialog("");
                                opcionVida.setTitle("Editar ");
                                opcionVida.setHeaderText("Editar vida del heroe seleccionado");
                                opcionVida.setContentText("Introduce un valor:");
                                Optional<String> vidaString = opcionVida.showAndWait();
                                if (vidaString.isPresent()) {
                                    int vida = Integer.parseInt(vidaString.get());
                                    modif.setVida(vida);
                                    lista_heroes_field.refresh();
                                    autoguardado();
                                }
                            } else if (result.get().equalsIgnoreCase("armadura")) {
                                TextInputDialog opcionArmadura = new TextInputDialog("");
                                opcionArmadura.setTitle("Editar ");
                                opcionArmadura.setHeaderText("Editar armadura del heroe seleccionado");
                                opcionArmadura.setContentText("Introduce un valor:");
                                Optional<String> armaduraString = opcionArmadura.showAndWait();
                                if (armaduraString.isPresent()) {
                                    int armadura = Integer.parseInt(armaduraString.get());
                                    modif.setArmadura(armadura);
                                    lista_heroes_field.refresh();
                                    autoguardado();
                                }
                            }
                        }
                    }
                } else {
                    error_botones.setText("Elija 'EMPEZAR BATALLA' o 'LUCHAR DE NUEVO . . .");
                }
                break;

            case "subir_bestia":
                if (!game.isBotones_off()) {
                    bestia auxSubirBestia = lista_bestias_field.getSelectionModel().getSelectedItem();//SE CAPTURA EL OBJETO SELECCIONADO
                    int subirBestia = game.getListaBestias().indexOf(auxSubirBestia);//SE AVERIGUA LA POSICIÓN (X) DE LA LISTA EN LA QUE RESIDE ESTE OBJETO
                    if (subirBestia > 0) {//SI ESTA POSICIÓN NO ES LA PRIMERA
                        bestia auxBajar = game.getListaBestias().get(subirBestia - 1);//SE OBTIENE EL OBJETO DE LA POSICIÓN ANTERIOR A X
                        game.getListaBestias().set(subirBestia - 1, auxSubirBestia);//EL OBJETO AL QUE SE LE HA DICHO 'SUBIR', SUBE DE POSICIÓN EN LA LISTA
                        game.getListaBestias().set(subirBestia, auxBajar);//Y EL OBJETO AL QUE SE LE HA ROBADO EL SITIO, BAJA DE POSICIÓN EN LA LISTA
                        lista_bestias_field.setItems(FXCollections.observableArrayList(game.getListaBestias()));//SE MUESTREA LA LISTA
                        autoguardado();
                    }
                } else {
                    error_botones.setText("Elija 'EMPEZAR BATALLA' o 'LUCHAR DE NUEVO . . .");
                }
                break;

            case "bajar_bestia":
                if (!game.isBotones_off()) {
                    bestia auxBajarBestia = lista_bestias_field.getSelectionModel().getSelectedItem();//SE CAPTURA EL OBJETO SELECCIONADO
                    int bajarBestia = game.getListaBestias().indexOf(auxBajarBestia); //SE AVERIGUA LA POSICION (X) DE LA LISTA EN LA QUE RESIDE ESTE OBJETO
                    if ((bajarBestia >= 0) && (bajarBestia < game.getListaBestias().size() - 1)) {//SI ESTA POSICION NO ES LA ÚLTIMA . . . Y HAY UN OBJETO SELECCIONADO
                        bestia auxSubir = game.getListaBestias().get(bajarBestia + 1); //SE OBTIENE EL OBJETO DE LA POSICIÓN POSTERIOR A X
                        game.getListaBestias().set(bajarBestia + 1, auxBajarBestia); //EL OBJETO SELECCIONADO, BAJA DE POSICIÓN EN LA LISTA
                        game.getListaBestias().set(bajarBestia, auxSubir); //Y EL OBJETO AL QUE SE LE HA ROBADO EL SITIO, SUBE DE POSICIÓN EN LA LISTA
                        lista_bestias_field.setItems(FXCollections.observableArrayList(game.getListaBestias())); //SE MUESTREA LA LISTA
                        autoguardado();
                    }
                } else {
                    error_botones.setText("Elija 'EMPEZAR BATALLA' o 'LUCHAR DE NUEVO . . .");
                }
                break;

            case "eliminar_bestia":
                if (!game.isBotones_off()) {
                    bestia auxEliminarBestia = lista_bestias_field.getSelectionModel().getSelectedItem(); // SE CAPTURA EL OBJETO SELECCIONADO
                    game.getListaBestias().remove(auxEliminarBestia); // . . . Y SE ELIMINA DE LA LISTA
                    lista_bestias_field.setItems(FXCollections.observableArrayList(game.getListaBestias())); //SE MUESTREA LA LISTA
                    autoguardado();
                    if (game.getListaHeroes().isEmpty() && game.getListaBestias().isEmpty()) {
                        game.setCarga(true);
                        game.setEmpezarLucha(false);
                    }
                } else {
                    error_botones.setText("Elija 'EMPEZAR BATALLA' o 'LUCHAR DE NUEVO . . .");
                }
                break;

            case "editar_bestia":
                if (!game.isBotones_off()) {
                    if (lista_bestias_field.getSelectionModel().getSelectedItem() != null) {
                        bestia modif = lista_bestias_field.getSelectionModel().getSelectedItem();

                        List<String> choices = new ArrayList<>();
                        choices.add("Nombre");
                        choices.add("Tipo");
                        choices.add("Vida");
                        choices.add("Armadura");
                        ChoiceDialog<String> opciones = new ChoiceDialog<>(null, choices);
                        opciones.setTitle("Editar ");
                        opciones.setHeaderText("Que opción deseas modificar");
                        opciones.setContentText("Elige una opción:");

                        while (true) {
                            Optional<String> result = opciones.showAndWait();
                            if (result.isEmpty()) break;
                            if (result.get().equalsIgnoreCase("nombre")) {
                                TextInputDialog opcionVida = new TextInputDialog("");
                                opcionVida.setTitle("Editar ");
                                opcionVida.setHeaderText("Editar nombre de la bestia seleccionada");
                                opcionVida.setContentText("Introduce un nombre:");
                                Optional<String> nombre = opcionVida.showAndWait();
                                nombre.ifPresent(modif::setNombre);
                                lista_bestias_field.refresh();
                                autoguardado();

                            } else if (result.get().equalsIgnoreCase("tipo")) {
                                ChoiceDialog<tipo> opcionTipo = new ChoiceDialog<>(null, Arrays.stream(tipo.values()).filter(tipo -> tipo.getTipoPersonaje().equals("Bestia")).collect(Collectors.toList()));
                                opcionTipo.setTitle("Editar ");
                                opcionTipo.setHeaderText("Editar la raza de la bestia seleccionada");
                                opcionTipo.setContentText("Elige una opción:");
                                Optional<tipo> tipo = opcionTipo.showAndWait();
                                tipo.ifPresent(modif::setType);
                                lista_bestias_field.refresh();
                                autoguardado();
                            } else if (result.get().equalsIgnoreCase("vida")) {
                                TextInputDialog opcionVida = new TextInputDialog("");
                                opcionVida.setTitle("Editar ");
                                opcionVida.setHeaderText("Editar vida de la bestia seleccionada");
                                opcionVida.setContentText("Introduce un valor:");
                                Optional<String> vidaString = opcionVida.showAndWait();
                                if (vidaString.isPresent()) {
                                    int vida = Integer.parseInt(vidaString.get());
                                    modif.setVida(vida);
                                    lista_bestias_field.refresh();
                                    autoguardado();
                                }
                            } else if (result.get().equalsIgnoreCase("armadura")) {
                                TextInputDialog opcionArmadura = new TextInputDialog("");
                                opcionArmadura.setTitle("Editar ");
                                opcionArmadura.setHeaderText("Editar armadura de la bestia seleccionada");
                                opcionArmadura.setContentText("Introduce un valor:");
                                Optional<String> armaduraString = opcionArmadura.showAndWait();
                                if (armaduraString.isPresent()) {
                                    int armadura = Integer.parseInt(armaduraString.get());
                                    modif.setArmadura(armadura);
                                    lista_bestias_field.refresh();
                                    autoguardado();
                                }
                            }
                        }
                    }
                } else {
                    error_botones.setText("Elija 'EMPEZAR BATALLA' o 'LUCHAR DE NUEVO . . .");
                }
                break;

            case "lucha":

                if (!game.isBotones_off()) {
                    if (game.getListaHeroes().size() != 0 && game.getListaBestias().size() != 0) {  //SI HAY SOLDADOS EN AMBOS EJERCITOS

                        fight_area.clear();

                        game.lucha(fight_area); //SE PINTA EN LA ARENA DE COMBATE EL PROCESO DE LUCHA Y SE DAN DOS OPCIONES
                        fight_area.appendText("\n\nPulsa 'Volver a Luchar' para jugar con el ejercito formado anteriormente . . .\nPulsa 'Empezar Batalla' para" + " volver a formar tu ejercito\n");

                        lista_heroes_field.refresh(); //REFRESCAR LISTAS
                        lista_bestias_field.refresh();

                        game.setEmpezarLucha(true);
                        game.setCarga(false);
                        game.setBotones_off(true);

                    } //else
                    //error_botones.setText("Debes completar ambos ejercitos para empezar a luchar"); //SI NO HAY SOLDADOS SE PINTA UN ERROR

                } else {
                    error_botones.setText("Elija 'EMPEZAR BATALLA' o 'LUCHAR DE NUEVO . . .");
                }

                break;

            case "empezar_batalla":
                if (game.isEmpezarLucha()) { //SI LA OPCION SE HA ACTIVADO

                    game.reiniciarListas(); //SE BORRAN LAS LISTAS PRINCIPALES

                    lista_heroes_field.setItems(FXCollections.observableArrayList(game.getListaHeroes())); //SE MUESTREAN LAS LISTAS
                    lista_bestias_field.setItems(FXCollections.observableArrayList(game.getListaBestias()));

                    fight_area.clear(); //SE LIMPIA LA ARENA DEL COMBATE

                    fight_area.appendText("CARGAR// Con la partida vacía, carga la partida anterior . . .");
                    fight_area.appendText("\nEMPEZAR BATALLA// Con al menos un ejercito creado, borra todos los datos . . .");
                    fight_area.appendText("\nBORRAR AUTOGUARDADO// Borra el archivo que almacena los datos guardados . . .");

                    game.setBotones_off(false);
                    game.setEmpezarLucha(false); //AL EMPEZAR BATALLA, SE DESHABILITA EMPEZAR DE NUEVO Y LUCHAR DE NUEVO
                    game.setCarga(true);

                    error_botones.setText("EMPIEZA LA BATALLA!!!");
                }
                break;

            case "load":
                if (game.isCarga()) {
                    File savegame = new File(util.pathDir + util.pathFile);

                    if (savegame.exists()) {
                        cargarPartida(savegame);
                    } else {
                        error_botones.setText("No hay partida guardada . . .");
                    }
                }
                break;
            case "delete_saves":
                File archivo = new File(util.pathDir + util.pathFile);
                if (archivo.exists()) {
                    if (archivo.delete()) {
                        error_botones.setText("PARTIDA ELIMINADA EXITOSAMENTE!!!");
                    } else {
                        error_botones.setText("No se ha podido eliminar la partida!");
                    }
                } else {
                    error_botones.setText("No hay partida guardada. . .");
                }
                break;
        }
    }


    /**
     * Al clicar en alguno de los campos de añadir heroe o bestia, los errores mostrados desaparecerá, dando una nueva oportunidad
     *
     * @param event
     */

    @FXML
    void resetErrorHeroeLabel(MouseEvent event) {
        error_heroe_label.setText("");
    }

    @FXML
    void resetErrorBestiaLabel(MouseEvent event) {
        error_bestia_label.setText("");
    }

    /**
     * Al introducir valores en los campos numéricos . . . se verificará que sean numéricos
     *
     * @param event
     */

    @FXML
    void errorCampo(KeyEvent event) {
        TextField campo = (TextField) event.getSource();

        switch (campo.getId()) {
            case "vida_heroe_field":
                utilesTeclado u = new utilesTeclado();
                if (!utilesTeclado.esUnNumero(vida_heroe_field.getText())) {
                    vida_heroe_field.setBorder(new Border(bordeStrokeError));
                    error_vida_heroe_label.setText("Error: Este campo debe ser numérico");
                    vidaHeroeCorrecta = false;
                } else if (vida_heroe_field.getText().equalsIgnoreCase("")) {
                    error_vida_heroe_label.setText("");
                    vidaHeroeCorrecta = false;
                } else {
                    error_vida_heroe_label.setText("");
                    vidaHeroeCorrecta = true;
                }
                break;
            case "armadura_heroe_field":
                if (!utilesTeclado.esUnNumero(armadura_heroe_field.getText())) {
                    armadura_heroe_field.setBorder(new Border(bordeStrokeError));
                    error_armadura_heroe_label.setText("Error: Este campo debe ser numérico");
                    armaduraHeroeCorrecta = false;
                } else if (armadura_heroe_field.getText().equals("")) {
                    error_armadura_heroe_label.setText("");
                    armaduraHeroeCorrecta = false;
                } else {
                    error_armadura_heroe_label.setText("");
                    armaduraHeroeCorrecta = true;
                }
                break;
            case "vida_bestia_field":
                if (!utilesTeclado.esUnNumero(vida_bestia_field.getText())) {
                    vida_bestia_field.setBorder(new Border(bordeStrokeError));
                    error_vida_bestia_label.setText("Error: Este campo debe ser numérico");
                    vidaBestiaCorrecta = false;
                } else if (vida_bestia_field.getText().equals("")) {
                    error_vida_bestia_label.setText("");
                    vidaBestiaCorrecta = false;
                } else {
                    error_vida_bestia_label.setText("");
                    vidaBestiaCorrecta = true;
                }
                break;
            case "armadura_bestia_field":
                if (!utilesTeclado.esUnNumero(armadura_bestia_field.getText())) {
                    armadura_bestia_field.setBorder(new Border(bordeStrokeError));
                    error_armadura_bestia_label.setText("Error: Este campo debe ser numérico");
                    armaduraBestiaCorrecta = false;
                } else if (armadura_bestia_field.getText().equals("")) {
                    error_armadura_bestia_label.setText("");
                    armaduraBestiaCorrecta = false;
                } else {
                    error_armadura_bestia_label.setText("");
                    armaduraBestiaCorrecta = true;
                }
                break;
        }
    }

    /**
     * Si se introduce texto en los campos, el borde volvera a ser el genérico, retirando así el error
     *
     * @param event
     */

    @FXML
    void introducirTexto(KeyEvent event) {
        TextField aux = (TextField) event.getSource();
        aux.setBorder(bordeGenerico);
    }

    @FXML
    void reset_errores_botones(MouseEvent event) {
        error_botones.setText("");
    }


    /**
     * Se le pasará un TextField para verificar si hay algo escrito
     *
     * @param t TextField
     * @return Devolverá si está vacío o no
     */

    private boolean verificar_campo_vacio(TextField t) {
        return t.getText().isEmpty();
    }

    /**
     * Se le pasará un ComboBox y varios TextField para verificar si hay algo selecconado o escrito, y en caso contrario, se pintarán de rojo
     * marcando así un error
     *
     * @param t
     */

    private void pintar_campos_vacios(ComboBox combo, TextField... t) {
        for (int i = 0; i < t.length; i++) {
            if (t[i].getText().isEmpty()) {
                t[i].setBorder(bordeError);
            } else {
                t[i].setBorder(bordeGenerico);
            }
        }
        if (combo.getSelectionModel().isEmpty()) combo.setBorder(bordeError);
        else combo.setBorder(bordeGenerico);
    }

    /**
     * Verificará que los campos numéricos solo procesen números y que no esten vacíos
     *
     * @param text
     * @return
     */

    private boolean campo_numerico_correcto(TextField text) {
        if (!utilesTeclado.esUnNumero(text.getText())) {
            text.setBorder(bordeError);
            return false;
        } else if (text.getText().equalsIgnoreCase("")) {
            return false;
        }
        return true;
    }

    /**
     * Rellenar los combos
     */

    private void combo_heroes() {
        List<tipo> listaHeroes = Arrays.asList(tipo.values());
        List<String> listaHeroesCombo = listaHeroes.stream().filter(tipo -> tipo.getTipoPersonaje().equalsIgnoreCase("heroe")).map(tipo -> tipo.name()).collect(Collectors.toList());
        tipo_heroe_combo.setItems(FXCollections.observableArrayList(listaHeroesCombo));
    }

    private void combo_bestias() {
        List<tipo> listaBestias = Arrays.asList(tipo.values());
        List<String> listaBestiasCombo = listaBestias.stream().filter(tipo -> tipo.getTipoPersonaje().equalsIgnoreCase("bestia")).map(tipo -> tipo.name()).collect(Collectors.toList());
        tipo_bestia_combo.setItems(FXCollections.observableArrayList(listaBestiasCombo));
    }

    private void cargarPartida(File f) {
        ObjectInputStream flujoEntrada = null;
        try {
            flujoEntrada = new ObjectInputStream(new FileInputStream(f));
            juego gameLoad = (juego) flujoEntrada.readObject();
            setGame(gameLoad);
            lista_bestias_field.setItems(FXCollections.observableArrayList(gameLoad.getListaBestias()));
            lista_heroes_field.setItems(FXCollections.observableArrayList(gameLoad.getListaHeroes()));

            game.setEmpezarLucha(true);
            game.setCarga(false);

            error_botones.setText("PARTIDA CARGADA EXITOSAMENTE!!!");
            flujoEntrada.close();
        } catch (IOException | ClassNotFoundException e) {
            error_botones.setText("No se ha podido cargar la partida!");
        }

    }
    private void autoguardado() {
        File carpeta = new File(util.pathDir);
        if (!carpeta.exists()) {
            if (carpeta.mkdir()) {
                guardarPartida(carpeta);
            } else
                error_botones.setText("No se ha podido crear el directorio de guardado");
        } else {
            guardarPartida(carpeta);
        }
    }
    private void guardarPartida(File f) {
        File savegame = new File(f + File.separator + util.pathFile);
        try {
            ObjectOutputStream flujoSalida = new ObjectOutputStream(new FileOutputStream(savegame));
            flujoSalida.writeObject(game);
            error_botones.setText("Autoguardado!!!");
            flujoSalida.close();
        } catch (IOException e) {
            error_botones.setText("No se ha podido guardar la partida!");
        }
    }
}