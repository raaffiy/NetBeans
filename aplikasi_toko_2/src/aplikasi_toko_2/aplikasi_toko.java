/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package aplikasi_toko_2;

import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Acer
 */
public class aplikasi_toko extends javax.swing.JFrame {
     Connection conn = connection.configDbConnection();

    /**
     * Creates new form aplikasi_toko
     */ 
    public aplikasi_toko() {
        initComponents();
        tampil();
        simpan();
    }
    
    private void tampil(){
        DefaultTableModel table_model = new DefaultTableModel();
        table_model.addColumn("N0.");
        table_model.addColumn("Kode Barang");
        table_model.addColumn("Nama BArang");
        table_model.addColumn("Harga Jual");
        table_model.addColumn("Stok");

    
        table_barang.setModel(table_model);
        ResultSet rs = null;
        Statement st = null;
        
        try {
            st = conn.createStatement();        
            String searchQuery = search.getText();

        String query = "SELECT * FROM barang WHERE nm_barang LIKE '%" + searchQuery + "%' OR kd_barang LIKE '%" + searchQuery + "%' ORDER BY kd_barang";
            rs = st.executeQuery(query);
            int no = 1;
            while(rs.next()){
                table_model.addRow(new Object[] {
                    no++,
                    rs.getString("kd_barang"),
                    rs.getString("nm_barang"),
                    rs.getString("hrg_jual"),
                    rs.getString("stok"),
                });
            }
            
            table_barang.setModel(table_model);
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, "gagal menambilkan data " + e.getMessage());
        } finally {
              try {
                if(rs != null)rs.close();
                if(st != null)st.close();
            } catch (Exception e) {
               JOptionPane.showConfirmDialog(null, "gagal menutup resource" + e.getMessage());
            }
        }
    }
    
    
    private void aktif (){
        kd_barang.setEnabled(true);
        nm_barang.setEnabled(true);
        hrg_jual.setEnabled(true);
        stok.setEnabled(true);
    };
    
        private void nonaktif (){
        kd_barang.setEnabled(false);
        nm_barang.setEnabled(false);
        hrg_jual.setEnabled(false);
        stok.setEnabled(false);
    };
        
    private void bersih (){
        kd_barang.setText("");
        nm_barang.setText("");
        hrg_jual.setText("");
        stok.setText("");
    };
        
    private void simpan (){
        if(kd_barang.getText().isEmpty() || nm_barang.getText().isEmpty() || hrg_jual.getText().isEmpty() || stok.getText().isEmpty()){
            JOptionPane.showConfirmDialog(null, "isi dengan lengkap ", "peringatan", JOptionPane.WARNING_MESSAGE);
        }else {
            PreparedStatement pst = null;
            
            try {
                    String sql  = "INSERT INTO barang (kd_barang, nm_barang, hrg_jual, stok) VALUES (?, ?, ?, ?)";
                    PreparedStatement pstmt = conn.prepareStatement(sql);

                    // Menggunakan PreparedStatement untuk menghindari SQL Injection
                    pstmt.setString(1, kd_barang.getText());
                    pstmt.setString(2, nm_barang.getText());
                    pstmt.setString(3, hrg_jual.getText());
                    pstmt.setString(4, stok.getText());

                    pstmt.executeUpdate();

                    // Menampilkan pesan bahwa data berhasil disimpan
                    JOptionPane.showMessageDialog(null, "Data Berhasil di Simpan.");

                    // Membersihkan field input setelah data disimpan
                    bersih();
            } catch (Exception e) {
               JOptionPane.showConfirmDialog(null, "keterangan eror : " + e.getMessage() , "eror" , JOptionPane.WARNING_MESSAGE);

            } finally {
                  try {
                    if(pst != null)pst.close();
                } catch (Exception e) {
                   JOptionPane.showConfirmDialog(null, "gagal menutup resource" + e.getMessage());
                }
            }
        }
    };
    
    private void hapus(){
        Statement st = null;

        try {
            st = conn.createStatement();
 
            String sql = "delete from barang where kd_barang = '" + kd_barang.getText() + "' ";
            st.executeUpdate(sql);
 
            bersih();
            tampil();
            JOptionPane.showConfirmDialog(null, "Berhasil menghapus" );
        } catch (Exception e) {
           JOptionPane.showConfirmDialog(null, e.getMessage());

        }finally {
        try {
            if (st != null) st.close(); // Tutup Statement setelah selesai
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal menutup Statement: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    }
    
    private void UpdateData (){
       Statement st = null;

        try {
             st = conn.createStatement();
           
       String kd_barang_lama = kd_barang.getText(); 
        
       String sql = "UPDATE barang SET " +
             "kd_barang= '" + kd_barang.getText() + "', " +
             "nm_barang= '" + nm_barang.getText() + "', " +
             "hrg_jual= '" + hrg_jual.getText() + "', " +
             "stok= '" + stok.getText() + "' " +
             "WHERE kd_barang= '" + kd_barang_lama + "' " +  // Kondisi pertama berdasarkan kd_barang_lama
             "OR nm_barang= '" + nm_barang.getText() + "'";  // Kondisi kedua berdasarkan nm_barang

       
             System.out.println("SQL: " + sql);

            st.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Data Berhasil Di Edit" );
            bersih();
            tampil();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal menutup Statement: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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

        jLabel1 = new javax.swing.JLabel();
        search = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        table_barang = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        nm_barang = new javax.swing.JTextField();
        hrg_jual = new javax.swing.JTextField();
        stok = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        kd_barang = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(new java.awt.Dimension(0, 0));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("cari nama barang");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 70, -1, -1));

        search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchActionPerformed(evt);
            }
        });
        search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                searchKeyTyped(evt);
            }
        });
        getContentPane().add(search, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 70, 178, -1));

        table_barang.setModel(new javax.swing.table.DefaultTableModel(
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
        table_barang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_barangMouseClicked(evt);
            }
        });
        table_barang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                table_barangKeyPressed(evt);
            }
        });
        jScrollPane3.setViewportView(table_barang);

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 327, 133));

        jLabel2.setFont(new java.awt.Font("Poppins", 1, 24)); // NOI18N
        jLabel2.setText("TABEL DATA BARANG");
        jLabel2.setAutoscrolls(true);
        jLabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel2.setInheritsPopupMenu(false);
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, -1, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setText("input data barang ");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 260, -1, -1));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("DATA BARANG"));

        jLabel4.setText("Kode Barang");

        jLabel5.setText("nama Barang");

        jLabel6.setText("Stok ");

        jLabel8.setText("Harga Jual");

        nm_barang.setEnabled(false);
        nm_barang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nm_barangActionPerformed(evt);
            }
        });
        nm_barang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nm_barangKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                nm_barangKeyReleased(evt);
            }
        });

        hrg_jual.setEnabled(false);
        hrg_jual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hrg_jualActionPerformed(evt);
            }
        });
        hrg_jual.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                hrg_jualKeyPressed(evt);
            }
        });

        stok.setEnabled(false);
        stok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stokActionPerformed(evt);
            }
        });

        jButton1.setText("TAMBAH");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("KELUAR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        kd_barang.setEnabled(false);
        kd_barang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kd_barangActionPerformed(evt);
            }
        });
        kd_barang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kd_barangKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                kd_barangKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel6)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(stok, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel8)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(hrg_jual, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel5)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                            .addComponent(nm_barang, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel4)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(kd_barang, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2)))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel5))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(kd_barang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(nm_barang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel8))
                            .addComponent(hrg_jual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6))
                    .addComponent(stok, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 310, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchActionPerformed

    private void searchKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchKeyTyped
        tampil();        // TODO add your handling code here:
    }//GEN-LAST:event_searchKeyTyped

    private void table_barangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_barangMouseClicked
        int row = table_barang.getSelectedRow();  // Mendapatkan indeks baris yang dipilih

        // Mengambil data dari tabel berdasarkan indeks baris
        String kode = table_barang.getModel().getValueAt(row, 1).toString();
        String nama = table_barang.getModel().getValueAt(row, 2).toString();
        String harga = table_barang.getModel().getValueAt(row, 3).toString();
        String stokBarang = table_barang.getModel().getValueAt(row, 4).toString();

        // Mengisi data ke dalam field input
        kd_barang.setText(kode);
        nm_barang.setText(nama);
        hrg_jual.setText(harga);
        stok.setText(stokBarang);

        // Mengaktifkan form agar bisa diedit
        jButton1.setText("UPDATE");
        jButton2.setText("BATAL");
        aktif();        // TODO add your handling code here:
    }//GEN-LAST:event_table_barangMouseClicked

    private void table_barangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_table_barangKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
            hapus();
        }          // TODO add your handling code here:
    }//GEN-LAST:event_table_barangKeyPressed

    private void nm_barangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nm_barangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nm_barangActionPerformed

    private void nm_barangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nm_barangKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            hrg_jual.requestFocus();
        }
    }//GEN-LAST:event_nm_barangKeyPressed

    private void nm_barangKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nm_barangKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_nm_barangKeyReleased

    private void hrg_jualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hrg_jualActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_hrg_jualActionPerformed

    private void hrg_jualKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_hrg_jualKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            stok.requestFocus();
        }          // TODO add your handling code here:
    }//GEN-LAST:event_hrg_jualKeyPressed

    private void stokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stokActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_stokActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if(jButton1.getText().equals("TAMBAH")){
            jButton1.setText("SIMPAN");
            jButton2.setText("BATAL");
            kd_barang.requestFocus();
            bersih();
            aktif();
        }else if(jButton1.getText().equals("SIMPAN")){
            jButton1.setText("TAMBAH");
            jButton2.setText("KELUAR");
            simpan();
            bersih();
            nonaktif();
            tampil();
        }else if(jButton1.getText().equals("UPDATE")){
            jButton1.setText("TAMBAH");
            jButton2.setText("KELUAR");
            UpdateData();
            bersih();
            nonaktif();
            tampil();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if(jButton2.getText().equals("KELUAR")){
            this.dispose();
        }else if(jButton2.getText().equals("BATAL")){
            bersih();
            nonaktif();
            jButton1.setText("TAMBAH");
            jButton2.setText("KELUAR");
        }     // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void kd_barangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kd_barangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kd_barangActionPerformed

    private void kd_barangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kd_barangKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_kd_barangKeyPressed

    private void kd_barangKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kd_barangKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_kd_barangKeyReleased

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
            java.util.logging.Logger.getLogger(aplikasi_toko.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(aplikasi_toko.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(aplikasi_toko.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(aplikasi_toko.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new aplikasi_toko().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField hrg_jual;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField kd_barang;
    private javax.swing.JTextField nm_barang;
    private javax.swing.JTextField search;
    private javax.swing.JTextField stok;
    private javax.swing.JTable table_barang;
    // End of variables declaration//GEN-END:variables
}
