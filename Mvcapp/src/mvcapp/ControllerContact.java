package mvcapp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import javax.swing.JTable;

public class ControllerContact {
    ModelContact modelContact;
    ViewContact viewContact;

    public ControllerContact(ModelContact modelContact, ViewContact viewContact) {
        this.modelContact = modelContact;
        this.viewContact = viewContact;

        if (modelContact.readContact() != null) {
            String[][] dataContact = modelContact.readContact();
            viewContact.tabel.setModel(new javax.swing.table.DefaultTableModel(dataContact, viewContact.namaKolom));
        } else {
            JOptionPane.showMessageDialog(null, "Data kosong");
        }

        viewContact.btnTambah.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nama = viewContact.getNama();
                String noHp = viewContact.getNoHp();
                String umur = viewContact.getUmur();
                String email = viewContact.getEmail();

                if (nama.isEmpty() || noHp.isEmpty() || umur.isEmpty() || email.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill all fields");
                } else {
                    modelContact.insertData(nama, noHp, umur, email);
                    String[][] dataContact = modelContact.readContact();
                    viewContact.tabel.setModel(new javax.swing.table.DefaultTableModel(dataContact, viewContact.namaKolom));
                }
            }
        });

        viewContact.btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nama = viewContact.getNama();
                String noHp = viewContact.getNoHp();
                String umur = viewContact.getUmur();
                String email = viewContact.getEmail();

                if (nama.isEmpty() || noHp.isEmpty() || umur.isEmpty() || email.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill all fields");
                } else {
                    modelContact.updateData(nama, noHp, umur, email);
                    String[][] dataContact = modelContact.readContact();
                    viewContact.tabel.setModel(new javax.swing.table.DefaultTableModel(dataContact, viewContact.namaKolom));
                }
            }
        });

        viewContact.btnCari.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cariNama = viewContact.getCariNama();
                if (cariNama.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter a name to search");
                } else {
                    String[][] dataContact = modelContact.cariContact(cariNama);
                    if (dataContact != null) {
                        viewContact.tabel.setModel(new javax.swing.table.DefaultTableModel(dataContact, viewContact.namaKolom));
                    } else {
                        JOptionPane.showMessageDialog(null, "Data tidak ditemukan");
                    }
                }
            }
        });

        viewContact.btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[][] dataContact = modelContact.readContact();
                viewContact.tabel.setModel(new javax.swing.table.DefaultTableModel(dataContact, viewContact.namaKolom));
            }
        });

        viewContact.tabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = viewContact.tabel.getSelectedRow();
                String selectedNama = viewContact.tabel.getValueAt(row, 0).toString();
                String selectedNoHp = viewContact.tabel.getValueAt(row, 1).toString();
                String selectedUmur = viewContact.tabel.getValueAt(row, 2).toString();
                String selectedEmail = viewContact.tabel.getValueAt(row, 3).toString();

                viewContact.tfNama.setText(selectedNama);
                viewContact.tfNoHp.setText(selectedNoHp);
                viewContact.tfUmur.setText(selectedUmur);
                viewContact.tfEmail.setText(selectedEmail);
            }
        });
    }
}
