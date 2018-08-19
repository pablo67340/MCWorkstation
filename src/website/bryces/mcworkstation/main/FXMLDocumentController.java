/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package website.bryces.mcworkstation.main;

import website.bryces.mcworkstation.items.JSONItem;
import website.bryces.mcworkstation.items.CustomItem;
import website.bryces.mcworkstation.items.MatLib;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.jfoenix.controls.JFXColorPicker;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import static java.lang.String.CASE_INSENSITIVE_ORDER;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import static java.util.Collections.sort;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import static javafx.collections.FXCollections.observableArrayList;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;

import javafx.scene.control.TableColumn;

import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.nodes.Tag;
import website.bryces.mcworkstation.snippets.Category;
import website.bryces.mcworkstation.snippets.SnippetLoader;

/**
 *
 * @author Bryce
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Label lblRandom;

    private Stage thisStage;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView lstItems;

    @FXML
    private TableColumn colMaterial, colItemID;

    @FXML
    protected TableColumn<CustomItem, ImageView> colImage = new TableColumn<CustomItem, ImageView>("Images");

    @FXML
    private TextField txtSearch;

    @FXML
    private JFXColorPicker colorChooser;

    @FXML
    private TextArea txtInput, txtOutput, txtSnippetInfo, txtSnippet;

    @FXML
    public ListView lstCat, lstSnippet;

    @FXML
    private RadioButton rdYML, rdJSON;
    
    @FXML
    private Button btnMinimize;

    private static FXMLDocumentController INSTANCE;

    private Double initialX, initialY;

    private final Map<String, JSONItem> itemAPI = new HashMap<>();
    private final Map<String, String> dict = new HashMap<>();
    public Map<String, Category> categories = new HashMap<>();
    protected ObservableList<CustomItem> model = observableArrayList();

    protected ObservableList<CustomItem> imgList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        INSTANCE = this;
        // TODO
        prepGUI();
        startRandomText();

    }

    public void prepGUI() {
        parseItems();
        colorChooser.setValue(Color.web("#616161"));
        Image image = new Image(getClass().getResource("/website/bryces/mcworkstation/assets/images/BG.png").toExternalForm());

        BackgroundImage myBI = new BackgroundImage(image, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        Background background = new Background(myBI);

        root.setBackground(background);

        txtInput.setOnKeyPressed((KeyEvent keyEvent) -> {
            if (keyEvent.getCode() == KeyCode.ENTER) {
                btnValidateAction(keyEvent);
            }
        });

        SnippetLoader snippetLoader = new SnippetLoader();
    }

    private String selectedCat = "";

    public void selectCategory(MouseEvent event) {
        lstSnippet.getItems().clear();
        selectedCat = (String) lstCat.getSelectionModel().getSelectedItem();

        Category cat = categories.get(selectedCat);
        ObservableList<String> snip = lstSnippet.getItems();
        cat.getSnippets().forEach((snippet) -> {
            snip.add(snippet.getName());
        });
        lstSnippet.setItems(snip);
    }

    public void selectSnippet(MouseEvent event) {

        Category cat = categories.get(selectedCat);

        cat.getSnippets().stream().filter((snippet) -> (snippet.getName().equalsIgnoreCase((String) lstSnippet.getSelectionModel().getSelectedItem()))).forEachOrdered((snippet) -> {
            String description = "";
            description += "Description: " + snippet.getDescription() + "\n\n";
            description += "Returns: " + snippet.getReturns();
            txtSnippetInfo.setText(description);
            txtSnippet.setText(snippet.getSnippet());
        });

    }

    public void startRandomText() {
        Thread t1 = new Thread(() -> {
            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(25), ev -> {
                Platform.runLater(() -> {
                    lblRandom.setText(getRandomString());
                });
            }));
            timeline.setCycleCount(Animation.INDEFINITE);
            timeline.play();
        });
        t1.start();
    }

    protected String getRandomString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890!@#$%^&*(){}/+_<>?~-';";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 6) {
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }

    public void setStage(Stage input) {
        thisStage = input;
    }

    public Stage getStage() {
        return thisStage;
    }

    public void showStage() {
        thisStage.show();
    }

    public void mouseDragAction(MouseEvent e) {
        getStage().getScene().getWindow().setX(e.getScreenX() - initialX);
        getStage().getScene().getWindow().setY(e.getScreenY() - initialY);
    }

    public void dragAction(MouseEvent event) {
        this.initialX = event.getSceneX();
        this.initialY = event.getSceneY();
    }

    public void btnCloseAction(ActionEvent event) {
        thisStage.close();
        System.exit(0);
    }

    public void setCopy(MouseEvent event) {
        TextField field = (TextField) event.getSource();

        String color = field.getText();
        StringSelection selection = new StringSelection(color);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, selection);
        Tooltip tp = new Tooltip("Copied!");
        tp.show(thisStage, event.getScreenX(), event.getScreenY());
        countDelay(tp);

    }

    public void parseItems() {
        Gson gson = new Gson();

        JSONParser parser = new JSONParser();

        Object obj = null;
        try {
            obj = parser.parse(getItems());
        } catch (ParseException ex) {
            System.out.println("Error parsing! " + ex.getMessage());
        }

        JSONArray jsonObject = (JSONArray) obj;

        for (Object itm : jsonObject) {
            JSONObject item = (JSONObject) itm;
            JSONItem jItem = gson.fromJson(item.toJSONString(), JSONItem.class);
            itemAPI.put(jItem.type + ":" + jItem.meta, jItem);
            String itemID = +jItem.type + ":" + jItem.meta;
            dict.put(jItem.type + ":" + jItem.meta, MatLib.MAP.get(itemID));
        }

        searchItem("", true);
    }

    public String getItems() {
        String body;
        try {
            URL url = new URL("https://minecraft-ids.grahamedgecombe.com/items.json");
            URLConnection con = url.openConnection();
            InputStream in = con.getInputStream();
            String encoding = con.getContentEncoding();
            encoding = encoding == null ? "UTF-8" : encoding;

            try (BufferedReader br= new BufferedReader(new InputStreamReader(in, encoding))) {
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                    sb.append('\n');
                }
                body = sb.toString();
            }

            return body;
        } catch (IOException e) {
            System.out.println("Error fetching item feed!");
        }
        return "";
    }

    public void searchItem(String input, Boolean bypass) {
        if (!input.equalsIgnoreCase("") || bypass) {
            lstItems.getItems().clear();
            List<String> result = new ArrayList();
            getDict().values().stream().forEach((key) -> {
                if (key.toUpperCase().contains(input.toUpperCase())) {
                    result.add(key);
                    sort(result, CASE_INSENSITIVE_ORDER);
                } else {
                    result.remove(key);
                    sort(result, CASE_INSENSITIVE_ORDER);
                }
            });

            for (Entry<String, String> entry : MatLib.MAP.entrySet()) {
                if (result.contains(entry.getValue())) {
                    String fixed = entry.getKey().replace(":", "-");
                    Image image = new Image(getClass().getResource("/website/bryces/mcworkstation/assets/items/" + fixed + ".png").toExternalForm());
                    CustomItem img = new CustomItem(new ImageView(image), entry.getValue(), entry.getKey());
                    imgList.add(img);

                    colImage.setCellValueFactory(new PropertyValueFactory<>("image"));
                    colMaterial.setCellValueFactory(new PropertyValueFactory<>("material"));
                    colItemID.setCellValueFactory(new PropertyValueFactory<>("ItemID"));
                    lstItems.setItems(imgList);
                    lstItems.getSelectionModel().setCellSelectionEnabled(true);

                    lstItems.setRowFactory(tv -> {
                        TableRow<CustomItem> row = new TableRow<>();
                        row.setOnMouseClicked(event -> {
                            if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY) {
                                CustomItem clickedRow = row.getItem();
                                Integer col = lstItems.getFocusModel().getFocusedCell().getColumn();

                                if (col == 1) {
                                    Tooltip tp = new Tooltip("Copied!");
                                    tp.show(root, event.getScreenX(), event.getScreenY());
                                    StringSelection selection = new StringSelection(clickedRow.getMaterial());
                                    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                                    clipboard.setContents(selection, selection);
                                    countDelay(tp);

                                } else if (col == 2) {
                                    Tooltip tp = new Tooltip("Copied!");
                                    tp.show(root, event.getScreenX(), event.getScreenY());
                                    StringSelection selection = new StringSelection(clickedRow.getItemID());
                                    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                                    clipboard.setContents(selection, selection);
                                    countDelay(tp);
                                }
                            }
                        });
                        return row;
                    });
                }
            }
        }
    }

    public void countDelay(Tooltip tp) {
        Thread t1 = new Thread(() -> {
            try {
                Thread.sleep(1000);
                Platform.runLater(() -> {
                    tp.hide();
                });
            } catch (InterruptedException e) {
                System.out.println("Error: " + e.getMessage());
            }

        });
        t1.start();
    }

    public Map<String, String> getDict() {
        return dict;
    }

    public Map<String, JSONItem> getItemAPI() {
        return itemAPI;
    }

    public void txtTypeEvent(Event e) {
        searchItem(txtSearch.getText(), false);
    }

    public void btnValidateAction(Event event) {
        if (rdYML.isSelected()) {
            try {
                Yaml yml = new Yaml();
                Object obj = yml.load(txtInput.getText());
                txtOutput.setText(yml.dumpAs(obj, Tag.YAML, DumperOptions.FlowStyle.FLOW));
            } catch (Exception e) {
                txtOutput.setText(e.toString());
            }
        } else {
            JSONParser parser = new JSONParser();
            try {
                JSONObject obj = (JSONObject) parser.parse(txtInput.getText());
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                JsonParser jp = new JsonParser();
                JsonElement je = jp.parse(obj.toJSONString());
                String prettyJsonString = gson.toJson(je);
                System.out.println(prettyJsonString);
                txtOutput.setText(prettyJsonString);
            } catch (ParseException e) {
                if (e.toString().contains("at position")) {
                    String[] pre = e.toString().split("at position");
                    String error = pre[0];
                    Integer line = Integer.parseInt(pre[1].replace(".", "").trim());
                    line -= 1;
                    error += "at character number " + line + ".";
                    txtOutput.setText(error);
                } else {
                    txtOutput.setText(e.toString());
                }
            }
        }
    }

    public static FXMLDocumentController getInstance() {
        return INSTANCE;
    }

    public void chooseSnippet(MouseEvent event) {
        Tooltip tp = new Tooltip("Copied!");
        tp.show(root, event.getScreenX(), event.getScreenY());
        StringSelection selection = new StringSelection(txtSnippet.getText());
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, selection);
        countDelay(tp);
    }
    
    public void btnMinimizeAction(ActionEvent event){
        thisStage.setIconified(true);
        // This is just to fix the permenant rippler bug.
        root.requestFocus();
    }

}
