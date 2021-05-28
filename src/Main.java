

public class Main {

	public static void main(String[] args) {
		
		Ciudad pehuajo = new Ciudad(1, "Pehuajo");
		pehuajo.setEstacionesDeServicio(3);
		pehuajo.setTieneBalanza(true);
		pehuajo.setTieneRadar(true);
		pehuajo.setTallerMecanicos(5);
		
		Ciudad ayacucho = new Ciudad(2, "Ayacucho");
		ayacucho.setEstacionesDeServicio(1);
		ayacucho.setTieneBalanza(false);
		ayacucho.setTieneRadar(false);
		ayacucho.setTallerMecanicos(2);
		
		Ciudad olavarria = new Ciudad(3, "Olavarria");
		olavarria.setEstacionesDeServicio(9);
		olavarria.setTieneBalanza(true);
		olavarria.setTieneRadar(false);
		olavarria.setTallerMecanicos(17);
		
		Ciudad rauch = new Ciudad(4, "Rauch");
		rauch.setEstacionesDeServicio(1);
		rauch.setTieneBalanza(false);
		rauch.setTieneRadar(true);
		rauch.setTallerMecanicos(0);
		
		Ciudad bolivar = new Ciudad(5, "Bolivar");
		bolivar.setEstacionesDeServicio(7);
		bolivar.setTieneBalanza(false);
		bolivar.setTieneRadar(false);
		bolivar.setTallerMecanicos(4);
		
		Ciudad tandil = new Ciudad(6, "Tandil");
		tandil.setEstacionesDeServicio(6);
		tandil.setTieneBalanza(true);
		tandil.setTieneRadar(true);
		tandil.setTallerMecanicos(5);
		
		Ciudad azul = new Ciudad(7, "Azul");
		azul.setEstacionesDeServicio(4);
		azul.setTieneBalanza(false);
		azul.setTieneRadar(true);
		azul.setTallerMecanicos(4);
		
		Ciudad marDelPlata = new Ciudad(8, "Mar Del Plata");
		marDelPlata.setEstacionesDeServicio(15);
		marDelPlata.setTieneBalanza(true);
		marDelPlata.setTieneRadar(false);
		marDelPlata.setTallerMecanicos(12);
		
		Mapa mapa = new Mapa();
		
		mapa.addCiudad(pehuajo);
		mapa.addCiudad(ayacucho);
		mapa.addCiudad(olavarria);
		mapa.addCiudad(rauch);
		mapa.addCiudad(bolivar);
		mapa.addCiudad(tandil);
		mapa.addCiudad(azul);
		mapa.addCiudad(marDelPlata);
		
		mapa.addRuta(pehuajo, bolivar, 70);
		mapa.addRuta(pehuajo, ayacucho, 540);
		mapa.addRuta(ayacucho, tandil, 70);
		mapa.addRuta(ayacucho, rauch, 50);
		mapa.addRuta(tandil, rauch, 60);
		mapa.addRuta(tandil, olavarria, 130);
		mapa.addRuta(tandil, marDelPlata, 200);
		mapa.addRuta(olavarria, rauch, 210);
		mapa.addRuta(olavarria, bolivar, 140);
		mapa.addRuta(bolivar, azul, 100);

		System.out.println(mapa.encontrarCamino(azul, ayacucho));
		System.out.println(mapa.encontrarCamino(rauch, marDelPlata));
		System.out.println(mapa.encontrarCamino(marDelPlata, pehuajo));
		
		mapa.borrarCiudad(ayacucho);
		
		mapa.borrarRuta(tandil, rauch);
		System.out.println(mapa.encontrarCamino(marDelPlata, pehuajo));
	}

}
