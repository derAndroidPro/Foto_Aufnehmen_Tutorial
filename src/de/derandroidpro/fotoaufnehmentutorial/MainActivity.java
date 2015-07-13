package de.derandroidpro.fotoaufnehmentutorial;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {
	


	Button btn1;
	ImageView iv1;
	Intent bildintent;
	
	File bildfile = new File(Environment.getExternalStorageDirectory() + "/FotoApp/bild.png");
	Uri bilduri = Uri.fromFile(bildfile);
	
	int Kameracode = 15;
	
	Bitmap bm1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		btn1 = (Button) findViewById(R.id.button1);
		iv1 = (ImageView) findViewById(R.id.imageView1);
		
		if (bildfile.exists()){
		bm1 = BitmapFactory.decodeFile(bildfile.getAbsolutePath());
		iv1.setImageBitmap(bm1);
		}
		
		btn1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				try{
				
				bildintent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				bildintent.putExtra(MediaStore.EXTRA_OUTPUT, bilduri);
				startActivityForResult(bildintent, Kameracode);
				
				}catch(Exception e){
					
					e.printStackTrace();
					Toast.makeText(getApplicationContext(), "Kamera nicht unterstützt!", Toast.LENGTH_SHORT).show();
					
				}
				
			}
		});
	}


	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		
		if (resultCode == RESULT_OK){
			
			if(requestCode == Kameracode){
				
				Toast.makeText(getApplicationContext(), "Bild gespeichert unter: " + bildfile.getAbsolutePath(), Toast.LENGTH_SHORT).show();
				
				bm1 = BitmapFactory.decodeFile(bildfile.getAbsolutePath());
				iv1.setImageBitmap(bm1);
				
				
			}
			
			
			
		}
		
		
		super.onActivityResult(requestCode, resultCode, data);
	}
	
}
