package com.dybcatering.live4teach.Tutor.EditorTexto;

import android.graphics.Color;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.dybcatering.live4teach.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import jp.wasabeef.richeditor.RichEditor;

public class EditorTextoActivity extends AppCompatActivity {

	private RichEditor mEditor;
	private TextView mPreview;
	private static final String FILE_NAME = "documento.doc";


	@RequiresApi(api = Build.VERSION_CODES.KITKAT)
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pizarra);
		System.setProperty("org.apache.poi.javax.xml.stream.XMLInputFactory", "com.fasterxml.aalto.stax.InputFactoryImpl");
		System.setProperty("org.apache.poi.javax.xml.stream.XMLOutputFactory", "com.fasterxml.aalto.stax.OutputFactoryImpl");
		System.setProperty("org.apache.poi.javax.xml.stream.XMLEventFactory", "com.fasterxml.aalto.stax.EventFactoryImpl");



		mEditor = (RichEditor) findViewById(R.id.editor);
		mEditor.setEditorHeight(200);
		mEditor.setEditorFontSize(22);
		mEditor.setEditorFontColor(Color.RED);
		//mEditor.setEditorBackgroundColor(Color.BLUE);
		//mEditor.setBackgroundColor(Color.BLUE);
		//mEditor.setBackgroundResource(R.drawable.bg);
		mEditor.setPadding(10, 10, 10, 10);
		//mEditor.setBackground("https://raw.githubusercontent.com/wasabeef/art/master/chip.jpg");
		mEditor.setPlaceholder("Empieza a Escribir aqui...");
		//mEditor.setInputEnabled(false);

		mPreview = (TextView) findViewById(R.id.preview);

		final File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);

		mEditor.setOnTextChangeListener(new RichEditor.OnTextChangeListener() {
			@Override public void onTextChange(String text) {
				mPreview.setText(text);
			}
		});

		findViewById(R.id.exportar).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				save();
				//ExportarDocumento exportarDocumento = new ExportarDocumento();
				//		CrearDoc(path, mEditor.toString().trim());
			}
		});

		findViewById(R.id.action_undo).setOnClickListener(new View.OnClickListener() {
			@Override public void onClick(View v) {
				mEditor.undo();
			}
		});

		findViewById(R.id.action_redo).setOnClickListener(new View.OnClickListener() {
			@Override public void onClick(View v) {
				mEditor.redo();
			}
		});

		findViewById(R.id.action_bold).setOnClickListener(new View.OnClickListener() {
			@Override public void onClick(View v) {
				mEditor.setBold();
			}
		});

		findViewById(R.id.action_italic).setOnClickListener(new View.OnClickListener() {
			@Override public void onClick(View v) {
				mEditor.setItalic();
			}
		});

		findViewById(R.id.action_subscript).setOnClickListener(new View.OnClickListener() {
			@Override public void onClick(View v) {
				mEditor.setSubscript();
			}
		});

		findViewById(R.id.action_superscript).setOnClickListener(new View.OnClickListener() {
			@Override public void onClick(View v) {
				mEditor.setSuperscript();
			}
		});

		findViewById(R.id.action_strikethrough).setOnClickListener(new View.OnClickListener() {
			@Override public void onClick(View v) {
				mEditor.setStrikeThrough();
			}
		});

		findViewById(R.id.action_underline).setOnClickListener(new View.OnClickListener() {
			@Override public void onClick(View v) {
				mEditor.setUnderline();
			}
		});

		findViewById(R.id.action_heading1).setOnClickListener(new View.OnClickListener() {
			@Override public void onClick(View v) {
				mEditor.setHeading(1);
			}
		});

		findViewById(R.id.action_heading2).setOnClickListener(new View.OnClickListener() {
			@Override public void onClick(View v) {
				mEditor.setHeading(2);
			}
		});

		findViewById(R.id.action_heading3).setOnClickListener(new View.OnClickListener() {
			@Override public void onClick(View v) {
				mEditor.setHeading(3);
			}
		});

		findViewById(R.id.action_heading4).setOnClickListener(new View.OnClickListener() {
			@Override public void onClick(View v) {
				mEditor.setHeading(4);
			}
		});

		findViewById(R.id.action_heading5).setOnClickListener(new View.OnClickListener() {
			@Override public void onClick(View v) {
				mEditor.setHeading(5);
			}
		});

		findViewById(R.id.action_heading6).setOnClickListener(new View.OnClickListener() {
			@Override public void onClick(View v) {
				mEditor.setHeading(6);
			}
		});

		findViewById(R.id.action_txt_color).setOnClickListener(new View.OnClickListener() {
			private boolean isChanged;

			@Override public void onClick(View v) {
				mEditor.setTextColor(isChanged ? Color.BLACK : Color.RED);
				isChanged = !isChanged;
			}
		});

		findViewById(R.id.action_bg_color).setOnClickListener(new View.OnClickListener() {
			private boolean isChanged;

			@Override public void onClick(View v) {
				mEditor.setTextBackgroundColor(isChanged ? Color.TRANSPARENT : Color.YELLOW);
				isChanged = !isChanged;
			}
		});

		findViewById(R.id.action_indent).setOnClickListener(new View.OnClickListener() {
			@Override public void onClick(View v) {
				mEditor.setIndent();
			}
		});

		findViewById(R.id.action_outdent).setOnClickListener(new View.OnClickListener() {
			@Override public void onClick(View v) {
				mEditor.setOutdent();
			}
		});

		findViewById(R.id.action_align_left).setOnClickListener(new View.OnClickListener() {
			@Override public void onClick(View v) {
				mEditor.setAlignLeft();
			}
		});

		findViewById(R.id.action_align_center).setOnClickListener(new View.OnClickListener() {
			@Override public void onClick(View v) {
				mEditor.setAlignCenter();
			}
		});

		findViewById(R.id.action_align_right).setOnClickListener(new View.OnClickListener() {
			@Override public void onClick(View v) {
				mEditor.setAlignRight();
			}
		});

		findViewById(R.id.action_blockquote).setOnClickListener(new View.OnClickListener() {
			@Override public void onClick(View v) {
				mEditor.setBlockquote();
			}
		});

		findViewById(R.id.action_insert_bullets).setOnClickListener(new View.OnClickListener() {
			@Override public void onClick(View v) {
				mEditor.setBullets();
			}
		});

		findViewById(R.id.action_insert_numbers).setOnClickListener(new View.OnClickListener() {
			@Override public void onClick(View v) {
				mEditor.setNumbers();
			}
		});

		findViewById(R.id.action_insert_image).setOnClickListener(new View.OnClickListener() {
			@Override public void onClick(View v) {
				mEditor.insertImage("https://image.shutterstock.com/image-vector/hola-spanish-greeting-handwritten-white-600w-1080284534.jpg",
						"dachshund");
			}
		});

		findViewById(R.id.action_insert_link).setOnClickListener(new View.OnClickListener() {
			@Override public void onClick(View v) {
				mEditor.insertLink("https://github.com/wasabeef", "wasabeef");
			}
		});
		findViewById(R.id.action_insert_checkbox).setOnClickListener(new View.OnClickListener() {
			@Override public void onClick(View v) {
				mEditor.insertTodo();
			}
		});
	}

	public void save() {
		String text = mPreview.getText().toString();
		FileOutputStream fos = null;

		try {
			fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
			fos.write(text.getBytes());

			//mEditor.clear();
			Toast.makeText(this, "Saved to " + getFilesDir() + "/" + FILE_NAME,
					Toast.LENGTH_LONG).show();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
