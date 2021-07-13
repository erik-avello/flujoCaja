package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import model.Conexion;
import model.registroDatosFlujo;
import java.util.ArrayList;

/**
 *
 * @author JOAQUIN
 */
public class DAO_RegistroTabla extends Conexion {

    public DAO_RegistroTabla() throws ClassNotFoundException, SQLException {
        super("bd_practica");
    }

    public void insertRegistros(String idFlujo, String idTipo, String accion, int idMes, String dato) throws SQLException {
        ejecutar("INSERT INTO registroDatosFlujo VALUES(NULL, '" + idFlujo + "','" + idTipo + "','" + accion + "','" + idMes + "','" + dato + "');");
        close();
    }

    /*
    public void editarRegistro(String nombreNuevo, String datoNuevo, String nombreAntiguo, String datoAntiguo, String idMes) throws SQLException{
        ejecutar("UPDATE registroDatosFlujo SET accion = '"+nombreNuevo+"', dato = '"+datoNuevo+"' where accion = '"+nombreAntiguo+"' and dato = '"+datoAntiguo+"' and idMes= '"+idMes+"';");
        close();
    }*/
    public void editarRegistroSinMes(String nombreNuevo, String datoNuevo, String nombreAntiguo, String datoAntiguo) throws SQLException {
        ejecutar("UPDATE registroDatosFlujo SET accion = '" + nombreNuevo + "', dato = '" + datoNuevo + "' where accion = '" + nombreAntiguo + "' and dato = '" + datoAntiguo + "';");
        close();
    }

    public void editarDato(String datoNuevo, String datoAntiguo, String idFlujo, String idMes) throws SQLException {
        ejecutar("UPDATE registroDatosFlujo SET dato = '" + datoNuevo + "' Where dato = '" + datoAntiguo + "' AND idFlujo = '" + idFlujo + "' AND idMes='" + idMes + "';");
        close();
    }

    public List<registroDatosFlujo> getRegistrosIngresoPorId(int idFlujo) throws SQLException {
        ResultSet rs = ejecutar("SELECT * FROM registroDatosFlujo WHERE idFlujo = '" + idFlujo + "';");
        registroDatosFlujo registro = null;
        List<registroDatosFlujo> lista = new ArrayList<>();

        while (rs.next()) {
            registro = new registroDatosFlujo();
            registro.setId(rs.getInt(1));
            registro.setIdFlujo(rs.getInt(2));
            registro.setIdTipo(rs.getInt(3));
            registro.setAccion(rs.getString(4));
            registro.setIdMes(rs.getInt(5));
            registro.setDato(rs.getString(6));
            lista.add(registro);
        }
        return lista;
    }

    public List<registroDatosFlujo> getRegistrosIngresoPorIdIngresos(int idFlujo) throws SQLException {
        ResultSet rs = ejecutar("SELECT * FROM registroDatosFlujo WHERE idFlujo = '" + idFlujo + "' AND idtipo=1;");
        registroDatosFlujo registro = null;
        List<registroDatosFlujo> lista = new ArrayList<>();

        while (rs.next()) {
            registro = new registroDatosFlujo();
            registro.setId(rs.getInt(1));
            registro.setIdFlujo(rs.getInt(2));
            registro.setIdTipo(rs.getInt(3));
            registro.setAccion(rs.getString(4));
            registro.setIdMes(rs.getInt(5));
            registro.setDato(rs.getString(6));
            lista.add(registro);
        }
        return lista;
    }

    public List<registroDatosFlujo> getRegistrosIngresoPorIdEgresos(int idFlujo) throws SQLException {
        ResultSet rs = ejecutar("SELECT * FROM registroDatosFlujo WHERE idFlujo = '" + idFlujo + "' AND idtipo=2;");
        registroDatosFlujo registro = null;
        List<registroDatosFlujo> lista = new ArrayList<>();

        while (rs.next()) {
            registro = new registroDatosFlujo();
            registro.setId(rs.getInt(1));
            registro.setIdFlujo(rs.getInt(2));
            registro.setIdTipo(rs.getInt(3));
            registro.setAccion(rs.getString(4));
            registro.setIdMes(rs.getInt(5));
            registro.setDato(rs.getString(6));
            lista.add(registro);
        }
        return lista;
    }

    public List<String> getAccion(int idFlujo, int idTipo) throws SQLException {
        ResultSet rs = ejecutar("SELECT accion from registroDatosFlujo where idFlujo = '" + idFlujo + "' AND idtipo = '" + idTipo + "' group by accion;");
        List<String> lista = new ArrayList<>();

        while (rs.next()) {
            String valor = rs.getString(1);
            lista.add(valor);
        }
        return lista;
    }

    public List<String> getDato(String accion) throws SQLException {
        ResultSet rs = ejecutar("SELECT dato from registroDatosFlujo where idFlujo = 1 AND accion = '" + accion + "';");
        List<String> lista = new ArrayList<>();

        while (rs.next()) {
            String valor = rs.getString(1);
            lista.add(valor);
        }
        return lista;
    }

    public List<String> getMes(int idFlujo) throws SQLException {
        ResultSet rs = ejecutar("SELECT idMes from registroDatosFlujo where idFlujo = '" + idFlujo + "' group by idMes;");
        List<String> lista = new ArrayList<>();

        while (rs.next()) {
            String valor = rs.getString(1);
            lista.add(valor);
        }
        return lista;
    }

}
