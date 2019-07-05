package com.example.pg;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.Calendar;

public class Main2Activity extends AppCompatActivity {
    EditText name, phone, email, website, description, category, beacon_id, map_id;
    Button uploud, new_item;
    DatabaseReference mDatabase;
    Uri icon_uri, g1_uri, g2_uri, g3_uri, g4_uri, g5_uri;
    String img_url, g1_url, g2_url, g3_url, g4_url, g5_url, x;
    ImageView icon, g1, g2, g3, g4, g5;
    String arr[] = new String[6];
    int i = 0;
    StorageReference storageReference;
    StorageReference filepath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        name = findViewById(R.id.name_edit_text);
        phone = findViewById(R.id.phone_edit_text);
        email = findViewById(R.id.email_edit_text);
        website = findViewById(R.id.website_edit_text);
        description = findViewById(R.id.description_edit_text);
        category = findViewById(R.id.category_edit_text);
        beacon_id = findViewById(R.id.beacon_id);
        map_id = findViewById(R.id.map_id);
        icon = findViewById(R.id.icon_image_view);
        uploud = findViewById(R.id.uploud_button);
        new_item = findViewById(R.id.act2_button);
        g1 = findViewById(R.id.image_view_gallary_1);
        g2 = findViewById(R.id.image_view_gallary_2);
        g3 = findViewById(R.id.image_view_gallary_3);
        g4 = findViewById(R.id.image_view_gallary_4);
        g5 = findViewById(R.id.image_view_gallary_5);

        arr[0] = "";

        Intent intent = getIntent();
        String s = intent.getStringExtra("category");


        Toast.makeText(Main2Activity.this, s, Toast.LENGTH_SHORT).show();

        new_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main2Activity.this, Main2Activity.class);
                startActivity(intent);
            }
        });

        mDatabase = FirebaseDatabase.getInstance().getReference("root").child(s);
        storageReference = FirebaseStorage.getInstance().getReference("uploads");
        uploud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userId = mDatabase.push().getKey();
                //    btn_Upload();

                img_url = arr[0];
                g1_url = arr[1];
                g2_url = arr[2];
                g3_url = arr[3];
                g4_url = arr[4];
                g5_url = arr[5];


                DataModule user = new DataModule(
                        name.getText().toString(),
                        phone.getText().toString(),
                        img_url,
                        email.getText().toString(),
                        website.getText().toString(),
                        description.getText().toString(),
                        category.getText().toString(),
                        beacon_id.getText().toString(),
                        map_id.getText().toString(),
                        g1_url,
                        g2_url,
                        g3_url,
                        g4_url,
                        g5_url
                );
                mDatabase.child(userId).setValue(user);
                i = 0;
            }
        });

    }

    public void imageView_click(View view) {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, 1);
    }

    public void gallery1_click(View view) {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, 2);
    }

    public void gallery2_click(View view) {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, 3);
    }

    public void gallery3_click(View view) {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, 4);
    }

    public void gallery4_click(View view) {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, 5);
    }

    public void gallery5_click(View view) {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, 6);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 1 && data != null) {
            icon_uri = data.getData();

            icon.setImageURI(icon_uri);
            Upload(icon_uri);
        } else if (resultCode == RESULT_OK && requestCode == 2 && data != null) {
            g1_uri = data.getData();
            g1.setImageURI(g1_uri);
            Upload(g1_uri);

        } else if (resultCode == RESULT_OK && requestCode == 3 && data != null) {
            g2_uri = data.getData();
            g2.setImageURI(g2_uri);
            Upload(g2_uri);
        } else if (resultCode == RESULT_OK && requestCode == 4 && data != null) {
            g3_uri = data.getData();
            g3.setImageURI(g3_uri);
            Upload(g3_uri);
        } else if (resultCode == RESULT_OK && requestCode == 5 && data != null) {
            g4_uri = data.getData();
            g4.setImageURI(g4_uri);
            Upload(g4_uri);
        } else if (resultCode == RESULT_OK && requestCode == 6 && data != null) {
            g5_uri = data.getData();
            g5.setImageURI(g5_uri);
            Upload(g5_uri);
        }

    }

    public void btn_Upload() {
        Calendar calendar = Calendar.getInstance();
        filepath = storageReference.child("photos").child("img_" + calendar.getTimeInMillis());


        filepath.putFile(icon_uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                filepath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        img_url = uri.toString();

                    }
                });
                Toast.makeText(getApplicationContext(), "is uploaded", Toast.LENGTH_SHORT).show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(Exception e) {
                Toast.makeText(getApplicationContext(), " nooo upload", Toast.LENGTH_SHORT).show();
            }
        });

    }


    public void Upload(Uri u) {
        Calendar calendar = Calendar.getInstance();
        filepath = storageReference.child("photos").child("img_" + calendar.getTimeInMillis());


        filepath.putFile(u).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                filepath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        arr[i] = uri.toString();
                        i++;

                        Log.v("aaaaaaa", uri.toString());
                    }
                });
                Toast.makeText(getApplicationContext(), "is uploaded", Toast.LENGTH_SHORT).show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(Exception e) {
                Toast.makeText(getApplicationContext(), " nooo upload", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
