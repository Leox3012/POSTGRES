import java.sql.Connection; 
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(
            String url:"jbc:postgresql://localhost:5432/banco", // URL de conexion
            String user:"postgres", //Usuario de la base de datos
            String password:"postgres" // Contraseña de la base de datos
        )) {
            System.out.println("Conexion exitosa");
            Statement statement = connection.createStatement();

            boolean result = statement.execute("INSERT INTO cuentas
            (numero_cuenta, tipo_cuenta, saldo_cuenta) VALUES ('123456789',
            'Ahorro', 1000) RETURNING id_cuenta);

            ResultSet resultSet = statement.executeQuery("SELECT * FROM cuentas");

While (resultSet.next()){
    //Acceder a los datos de cada fila
    int idCuenta = resultSet.getInt("id_cuenta");
    String numeroCuenta = resultSet.getString("numero_cuenta");
    String tipoCuenta = resultSet.getString("tipo_cuenta");
    double saldo = resultSet.getDouble("salo_cuenta");
    System.out.println("ID: " + idCuenta + ", Numero: " + numeroCuenta + ", Tipo: " + tipoCuenta + " + saldo);
}

        }  catch (SQLException e) {
            System.out.println("Error de conexion: " + e.getMessage());
        }

    }

}