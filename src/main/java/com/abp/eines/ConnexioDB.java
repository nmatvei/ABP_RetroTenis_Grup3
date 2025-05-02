package com.abp.eines;

import java.sql.*;

/**
 * Classe ConnexioDB que s'encarrega de fer la connexió amb la base de dades, passar
 * totes les dades per tal de fer el registre de la partida i de mostrar els 10 jugadors
 * amb la puntuació més alta.
 *
 * @author Pau, Nikita, Bea, Adria, Leyre
 */
public class ConnexioDB {

    /**
     * Mètode per obrir la connexió amb la base de dades.
     *
     * @return la connexió oberta
     */
    public static Connection obrirConnexio() {
        Connection connexio = null;
        try {
            String userName = "usuari12";
            String password = "1234";
            String url = "jdbc:mysql://172.19.100.175:3306/retro_tenis";
            connexio = DriverManager.getConnection(url, userName, password);
        } catch (SQLException e) {
            System.out.println(" Error a la connexió: " + e.getMessage());
        }
        return connexio;
    }

    /**
     * Mètode per inserir les dades d'una partida trucant al procediment
     * insertarRegistrePartida en la pròpia base de dades.
     *
     * @param nomJugador el nom del jugador que ha jugat la partida
     * @param puntuacio la puntuació obtinguda pel jugador
     * @param idioma l'idioma en què ha jugat el jugador
     */
    public void insertarRegistrePartida(String nomJugador, int puntuacio, String idioma) {
        try (Connection conn = obrirConnexio();
             CallableStatement cstmt = conn.prepareCall("{CALL insertarRegistrePartida(?, ?, ?)}")) {
            cstmt.setString(1, nomJugador);
            cstmt.setInt(2, puntuacio);
            cstmt.setString(3, idioma);
            cstmt.execute();
        } catch (SQLException e) {
            System.out.println(" Error al insertar la partida: " + e.getMessage());
        }
    }

    /**
     * Mètode per mostrar el Top 10 de jugadors amb la millor puntuació
     *
     * @return Un objecte de la classe Object que guarda el nom del jugador i la puntuació dels 10 millors
     */
    public Object[][] obtenirTop10Jugadors() {
        String sql = "{CALL mostrarTop10Jugadors()}";
        Object[][] dades = new Object[10][2]; // 10 files, 2 columnes
        int i = 0;

        try (Connection conn = obrirConnexio();
             CallableStatement cstmt = conn.prepareCall(sql);
             ResultSet rs = cstmt.executeQuery()) {

            // Anem guardant cadascun dels elements de la taula resultant de la consulta dins de taula
            while (rs.next() && i < 10) {
                dades[i][0] = rs.getString("nomJugador");
                dades[i][1] = rs.getInt("puntuacio");
                i++;
            }
        } catch (SQLException e) {
            System.out.println(" Error al obtenir el top 10: " + e.getMessage());
        }
        return dades;
    }

    /**
     * Mètode per etornar el text traduït segons l'idioma i l'identificador de la paraula.
     * Aquest mètode consulta la base de dades a la taula de textosJoc per obtenir
     * la traducció corresponent a l'idioma especificat.
     *
     * @param idioma   l'idioma en què es vol obtenir el text
     * @param paraula  l'identificador del text a traduir
     * @return el text traduït en l'idioma desitjat, o una cadena buida si no es troba.
     */
    public String obtenirTextTraduit(String idioma, String paraula){
        String sql = "SELECT " + idioma + " FROM textosJoc WHERE id = ?";
        String text = "";

        try (Connection conn = ConnexioDB.obrirConnexio();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, paraula);
            ResultSet rs = stmt.executeQuery();

            // Extreiem el text de la taula resultant de la consulta
            if (rs.next()) {
                text = rs.getString(1);
            }
            // Tanquem el ResultSet
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error al obtenir text traduït: " + e.getMessage());
        }
        return text;
    }
}