/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ukk_wijoyo_2;

import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JFrame;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import java.awt.*;
/**
 *
 * @author gaming
 */
public class Main_Menu extends javax.swing.JFrame {
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    /**
     * Creates new form Main_Menu
     */
    public Main_Menu() {
        initComponents();
        try {
            conn = koneksi.wijoyokonek();
            load();
        } catch (SQLException ex) {
            Logger.getLogger(Main_Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void load(){
        
        try {
            String perintah = "SELECT * FROM user WHERE username='"+txtuser.getText()+"'";
            pst = conn.prepareStatement(perintah);
            rs = pst.executeQuery();
            
             if(rs.next()){
                 String tambah1 = rs.getString("nama");
                 txtnama.setText(tambah1);
                 String tambah2 = rs.getString("akses");
                 txtakses.setText(tambah2);
             }else{
             JOptionPane.showMessageDialog(null, "Profil Tidak Ditemukan");
             }
                 
        } catch (SQLException ex) {
            Logger.getLogger(Main_Menu.class.getName()).log(Level.SEVERE, null, ex);
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtwaktu = new javax.swing.JLabel();
        txtnama = new javax.swing.JTextField();
        txtakses = new javax.swing.JTextField();
        txtuser = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        btnmaskapai = new javax.swing.JButton();
        btnkereta = new javax.swing.JButton();
        btnrute = new javax.swing.JButton();
        btnreservasi = new javax.swing.JButton();
        btnpelanggan = new javax.swing.JButton();
        btnpengguna = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(221, 75, 57));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Aplikasi Ticketing Kereta dan Pesawat");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("MAIN MENU");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 29, -1, 40));

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("?");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 0, -1, -1));

        jLabel12.setBackground(new java.awt.Color(255, 255, 255));
        jLabel12.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("-");
        jLabel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel12MouseClicked(evt);
            }
        });
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 0, -1, -1));

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ukk_wijoyo_2/img/icons8_Menu_24px.png"))); // NOI18N
        jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 30, -1, 40));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(-5, 0, 540, 70));

        jPanel3.setBackground(new java.awt.Color(34, 45, 50));

        jPanel4.setBackground(new java.awt.Color(44, 59, 65));

        jLabel4.setFont(new java.awt.Font("Segoe UI Light", 0, 11)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Nama :");

        jLabel5.setFont(new java.awt.Font("Segoe UI Light", 0, 11)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Akses :");

        jLabel8.setFont(new java.awt.Font("Segoe UI Light", 0, 11)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Waktu :");

        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Wijoyo Wisnu M");

        jLabel10.setFont(new java.awt.Font("Segoe UI Light", 0, 11)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Username :");

        txtwaktu.setFont(new java.awt.Font("Segoe UI Light", 0, 11)); // NOI18N
        txtwaktu.setForeground(new java.awt.Color(255, 255, 255));
        txtwaktu.setText("--");

        txtnama.setBackground(new java.awt.Color(44, 59, 65));
        txtnama.setForeground(new java.awt.Color(255, 255, 255));
        txtnama.setText(".");
        txtnama.setBorder(null);

        txtakses.setBackground(new java.awt.Color(44, 59, 65));
        txtakses.setForeground(new java.awt.Color(255, 255, 255));
        txtakses.setText(".");
        txtakses.setBorder(null);

        txtuser.setBackground(new java.awt.Color(44, 59, 65));
        txtuser.setForeground(new java.awt.Color(255, 255, 255));
        txtuser.setText("jTextField1");
        txtuser.setBorder(null);
        txtuser.setFocusable(false);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtnama, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtakses, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtwaktu)
                            .addComponent(txtuser, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtuser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtnama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtakses, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtwaktu))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addContainerGap())
        );

        jLabel3.setFont(new java.awt.Font("Segoe UI Light", 0, 11)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ukk_wijoyo_2/img/icons8_User_24px.png"))); // NOI18N
        jLabel3.setText("Profil Pengguna");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 120, 290));

        btnmaskapai.setBackground(new java.awt.Color(221, 75, 57));
        btnmaskapai.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        btnmaskapai.setForeground(new java.awt.Color(255, 255, 255));
        btnmaskapai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ukk_wijoyo_2/img/icons8_Airplane_Mode_On_24px.png"))); // NOI18N
        btnmaskapai.setText("Maskapai");
        btnmaskapai.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnmaskapai.setBorderPainted(false);
        btnmaskapai.setFocusPainted(false);
        btnmaskapai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnmaskapaiActionPerformed(evt);
            }
        });
        jPanel1.add(btnmaskapai, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 100, 120, 100));

        btnkereta.setBackground(new java.awt.Color(221, 75, 57));
        btnkereta.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        btnkereta.setForeground(new java.awt.Color(255, 255, 255));
        btnkereta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ukk_wijoyo_2/img/icons8_Train_24px.png"))); // NOI18N
        btnkereta.setText("Kereta");
        btnkereta.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnkereta.setBorderPainted(false);
        btnkereta.setFocusPainted(false);
        btnkereta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnkeretaActionPerformed(evt);
            }
        });
        jPanel1.add(btnkereta, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 100, 130, 100));

        btnrute.setBackground(new java.awt.Color(221, 75, 57));
        btnrute.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        btnrute.setForeground(new java.awt.Color(255, 255, 255));
        btnrute.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ukk_wijoyo_2/img/icons8_Tracks_24px.png"))); // NOI18N
        btnrute.setText("Rute");
        btnrute.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnrute.setBorderPainted(false);
        btnrute.setFocusPainted(false);
        btnrute.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnruteActionPerformed(evt);
            }
        });
        jPanel1.add(btnrute, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 100, 130, 100));

        btnreservasi.setBackground(new java.awt.Color(221, 75, 57));
        btnreservasi.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        btnreservasi.setForeground(new java.awt.Color(255, 255, 255));
        btnreservasi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ukk_wijoyo_2/img/icons8_Booking_24px.png"))); // NOI18N
        btnreservasi.setText("Reservasi");
        btnreservasi.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnreservasi.setBorderPainted(false);
        btnreservasi.setFocusPainted(false);
        btnreservasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnreservasiActionPerformed(evt);
            }
        });
        jPanel1.add(btnreservasi, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 210, 130, 100));

        btnpelanggan.setBackground(new java.awt.Color(221, 75, 57));
        btnpelanggan.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        btnpelanggan.setForeground(new java.awt.Color(255, 255, 255));
        btnpelanggan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ukk_wijoyo_2/img/icons8_Contact_Details_24px.png"))); // NOI18N
        btnpelanggan.setText("Pelanggan");
        btnpelanggan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnpelanggan.setBorderPainted(false);
        btnpelanggan.setFocusPainted(false);
        btnpelanggan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnpelangganActionPerformed(evt);
            }
        });
        jPanel1.add(btnpelanggan, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 210, 130, 100));

        btnpengguna.setBackground(new java.awt.Color(221, 75, 57));
        btnpengguna.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        btnpengguna.setForeground(new java.awt.Color(255, 255, 255));
        btnpengguna.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ukk_wijoyo_2/img/icons8_User_24px.png"))); // NOI18N
        btnpengguna.setText("Pengguna");
        btnpengguna.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnpengguna.setBorderPainted(false);
        btnpengguna.setFocusPainted(false);
        btnpengguna.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnpenggunaActionPerformed(evt);
            }
        });
        jPanel1.add(btnpengguna, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 210, 120, 100));

        jButton9.setBackground(new java.awt.Color(255, 255, 255));
        jButton9.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jButton9.setForeground(new java.awt.Color(102, 102, 102));
        jButton9.setText("Log Out");
        jButton9.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 320, 400, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    public JTextField getJTextField(){
        return txtnama;
    }
    private void jLabel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseClicked
        this.setState(Main_Menu.ICONIFIED);        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel12MouseClicked

    private void btnmaskapaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnmaskapaiActionPerformed
        maskapai B = new maskapai();
        B.setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_btnmaskapaiActionPerformed

    private void btnkeretaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnkeretaActionPerformed
        kereta U = new kereta();
        U.setVisible(true);
    }//GEN-LAST:event_btnkeretaActionPerformed

    private void btnruteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnruteActionPerformed
        rute U = new rute();
        U.setVisible(true);
    }//GEN-LAST:event_btnruteActionPerformed

    private void btnreservasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnreservasiActionPerformed
        reservasi U = new reservasi();
        U.setVisible(true);
    }//GEN-LAST:event_btnreservasiActionPerformed

    private void btnpelangganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnpelangganActionPerformed
        pelanggan U = new pelanggan();
        U.setVisible(true);
    }//GEN-LAST:event_btnpelangganActionPerformed

    private void btnpenggunaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnpenggunaActionPerformed
        pengguna U = new pengguna();
        U.setVisible(true);
    }//GEN-LAST:event_btnpenggunaActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        Login_user masuk = new Login_user();
        masuk.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton9ActionPerformed

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
            java.util.logging.Logger.getLogger(Main_Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main_Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main_Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main_Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main_Menu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnkereta;
    public javax.swing.JButton btnmaskapai;
    public javax.swing.JButton btnpelanggan;
    public javax.swing.JButton btnpengguna;
    public javax.swing.JButton btnreservasi;
    public javax.swing.JButton btnrute;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    public javax.swing.JTextField txtakses;
    public javax.swing.JTextField txtnama;
    public javax.swing.JTextField txtuser;
    public javax.swing.JLabel txtwaktu;
    // End of variables declaration//GEN-END:variables
}