package com.example.p_pt05_2072009.Dao;

import com.example.p_pt05_2072009.Model.Category;
import com.example.p_pt05_2072009.Util.MyConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryDao implements Daointerface<Category>{
    @Override
    public ObservableList<Category> getData() {
        ObservableList<Category> cList;
        cList = FXCollections.observableArrayList();
        Connection conn = MyConnection.getConnection();
        String query = "SELECT * FROM category";
        PreparedStatement ps;
        try {
            ps = conn.prepareStatement(query);
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                int id = result.getInt("idcategory");
                String nama = result.getString("category");
                Category c = new Category(id, nama);
                cList.add(c);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return cList;
    }

    @Override
    public void addData(Category data) {
        Connection conn = MyConnection.getConnection();
        String sql = "INSERT INTO Category(idcategory,category) values(?,?)";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, data.getId());
            ps.setString(2, data.getCategory());
            int hasil = ps.executeUpdate();
            if (hasil > 0) {
                System.out.println("Berhasil ditambahkan");
            }

        } catch (SQLException var6) {
            throw new RuntimeException(var6);
        }
    }

    @Override
    public void deleteData(Category data) {

    }

    @Override
    public int updateData(Category data) {

        return 0;
    }
}
