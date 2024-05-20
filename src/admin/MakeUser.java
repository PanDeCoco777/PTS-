/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;

import Parking.SetUp;
import User.UserDash;
import com.mysql.jdbc.Connection;
import config.dcConnector;
import config.passwordHash;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.NoSuchAlgorithmException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import pts.LoginForm;

/**
 *
 * @author pc
 */

public class MakeUser extends javax.swing.JFrame {
    

    /**
     * Creates new form MakeUser
     */
    public MakeUser() {
        initComponents();
        
    }
    
  
    public String destination = "";
    File selectedFile;
    public String oldpath;
    public String path;
    
    public int FileExistenceChecker(String path){
        File file = new File(path);
        String fileName = file.getName();
        
        Path filePath = Paths.get("src/userimages", fileName);
        boolean fileExists = Files.exists(filePath);
        
        if (fileExists) {
            return 1;
        } else {
            return 0;
        }
    
    }
    
  public static int getHeightFromWidth(String imagePath, int desiredWidth) {
        try {
            // Read the image file
            File imageFile = new File(imagePath);
            BufferedImage image = ImageIO.read(imageFile);
            
            // Get the original width and height of the image
            int originalWidth = image.getWidth();
            int originalHeight = image.getHeight();
            
            // Calculate the new height based on the desired width and the aspect ratio
            int newHeight = (int) ((double) desiredWidth / originalWidth * originalHeight);
            
            return newHeight;
        } catch (IOException ex) {
            System.out.println("No image found!");
        }
        
        return -1;
    }
    
    
    
    
    public  ImageIcon ResizeImage(String ImagePath, byte[] pic, JLabel label) {
    ImageIcon MyImage = null;
        if(ImagePath !=null){
            MyImage = new ImageIcon(ImagePath);
        }else{
            MyImage = new ImageIcon(pic);
        }
        
    int newHeight = getHeightFromWidth(ImagePath, label.getWidth());

    Image img = MyImage.getImage();
    Image newImg = img.getScaledInstance(label.getWidth(), newHeight, Image.SCALE_SMOOTH);
    ImageIcon image = new ImageIcon(newImg);
    return image;
}
    
    
     public void imageUpdater(String existingFilePath, String newFilePath){
        File existingFile = new File(existingFilePath);
        if (existingFile.exists()) {
            String parentDirectory = existingFile.getParent();
            File newFile = new File(newFilePath);
            String newFileName = newFile.getName();
            File updatedFile = new File(parentDirectory, newFileName);
            existingFile.delete();
            try {
                Files.copy(newFile.toPath(), updatedFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Image updated successfully.");
            } catch (IOException e) {
                System.out.println("Error occurred while updating the image: "+e);
            }
        } else {
            try{
                Files.copy(selectedFile.toPath(), new File(destination).toPath(), StandardCopyOption.REPLACE_EXISTING);
            }catch(IOException e){
                System.out.println("Error on update!");
            }
        }
   }

    
    
    public static String Email, Username;
    
    public boolean duplicateCheck(){
        
        dcConnector dcc = new dcConnector();
        try{
            String query = "SELECT * FROM TTB  WHERE u_Username = '" + us.getText() + "' OR u_Email = '" + em.getText() + "'";
            ResultSet resultSet = dcc.getData(query);
            
            
            if(resultSet.next()){
                Email = resultSet.getString("u_Email");
                if(Email.equals(em.getText())){
                    JOptionPane.showMessageDialog(null,"Email already used");
                    em.setText("");
                }
                Username = resultSet.getString("u_Username");
                 if(Username.equals(us.getText())){
                    JOptionPane.showMessageDialog(null,"Username already used");
                    us.setText("");
                }
                return true;

            }else{
                return false;
            }
            
            }catch(SQLException ex){
                System.out.println(""+ex);
            
        }
        return true;
        }  
    
    public boolean updateCheck(){
        
        dcConnector dcc = new dcConnector();
        try{
            String sql = "SELECT * FROM ttb WHERE (u_Username = '"+us.getText()+"' OR u_Email = '"+em.getText()+"') AND u_id != '"+uid.getText()+"'";
            ResultSet rs = dcc.getData(sql);
            
            if(rs.next()){
                Email = rs.getString("u_Email");
                if(Email.equals(em.getText())){
                    JOptionPane.showMessageDialog(null, "Email is Already Used.");
                    em.setText("");
                }
                Username = rs.getString("u_Username");
                if(Username.equals(us.getText())){
                    JOptionPane.showMessageDialog(null, "Username is Already Used.");
                    us.setText("");
                }
                return true;
            }else{
                return false;
            }
        }catch(SQLException ex){
            System.out.println(""+ex);
            return true;
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

        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        us = new javax.swing.JTextField();
        pw = new javax.swing.JPasswordField();
        jLabel5 = new javax.swing.JLabel();
        em = new javax.swing.JTextField();
        ln = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        ut = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        ut1 = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        uid = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        add1 = new javax.swing.JButton();
        Update = new javax.swing.JButton();
        cancel = new javax.swing.JButton();
        refresh = new javax.swing.JButton();
        delet = new javax.swing.JButton();
        clear = new javax.swing.JButton();
        fn = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        sp = new javax.swing.JCheckBox();
        jLabel9 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        us1 = new javax.swing.JTextField();
        pw1 = new javax.swing.JPasswordField();
        jLabel13 = new javax.swing.JLabel();
        em1 = new javax.swing.JTextField();
        ln1 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        ut2 = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        ut3 = new javax.swing.JComboBox<>();
        jLabel16 = new javax.swing.JLabel();
        uid1 = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        add2 = new javax.swing.JButton();
        Update1 = new javax.swing.JButton();
        cancel1 = new javax.swing.JButton();
        refresh1 = new javax.swing.JButton();
        delet1 = new javax.swing.JButton();
        clear1 = new javax.swing.JButton();
        fn1 = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        sp1 = new javax.swing.JCheckBox();
        select = new javax.swing.JButton();
        remove = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        image = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(5, 22, 26));
        jPanel2.setForeground(new java.awt.Color(5, 22, 26));
        jPanel2.setLayout(null);

        jPanel3.setBackground(new java.awt.Color(7, 46, 51, 80));
        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel2.setBackground(new java.awt.Color(41, 41, 41));
        jLabel2.setFont(new java.awt.Font("Sitka Small", 1, 10)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(109, 165, 192));
        jLabel2.setText("Password");

        jLabel3.setBackground(new java.awt.Color(41, 41, 41));
        jLabel3.setFont(new java.awt.Font("Sitka Small", 1, 10)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(109, 165, 192));
        jLabel3.setText("Username");

        jLabel5.setBackground(new java.awt.Color(41, 41, 41));
        jLabel5.setFont(new java.awt.Font("Sitka Small", 1, 10)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(109, 165, 192));
        jLabel5.setText("Email");

        jLabel7.setBackground(new java.awt.Color(41, 41, 41));
        jLabel7.setFont(new java.awt.Font("Sitka Small", 1, 10)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(109, 165, 192));
        jLabel7.setText("Last Name");

        ut.setFont(new java.awt.Font("Sitka Banner", 1, 12)); // NOI18N
        ut.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "User", "Admin" }));

        jLabel8.setBackground(new java.awt.Color(41, 41, 41));
        jLabel8.setFont(new java.awt.Font("Sitka Small", 1, 10)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(109, 165, 192));
        jLabel8.setText("User Type");

        ut1.setFont(new java.awt.Font("Sitka Banner", 1, 12)); // NOI18N
        ut1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Active", "Pending" }));

        jLabel10.setBackground(new java.awt.Color(41, 41, 41));
        jLabel10.setFont(new java.awt.Font("Sitka Small", 1, 10)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(109, 165, 192));
        jLabel10.setText("User Status");

        uid.setEnabled(false);
        uid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uidActionPerformed(evt);
            }
        });

        jLabel6.setBackground(new java.awt.Color(41, 41, 41));
        jLabel6.setFont(new java.awt.Font("Sitka Small", 1, 10)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(109, 165, 192));
        jLabel6.setText("Id");

        add1.setBackground(new java.awt.Color(7, 46, 51,80));
        add1.setFont(new java.awt.Font("Sitka Small", 3, 11)); // NOI18N
        add1.setForeground(new java.awt.Color(109, 165, 192));
        add1.setText("Add");
        add1.setBorderPainted(false);
        add1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add1ActionPerformed(evt);
            }
        });

        Update.setBackground(new java.awt.Color(7, 46, 51,80));
        Update.setFont(new java.awt.Font("Sitka Small", 3, 11)); // NOI18N
        Update.setForeground(new java.awt.Color(109, 165, 192));
        Update.setText("Update");
        Update.setBorderPainted(false);
        Update.setEnabled(false);
        Update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateActionPerformed(evt);
            }
        });

        cancel.setBackground(new java.awt.Color(7, 46, 51,80));
        cancel.setFont(new java.awt.Font("Sitka Small", 3, 11)); // NOI18N
        cancel.setForeground(new java.awt.Color(109, 165, 192));
        cancel.setText("Cancel");
        cancel.setBorderPainted(false);
        cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelActionPerformed(evt);
            }
        });

        refresh.setBackground(new java.awt.Color(7, 46, 51,80));
        refresh.setFont(new java.awt.Font("Sitka Small", 3, 11)); // NOI18N
        refresh.setForeground(new java.awt.Color(109, 165, 192));
        refresh.setText("Refresh");
        refresh.setBorderPainted(false);
        refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshActionPerformed(evt);
            }
        });

        delet.setBackground(new java.awt.Color(7, 46, 51,80));
        delet.setFont(new java.awt.Font("Sitka Small", 3, 11)); // NOI18N
        delet.setForeground(new java.awt.Color(109, 165, 192));
        delet.setText("Delet");
        delet.setBorderPainted(false);
        delet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deletActionPerformed(evt);
            }
        });

        clear.setBackground(new java.awt.Color(7, 46, 51,80));
        clear.setFont(new java.awt.Font("Sitka Small", 3, 11)); // NOI18N
        clear.setForeground(new java.awt.Color(109, 165, 192));
        clear.setText("Clear");
        clear.setBorderPainted(false);
        clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearActionPerformed(evt);
            }
        });

        jLabel11.setBackground(new java.awt.Color(41, 41, 41));
        jLabel11.setFont(new java.awt.Font("Sitka Small", 1, 10)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(109, 165, 192));
        jLabel11.setText("Name");

        sp.setBackground(new java.awt.Color(7, 46, 51,80));
        sp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                spActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(ut, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(ut1, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(sp, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(pw, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(fn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(refresh, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                    .addComponent(add1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(Update, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ln, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(em, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(us, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(uid, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                .addComponent(delet, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(clear, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(add1)
                    .addComponent(Update))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(delet)
                    .addComponent(clear))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(refresh)
                    .addComponent(cancel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(uid, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addGap(1, 1, 1)
                .addComponent(em, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addComponent(jLabel11)
                .addGap(7, 7, 7)
                .addComponent(fn, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addGap(7, 7, 7)
                .addComponent(ln, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(us, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pw, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(sp, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(5, 5, 5)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ut1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel2.add(jPanel3);
        jPanel3.setBounds(60, 10, 230, 470);

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Borcelle CAR wash (1) (1).png"))); // NOI18N
        jPanel2.add(jLabel9);
        jLabel9.setBounds(50, 80, 250, 350);

        jPanel4.setBackground(new java.awt.Color(7, 46, 51, 80));
        jPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel4.setBackground(new java.awt.Color(41, 41, 41));
        jLabel4.setFont(new java.awt.Font("Sitka Small", 1, 10)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(109, 165, 192));
        jLabel4.setText("Password");

        jLabel12.setBackground(new java.awt.Color(41, 41, 41));
        jLabel12.setFont(new java.awt.Font("Sitka Small", 1, 10)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(109, 165, 192));
        jLabel12.setText("Username");

        jLabel13.setBackground(new java.awt.Color(41, 41, 41));
        jLabel13.setFont(new java.awt.Font("Sitka Small", 1, 10)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(109, 165, 192));
        jLabel13.setText("Email");

        jLabel14.setBackground(new java.awt.Color(41, 41, 41));
        jLabel14.setFont(new java.awt.Font("Sitka Small", 1, 10)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(109, 165, 192));
        jLabel14.setText("Last Name");

        ut2.setFont(new java.awt.Font("Sitka Banner", 1, 12)); // NOI18N
        ut2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "User", "Admin" }));

        jLabel15.setBackground(new java.awt.Color(41, 41, 41));
        jLabel15.setFont(new java.awt.Font("Sitka Small", 1, 10)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(109, 165, 192));
        jLabel15.setText("User Type");

        ut3.setFont(new java.awt.Font("Sitka Banner", 1, 12)); // NOI18N
        ut3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Active", "Pending" }));

        jLabel16.setBackground(new java.awt.Color(41, 41, 41));
        jLabel16.setFont(new java.awt.Font("Sitka Small", 1, 10)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(109, 165, 192));
        jLabel16.setText("User Status");

        uid1.setEnabled(false);
        uid1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uid1ActionPerformed(evt);
            }
        });

        jLabel17.setBackground(new java.awt.Color(41, 41, 41));
        jLabel17.setFont(new java.awt.Font("Sitka Small", 1, 10)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(109, 165, 192));
        jLabel17.setText("Id");

        add2.setBackground(new java.awt.Color(7, 46, 51,80));
        add2.setFont(new java.awt.Font("Sitka Small", 3, 11)); // NOI18N
        add2.setForeground(new java.awt.Color(109, 165, 192));
        add2.setText("Add");
        add2.setBorderPainted(false);
        add2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add2ActionPerformed(evt);
            }
        });

        Update1.setBackground(new java.awt.Color(7, 46, 51,80));
        Update1.setFont(new java.awt.Font("Sitka Small", 3, 11)); // NOI18N
        Update1.setForeground(new java.awt.Color(109, 165, 192));
        Update1.setText("Update");
        Update1.setBorderPainted(false);
        Update1.setEnabled(false);
        Update1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Update1ActionPerformed(evt);
            }
        });

        cancel1.setBackground(new java.awt.Color(7, 46, 51,80));
        cancel1.setFont(new java.awt.Font("Sitka Small", 3, 11)); // NOI18N
        cancel1.setForeground(new java.awt.Color(109, 165, 192));
        cancel1.setText("Cancel");
        cancel1.setBorderPainted(false);
        cancel1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancel1ActionPerformed(evt);
            }
        });

        refresh1.setBackground(new java.awt.Color(7, 46, 51,80));
        refresh1.setFont(new java.awt.Font("Sitka Small", 3, 11)); // NOI18N
        refresh1.setForeground(new java.awt.Color(109, 165, 192));
        refresh1.setText("Refresh");
        refresh1.setBorderPainted(false);
        refresh1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refresh1ActionPerformed(evt);
            }
        });

        delet1.setBackground(new java.awt.Color(7, 46, 51,80));
        delet1.setFont(new java.awt.Font("Sitka Small", 3, 11)); // NOI18N
        delet1.setForeground(new java.awt.Color(109, 165, 192));
        delet1.setText("Delet");
        delet1.setBorderPainted(false);
        delet1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delet1ActionPerformed(evt);
            }
        });

        clear1.setBackground(new java.awt.Color(7, 46, 51,80));
        clear1.setFont(new java.awt.Font("Sitka Small", 3, 11)); // NOI18N
        clear1.setForeground(new java.awt.Color(109, 165, 192));
        clear1.setText("Clear");
        clear1.setBorderPainted(false);
        clear1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clear1ActionPerformed(evt);
            }
        });

        jLabel18.setBackground(new java.awt.Color(41, 41, 41));
        jLabel18.setFont(new java.awt.Font("Sitka Small", 1, 10)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(109, 165, 192));
        jLabel18.setText("Name");

        sp1.setBackground(new java.awt.Color(7, 46, 51,80));
        sp1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sp1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(ut2, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(ut3, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(sp1, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(pw1, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(fn1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addComponent(cancel1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(refresh1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                    .addComponent(add2, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(Update1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ln1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(em1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(us1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(uid1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                .addComponent(delet1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(clear1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(add2)
                    .addComponent(Update1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(delet1)
                    .addComponent(clear1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(refresh1)
                    .addComponent(cancel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(uid1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13)
                .addGap(1, 1, 1)
                .addComponent(em1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addComponent(jLabel18)
                .addGap(7, 7, 7)
                .addComponent(fn1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14)
                .addGap(7, 7, 7)
                .addComponent(ln1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(us1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pw1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(sp1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(5, 5, 5)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ut2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ut3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel2.add(jPanel4);
        jPanel4.setBounds(60, 10, 230, 470);

        select.setBackground(new java.awt.Color(7, 46, 51,80));
        select.setFont(new java.awt.Font("Sitka Small", 3, 11)); // NOI18N
        select.setForeground(new java.awt.Color(109, 165, 192));
        select.setText("Select");
        select.setBorderPainted(false);
        select.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectActionPerformed(evt);
            }
        });
        jPanel2.add(select);
        select.setBounds(380, 380, 67, 23);

        remove.setBackground(new java.awt.Color(7, 46, 51,80));
        remove.setFont(new java.awt.Font("Sitka Small", 3, 11)); // NOI18N
        remove.setForeground(new java.awt.Color(109, 165, 192));
        remove.setText("Remove");
        remove.setBorderPainted(false);
        remove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeActionPerformed(evt);
            }
        });
        jPanel2.add(remove);
        remove.setBounds(520, 380, 77, 23);

        jPanel5.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel5.setLayout(null);
        jPanel5.add(image);
        image.setBounds(0, -1, 270, 330);

        jPanel2.add(jPanel5);
        jPanel5.setBounds(350, 30, 270, 330);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 653, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 507, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void add1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add1ActionPerformed
         if(ln.getText().isEmpty() || em.getText().isEmpty() || us.getText().isEmpty() || pw.getText().isEmpty()||fn.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Please fill in all required Data.");
        }else if (pw.getText().length() < 8) {
            JOptionPane.showMessageDialog(null, "Password should have at least 8 characters or Above.");
            pw.setText("");
        }else if(duplicateCheck()){    
            System.out.println("Duplicated Exist");
        }else{
            
          try{  
            dcConnector dbc = new dcConnector();

              
            String pass = passwordHash.hashPassword(pw.getText());
            
            if(dbc.insertData("INSERT INTO ttb (u_Fname, u_Lname, u_Email, u_Username, u_Password, u_type, u_status, u_image)"
                    +" VALUES ('"+uid.getText()+"', '"+ln.getText()+"', '"+em.getText()+"', '"+us.getText()+"', '"
                    +pass+"','"+fn.getText()+"', '"+ut.getSelectedItem()+"', '"+ut1.getSelectedItem()+ "','"+destination+"')")){
              try{    
                Files.copy(selectedFile.toPath(),new File(destination).toPath(),StandardCopyOption.REPLACE_EXISTING);
                JOptionPane.showMessageDialog(null, "Inserted Successfully!");
                LoginForm lf = new LoginForm();
                lf.setVisible(true);
                this.dispose();
            }catch(IOException ex){
                   System.out.println("Insert Image Error: "+ex); 
                    }   
            }else{
                JOptionPane.showMessageDialog(null, "Connection Error!");
            }
        }catch(NoSuchAlgorithmException ex){
                System.out.println(""+ex);
                
        }
        }
    }//GEN-LAST:event_add1ActionPerformed

    private void UpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdateActionPerformed
    
           if(em.getText().isEmpty() || fn.getText().isEmpty() || ln.getText().isEmpty() 
            || us.getText().isEmpty() || pw.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Please fill in all required Data.");
        }else if (pw.getText().length() < 8) {
            JOptionPane.showMessageDialog(null, "Password should have at least 8 characters or Above.");
            pw.setText("");
        }else if(updateCheck()){    
            System.out.println("Duplicated Exist");
        }else{
            dcConnector dbc =new dcConnector();
        try{    
            String pass = passwordHash.hashPassword(pw.getText());
            
            dbc.updateData("UPDATE ttb SET "
            + "u_Fname = '"+fn.getText()+"', u_Lname = '"+ln.getText()+"', "
                + "u_Email = '"+em.getText()+"', u_Username ='"+us.getText()+ "', "
                    + "u_Password = '"+pass+"', "
                        + "u_type = '"+ut.getSelectedItem()+"', "
                            + "u_status = '"+ut1.getSelectedItem()+"' "
                                + ",u_image = '"+destination+ "' WHERE u_id ='"+Integer.valueOf(uid.getText())+"'");
                        
                     if(destination.isEmpty()){
                         File existingFile = new File(oldpath);
                         if(existingFile.exists()){
                             existingFile.delete();
                         }
                     }else{
                         if(!(oldpath.equals(path))){
                               imageUpdater(oldpath, path);
                         }
                     }     
                         
                         UserForm uf = new UserForm();
                         uf.setVisible(true);
                         this.dispose();
        
        } catch(NoSuchAlgorithmException ex){
                System.out.println(""+ex);
                
        }
        }
    }//GEN-LAST:event_UpdateActionPerformed

    private void cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelActionPerformed
            UserForm uf = new UserForm();
            uf.setVisible(true);
            this.dispose();

    }//GEN-LAST:event_cancelActionPerformed

    private void refreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_refreshActionPerformed

    private void deletActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deletActionPerformed
   
    }//GEN-LAST:event_deletActionPerformed

    private void clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearActionPerformed
      
    }//GEN-LAST:event_clearActionPerformed

    private void uidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_uidActionPerformed

    private void spActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_spActionPerformed
        if(sp.isSelected()){
            pw.setEchoChar((char)0);
        }else{
            pw.setEchoChar('*');
        }
    }//GEN-LAST:event_spActionPerformed

    private void uid1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uid1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_uid1ActionPerformed

    private void add2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_add2ActionPerformed

    private void Update1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Update1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Update1ActionPerformed

    private void cancel1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancel1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cancel1ActionPerformed

    private void refresh1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refresh1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_refresh1ActionPerformed

    private void delet1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delet1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_delet1ActionPerformed

    private void clear1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clear1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_clear1ActionPerformed

    private void sp1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sp1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sp1ActionPerformed

    private void selectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectActionPerformed
        JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    try {
                        selectedFile = fileChooser.getSelectedFile();
                        destination = "src/userimages/" + selectedFile.getName();
                        path  = selectedFile.getAbsolutePath();
                        
                        
                        if(FileExistenceChecker(path) == 1){
                          JOptionPane.showMessageDialog(null, "File Already Exist, Rename or Choose another!");
                            destination = "";
                            path="";
                        }else{
                            image.setIcon(ResizeImage(path, null, image));
                            select.setEnabled(false);
                            remove.setEnabled(true);
                        }
                    } catch (Exception ex) {
                        System.out.println("File Error!");
                    }
                }
        
    }//GEN-LAST:event_selectActionPerformed

    private void removeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeActionPerformed
        remove.setEnabled(false);
        select.setEnabled(true);
        image.setIcon(null);
        destination = "";
        path = "";
        
        
    }//GEN-LAST:event_removeActionPerformed

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
            java.util.logging.Logger.getLogger(MakeUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MakeUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MakeUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MakeUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MakeUser().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton Update;
    public javax.swing.JButton Update1;
    public javax.swing.JButton add1;
    public javax.swing.JButton add2;
    private javax.swing.JButton cancel;
    private javax.swing.JButton cancel1;
    public javax.swing.JButton clear;
    public javax.swing.JButton clear1;
    private javax.swing.JButton delet;
    private javax.swing.JButton delet1;
    public javax.swing.JTextField em;
    public javax.swing.JTextField em1;
    public javax.swing.JTextField fn;
    public javax.swing.JTextField fn1;
    public javax.swing.JLabel image;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    public javax.swing.JTextField ln;
    public javax.swing.JTextField ln1;
    public javax.swing.JPasswordField pw;
    public javax.swing.JPasswordField pw1;
    private javax.swing.JButton refresh;
    private javax.swing.JButton refresh1;
    public javax.swing.JButton remove;
    public javax.swing.JButton select;
    private javax.swing.JCheckBox sp;
    private javax.swing.JCheckBox sp1;
    public javax.swing.JTextField uid;
    public javax.swing.JTextField uid1;
    public javax.swing.JTextField us;
    public javax.swing.JTextField us1;
    public javax.swing.JComboBox<String> ut;
    public javax.swing.JComboBox<String> ut1;
    public javax.swing.JComboBox<String> ut2;
    public javax.swing.JComboBox<String> ut3;
    // End of variables declaration//GEN-END:variables
}
