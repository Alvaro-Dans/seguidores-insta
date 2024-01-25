package programs;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UnfollowInstagram {

	public static void main(String[] args) {
		// Coger perfiles de seguidores y seguidos en lista y comprobar si a los que
		// sigo me siguen
		// Forma de hacer -> split por "nombre de perfil"

		String seguidores = "";
		String seguidos = "";
		try {
			seguidores = new String(
					Files.readAllBytes(Paths.get("src/main/resources/instagram/seguidoresContent.txt")));
			seguidos = new String(Files.readAllBytes(Paths.get("src/main/resources/instagram/seguidosContent.txt")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<String> listaSeguidores = new ArrayList<>();
		listaSeguidores.addAll(Arrays.asList(seguidores.split("perfil de ")));
		listaSeguidores.remove(0);
		List<String> listaSeguidos = new ArrayList<>();
		listaSeguidos.addAll(Arrays.asList(seguidos.split("perfil de ")));
		listaSeguidos.remove(0);

		int contadorSeguidos = 1;
		int contadorSeguidores = 1;
		for (int i = 0; i < listaSeguidos.size(); i++) {
			listaSeguidos.add(i, listaSeguidos.get(i).split("\"")[0]);
			listaSeguidos.remove(i + 1);
			contadorSeguidos++;
		}

		for (int i = 0; i < listaSeguidores.size(); i++) {
			listaSeguidores.add(i, listaSeguidores.get(i).split("\"")[0]);
			listaSeguidores.remove(i + 1);
			contadorSeguidores++;
		}
		System.out.println("Seguidos: " + contadorSeguidos);
		System.out.println("Seguidores: " + contadorSeguidores);

		List<String> listaUsuariosNoSiguiendo = new ArrayList<>();

		System.out.println("Usuarios que no me siguen:\n");
		for (String perfilSeguido : listaSeguidos) {
			if (!listaSeguidores.contains(perfilSeguido)) {
				listaUsuariosNoSiguiendo.add(perfilSeguido);
				System.out.println("- " + perfilSeguido);
			}
		}

	}

}
