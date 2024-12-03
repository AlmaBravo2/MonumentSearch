package com.iei.almacenDatos.Wrapper.CastillaYLeon.Utils;

public class CleanCoord {

    public static String validateAndCleanCoordinate(String coordinate) {
        if (coordinate == null || coordinate.isEmpty()) {
            return null; // Si la coordenada es nula o vacía, no hay nada que limpiar.
        }

        // Expresión regular para validar una coordenada decimal.
        String regex = "^-?\\d+(\\.\\d+)?$"; // Coordenada válida: -5.42709, 40.12345, etc.
        if (coordinate.matches(regex)) {
            return coordinate; // Si es válida, la devolvemos sin cambios.
        }

        // Si contiene caracteres no válidos, intentamos limpiarla.
        String cleaned = coordinate.replaceAll("[^\\d.-]", ""); // Eliminamos caracteres no numéricos excepto '-' y '.'.

        // Validamos de nuevo después de limpiar.
        if (cleaned.matches(regex)) {
            return cleaned;
        }

        // Si sigue siendo inválida después de limpiarla, devolvemos null o generamos un error.
        System.err.println("Error: Coordenada inválida incluso después de limpiar: " + coordinate);
        return null;
    }

}
