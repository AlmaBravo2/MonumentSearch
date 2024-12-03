package com.iei.almacenDatos.Wrapper.ComunitatValenciana.Utils;

public class LevenshteinComparator {

    // Método para calcular la distancia de Levenshtein
    public static int calculateLevenshteinDistance(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();

        // Crear una matriz para almacenar las distancias
        int[][] dp = new int[m + 1][n + 1];

        // Inicializar la matriz
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
        }

        // Llenar la matriz usando programación dinámica
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                int cost = (s1.charAt(i - 1) == s2.charAt(j - 1)) ? 0 : 1;
                dp[i][j] = Math.min(
                        Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1),
                        dp[i - 1][j - 1] + cost
                );
            }
        }

        return dp[m][n];
    }

}
