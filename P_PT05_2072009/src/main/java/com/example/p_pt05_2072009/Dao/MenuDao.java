package com.example.p_pt05_2072009.Dao;

import com.example.p_pt05_2072009.Model.Category;
import com.example.p_pt05_2072009.Model.Menu;
import com.example.p_pt05_2072009.Util.MyConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MenuDao implements Daointerface<Menu> {

    @Override
    public ObservableList<Menu> getData() {
        ObservableList<Menu> translist;
        translist = FXCollections.observableArrayList();

        Connection conn = MyConnection.getConnection();
        String isisql = "SELECT m.idmenu, m.namamenu, m.price,m.description, c.idcategory, c.category FROM menu m JOIN category c ON m.category_idcategory= c.idcategory";
        PreparedStatement ps;
        try {
            ps = conn.prepareStatement(isisql);
            ResultSet hasil = ps.executeQuery();
            while (hasil.next()){
                int id = hasil.getInt("idmenu");
                String nama = hasil.getString("namamenu");
                double harga = hasil.getDouble("price");
                String desc = hasil.getString("description");
                int idcat = hasil.getInt("idcategory");
                String namaKategori = hasil.getString("category");
                Category kategori = new Category(idcat, namaKategori);
                Menu menu = new Menu(id,nama,desc,harga,kategori);
                translist.add(menu);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return translist;
    }

    @Override
    public void addData(Menu data) {
        Connection conn = MyConnection.getConnection();
        String query = "INSERT INTO menu(idmenu, namamenu, price,description,category_idcategory) VALUES(?, ?, ?, ?, ?)";

        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, data.getId());
            ps.setString(2, data.getName());
            ps.setDouble(3, data.getPrice());
            ps.setString(4, data.getDescription());
            ps.setInt(5, data.getCategory().getId());
            int result = ps.executeUpdate();
            if (result > 0) {
                System.out.println("data berhasil ditambahkan");
            }

        } catch (SQLException var6) {
            throw new RuntimeException(var6);
        }
    }

    @Override
    public void deleteData(Menu data) {
        Connection conn = MyConnection.getConnection();
        String query = "DELETE FROM menu WHERE idmenu = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, data.getId());
            int result = ps.executeUpdate();
            if (result > 0) {
                System.out.println("data berhasil dihapus");
            }

        } catch (SQLException var6) {
            throw new RuntimeException(var6);
        }
    }
    public int updateData(Menu data) {
        int result;
        Connection conn = MyConnection.getConnection();
        String query = "UPDATE menu SET namamenu=?, price=?,description=?,category_idcategory=? WHERE idmenu = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, data.getName());
            ps.setDouble(2, data.getPrice());
            ps.setString(3, data.getDescription());
            ps.setInt(4, data.getCategory().getId());
            ps.setInt(5, data.getId());
            result = ps.executeUpdate();

        } catch (SQLException var6) {
            throw new RuntimeException(var6);
        }
        return result;
    }
}
