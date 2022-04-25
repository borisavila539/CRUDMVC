
package controlador;

import com.sun.xml.internal.ws.api.streaming.XMLStreamReaderFactory;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.PersonaDAO;
import modelo.persona;
import vista.Vista;

public class controlador implements ActionListener{
    
    PersonaDAO dao = new PersonaDAO();
    persona p = new persona();
    Vista vista = new Vista();
    DefaultTableModel modelo = new DefaultTableModel();
    
    public controlador(Vista v){
        this.vista = v;
        this.vista.btnListar.addActionListener(this);
        this.vista.btnGuardar.addActionListener(this);
        this.vista.btnEditar.addActionListener(this);
        this.vista.btnActualizar.addActionListener(this);
        this.vista.btnEliminar.addActionListener(this);
        listar(vista.tblTable);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==vista.btnListar){
            limpiar();
            listar(vista.tblTable);
        }
        if(e.getSource()==vista.btnGuardar){
            agregar();
            limpiar();
            listar(vista.tblTable);
        }
        if(e.getSource()==vista.btnEditar){
            int fila=vista.tblTable.getSelectedRow();
            if(fila==-1){
                JOptionPane.showMessageDialog(vista, "Debe seleccionar una fila...");
            }else{
                int id= Integer.parseInt((String)vista.tblTable.getValueAt(fila, 0).toString());
                String nombre=(String)vista.tblTable.getValueAt(fila, 1).toString();
                String correo=(String)vista.tblTable.getValueAt(fila, 2).toString();
                String telefono=(String)vista.tblTable.getValueAt(fila, 3).toString();
                vista.txtID.setText(id+"");
                vista.txtNombre.setText(nombre);
                vista.txtCorreo.setText(correo);
                vista.txtTelefono.setText(telefono);
            }
        }
        if(e.getSource()==vista.btnActualizar){
            Actualizar();
            limpiar();
            listar(vista.tblTable);
        }
        if(e.getSource()==vista.btnEliminar){
            int fila = vista.tblTable.getSelectedRow();
            
            if(fila==-1){
                JOptionPane.showMessageDialog(vista, "Debe seleccionar una fila...");
            }else{
                int id =Integer.parseInt((String)vista.tblTable.getValueAt(fila, 0).toString());
                dao.delete(id);
                JOptionPane.showMessageDialog(vista, "Usuario eliminado...");
                limpiar();
                listar(vista.tblTable); 
            }
        }
    }
    
    public void agregar(){
        String nombre= vista.txtNombre.getText();
        String correo = vista.txtCorreo.getText();
        String telefono = vista.txtTelefono.getText();
        p.setNombre(nombre);
        p.setCorreo(correo);
        p.setTelefono(telefono);
        dao.agregar(p);
    }
    
    public void listar(JTable tabla){
        modelo = (DefaultTableModel)tabla.getModel();
        List<persona>lista=dao.listar();
        Object[]object=new Object[4];
        for (int i = 0; i < lista.size(); i++) {
            object[0]=lista.get(i).getId();
            object[1]=lista.get(i).getNombre();
            object[2]=lista.get(i).getCorreo();
            object[3]=lista.get(i).getTelefono();
            modelo.addRow(object);
        }
        
        vista.tblTable.setModel(modelo);
    }
    public void Actualizar(){
        int id = Integer.parseInt(vista.txtID.getText());
        String nombre= vista.txtNombre.getText();
        String correo = vista.txtCorreo.getText();
        String telefono = vista.txtTelefono.getText();
        p.setId(id);
        p.setNombre(nombre);
        p.setCorreo(correo);
        p.setTelefono(telefono);
        int r = dao.Actualizar(p);
        if(r==1){
            JOptionPane.showMessageDialog(vista,"Usuario actualizado con exito");
        }else{
            JOptionPane.showMessageDialog(vista,"Error");
        }
    }
    
    public void limpiar(){
        for (int i = 0; i < vista.tblTable.getRowCount(); i++) {
            modelo.removeRow(i);
            i=i-1;
        }
    }
    
}
