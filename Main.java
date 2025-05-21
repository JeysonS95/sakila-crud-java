import manager.FilmManagerDB;
import entity.Film;
import entity.Language;

import java.util.Scanner;
import java.util.ArrayList;

@SuppressWarnings("resource")
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        FilmManagerDB filmManager = new FilmManagerDB();

        int opcion;

        do {
            System.out.println("\n🎬 MENÚ - GESTIÓN DE PELÍCULAS (FILM)");
            System.out.println("1. Ver todas las películas");
            System.out.println("2. Buscar película por ID");
            System.out.println("3. Insertar nueva película");
            System.out.println("4. Actualizar película");
            System.out.println("5. Eliminar película");
            System.out.println("0. Salir");
            System.out.print("👉 Elige una opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1:
                    ArrayList<Film> lista = filmManager.getAll();
                    for (Film f : lista) {
                        System.out.println(f);
                    }
                    break;
                case 2:
                    System.out.print("🔍 Ingresa el ID de la película: ");
                    int idBuscar = sc.nextInt();
                    Film f = filmManager.get(idBuscar);
                    if (f != null) {
                        System.out.println("🎯 Película encontrada: " + f);
                    } else {
                        System.out.println("⚠️ No se encontró la película.");
                    }
                    break;
                case 3:
                    System.out.print("🎬 Título: ");
                    String title = sc.nextLine();
                    System.out.print("📝 Descripción: ");
                    String desc = sc.nextLine();
                    System.out.print("🌐 ID del lenguaje: ");
                    int langId = sc.nextInt();
                    Language lang = new Language(langId, ""); // nombre vacío, no se necesita
                    filmManager.post(new Film(0, title, desc, lang));
                    System.out.println("✅ Película insertada.");
                    break;
                case 4:
                    System.out.print("✏️ ID de la película a actualizar: ");
                    int idActualizar = sc.nextInt();
                    sc.nextLine();
                    System.out.print("🆕 Nuevo título: ");
                    String nuevoTitulo = sc.nextLine();
                    System.out.print("🆕 Nueva descripción: ");
                    String nuevaDesc = sc.nextLine();
                    System.out.print("🆕 Nuevo ID de lenguaje: ");
                    int nuevoLang = sc.nextInt();
                    filmManager.put(idActualizar, nuevoTitulo, nuevaDesc, nuevoLang);
                    System.out.println("✅ Película actualizada.");
                    break;
                case 5:
                    System.out.print("🗑️ ID de la película a eliminar: ");
                    int idEliminar = sc.nextInt();
                    filmManager.delete(idEliminar);
                    System.out.println("✅ Película eliminada.");
                    break;
                case 0:
                    System.out.println("👋 ¡Hasta luego!");
                    break;
                default:
                    System.out.println("⚠️ Opción inválida.");
            }

        } while (opcion != 0);
    }
}
