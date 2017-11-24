package juma.strathmore.com.volley;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Cache;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import static android.R.attr.id;
import static android.system.Os.remove;

public class MainActivity extends AppCompatActivity {
    private static final String IMAGE_REQUEST_URL = "https://androidtutorial.com/api/lg_nexus_5x";
    private static final String STRING_REQUEST_URL = "https://androidtutorial.com/api/VolleyString";
    private static final String JSON_OBJECT_REQUEST_URL = "https://androidtutorial.com/api/volleyJsonObject";
    private static final String JSON_ARRAY_REQUEST_URL = "https://androidtutorial.com/api/volleyJsonArray";
    ProgressDialog progressDialog;
    private static final String TAG = "Server Response";
    private Button stringRequestButton, JsonObjectRequestButton, JsonArrayRequestButton, ImageRequestButton;
    private View showDialogView;
    private TextView outputTextView;
    private ImageView outputImageView;
    //private Button JsonObjectRequestButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        progressDialog = new ProgressDialog(this);
        stringRequestButton = (Button) findViewById(R.id.button_get_string);
        JsonObjectRequestButton = (Button) findViewById(R.id.button_get_Json_object);
        JsonArrayRequestButton = (Button) findViewById(R.id.button_get_Json_array);
        ImageRequestButton = (Button) findViewById(R.id.button_get_image);
        //click listeners
        stringRequestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                volleyJsonObjectRequest(STRING_REQUEST_URL);
            }
        });
        JsonObjectRequestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                volleyJsonObjectRequest(JSON_OBJECT_REQUEST_URL);


            }
        });
        JsonArrayRequestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                volleyJsonArrayRequest(JSON_ARRAY_REQUEST_URL);

            }
        });
        ImageRequestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                volleyImageLoader(IMAGE_REQUEST_URL);
            }
        });

    }


    public void volleyStringRequest(String url) {
        String REQUEST_TAG = "juma.strathmore.com.volley";


        progressDialog.setMessage("Loading...");
        progressDialog.show();

        StringRequest strReq = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, response.toString());
                LayoutInflater li = LayoutInflater.from(MainActivity.this);
                showDialogView = li.inflate(R.layout.show_dialog, null);
                outputTextView = (TextView) showDialogView.findViewById(R.id.text_view_dialog);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                alertDialogBuilder.setView(showDialogView);
                alertDialogBuilder
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {

                            }
                        })
                        .setCancelable(false)
                        .create();
                outputTextView.setText(response.toString());
                alertDialogBuilder.show();
                progressDialog.hide();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                progressDialog.hide();
            }

        });
        //Adding String request to request queue
        AppSingleton.getInstance(getApplicationContext()).addToRequestQueue(strReq, REQUEST_TAG);

    }

    public void volleyJsonObjectRequest(String url) {
        String REQUEST_TAG = "juma.strathmore.com.volley";
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        JsonObjectRequest jsonObjectReq = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());

                        LayoutInflater li = LayoutInflater.from(MainActivity.this);
                        showDialogView = li.inflate(R.layout.show_dialog, null);
                        outputTextView = (TextView) showDialogView.findViewById(R.id.text_view_dialog);
                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                        alertDialogBuilder.setView(showDialogView);
                        alertDialogBuilder
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {

                                    }
                                })
                                .setCancelable(false)
                                .create();
                        outputTextView.setText(response.toString());
                        alertDialogBuilder.show();
                        progressDialog.hide();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                progressDialog.hide();
            }

        });
        // Adding JsonObject request to request queue
        AppSingleton.getInstance(getApplicationContext()).addToRequestQueue(jsonObjectReq, REQUEST_TAG);
    }

    public void volleyJsonArrayRequest(String url) {
        String REQUEST_TAG = "juma.strathmore.com.volley.volleyJsonArrayRequest";
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        JsonArrayRequest jsonArrayReq = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());
                        LayoutInflater li = LayoutInflater.from(MainActivity.this);
                        showDialogView = li.inflate(R.layout.show_dialog, null);
                        outputTextView = (TextView) showDialogView.findViewById(R.id.text_view_dialog);
                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                        alertDialogBuilder.setView(showDialogView);
                        alertDialogBuilder
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                    }

                                })
                                .setCancelable(false)
                                .create();
                        outputTextView.setText(response.toString());
                        alertDialogBuilder.show();
                        progressDialog.hide();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                progressDialog.hide();
            }

        });
        //Adding JsonObject request to request queue
        AppSingleton.getInstance(getApplicationContext()).addToRequestQueue(jsonArrayReq, REQUEST_TAG);
    }

    public void volleyImageLoader(String url) {
        ImageLoader imageLoader = AppSingleton.getInstance(getApplicationContext()).getmImageLoader();

        imageLoader.get(url, new ImageLoader.ImageListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Image Load Error: " + error.getMessage());
            }

            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean arg1) {
                if (response.getBitmap() != null) {
                    LayoutInflater li = LayoutInflater.from(MainActivity.this);
                    showDialogView = li.inflate(R.layout.show_dialog, null);
                    outputImageView = (ImageView) showDialogView.findViewById(R.id.image_view_dialog);
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                    alertDialogBuilder.setView(showDialogView);
                    alertDialogBuilder
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                }

                            })
                            .setCancelable(false)
                            .create();
                    outputImageView.setImageBitmap(response.getBitmap());
                    alertDialogBuilder.show();


                }

            }
        });
    }

    public void volleyCacheRequest(String url) {
        Cache cache = AppSingleton.getInstance(getApplicationContext()).getRequestQueue().getCache();
        Cache.Entry reqEntry = cache.get(url);
        if (reqEntry != null) {
            try {
                String data = new String(reqEntry.data, "UTF-8");
                //hANDLE Data Here
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        } else {
            //request Not present in cache, launch a network request instead.

        }
    }

    public void volleyInvalidatesCache(String url) {
        AppSingleton.getInstance(getApplicationContext()).getRequestQueue().getCache().invalidate(url, true);

    }

    public void volleyDeleteCache(String url) {
        AppSingleton.getInstance(getApplicationContext()).getRequestQueue().getCache().remove(url);
    }

    public void volleyClearCache() {
        AppSingleton.getInstance(getApplicationContext()).getRequestQueue().getCache().clear();
    }

}