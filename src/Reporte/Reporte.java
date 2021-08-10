package Reporte;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import sample.ConexionBD;

public class Reporte {


    public void crearPDF(String numF) throws JRException {
        JasperReport archivo = JasperCompileManager.compileReport("C:\\Users\\ramig\\IdeaProjects\\SistemaContable\\src\\Reporte\\reportefacturacion.jrxml");
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("numF", numF);
        Connection conn = ConexionBD.getConnection();
        JasperPrint prin = JasperFillManager.fillReport(archivo, map,conn);
        JasperViewer ver = new JasperViewer(prin, false);
        ver.setVisible(true);
        ver.setTitle("Reporte de la factura " + numF);
    }

}
