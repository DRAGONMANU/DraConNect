package com.example.draconnect;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ButtonActivity extends AppCompatActivity implements View.OnClickListener {
    Button add_bt;
    private ListView lv;

    ArrayList<String> arrayListip;
    ArrayList<String> arrayListport;
    ArrayList<String> arrayListname;
    ArrayList<String> arrayListparam;
    MyAdapter adapter;


    public final static String PREF_IP = "PREF_IP_ADDRESS";
    public final static String PREF_PORT = "PREF_PORT_NUMBER";
    // declare buttons and text inputs

    //dragon private Button buttonPin11,buttonPin12,buttonPin13;

    private EditText editTextIPAddress, editTextPort, editTextName, editTextParam;
    // shared preferences objects used to save the IP address and port so that the user doesn't have to
    // type them next time he/she opens the app.
    SharedPreferences.Editor editor;
    SharedPreferences sharedPreferences;
    private static final String TAG = ButtonActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.button);


        //sharedPreferences = getSharedPreferences("HTTP_HELPER_PREFS",Context.MODE_PRIVATE);
        //editor = sharedPreferences.edit();

        // assign listview
        lv = (ListView) findViewById(R.id.list_view);

        // assign button
        add_bt = (Button) findViewById(R.id.add_list_button);
        add_bt.setOnClickListener(this);

        // assign text inputs
        editTextIPAddress = (EditText) findViewById(R.id.add_item_ip);
        editTextPort = (EditText) findViewById(R.id.add_item_port);
        editTextName = (EditText) findViewById(R.id.add_item_name);
        editTextParam = (EditText) findViewById(R.id.add_item_param);

        //Arraylist
        arrayListip = new ArrayList<>();
        arrayListport = new ArrayList<>();
        arrayListname = new ArrayList<>();
        arrayListparam = new ArrayList<>();

        adapter = new MyAdapter(ButtonActivity.this, arrayListip, arrayListport, arrayListname, arrayListparam);
        lv.setAdapter(adapter);
        Log.d(TAG, "done");

        //onBtnClick();
        // set button listener (this class)
        //buttonPin11.setOnClickListener(this);
        //buttonPin12.setOnClickListener(this);
        //buttonPin13.setOnClickListener(this);

        // get the IP address and port number from the last time the user used the app,
        // put an empty string "" is this is the first time.
        //editTextIPAddress.setText(sharedPreferences.getString(PREF_IP,""));
        //editTextPort.setText(sharedPreferences.getString(PREF_PORT,""));
    }

    /*public void onBtnClick() {
        Log.d(TAG, "button");
        add_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ip = editTextIPAddress.getText().toString();
                String port = editTextPort.getText().toString();
                String name = editTextName.getText().toString();
                String param = editTextParam.getText().toString();
                arrayListip.add(ip);
                arrayListport.add(port);
                arrayListname.add(name);
                arrayListparam.add(param);
            }
        });
    }
*/
    class MyAdapter extends ArrayAdapter<String> {
        Context context;
        ArrayList<String> listip;
        ArrayList<String> listport;
        ArrayList<String> listname;
        ArrayList<String> listparam;

        MyAdapter(Context c, ArrayList<String> arrayListIp, ArrayList<String> arrayListport, ArrayList<String> arrayListname, ArrayList<String> arrayListparam) {
            super(c, R.layout.row, R.id.name, arrayListname);
            this.context = c;
            this.listip = arrayListIp;
            this.listport = arrayListport;
            this.listname = arrayListname;
            this.listparam = arrayListparam;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.row, parent, false);
            TextView name = (TextView) row.findViewById(R.id.name);
            TextView param = (TextView) row.findViewById(R.id.param);
            TextView ip = (TextView) row.findViewById(R.id.ip);
            TextView port = (TextView) row.findViewById(R.id.port);
            name.setText(listname.get(position));
            param.setText(listparam.get(position));
            ip.setText(listip.get(position));
            port.setText(listport.get(position));
            return row;
        }

    }


    @Override
    public void onClick(View view) {
        Log.d(TAG, "button");
        String ip = editTextIPAddress.getText().toString();
        String port = editTextPort.getText().toString();
        String name = editTextName.getText().toString();
        String param = editTextParam.getText().toString();
        arrayListip.add(ip);
        arrayListport.add(port);
        arrayListname.add(name);
        arrayListparam.add(param);

        adapter.notifyDataSetChanged();
    }
}
/*

        // get the pin number
        String parameterValue = "";
        // get the ip address
        String ipAddress = editTextIPAddress.getText().toString().trim();
        // get the port number
        String portNumber = editTextPort.getText().toString().trim();


        // save the IP address and port for the next time the app is used
        editor.putString(PREF_IP,ipAddress); // set the ip address value to save
        editor.putString(PREF_PORT,portNumber); // set the port number to save
        editor.commit(); // save the IP and PORT

        // get the pin number from the button that was clicked
        /*if(view.getId()==buttonPin11.getId())
        {
            parameterValue = "11";
        }
        else if(view.getId()==buttonPin12.getId())
        {
            parameterValue = "12";
        }
        else
        {
            parameterValue = "13";
        }



        // execute HTTP request
        if(ipAddress.length()>0 && portNumber.length()>0) {
            new HttpRequestAsyncTask(
                    view.getContext(), parameterValue, ipAddress, portNumber, "pin"
            ).execute();
        }
*/    //}

    /**
     * Description: Send an HTTP Get request to a specified ip address and port.
     * Also send a parameter "parameterName" with the value of "parameterValue".
     * @param parameterValue the pin number to toggle
     * @param ipAddress the ip address to send the request to
     * @param portNumber the port number of the ip address
     * @param parameterName
     * @return The ip address' reply text, or an ERROR message is it fails to receive one
     */
 /*   public String sendRequest(String parameterValue, String ipAddress, String portNumber, String parameterName)
    {
        String serverResponse = "ERROR";

        try {

            HttpClient httpclient = new DefaultHttpClient(); // create an HTTP client
            // define the URL e.g. http://myIpaddress:myport/?pin=13 (to toggle pin 13 for example)
            URI website = new URI("http://"+ipAddress+":"+portNumber+"/?"+parameterName+"="+parameterValue);
            HttpGet getRequest = new HttpGet(); // create an HTTP GET object
            getRequest.setURI(website); // set the URL of the GET request
            HttpResponse response = httpclient.execute(getRequest); // execute the request
            // get the ip address server's reply
            InputStream content = null;
            content = response.getEntity().getContent();
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    content
            ));
            serverResponse = in.readLine();
            // Close the connection
            content.close();
        } catch (ClientProtocolException e) {
            // HTTP error
            serverResponse = e.getMessage();
            e.printStackTrace();
        } catch (IOException e) {
            // IO error
            serverResponse = e.getMessage();
            e.printStackTrace();
        } catch (URISyntaxException e) {
            // URL syntax error
            serverResponse = e.getMessage();
            e.printStackTrace();
        }
        // return the server's reply/response text
        return serverResponse;
    }

*/
    /**
     * An AsyncTask is needed to execute HTTP requests in the background so that they do not
     * block the user interface.
     */
  /*   private class HttpRequestAsyncTask extends AsyncTask<Void, Void, Void> {

        // declare variables needed
        private String requestReply,ipAddress, portNumber;
        private Context context;
        private AlertDialog alertDialog;
        private String parameter;
        private String parameterValue;
*/
        /**
         * Description: The asyncTask class constructor. Assigns the values used in its other methods.
         * @param context the application context, needed to create the dialog
         * @param parameterValue the pin number to toggle
         * @param ipAddress the ip address to send the request to
         * @param portNumber the port number of the ip address
         */
 /*       public HttpRequestAsyncTask(Context context, String parameterValue, String ipAddress, String portNumber, String parameter)
        {
            this.context = context;

            alertDialog = new AlertDialog.Builder(this.context)
                    .setTitle("HTTP Response From IP Address:")
                    .setCancelable(true)
                    .create();

            this.ipAddress = ipAddress;
            this.parameterValue = parameterValue;
            this.portNumber = portNumber;
            this.parameter = parameter;
        }
*/
        /**
         * Name: doInBackground
         * Description: Sends the request to the ip address
         * @param voids
         * @return
         */
/*        @Override
        protected Void doInBackground(Void... voids) {
            alertDialog.setMessage("Data sent, waiting for reply from server...");
            if(!alertDialog.isShowing())
            {
                alertDialog.show();
            }
            requestReply = sendRequest(parameterValue,ipAddress,portNumber, parameter);
            return null;
        }
*/
        /**
         * Name: onPostExecute
         * Description: This function is executed after the HTTP request returns from the ip address.
         * The function sets the dialog's message with the reply text from the server and display the dialog
         * if it's not displayed already (in case it was closed by accident);
         * @param aVoid void parameter
         */
 /*       @Override
        protected void onPostExecute(Void aVoid) {
            alertDialog.setMessage(requestReply);
            if(!alertDialog.isShowing())
            {
                alertDialog.show(); // show dialog
            }
        }
*/
        /**
         * Name: onPreExecute
         * Description: This function is executed before the HTTP request is sent to ip address.
         * The function will set the dialog's message and display the dialog.
         */
 /*       @Override
        protected void onPreExecute() {
            alertDialog.setMessage("Sending data to server, please wait...");
            if(!alertDialog.isShowing())
            {
                alertDialog.show();
            }
        }

    }
*/
//}
