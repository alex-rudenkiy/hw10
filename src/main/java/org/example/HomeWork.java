package org.example;


import java.io.*;

public class HomeWork {
    // Функция Find
    int find(int[] parent, int x) {
        if (parent[x] != x) {
            parent[x] = find(parent, parent[x]);
        }
        return parent[x];
    }

    // Функция Union
    void union(int[] parent, int x, int y) {
        parent[find(parent, x)] = find(parent, y);
    }
    
    /**
     * <h1>Задание 1.</h1>
     * Решить задачу https://codeforces.com/contest/356/problem/A
     */
    public void championship(InputStream in, OutputStream out) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        PrintWriter writer = new PrintWriter(out);

        // Читаем входные данные
        String[] firstLine = reader.readLine().split(" ");
        int n = Integer.parseInt(firstLine[0]); // количество рыцарей
        int m = Integer.parseInt(firstLine[1]); // количество сражений

        // Массив для хранения победителя каждого рыцаря
        int[] defeated = new int[n + 1];
        // Массив для DSU
        int[] parent = new int[n + 2];

        // Инициализация DSU
        for (int i = 1; i <= n + 1; i++) {
            parent[i] = i;
        }

        // Обрабатываем сражения
        for (int i = 0; i < m; i++) {
            String[] battle = reader.readLine().split(" ");
            int l = Integer.parseInt(battle[0]);
            int r = Integer.parseInt(battle[1]);
            int x = Integer.parseInt(battle[2]);

            // Обрабатываем рыцарей от l до r, кроме x
            for (int knight = find(parent, l); knight <= r; knight = find(parent, knight + 1)) {
                if (knight != x) {
                    defeated[knight] = x; // Рыцарь knight побежден x
                    union(parent, knight, knight + 1); // Исключаем рыцаря knight
                }
            }
        }

        // Выводим результат
        for (int i = 1; i <= n; i++) {
            writer.print(defeated[i]);
            if(i <= n-1){
                writer.print(" ");
            }
        }
        writer.flush();
    }
}
