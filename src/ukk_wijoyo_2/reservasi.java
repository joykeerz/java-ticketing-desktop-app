/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ukk_wijoyo_2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.print.*;
import java.sql.Date;
import java.sql.Statement;
import java.text.*;
import javax.swing.table.DefaultTableModel;
import javax.print.PrintException;
import javax.swing.JFrame;
import javax.swing.JPanel;
/**
 *
 * @author gaming
 */
public class reservasi extends javax.swing.JFrame {
        Connection conn         = null;
        ResultSet rs            = null;
        PreparedStatement pst   = null;
    /**
     * Creates new form reservasi
     */
    public reservasi() {
        initComponents();
            try {
                conn = koneksi.wijoyokonek();
                show_data();
                show_data_pelanggan();
                load_pelanggan();
                auto_number_Reservasi();
                
            } catch (SQLException ex) {
                Logger.getLogger(reservasi.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    private void auto_number_Reservasi(){
        String perintah = "SELECT MAX(id_reserve) FROM reservasi";
            try {
                pst = conn.prepareStatement(perintah);
                rs = pst.executeQuery();
                
                if (rs.next()){
                    int a = rs.getInt(1);
                    lbl_reserve.setText(""+Integer.toString(a+1));
                    
                }
            } catch (SQLException ex) {
                System.out.println(""+ex.getMessage());;
            }
            
    }
    
    private void auto_number_seat_Kereta(){
        String perintah = "SELECT MAX(id_reserve) FROM reservasi";
            try {
                pst = conn.prepareStatement(perintah);
                rs = pst.executeQuery();
                
                while (rs.next()){
                    int a = rs.getInt(1);
                    lbl_reserve.setText(""+Integer.toString(a+1));
                }
            } catch (SQLException ex) {
                System.out.println(""+ex.getMessage());;
            }
            
    }
    
    private void show_data(){
        String perintah = "SELECT * FROM rute";
        try {
            pst = conn.prepareStatement(perintah);
            rs = pst.executeQuery();
            
            DefaultTableModel makan = new DefaultTableModel();
            makan.addColumn("ID");
            makan.addColumn("Dari");
            makan.addColumn("Ke");
            makan.addColumn("Harga");
            makan.addColumn("Nama Transport");
            makan.addColumn("Tanggal Berangkat");
        
        while (rs.next()){
            makan.addRow(new Object[]{rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)});
        }
        tbrute2.setModel(makan);
        } catch (SQLException ex) {
            
        }
    }
    
    private void show_data_pelanggan(){
        String perintah = "SELECT * FROM pelanggan";
        try {
            pst = conn.prepareStatement(perintah);
            rs = pst.executeQuery();
            
            DefaultTableModel makan = new DefaultTableModel();
            makan.addColumn("ID");
            makan.addColumn("Nama");
            makan.addColumn("Alamat");
            makan.addColumn("Telp");
            makan.addColumn("JK");
            makan.addColumn("Umur");
        
        while (rs.next()){
            makan.addRow(new Object[]{rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)});
        }
        tbl_pelanggan.setModel(makan);
        } catch (SQLException ex) {
            
        }
    }
    
    private void show_data_search(){
        
        try {
            String perintah = "SELECT * FROM rute WHERE depart_from LIKE '%"+tb_dari.getText()+"%' AND depart_to LIKE '%"+tb_ke.getText()+"%' OR depart_date='"+((JTextField)tgl_berangkat.getDateEditor().getUiComponent()).getText()+"'";
            pst = conn.prepareStatement(perintah);
            rs = pst.executeQuery();
            
            DefaultTableModel makan = new DefaultTableModel();
            makan.addColumn("ID");
            makan.addColumn("Dari");
            makan.addColumn("Ke");
            makan.addColumn("Harga");
            makan.addColumn("Nama Transport");
            makan.addColumn("Tanggal Berangkat");
            
            //if((rs.next())){
                while (rs.next()){
                    makan.addRow(new Object[]{rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)});
                }
                    tbrute2.setModel(makan);
                    clear();
            //}else{
                    //JOptionPane.showMessageDialog(null, "Data Tidak  Ditemukan");
                    //clear();
           // }
        
        } catch (SQLException ex) {
            
        }
    }
    
