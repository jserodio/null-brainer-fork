package packGestores;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class GestorTwitter {
	private static GestorTwitter miGestorTwitter;
	
	private GestorTwitter() {}

	public static GestorTwitter getGestorTwitter(){
		if(miGestorTwitter==null){
			miGestorTwitter= new GestorTwitter();
		}
		return miGestorTwitter;
	}
	
	public void compartirTwitter(String pTexto) throws IOException, URISyntaxException{
		String texto = this.sanearURI(pTexto);
		Desktop d = Desktop.getDesktop();
		d.browse(new URI("https://twitter.com/intent/tweet?text="+texto));
	}
	
	private String sanearURI(String pT){
		String texto = "";
		for(int i = 0; i<pT.length(); i++){
			if(pT.charAt(i)==' ') texto += "+";
			else texto += pT.charAt(i);
		}
		return texto;
	}
}
