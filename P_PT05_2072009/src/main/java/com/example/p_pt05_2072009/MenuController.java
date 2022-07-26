package com.example.p_pt05_2072009;

import java.io.IOException;

import com.example.p_pt05_2072009.Dao.CategoryDao;
import com.example.p_pt05_2072009.Dao.MenuDao;
import com.example.p_pt05_2072009.Model.Category;
import com.example.p_pt05_2072009.Model.Menu;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;
public class MenuController {
    public TextField idmenu;
    public TextField namamenu;
    public TextField harga;
    public TextArea desc;
    public ComboBox<Category> isicategory;
    public Button btnSave;
    public Button btnReset;
    public Button btnUpdate;
    public Button btnDelete;
    public TableView<Menu> tabelmenu;
    public TableColumn<String, Menu> colid;
    public TableColumn<String, Menu> colnama;
    public TableColumn<Float, Menu> colharga;
    public TableColumn<Category, Menu> colcategory;
    public MenuItem showcat;
    public MenuItem close;
    private Stage stage;
    ObservableList<Menu> listmenu,listmenu2;
    ObservableList<Category> listcat;

    public MenuController() {
    }

    public void initialize() throws IOException {
        this.stage = new Stage();
        this.showcat.setAccelerator(KeyCombination.keyCombination("Alt+F2"));
        this.close.setAccelerator(KeyCombination.keyCombination("Alt+X"));
        CategoryDao cDao = new CategoryDao();
        this.listcat = cDao.getData();
        this.isicategory.setItems(this.listcat);
        this.ShowData();
        this.btnUpdate.setDisable(true);
        this.btnDelete.setDisable(true);
    }

    public void showcat(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Category-View.fxml"));
        Scene scene = new Scene((Parent)fxmlLoader.load(), 600.0, 300.0);
        CategoryController ctgController = (CategoryController)fxmlLoader.getController();
        this.stage.setTitle("Category Management");
        this.stage.setScene(scene);
        this.stage.showAndWait();
    }

    public void close(ActionEvent actionEvent) {
        this.idmenu.getScene().getWindow().hide();
    }

    public void ShowData() {
        MenuDao dao = new MenuDao();
        this.listmenu = dao.getData();
        this.tabelmenu.setItems(this.listmenu);
        this.colid.setCellValueFactory(new PropertyValueFactory("id"));
        this.colnama.setCellValueFactory(new PropertyValueFactory("name"));
        this.colharga.setCellValueFactory(new PropertyValueFactory("price"));
        this.colcategory.setCellValueFactory(new PropertyValueFactory("category"));

    }

    public void savebtn(ActionEvent actionEvent) {
        MenuDao dao = new MenuDao();
        if (this.idmenu.getText() != null && this.namamenu.getText() != null && this.harga.getText() != null && this.desc.getText() != null && this.isicategory.getValue() != null) {
            dao.addData(new Menu(Integer.parseInt(this.idmenu.getText()), this.namamenu.getText(), this.desc.getText(),Double.parseDouble(this.harga.getText()), (Category)this.isicategory.getValue()));
            this.ShowData();
            this.resetbtn();
        } else {
            Alert alert = new Alert(AlertType.CONFIRMATION, "Please Fill all the field", new ButtonType[]{ButtonType.OK});
            alert.showAndWait();
        }

    }
    public void klikcategory() {
        CategoryDao cDao = new CategoryDao();
        this.listcat = cDao.getData();
        this.isicategory.setItems(this.listcat);
    }
    public void resetbtn() {
        this.idmenu.clear();
        this.namamenu.clear();
        this.harga.clear();
        this.desc.clear();
        this.isicategory.getSelectionModel().select(-1);
    }


    public void updatebtn(ActionEvent actionEvent) {
        MenuDao dao = new MenuDao();
        int result = dao.updateData(new Menu(Integer.parseInt(this.idmenu.getText()), this.namamenu.getText(), this.desc.getText(),Double.parseDouble(this.harga.getText()), (Category)this.isicategory.getValue()));
        if (result>0){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Berhasil Update", ButtonType.OK);
            alert.showAndWait();
            ShowData();
            resetbtn();
        }
    }
    public void dataterpilih() {
        this.btnUpdate.setDisable(false);
        this.btnDelete.setDisable(false);
        this.btnSave.setDisable(true);
        tabelmenu.getSelectionModel().getSelectedItems();
        listmenu2 = tabelmenu.getSelectionModel().getSelectedItems();
        idmenu.setText(String.valueOf(tabelmenu.getSelectionModel().getSelectedItem().getId()));
        namamenu.setText(tabelmenu.getSelectionModel().getSelectedItem().getName());
        harga.setText(String.valueOf(tabelmenu.getSelectionModel().getSelectedItem().getPrice()));
        desc.setText(tabelmenu.getSelectionModel().getSelectedItem().getDescription());
        isicategory.setValue(tabelmenu.getSelectionModel().getSelectedItem().getCategory());
        idmenu.setDisable(true);
    }
    public void deletebtn(ActionEvent actionEvent) {
        MenuDao dao = new MenuDao();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.OK, ButtonType.CANCEL);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.OK) {
            for (Menu p: listmenu2) {
                dao.deleteData(p);
            }
        }
        ShowData();
    }

}