    private void show_data_search_pelanggan(){
        
        try {
            String perintah = "SELECT * FROM pelanggan WHERE nama LIKE '%"+nama_pelanggan.getText()+"%' OR id_pelanggan LIKE '%"+id_pelanggan.getSelectedItem()+"%'";
            pst = conn.prepareStatement(perintah);
            rs = pst.executeQuery();
            
            DefaultTableModel makan = new DefaultTableModel();
            makan.addColumn("ID");
            makan.addColumn("Nama");
            makan.addColumn("Alamat");
            makan.addColumn("Telp");
            makan.addColumn("JK");
            makan.addColumn("Umur");
            
            //if((rs.next())){
                while (rs.next()){
                    makan.addRow(new Object[]{rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)});
                }
                    tbl_pelanggan.setModel(makan);
                    nama_pelanggan.setText("");
                    id_pelanggan.setSelectedItem("");
                    
           // }else{
            //        JOptionPane.showMessageDialog(null, "Data Tidak  Ditemukan");
            //        nama_pelanggan.setText("");
            //        id_pelanggan.setSelectedItem("");
            //}
        
        } catch (SQLException ex) {
            
        }
    }
    
    private void clear(){
        tb_dari.setText("");
        tb_ke.setText("");
        tgl_berangkat.setDate(null);
    }
    private void clear_all(){
        Sum_alamat.setText("");
        sum_dari.setText("");
        Sum_Biaya.setText("");
        sum_hargakelas.setText("");
        sum_jadwal.setText("");
        sum_ke.setText("");
        sum_nama.setText("");
        sum_pemesan.setText("");
        sum_telp.setText("");
        sum_tglbooking.setText("");
        lbl_reserve.setText("");
        
    }
    
    private void load_pelanggan(){
        try {
            Statement cs = conn.createStatement();
            ResultSet rs = cs.executeQuery("select * from pelanggan");
            while (rs.next()) {
                String item = rs.getString("id_pelanggan");
                id_pelanggan.addItem(item);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        
    
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jTabbedPane4 = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        tb_dari = new javax.swing.JTextField();
        tb_ke = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        tgl_berangkat = new com.toedter.calendar.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbrute2 = new javax.swing.JTable();
        jPanel14 = new javax.swing.JPanel();
        search = new javax.swing.JButton();
        clear1 = new javax.swing.JButton();
        next1 = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel4 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        tgl_booking = new com.toedter.calendar.JDateChooser();
        jPanel16 = new javax.swing.JPanel();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jPanel18 = new javax.swing.JPanel();
        kd_rute = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        kd_user = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        tb_from = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        tb_to = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        harga_trans = new javax.swing.JTextField();
        total_harga = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jumlah = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        harga_rute = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jPanel30 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        sisa = new javax.swing.JTextField();
        kursi = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbl_pelanggan = new javax.swing.JTable();
        jPanel22 = new javax.swing.JPanel();
        search2 = new javax.swing.JButton();
        next3 = new javax.swing.JButton();
        clear4 = new javax.swing.JButton();
        jPanel23 = new javax.swing.JPanel();
        nama_pelanggan = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        id_pelanggan = new javax.swing.JComboBox<>();
        jPanel24 = new javax.swing.JPanel();
        Tambah_pel = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jPanel25 = new javax.swing.JPanel();
        jPanel26 = new javax.swing.JPanel();
        sum_jadwal = new javax.swing.JTextField();
        sum_tglbooking = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        sum_dari = new javax.swing.JTextField();
        sum_ke = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        sum_nama = new javax.swing.JTextField();
        sum_hargakelas = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        jPanel27 = new javax.swing.JPanel();
        sum_telp = new javax.swing.JTextField();
        Sum_Biaya = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        sum_pemesan = new javax.swing.JTextField();
        Sum_alamat = new javax.swing.JTextField();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        lbl_reserve = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        lbl_seatcode = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        lbl_reserve1 = new javax.swing.JLabel();
        jPanel28 = new javax.swing.JPanel();
        submit = new javax.swing.JButton();
        jPanel29 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel10 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        tb_dari1 = new javax.swing.JTextField();
        tb_ke1 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        tgl_berangkat1 = new com.toedter.calendar.JDateChooser();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbrute3 = new javax.swing.JTable();
        jPanel17 = new javax.swing.JPanel();
        search1 = new javax.swing.JButton();
        clear2 = new javax.swing.JButton();
        next2 = new javax.swing.JButton();
        jLabel27 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        tgl_booking1 = new com.toedter.calendar.JDateChooser();
        jPanel20 = new javax.swing.JPanel();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jPanel31 = new javax.swing.JPanel();
        kd_rute1 = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        kd_user1 = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        tb_from1 = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        tb_to1 = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        harga_trans1 = new javax.swing.JTextField();
        total_harga1 = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        jumlah1 = new javax.swing.JTextField();
        jLabel45 = new javax.swing.JLabel();
        harga_rute1 = new javax.swing.JTextField();
        jLabel48 = new javax.swing.JLabel();
        jPanel32 = new javax.swing.JPanel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        sisa1 = new javax.swing.JTextField();
        kursi1 = new javax.swing.JTextField();
        jPanel12 = new javax.swing.JPanel();
        jPanel33 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tbl_pelanggan1 = new javax.swing.JTable();
        jPanel34 = new javax.swing.JPanel();
        search3 = new javax.swing.JButton();
        next4 = new javax.swing.JButton();
        clear5 = new javax.swing.JButton();
        jPanel35 = new javax.swing.JPanel();
        nama_pelanggan1 = new javax.swing.JTextField();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        id_pelanggan1 = new javax.swing.JComboBox<>();
        jPanel36 = new javax.swing.JPanel();
        Tambah_pel1 = new javax.swing.JButton();
        jPanel15 = new javax.swing.JPanel();
        jPanel37 = new javax.swing.JPanel();
        jPanel38 = new javax.swing.JPanel();
        sum_jadwal1 = new javax.swing.JTextField();
        sum_tglbooking1 = new javax.swing.JTextField();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        sum_dari1 = new javax.swing.JTextField();
        sum_ke1 = new javax.swing.JTextField();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        sum_nama1 = new javax.swing.JTextField();
        sum_hargakelas1 = new javax.swing.JTextField();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jPanel39 = new javax.swing.JPanel();
        sum_telp1 = new javax.swing.JTextField();
        Sum_Biaya1 = new javax.swing.JTextField();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        sum_pemesan1 = new javax.swing.JTextField();
        Sum_alamat1 = new javax.swing.JTextField();
        jLabel62 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        jPanel40 = new javax.swing.JPanel();
        submit1 = new javax.swing.JButton();
        jPanel41 = new javax.swing.JPanel();
        jLabel64 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(221, 75, 57));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Reservasi");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 30, 100, -1));

        jLabel11.setBackground(new java.awt.Color(255, 255, 255));
        jLabel11.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("X");
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel11MouseClicked(evt);
            }
        });
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 0, -1, -1));

        jLabel12.setBackground(new java.awt.Color(255, 255, 255));
        jLabel12.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("-");
        jLabel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel12MouseClicked(evt);
            }
        });
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 0, 10, -1));

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("?");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 0, -1, -1));

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ukk_wijoyo_2/img/icons8_Booking_24px.png"))); // NOI18N
        jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 30, 30, 30));

        jLabel44.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(255, 255, 255));
        jLabel44.setText("Aplikasi Ticketing");
        jPanel2.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 0, 180, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(-5, 0, 850, 70));

        jTabbedPane4.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane4.setTabPlacement(javax.swing.JTabbedPane.LEFT);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel3.add(tb_dari, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 70, -1));
        jPanel3.add(tb_ke, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 10, 149, -1));

        jLabel3.setText("Ke :");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 14, -1, -1));

        jLabel4.setText("Tanggal Berangkat :");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 49, -1, -1));

        tgl_berangkat.setDateFormatString("yyyy-MM-dd");
        jPanel3.add(tgl_berangkat, new org.netbeans.lib.awtextra.AbsoluteConstraints(147, 49, 150, -1));

        tbrute2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbrute2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbrute2MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbrute2);

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(303, 11, -1, 170));

        jPanel14.setBackground(new java.awt.Color(232, 135, 123));
        jPanel14.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        search.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ukk_wijoyo_2/img/icons8_Search_24px.png"))); // NOI18N
        search.setContentAreaFilled(false);
        search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchActionPerformed(evt);
            }
        });
        jPanel14.add(search, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 3, -1, 40));

        clear1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ukk_wijoyo_2/img/icons8_Delete_24px.png"))); // NOI18N
        clear1.setContentAreaFilled(false);
        jPanel14.add(clear1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 2, 80, 40));

        next1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ukk_wijoyo_2/img/icons8_Right_24px.png"))); // NOI18N
        next1.setContentAreaFilled(false);
        next1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                next1ActionPerformed(evt);
            }
        });
        jPanel14.add(next1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 2, 60, 40));

        jPanel3.add(jPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, -1, -1));

        jLabel16.setText("Dari :");
        jPanel3.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 14, -1, -1));

        jSeparator1.setBackground(new java.awt.Color(221, 75, 57));
        jPanel3.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 188, 750, 10));

        jTabbedPane1.addTab("Cari Tiket", jPanel3);

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel15.setText("Tanggal Booking :");
        jPanel13.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 49, -1, -1));

        tgl_booking.setDateFormatString("yyyy-MM-dd");
        jPanel13.add(tgl_booking, new org.netbeans.lib.awtextra.AbsoluteConstraints(148, 49, 149, -1));

        jPanel16.setBackground(new java.awt.Color(232, 135, 123));
        jPanel16.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ukk_wijoyo_2/img/icons8_Delete_24px.png"))); // NOI18N
        jButton11.setContentAreaFilled(false);
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        jPanel16.add(jButton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 2, 80, 40));

        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ukk_wijoyo_2/img/icons8_Right_24px.png"))); // NOI18N
        jButton12.setContentAreaFilled(false);
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        jPanel16.add(jButton12, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 2, 60, 40));

        jPanel13.add(jPanel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 350, 287, 40));

        jPanel18.setBackground(new java.awt.Color(209, 181, 177));

        kd_rute.setEnabled(false);

        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Rute");

        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("User");

        kd_user.setEnabled(false);

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19)
                .addGap(4, 4, 4)
                .addComponent(kd_rute, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel20)
                .addGap(4, 4, 4)
                .addComponent(kd_user, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(kd_user, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(kd_rute, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel20)
                            .addComponent(jLabel19))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel13.add(jPanel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, 287, 40));
        jPanel13.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 14, -1, -1));

        tb_from.setEnabled(false);
        jPanel13.add(tb_from, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 11, 70, -1));

        jLabel18.setText("Ke :");
        jPanel13.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 14, -1, -1));

        tb_to.setEnabled(false);
        jPanel13.add(tb_to, new org.netbeans.lib.awtextra.AbsoluteConstraints(148, 11, 149, -1));

        jLabel21.setText("Dari :");
        jPanel13.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 14, -1, -1));

        jLabel23.setText("Harga Transport :");
        jPanel13.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 114, -1, -1));

        harga_trans.setEnabled(false);
        jPanel13.add(harga_trans, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 111, 70, -1));

        total_harga.setEnabled(false);
        jPanel13.add(total_harga, new org.netbeans.lib.awtextra.AbsoluteConstraints(77, 185, 220, -1));

        jLabel24.setText("Total Harga :");
        jPanel13.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 188, -1, -1));

        jumlah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jumlahActionPerformed(evt);
            }
        });
        jumlah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jumlahKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jumlahKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jumlahKeyTyped(evt);
            }
        });
        jPanel13.add(jumlah, new org.netbeans.lib.awtextra.AbsoluteConstraints(227, 142, 70, -1));

        jLabel25.setText("Jumlah :");
        jPanel13.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(174, 145, 49, -1));

        harga_rute.setEnabled(false);
        jPanel13.add(harga_rute, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 142, 70, -1));

        jLabel26.setText("Harga Rute :");
        jPanel13.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 145, 70, -1));

        jPanel30.setBackground(new java.awt.Color(221, 75, 57));

        javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
        jPanel30.setLayout(jPanel30Layout);
        jPanel30Layout.setHorizontalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 460, Short.MAX_VALUE)
        );
        jPanel30Layout.setVerticalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 410, Short.MAX_VALUE)
        );

        jPanel13.add(jPanel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, -10, 460, 410));

        jLabel5.setText("Sisa Kursi     :");
        jPanel13.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, 70, -1));

        jLabel6.setText("Jumlah Kursi :");
        jPanel13.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, -1, -1));

        sisa.setEnabled(false);
        jPanel13.add(sisa, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 250, 60, -1));

        kursi.setEnabled(false);
        jPanel13.add(kursi, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 220, 60, -1));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 770, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Pilihan", jPanel4);

        jPanel21.setBackground(new java.awt.Color(255, 255, 255));
        jPanel21.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tbl_pelanggan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbl_pelanggan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_pelangganMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tbl_pelanggan);

        jPanel21.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(303, 11, -1, 380));

        jPanel22.setBackground(new java.awt.Color(232, 135, 123));
        jPanel22.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        search2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ukk_wijoyo_2/img/icons8_Search_24px.png"))); // NOI18N
        search2.setContentAreaFilled(false);
        search2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                search2ActionPerformed(evt);
            }
        });
        jPanel22.add(search2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 3, -1, 40));

        next3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ukk_wijoyo_2/img/icons8_Right_24px.png"))); // NOI18N
        next3.setContentAreaFilled(false);
        next3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                next3ActionPerformed(evt);
            }
        });
        jPanel22.add(next3, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 2, 60, 40));

        clear4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ukk_wijoyo_2/img/icons8_Delete_24px.png"))); // NOI18N
        clear4.setContentAreaFilled(false);
        jPanel22.add(clear4, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 2, 80, 40));

        jPanel21.add(jPanel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, -1, -1));

        jPanel23.setBackground(new java.awt.Color(232, 135, 123));
        jPanel23.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel23.add(nama_pelanggan, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, 60, -1));

        jLabel38.setBackground(new java.awt.Color(255, 255, 255));
        jLabel38.setForeground(new java.awt.Color(255, 255, 255));
        jLabel38.setText("Nama");
        jPanel23.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, 20));

        jLabel36.setBackground(new java.awt.Color(255, 255, 255));
        jLabel36.setForeground(new java.awt.Color(255, 255, 255));
        jLabel36.setText("ID");
        jPanel23.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 40, -1, 20));

        jLabel1.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("CARI PELANGGAN");
        jPanel23.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 230, -1));

        jPanel23.add(id_pelanggan, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 40, 120, -1));

        jPanel21.add(jPanel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 280, 70));

        jPanel24.setBackground(new java.awt.Color(232, 135, 123));
        jPanel24.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Tambah_pel.setForeground(new java.awt.Color(255, 255, 255));
        Tambah_pel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ukk_wijoyo_2/img/icons8_Add_New_24px.png"))); // NOI18N
        Tambah_pel.setText("Tambah Baru");
        Tambah_pel.setContentAreaFilled(false);
        Tambah_pel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Tambah_pelActionPerformed(evt);
            }
        });
        jPanel24.add(Tambah_pel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 280, 40));

        jPanel21.add(jPanel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 280, 40));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 770, Short.MAX_VALUE)
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel7Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, 763, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel7Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("Data Pelanggan", jPanel7);

        jPanel25.setBackground(new java.awt.Color(255, 255, 255));
        jPanel25.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel26.setBackground(new java.awt.Color(232, 135, 123));
        jPanel26.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        sum_jadwal.setBackground(new java.awt.Color(232, 135, 123));
        sum_jadwal.setForeground(new java.awt.Color(255, 255, 255));
        jPanel26.add(sum_jadwal, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 10, 110, -1));

        sum_tglbooking.setBackground(new java.awt.Color(232, 135, 123));
        sum_tglbooking.setForeground(new java.awt.Color(255, 255, 255));
        jPanel26.add(sum_tglbooking, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 50, 110, -1));

        jLabel42.setForeground(new java.awt.Color(255, 255, 255));
        jLabel42.setText("Jadwal Berangkat");
        jPanel26.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 10, -1, -1));

        sum_dari.setBackground(new java.awt.Color(232, 135, 123));
        sum_dari.setForeground(new java.awt.Color(255, 255, 255));
        sum_dari.setToolTipText("");
        jPanel26.add(sum_dari, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 10, 110, -1));

        sum_ke.setBackground(new java.awt.Color(232, 135, 123));
        sum_ke.setForeground(new java.awt.Color(255, 255, 255));
        jPanel26.add(sum_ke, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 50, 110, -1));

        jLabel39.setForeground(new java.awt.Color(255, 255, 255));
        jLabel39.setText("Ke");
        jPanel26.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 50, -1, -1));

        jLabel37.setForeground(new java.awt.Color(255, 255, 255));
        jLabel37.setText("Dari");
        jPanel26.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 10, -1, -1));

        sum_nama.setBackground(new java.awt.Color(232, 135, 123));
        sum_nama.setForeground(new java.awt.Color(255, 255, 255));
        jPanel26.add(sum_nama, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 10, 110, -1));

        sum_hargakelas.setBackground(new java.awt.Color(232, 135, 123));
        sum_hargakelas.setForeground(new java.awt.Color(255, 255, 255));
        jPanel26.add(sum_hargakelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 50, 110, -1));

        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Harga (Kelas)");
        jPanel26.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, -1));

        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Nama Kereta");
        jPanel26.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jLabel65.setForeground(new java.awt.Color(255, 255, 255));
        jLabel65.setText("Tanggal Booking");
        jPanel26.add(jLabel65, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 50, -1, -1));

        jPanel25.add(jPanel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 710, 90));

        jPanel27.setBackground(new java.awt.Color(232, 135, 123));
        jPanel27.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        sum_telp.setBackground(new java.awt.Color(232, 135, 123));
        sum_telp.setForeground(new java.awt.Color(255, 255, 255));
        jPanel27.add(sum_telp, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 10, 110, -1));

        Sum_Biaya.setBackground(new java.awt.Color(232, 135, 123));
        Sum_Biaya.setForeground(new java.awt.Color(255, 255, 255));
        jPanel27.add(Sum_Biaya, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 50, 110, -1));

        jLabel40.setForeground(new java.awt.Color(255, 255, 255));
        jLabel40.setText("Biaya");
        jPanel27.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(226, 50, 30, 20));

        jLabel41.setForeground(new java.awt.Color(255, 255, 255));
        jLabel41.setText("Telp");
        jPanel27.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 10, -1, -1));

        sum_pemesan.setBackground(new java.awt.Color(232, 135, 123));
        sum_pemesan.setForeground(new java.awt.Color(255, 255, 255));
        jPanel27.add(sum_pemesan, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 10, 110, -1));

        Sum_alamat.setBackground(new java.awt.Color(232, 135, 123));
        Sum_alamat.setForeground(new java.awt.Color(255, 255, 255));
        jPanel27.add(Sum_alamat, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 50, 110, -1));

        jLabel46.setForeground(new java.awt.Color(255, 255, 255));
        jLabel46.setText("Alamat");
        jPanel27.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, -1));

        jLabel47.setForeground(new java.awt.Color(255, 255, 255));
        jLabel47.setText("Nama Pemesan");
        jPanel27.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        lbl_reserve.setForeground(new java.awt.Color(255, 255, 255));
        lbl_reserve.setText("ID");
        jPanel27.add(lbl_reserve, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 10, -1, 20));

        jLabel66.setForeground(new java.awt.Color(255, 255, 255));
        jLabel66.setText("ID Reservasi :");
        jPanel27.add(jLabel66, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 10, -1, 20));

        lbl_seatcode.setForeground(new java.awt.Color(255, 255, 255));
        lbl_seatcode.setText("ID");
        jPanel27.add(lbl_seatcode, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 50, -1, 20));

        jLabel68.setForeground(new java.awt.Color(255, 255, 255));
        jLabel68.setText("Seat Code     :");
        jPanel27.add(jLabel68, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 50, 80, 20));

        lbl_reserve1.setForeground(new java.awt.Color(255, 255, 255));
        lbl_reserve1.setText("RS");
        jPanel27.add(lbl_reserve1, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 10, -1, 20));

        jPanel25.add(jPanel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 710, 90));

        jPanel28.setBackground(new java.awt.Color(232, 135, 123));
        jPanel28.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        submit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ukk_wijoyo_2/img/icons8_Checkmark_24px.png"))); // NOI18N
        submit.setContentAreaFilled(false);
        submit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitActionPerformed(evt);
            }
        });
        jPanel28.add(submit, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 0, 90, 40));

        jPanel25.add(jPanel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 330, 180, -1));

        jPanel29.setBackground(new java.awt.Color(232, 135, 123));

        jLabel2.setFont(new java.awt.Font("Segoe UI Light", 1, 48)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ukk_wijoyo_2/img/icons8_View_Details_48px.png"))); // NOI18N
        jLabel2.setText("DETAIL TIKET PESANAN ");

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel29Layout.createSequentialGroup()
                .addContainerGap(47, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 653, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel25.add(jPanel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 710, -1));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Summary", jPanel8);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        jTabbedPane4.addTab("Kereta", jPanel5);

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel9.add(tb_dari1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 70, -1));
        jPanel9.add(tb_ke1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 10, 149, -1));

        jLabel9.setText("Ke :");
        jPanel9.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 14, -1, -1));

        jLabel22.setText("Tanggal Berangkat :");
        jPanel9.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 49, -1, -1));

        tgl_berangkat1.setDateFormatString("yyyy-MM-dd");
        jPanel9.add(tgl_berangkat1, new org.netbeans.lib.awtextra.AbsoluteConstraints(147, 49, 150, -1));

        tbrute3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbrute3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbrute3MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbrute3);

        jPanel9.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(303, 11, -1, 380));

        jPanel17.setBackground(new java.awt.Color(232, 135, 123));
        jPanel17.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        search1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ukk_wijoyo_2/img/icons8_Search_24px.png"))); // NOI18N
        search1.setContentAreaFilled(false);
        search1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                search1ActionPerformed(evt);
            }
        });
        jPanel17.add(search1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 3, -1, 40));

        clear2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ukk_wijoyo_2/img/icons8_Delete_24px.png"))); // NOI18N
        clear2.setContentAreaFilled(false);
        jPanel17.add(clear2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 2, 80, 40));

        next2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ukk_wijoyo_2/img/icons8_Right_24px.png"))); // NOI18N
        next2.setContentAreaFilled(false);
        next2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                next2ActionPerformed(evt);
            }
        });
        jPanel17.add(next2, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 2, 60, 40));

        jPanel9.add(jPanel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, -1, -1));

        jLabel27.setText("Dari :");
        jPanel9.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 14, -1, -1));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 770, Short.MAX_VALUE)
            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel10Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 770, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel10Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jTabbedPane2.addTab("Cari Tiket", jPanel10);

        jPanel19.setBackground(new java.awt.Color(255, 255, 255));
        jPanel19.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel28.setText("Tanggal Booking :");
        jPanel19.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 49, -1, -1));
        jPanel19.add(tgl_booking1, new org.netbeans.lib.awtextra.AbsoluteConstraints(148, 49, 149, -1));

        jPanel20.setBackground(new java.awt.Color(232, 135, 123));
        jPanel20.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ukk_wijoyo_2/img/icons8_Search_24px.png"))); // NOI18N
        jButton13.setContentAreaFilled(false);
        jPanel20.add(jButton13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 3, -1, 40));

        jButton14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ukk_wijoyo_2/img/icons8_Delete_24px.png"))); // NOI18N
        jButton14.setContentAreaFilled(false);
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });
        jPanel20.add(jButton14, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 2, 80, 40));

        jButton15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ukk_wijoyo_2/img/icons8_Right_24px.png"))); // NOI18N
        jButton15.setContentAreaFilled(false);
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });
        jPanel20.add(jButton15, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 2, 60, 40));

        jPanel19.add(jPanel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 350, 287, 40));

        jPanel31.setBackground(new java.awt.Color(209, 181, 177));

        kd_rute1.setEnabled(false);

        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setText("Rute");

        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setText("User");

        kd_user1.setEnabled(false);

        javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
        jPanel31.setLayout(jPanel31Layout);
        jPanel31Layout.setHorizontalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel29)
                .addGap(4, 4, 4)
                .addComponent(kd_rute1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel30)
                .addGap(4, 4, 4)
                .addComponent(kd_user1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel31Layout.setVerticalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(kd_user1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(kd_rute1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel31Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel30)
                            .addComponent(jLabel29))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel19.add(jPanel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, 287, 40));
        jPanel19.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 14, -1, -1));

        tb_from1.setEnabled(false);
        jPanel19.add(tb_from1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 11, 70, -1));

        jLabel32.setText("Ke :");
        jPanel19.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 14, -1, -1));

        tb_to1.setEnabled(false);
        jPanel19.add(tb_to1, new org.netbeans.lib.awtextra.AbsoluteConstraints(148, 11, 149, -1));

        jLabel33.setText("Dari :");
        jPanel19.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 14, -1, -1));

        jLabel34.setText("Harga Transport :");
        jPanel19.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 114, -1, -1));

        harga_trans1.setEnabled(false);
        jPanel19.add(harga_trans1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 111, 70, -1));

        total_harga1.setEnabled(false);
        jPanel19.add(total_harga1, new org.netbeans.lib.awtextra.AbsoluteConstraints(77, 185, 220, -1));

        jLabel35.setText("Total Harga :");
        jPanel19.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 188, -1, -1));

        jumlah1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jumlah1ActionPerformed(evt);
            }
        });
        jumlah1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jumlah1KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jumlah1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jumlah1KeyTyped(evt);
            }
        });
        jPanel19.add(jumlah1, new org.netbeans.lib.awtextra.AbsoluteConstraints(227, 142, 70, -1));

        jLabel45.setText("Jumlah :");
        jPanel19.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(174, 145, 49, -1));

        harga_rute1.setEnabled(false);
        jPanel19.add(harga_rute1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 142, 70, -1));

        jLabel48.setText("Harga Rute :");
        jPanel19.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 145, 70, -1));

        jPanel32.setBackground(new java.awt.Color(221, 75, 57));

        javax.swing.GroupLayout jPanel32Layout = new javax.swing.GroupLayout(jPanel32);
        jPanel32.setLayout(jPanel32Layout);
        jPanel32Layout.setHorizontalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 460, Short.MAX_VALUE)
        );
        jPanel32Layout.setVerticalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 410, Short.MAX_VALUE)
        );

        jPanel19.add(jPanel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, -10, 460, 410));

        jLabel49.setText("Sisa Kursi     :");
        jPanel19.add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, 70, -1));

        jLabel50.setText("Jumlah Kursi :");
        jPanel19.add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, -1, -1));

        sisa1.setEnabled(false);
        jPanel19.add(sisa1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 250, 60, -1));

        kursi1.setEnabled(false);
        jPanel19.add(kursi1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 220, 60, -1));

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 770, Short.MAX_VALUE)
            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Pilihan", jPanel11);

        jPanel33.setBackground(new java.awt.Color(255, 255, 255));
        jPanel33.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tbl_pelanggan1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbl_pelanggan1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_pelanggan1MouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tbl_pelanggan1);

        jPanel33.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(303, 11, -1, 380));

        jPanel34.setBackground(new java.awt.Color(232, 135, 123));
        jPanel34.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        search3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ukk_wijoyo_2/img/icons8_Search_24px.png"))); // NOI18N
        search3.setContentAreaFilled(false);
        search3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                search3ActionPerformed(evt);
            }
        });
        jPanel34.add(search3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 3, -1, 40));

        next4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ukk_wijoyo_2/img/icons8_Right_24px.png"))); // NOI18N
        next4.setContentAreaFilled(false);
        next4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                next4ActionPerformed(evt);
            }
        });
        jPanel34.add(next4, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 2, 60, 40));

        clear5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ukk_wijoyo_2/img/icons8_Delete_24px.png"))); // NOI18N
        clear5.setContentAreaFilled(false);
        jPanel34.add(clear5, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 2, 80, 40));

        jPanel33.add(jPanel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, -1, -1));

        jPanel35.setBackground(new java.awt.Color(232, 135, 123));
        jPanel35.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel35.add(nama_pelanggan1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, 60, -1));

        jLabel51.setBackground(new java.awt.Color(255, 255, 255));
        jLabel51.setForeground(new java.awt.Color(255, 255, 255));
        jLabel51.setText("Nama");
        jPanel35.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, 20));

        jLabel52.setBackground(new java.awt.Color(255, 255, 255));
        jLabel52.setForeground(new java.awt.Color(255, 255, 255));
        jLabel52.setText("ID");
        jPanel35.add(jLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 40, -1, 20));

        jLabel53.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
        jLabel53.setForeground(new java.awt.Color(255, 255, 255));
        jLabel53.setText("CARI PELANGGAN");
        jPanel35.add(jLabel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 230, -1));

        jPanel35.add(id_pelanggan1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 40, 120, -1));

        jPanel33.add(jPanel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 280, 70));

        jPanel36.setBackground(new java.awt.Color(232, 135, 123));
        jPanel36.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Tambah_pel1.setForeground(new java.awt.Color(255, 255, 255));
        Tambah_pel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ukk_wijoyo_2/img/icons8_Add_New_24px.png"))); // NOI18N
        Tambah_pel1.setText("Tambah Baru");
        Tambah_pel1.setContentAreaFilled(false);
        Tambah_pel1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Tambah_pel1ActionPerformed(evt);
            }
        });
        jPanel36.add(Tambah_pel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 280, 40));

        jPanel33.add(jPanel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 280, 40));

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 770, Short.MAX_VALUE)
            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel12Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel33, javax.swing.GroupLayout.PREFERRED_SIZE, 763, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel12Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel33, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jTabbedPane2.addTab("Data Pelanggan", jPanel12);

        jPanel37.setBackground(new java.awt.Color(255, 255, 255));
        jPanel37.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel38.setBackground(new java.awt.Color(232, 135, 123));
        jPanel38.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        sum_jadwal1.setBackground(new java.awt.Color(232, 135, 123));
        sum_jadwal1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel38.add(sum_jadwal1, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 10, 110, -1));

        sum_tglbooking1.setBackground(new java.awt.Color(232, 135, 123));
        sum_tglbooking1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel38.add(sum_tglbooking1, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 50, 110, -1));

        jLabel54.setForeground(new java.awt.Color(255, 255, 255));
        jLabel54.setText("Tanggal Booking");
        jPanel38.add(jLabel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 50, -1, -1));

        jLabel55.setForeground(new java.awt.Color(255, 255, 255));
        jLabel55.setText("Jadwal Berangkat");
        jPanel38.add(jLabel55, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 10, -1, -1));

        sum_dari1.setBackground(new java.awt.Color(232, 135, 123));
        sum_dari1.setForeground(new java.awt.Color(255, 255, 255));
        sum_dari1.setToolTipText("");
        jPanel38.add(sum_dari1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 10, 110, -1));

        sum_ke1.setBackground(new java.awt.Color(232, 135, 123));
        sum_ke1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel38.add(sum_ke1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 50, 110, -1));

        jLabel56.setForeground(new java.awt.Color(255, 255, 255));
        jLabel56.setText("Ke");
        jPanel38.add(jLabel56, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 50, -1, -1));

        jLabel57.setForeground(new java.awt.Color(255, 255, 255));
        jLabel57.setText("Dari");
        jPanel38.add(jLabel57, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 10, -1, -1));

        sum_nama1.setBackground(new java.awt.Color(232, 135, 123));
        sum_nama1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel38.add(sum_nama1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 10, 110, -1));

        sum_hargakelas1.setBackground(new java.awt.Color(232, 135, 123));
        sum_hargakelas1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel38.add(sum_hargakelas1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 50, 110, -1));

        jLabel58.setForeground(new java.awt.Color(255, 255, 255));
        jLabel58.setText("Harga (Kelas)");
        jPanel38.add(jLabel58, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, -1));

        jLabel59.setForeground(new java.awt.Color(255, 255, 255));
        jLabel59.setText("Nama Kereta");
        jPanel38.add(jLabel59, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jPanel37.add(jPanel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 710, 90));

        jPanel39.setBackground(new java.awt.Color(232, 135, 123));
        jPanel39.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        sum_telp1.setBackground(new java.awt.Color(232, 135, 123));
        sum_telp1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel39.add(sum_telp1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 10, 110, -1));

        Sum_Biaya1.setBackground(new java.awt.Color(232, 135, 123));
        Sum_Biaya1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel39.add(Sum_Biaya1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 50, 110, -1));

        jLabel60.setForeground(new java.awt.Color(255, 255, 255));
        jLabel60.setText("Biaya");
        jPanel39.add(jLabel60, new org.netbeans.lib.awtextra.AbsoluteConstraints(226, 50, 30, 20));

        jLabel61.setForeground(new java.awt.Color(255, 255, 255));
        jLabel61.setText("Telp");
        jPanel39.add(jLabel61, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 10, -1, -1));

        sum_pemesan1.setBackground(new java.awt.Color(232, 135, 123));
        sum_pemesan1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel39.add(sum_pemesan1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 10, 110, -1));

        Sum_alamat1.setBackground(new java.awt.Color(232, 135, 123));
        Sum_alamat1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel39.add(Sum_alamat1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 50, 110, -1));

        jLabel62.setForeground(new java.awt.Color(255, 255, 255));
        jLabel62.setText("Alamat");
        jPanel39.add(jLabel62, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, -1));

        jLabel63.setForeground(new java.awt.Color(255, 255, 255));
        jLabel63.setText("Nama Pemesan");
        jPanel39.add(jLabel63, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jPanel37.add(jPanel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 710, 90));

        jPanel40.setBackground(new java.awt.Color(232, 135, 123));
        jPanel40.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        submit1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ukk_wijoyo_2/img/icons8_Checkmark_24px.png"))); // NOI18N
        submit1.setContentAreaFilled(false);
        jPanel40.add(submit1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 0, 90, 40));

        jPanel37.add(jPanel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 330, 180, -1));

        jPanel41.setBackground(new java.awt.Color(232, 135, 123));

        jLabel64.setFont(new java.awt.Font("Segoe UI Light", 1, 48)); // NOI18N
        jLabel64.setForeground(new java.awt.Color(255, 255, 255));
        jLabel64.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ukk_wijoyo_2/img/icons8_View_Details_48px.png"))); // NOI18N
        jLabel64.setText("DETAIL TIKET PESANAN ");

        javax.swing.GroupLayout jPanel41Layout = new javax.swing.GroupLayout(jPanel41);
        jPanel41.setLayout(jPanel41Layout);
        jPanel41Layout.setHorizontalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel41Layout.createSequentialGroup()
                .addContainerGap(47, Short.MAX_VALUE)
                .addComponent(jLabel64, javax.swing.GroupLayout.PREFERRED_SIZE, 653, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel41Layout.setVerticalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel41Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel64, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel37.add(jPanel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 710, -1));

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("Summary", jPanel15);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2)
        );

        jTabbedPane4.addTab("Pesawat", jPanel6);

        jPanel1.add(jTabbedPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 830, 430));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 515, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseClicked
        this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel11MouseClicked

    private void jLabel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseClicked
        this.setState(ICONIFIED);        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel12MouseClicked

    private void tbrute2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbrute2MouseClicked
        // TODO add your handling code here:
        int row = tbrute2.getSelectedRow();
        String diklik = (tbrute2.getModel().getValueAt(row, 0).toString());
        String sql = "SELECT * FROM rute WHERE id_rute='"+diklik+"'";
        
        String diklik2 = (tbrute2.getModel().getValueAt(row, 4).toString());
        String sql2 = "SELECT * FROM kereta WHERE nama_kereta='"+diklik2+"'";
        
        try {
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            if((rs.next())){
                String tambah1 = rs.getString("depart_from");
                tb_from.setText(tambah1);
                sum_dari.setText(tambah1);
                String tambah2 = rs.getString("depart_to");
                tb_to.setText(tambah2);
                sum_ke.setText(tambah2);
                String tambah3 = rs.getString("id_rute");
                kd_rute.setText(tambah3);
                String tambah4 = rs.getString("harga");
                harga_rute.setText(tambah4);
                String tambah12 = rs.getString("depart_date");
                sum_jadwal.setText(tambah12);
                
                //String tambah11 = rs.getString("depart_date");
                //((JTextField)tgl_berangkat.getDateEditor().getUiComponent()).setText(tambah11);
            }
            
            pst = conn.prepareStatement(sql2);
            rs = pst.executeQuery();

            if((rs.next())){
                String tambah4 = rs.getString("harga");
                harga_trans.setText(tambah4);
                sum_hargakelas.setText(tambah4);
                String tambah5 = rs.getString("nama_kereta");
                sum_nama.setText(tambah5);
                String tambah52 = rs.getString("jumlah_seat");
                kursi.setText(tambah52);
                
                //String tambah11 = rs.getString("depart_date");
                //((JTextField)tgl_berangkat.getDateEditor().getUiComponent()).setText(tambah11);
            }
        } catch (SQLException ex) {
            Logger.getLogger(kereta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tbrute2MouseClicked

    private void jumlahKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jumlahKeyTyped

    }//GEN-LAST:event_jumlahKeyTyped

    private void jumlahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jumlahActionPerformed
    
    }//GEN-LAST:event_jumlahActionPerformed

    private void jumlahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jumlahKeyPressed
        // TODO add your handling code here:

    }//GEN-LAST:event_jumlahKeyPressed

    private void jumlahKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jumlahKeyReleased
        // TODO add your handling code here:
        int angka1,angka2,angka3,kursi1,hasil,hasil2;

        angka1 = Integer.parseInt(harga_rute.getText());
        angka2 = Integer.parseInt(jumlah.getText());
        angka3 = Integer.parseInt(harga_trans.getText());
        
        kursi1 = Integer.parseInt(kursi.getText());
        
        hasil = (angka3 + angka1) * angka2;
        hasil2 = (kursi1 - angka2);
        total_harga.setText(Integer.toString(hasil));
        sisa.setText(Integer.toString(hasil2));
    }//GEN-LAST:event_jumlahKeyReleased

    private void searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchActionPerformed
        show_data_search();
    }//GEN-LAST:event_searchActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        show_data();
        clear();
        
    }//GEN-LAST:event_jButton11ActionPerformed

    private void next1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_next1ActionPerformed
        // TODO add your handling code here:
        auto_number_Reservasi();
        jTabbedPane1.setSelectedIndex(1);
        Sum_Biaya.setText(total_harga.getText());
                sum_tglbooking.setText(((JTextField)tgl_booking.getDateEditor().getUiComponent()).getText());
    }//GEN-LAST:event_next1ActionPerformed

    private void tbl_pelangganMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_pelangganMouseClicked
        // TODO add your handling code here:
        int row = tbl_pelanggan.getSelectedRow();
        String diklik = (tbl_pelanggan.getModel().getValueAt(row, 0).toString());
        String sql = "SELECT * FROM pelanggan WHERE id_pelanggan='"+diklik+"'";
        
        try{
        pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            if((rs.next())){
                String tambah1 = rs.getString("nama");
                sum_pemesan.setText(tambah1);
                String tambah12 = rs.getString("alamat");
                Sum_alamat.setText(tambah12);
                String tambah123 = rs.getString("telp");
                sum_telp.setText(tambah123);
                //String tambah11 = rs.getString("depart_date");
                //((JTextField)tgl_berangkat.getDateEditor().getUiComponent()).setText(tambah11);
            }
            } catch (SQLException ex) {
            Logger.getLogger(kereta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tbl_pelangganMouseClicked

    private void search2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_search2ActionPerformed
        // TODO add your handling code here:
        show_data_search_pelanggan();
    }//GEN-LAST:event_search2ActionPerformed

    private void next3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_next3ActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(3);
                Sum_Biaya.setText(total_harga.getText());
                sum_tglbooking.setText(((JTextField)tgl_booking.getDateEditor().getUiComponent()).getText());
    }//GEN-LAST:event_next3ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
        auto_number_Reservasi();
        jTabbedPane1.setSelectedIndex(2);
        Sum_Biaya.setText(total_harga.getText());
                sum_tglbooking.setText(((JTextField)tgl_booking.getDateEditor().getUiComponent()).getText());
    }//GEN-LAST:event_jButton12ActionPerformed

    private void Tambah_pelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Tambah_pelActionPerformed
        // TODO add your handling code here:
        pelanggan U = new pelanggan();
        U.setVisible(true);
    }//GEN-LAST:event_Tambah_pelActionPerformed

    private void tbrute3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbrute3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbrute3MouseClicked

    private void search1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_search1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_search1ActionPerformed

    private void next2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_next2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_next2ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jumlah1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jumlah1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jumlah1ActionPerformed

    private void jumlah1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jumlah1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jumlah1KeyPressed

    private void jumlah1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jumlah1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jumlah1KeyReleased

    private void jumlah1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jumlah1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jumlah1KeyTyped

    private void tbl_pelanggan1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_pelanggan1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbl_pelanggan1MouseClicked

    private void search3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_search3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_search3ActionPerformed

    private void next4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_next4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_next4ActionPerformed

    private void Tambah_pel1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Tambah_pel1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Tambah_pel1ActionPerformed

    private void submitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitActionPerformed
            
            try {
                String check = "SELECT * FROM reservasi WHERE id_reserve='"+lbl_reserve.getText()+"'";
                pst = conn.prepareStatement(check);
                rs = pst.executeQuery();
                
                if((!rs.next())){
                    String query = "INSERT INTO reservasi (id_reserve,reserve_date,total_price,depart_from,depart_to,id_rute,id_user) value(?,?,?,?,?,?,?)";
                    pst = conn.prepareStatement(query);
                    
                    pst.setString(1, lbl_reserve.getText());
                    pst.setString(2, ((JTextField)tgl_booking.getDateEditor().getUiComponent()).getText());
                    pst.setString(3, total_harga.getText());
                    pst.setString(4, tb_from.getText());
                    pst.setString(5, tb_to.getText());
                    pst.setString(6, kd_rute.getText());
                    pst.setString(7, "2");
                    
                    pst.execute();
                    JOptionPane.showMessageDialog(null, "Reservasi Berhasil");
                }else{
                    JOptionPane.showMessageDialog(null, "Data Sudah ada");
                }
                //beda input
                String check2 = "SELECT * FROM detail_pesan WHERE id_reserve='"+lbl_reserve.getText()+"'";
                pst = conn.prepareStatement(check2);
                rs = pst.executeQuery();
                int row = tbl_pelanggan.getSelectedRow();
                if((!rs.next())){
                    String query2 = "INSERT INTO detail_pesan (id_reserve,id_pelanggan,seat_code) value(?,?,?)";
                    pst = conn.prepareStatement(query2);
                    
                    pst.setString(1, lbl_reserve.getText());
                    pst.setString(2, tbl_pelanggan.getModel().getValueAt(row, 0).toString());
                    pst.setString(3, "1");
                    
                    pst.execute();
                    JOptionPane.showMessageDialog(null, "Reservasi Berhasil");
                }else{
                    JOptionPane.showMessageDialog(null, "Data Sudah ada");
                }
                //update seat
                int row2 = tbrute2.getSelectedRow();
                String diklik = (tbrute2.getModel().getValueAt(row2, 4).toString());
                String sql = "UPDATE kereta SET jumlah_seat=? where nama_kereta='"+diklik+"'";
                    pst = conn.prepareStatement(sql);
                    pst.setString(1, sisa.getText());
                    pst.execute();
                    clear_all();
                    jTabbedPane1.setSelectedIndex(0);
                    
                    
            } catch (SQLException ex) {
                Logger.getLogger(reservasi.class.getName()).log(Level.SEVERE, null, ex);
            }
            
    }//GEN-LAST:event_submitActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(reservasi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(reservasi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(reservasi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(reservasi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new reservasi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Sum_Biaya;
    private javax.swing.JTextField Sum_Biaya1;
    private javax.swing.JTextField Sum_alamat;
    private javax.swing.JTextField Sum_alamat1;
    private javax.swing.JButton Tambah_pel;
    private javax.swing.JButton Tambah_pel1;
    private javax.swing.JButton clear1;
    private javax.swing.JButton clear2;
    private javax.swing.JButton clear4;
    private javax.swing.JButton clear5;
    private javax.swing.JTextField harga_rute;
    private javax.swing.JTextField harga_rute1;
    private javax.swing.JTextField harga_trans;
    private javax.swing.JTextField harga_trans1;
    private javax.swing.JComboBox<String> id_pelanggan;
    private javax.swing.JComboBox<String> id_pelanggan1;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel36;
    private javax.swing.JPanel jPanel37;
    private javax.swing.JPanel jPanel38;
    private javax.swing.JPanel jPanel39;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel40;
    private javax.swing.JPanel jPanel41;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane4;
    private javax.swing.JTextField jumlah;
    private javax.swing.JTextField jumlah1;
    private javax.swing.JTextField kd_rute;
    private javax.swing.JTextField kd_rute1;
    private javax.swing.JTextField kd_user;
    private javax.swing.JTextField kd_user1;
    private javax.swing.JTextField kursi;
    private javax.swing.JTextField kursi1;
    private javax.swing.JLabel lbl_reserve;
    private javax.swing.JLabel lbl_reserve1;
    private javax.swing.JLabel lbl_seatcode;
    private javax.swing.JTextField nama_pelanggan;
    private javax.swing.JTextField nama_pelanggan1;
    private javax.swing.JButton next1;
    private javax.swing.JButton next2;
    private javax.swing.JButton next3;
    private javax.swing.JButton next4;
    private javax.swing.JButton search;
    private javax.swing.JButton search1;
    private javax.swing.JButton search2;
    private javax.swing.JButton search3;
    private javax.swing.JTextField sisa;
    private javax.swing.JTextField sisa1;
    private javax.swing.JButton submit;
    private javax.swing.JButton submit1;
    private javax.swing.JTextField sum_dari;
    private javax.swing.JTextField sum_dari1;
    private javax.swing.JTextField sum_hargakelas;
    private javax.swing.JTextField sum_hargakelas1;
    private javax.swing.JTextField sum_jadwal;
    private javax.swing.JTextField sum_jadwal1;
    private javax.swing.JTextField sum_ke;
    private javax.swing.JTextField sum_ke1;
    private javax.swing.JTextField sum_nama;
    private javax.swing.JTextField sum_nama1;
    private javax.swing.JTextField sum_pemesan;
    private javax.swing.JTextField sum_pemesan1;
    private javax.swing.JTextField sum_telp;
    private javax.swing.JTextField sum_telp1;
    private javax.swing.JTextField sum_tglbooking;
    private javax.swing.JTextField sum_tglbooking1;
    private javax.swing.JTextField tb_dari;
    private javax.swing.JTextField tb_dari1;
    private javax.swing.JTextField tb_from;
    private javax.swing.JTextField tb_from1;
    private javax.swing.JTextField tb_ke;
    private javax.swing.JTextField tb_ke1;
    private javax.swing.JTextField tb_to;
    private javax.swing.JTextField tb_to1;
    private javax.swing.JTable tbl_pelanggan;
    private javax.swing.JTable tbl_pelanggan1;
    private javax.swing.JTable tbrute2;
    private javax.swing.JTable tbrute3;
    private com.toedter.calendar.JDateChooser tgl_berangkat;
    private com.toedter.calendar.JDateChooser tgl_berangkat1;
    private com.toedter.calendar.JDateChooser tgl_booking;
    private com.toedter.calendar.JDateChooser tgl_booking1;
    private javax.swing.JTextField total_harga;
    private javax.swing.JTextField total_harga1;
    // End of variables declaration//GEN-END:variables
}
