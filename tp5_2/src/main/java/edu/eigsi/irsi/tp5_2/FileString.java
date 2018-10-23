package edu.eigsi.irsi.tp5_2;

//Classe permettant de lire ou d'écrire sur la SD Card
//Nécessite la permission :     
//
// Pour les SdkVersion > 23 autoriser explicitement dans les propriétés de l'application
// l'ecriture sur la carte SD 

import android.app.Activity;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileString {
  private final Activity activity;
  private File file;
	private BufferedReader bufferredReader = null;
	private BufferedWriter bufferedWriter = null;

  public FileString(Activity activity) {
    this.activity = activity;
  }

	/**
	 * Créer ou ouvrir un fichier.
	 * @param fileName : nom du fichier
	 * @param mode : "w" en écriture, "r" en lecture
	 * @return true si l'opération réussit
	 */
	public boolean createFile(String fileName, String mode) {
		boolean status = false;

    if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
			file = new File(Environment.getExternalStorageDirectory(), fileName);
        try {
        if (mode.contains("w")) {
          bufferedWriter = new BufferedWriter(new FileWriter(file, false));
          status = true;
        }
				if (file.exists() && mode.contains("a")) {
          bufferedWriter = new BufferedWriter(new FileWriter(file, true));
          status = true;
        }
				if (file.exists() && mode.contains("r")) {
          bufferredReader = new BufferedReader(new FileReader(file));
          status = true;
        }
			}
			catch (IOException e) {
				Log.e("ErrFile", e.toString());
			}
			catch (SecurityException e) {
				Log.e("ErrFile", e.toString());
			}
			catch (Exception e) {
				Log.e("ErrFile", e.toString());
			}
		}
		return status;
	}

 	/**
	 * Ecrit une chaine de caractére sur la SD Card.
	 * N'ajoute pas de saut de ligne
	 * @param txt chaine de caractére é écrire
	 * @return true si l'opération réussit
	 */
  public boolean writeLine(String txt) {
    boolean status = false;
    if (bufferedWriter!=null) {
      try {
        bufferedWriter.write(txt);
        status = true;
      }
      catch (IOException e) {
        Log.e("ErrFile", e.toString());
      }
      catch (SecurityException e) {
        Log.e("ErrFile", e.toString()); // Traitement des exceptions ...
      }
      catch (Exception e) {
        Log.e("ErrFile", e.toString()); // Traitement des exceptions ...
      }
    }
    return status;
  }


	/**
	 * Ecrit une liste sur la SD Card.
	 * Ajoute un saut de ligne en chaque lignes
	 * @return true si l'opération réussit
	 */
  public boolean writeList(ArrayList<String> list)  {
    boolean status = false;
    if (bufferedWriter!=null) {
      try {
        for (String elem: list)
          bufferedWriter.write(elem + "\r\n");
        status = true;
      }
      catch (IOException e) {
        Log.e("ErrFile", e.toString());
      }
      catch (SecurityException e) {
        Log.e("ErrFile", e.toString()); // Traitement des exceptions ...
      }
      catch (Exception e) {
        Log.e("ErrFile", e.toString()); // Traitement des exceptions ...
      }
    }
    return status;
  }
	/**
	 * Lit un fichier sur la SD Card
	 * @return Liste contennant le fichier lu ou nul, si fichier non ouvert
	 */
  public ArrayList<String> readFile() {
    ArrayList<String> list = null;
    if (bufferredReader!=null) {
      list = new ArrayList<String>();
      try {
        String str;
        while ((str=bufferredReader.readLine())!=null)
          list.add(str);
      }
      catch (IOException e) {
        Log.e("ErrFile", e.toString());
        list.add("");
      }
    }
    return list;
  }


  /**
	 * Ferme le fichier courant sur la SD Card
	 */
  public void close() {
    try {
      if (bufferredReader != null) bufferredReader.close();
      if (bufferedWriter != null)  bufferedWriter.close();
    }
    catch (IOException e) {
      Log.e("ErrFile", e.toString());
    }
  }

	/**
	 * Efface le fichier courant sur la SD Card
	 * @return true si l'opération réussit
	 */
	public boolean delete() {
		return (file != null && file.delete());
	}
}
