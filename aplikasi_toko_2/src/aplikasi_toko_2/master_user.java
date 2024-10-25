/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package aplikasi_toko_2;

/**
 *
 * @author Advan
 */
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class master_user extends javax.swing.JFrame {
         Connection conn = connection.configDbConnection();

    /**
     * Creates new form master_user
     */
    public master_user() {
         initComponents();
        tampil();
        simpan();
    }
    
    private void tampil(){
        DefaultTableModel table_model = new DefaultTableModel();
        table_model.addColumn("N0.");
        table_model.addColumn("Kode User");
        table_model.addColumn("Akses Level");
        table_model.addColumn("Password");
        table_model.addColumn("Nama User");

        table_user.setModel(table_model);
        ResultSet rs = null;
        Statement st = null;
        
        try {
            st = conn.createStatement();

        String query = "SELECT * FROM user WHERE nama_user LIKE '%" + "%' OR kd_user LIKE '%" + "%' ORDER BY kd_user";
            rs = st.executeQuery(query);
            int no = 1;
            while(rs.next()){
                table_model.addRow(new Object[] {
                    no++,
                    rs.getString("kd_user"),
                    rs.getString("nama_user"),
                    rs.getString("password"),
                    rs.getString("level"),
                });
            }
            
            table_user.setModel(table_model);
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, "gagal mengambil data " + e.getMessage());
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
        kd_user.setEnabled(true);
        nama.setEnabled(true);
        password.setEnabled(true);
        level.setEnabled(true);
    };
    
        private void nonaktif (){
        kd_user.setEnabled(false);
        nama.setEnabled(false);
        password.setEnabled(false);
        level.setEnabled(false);
    };
        
         private void bersih (){
        kd_user.setText("");
        nama.setText("");
        password.setText("");
         };
         
         
         private void simpan (){
        if(kd_user.getText().isEmpty() || nama.getText().isEmpty() || password.getPassword().length == 0){
            JOptionPane.showConfirmDialog(null, "isi dengan lengkap ", "peringatan", JOptionPane.WARNING_MESSAGE);
        }else {
            PreparedStatement pst = null;
            
            try {
                    String sql  = "INSERT INTO user (kd_user, nama_user, password, level) VALUES (?, ?, ?, ?)";
                    PreparedStatement pstmt = conn.prepareStatement(sql);
                    
                    Object selectItem = level.getSelectedItem();
                    
                    
                    pstmt.setString(1, kd_user.getText());
                    pstmt.setString(2, nama.getText());
                    pstmt.setString(3, password.getPassword().toString());
                    pstmt.setString(4, selectItem.toString());

                    pstmt.executeUpdate();

                    JOptionPane.showMessageDialog(null, "Data Berhasil di Simpan.");

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
 
            String sql = "delete from user where kd_user = '" + kd_user.getText() + "' ";
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


               String sql = "UPDATE user SET " +
                     "kd_user = '" + kd_user.getText() + "', " +
                     "nama_user = '" + nama.getText() + "', " +
                     "password = '" + password.getPassword().hashCode() + "', " +
                     "level = '" + level.getSelectedItem().toString() + "' " +
                     "WHERE kd_user = '" + kd_user.getText() + "'";  

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

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        level = new javax.swing.JComboBox<>();
        password = new javax.swing.JPasswordField();
        nama = new javax.swing.JTextField();
        kd_user = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        table_user = new javax.swing.JTable();
        btn_tambah = new javax.swing.JButton();
        btn_keluar = new javax.swing.JButton();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Data User"));

        jLabel1.setText("Kode User");

        jLabel2.setText("Nama User");

        jLabel3.setText("Password");

        jLabel4.setText("Akses level");

        level.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", " " }));
        level.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        nama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                namaActionPerformed(evt);
            }
        });
        nama.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                namaKeyPressed(evt);
            }
        });

        kd_user.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kd_userActionPerformed(evt);
            }
        });
        kd_user.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kd_userKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(level, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(25, 25, 25)
                        .addComponent(password))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(nama, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                            .addComponent(kd_user))))
                .addContainerGap(53, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(kd_user, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(nama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(level, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(51, Short.MAX_VALUE))
        );

        table_user.setModel(new javax.swing.table.DefaultTableModel(
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
        table_user.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_userMouseClicked(evt);
            }
        });
        table_user.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                table_userKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(table_user);

        btn_tambah.setText("TAMBAH");
        btn_tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tambahActionPerformed(evt);
            }
        });

        btn_keluar.setText("KELUAR");
        btn_keluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_keluarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addComponent(btn_tambah, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(btn_keluar)))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_tambah, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(btn_keluar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void kd_userActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kd_userActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kd_userActionPerformed

    private void table_userMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_userMouseClicked
        int row = table_user.getSelectedRow();  

        String kode = table_user.getModel().getValueAt(row, 1).toString();
        String nama_user = table_user.getModel().getValueAt(row, 2).toString();
        String harga = table_user.getModel().getValueAt(row, 3).toString();
        String stokBarang = table_user.getModel().getValueAt(row, 4).toString();

        kd_user.setText(kode);
        nama.setText(nama_user);

        password.setText(harga);

        level.setSelectedItem(stokBarang);

        btn_tambah.setText("UPDATE");
        btn_keluar.setText("BATAL");

        aktif();

                     // TODO add your handling code here:
    }//GEN-LAST:event_table_userMouseClicked

    private void btn_tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tambahActionPerformed
          if(btn_tambah.getText().equals("TAMBAH")){
        btn_tambah.setText("SIMPAN");
        btn_keluar.setText("BATAL");
        kd_user.requestFocus();
        bersih();
        aktif();
      }else if(btn_tambah.getText().equals("SIMPAN")){
        btn_tambah.setText("TAMBAH");
        btn_keluar.setText("KELUAR");
        simpan();
        bersih();
        nonaktif();
        tampil();
      }else if(btn_tambah.getText().equals("UPDATE")){
        btn_tambah.setText("TAMBAH");
        btn_keluar.setText("KELUAR");
        UpdateData();
        bersih();
        nonaktif();
        tampil();
      }        // TODO add your handling code here:
    }//GEN-LAST:event_btn_tambahActionPerformed

    private void btn_keluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_keluarActionPerformed
           if(btn_keluar.getText().equals("KELUAR")){
        this.dispose();
      }else if(btn_keluar.getText().equals("BATAL")){
        bersih();
        nonaktif();
        btn_tambah.setText("TAMBAH");
        btn_keluar.setText("KELUAR");
      }     // TODO add your handling code here:        // TODO add your handling code here:
    }//GEN-LAST:event_btn_keluarActionPerformed

    private void table_userKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_table_userKeyPressed
         if(evt.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
            hapus();
        }           // TODO add your handling code here:
    }//GEN-LAST:event_table_userKeyPressed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        nonaktif();        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowActivated

    private void kd_userKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kd_userKeyPressed
         if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            nama.requestFocus();
        }           // TODO add your handling code here:
    }//GEN-LAST:event_kd_userKeyPressed

    private void namaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_namaActionPerformed
                // TODO add your handling code here:
    }//GEN-LAST:event_namaActionPerformed

    private void namaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_namaKeyPressed
         if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            password.requestFocus();
        }           // TODO add your handling code here:
    }//GEN-LAST:event_namaKeyPressed

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
            java.util.logging.Logger.getLogger(master_user.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(master_user.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(master_user.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(master_user.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new master_user().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_keluar;
    private javax.swing.JButton btn_tambah;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField kd_user;
    private javax.swing.JComboBox<String> level;
    private javax.swing.JTextField nama;
    private javax.swing.JPasswordField password;
    private javax.swing.JTable table_user;
    // End of variables declaration//GEN-END:variables
}
