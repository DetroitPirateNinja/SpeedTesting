/**
 * Class: SpeedTestLoader
 *
 * @author Kevin Hein
 *
 * 2021-10-14   Kevin Hein  Created
 *
 * Description
 * -----------
 * The purpose of the SpeedTestLoader is to load the JSON results from the SpeedTest into the
 * MySQL database
 */
import java.io.FileReader;
import java.util.Iterator;
import java.util.Map;

// JSON Libraries
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class SpeedTestLoader
{
    /**
     * Main method
     *
     * @param args[0] - Should be the name of the file with the results
     */
    public static void main(String args[])
    {
        // Read the name of the file that has the results
        // If there is no file - stop processing
        if ( args.length != 1 )
        {
            System.err.println("Usage is:  java SpeedTestLoader results_file_name");
            System.exit(1);
        }

        // Get the information from the file
        try
        {
            // Parsing the JSON file
            Object obj = new JSONParser().parse(new FileReader(args[0]));

            // Typecasting obj to JSONObject
            JSONObject jo = (JSONObject) obj;

            // Getting info out of the file
            // Get the general info
            String field1 = (String) jo.get(Constants.TYPE);
            String field2 = (String) jo.get(Constants.TIMESTAMP);

            // Get the Ping test
            JSONObject ping = (JSONObject) jo.get(Constants.PING);

            Double jitter = (Double) ping.get(Constants.JITTER);
            Double latency = (Double) ping.get(Constants.LATENCY);

            // Get the download speed
            JSONObject download = (JSONObject) jo.get(Constants.DOWNLOAD);

            Long downloadBandwidth = (Long) download.get(Constants.DOWNLOAD_BANDWIDTH);
            Long downloadBytes     = (Long) download.get(Constants.DOWNLOAD_BYTES);
            Long downloadElapsed   = (Long) download.get(Constants.DOWNLOAD_ELAPSED);

            // Get the upload speed
            JSONObject upload = (JSONObject) jo.get(Constants.UPLOAD);

            Long uploadBandwidth = (Long) upload.get(Constants.UPLOAD_BANDWIDTH);
            Long uploadBytes     = (Long) upload.get(Constants.UPLOAD_BYTES);
            Long uploadElapsed   = (Long) upload.get(Constants.UPLOAD_ELAPSED);

            // Get the ISP
            String ispName = (String) jo.get(Constants.ISP);

            // Get the computer interface information
            JSONObject networkInterface = (JSONObject) jo.get(Constants.INTERFACE);

            String internalIP    = (String) networkInterface.get(Constants.INTERFACE_INTERNAL_IP);
            String interfaceName = (String) networkInterface.get(Constants.INTERFACE_NAME);
            String macAddr       = (String) networkInterface.get(Constants.INTERFACE_MAC_ADDR);
            Boolean isVPN        = (Boolean) networkInterface.get(Constants.INTERFACE_IS_VPN);
            String externalIP    = (String) networkInterface.get(Constants.INTERFACE_EXTERNAL_IP);

            // Get the server information that provided the responses
            JSONObject server = (JSONObject) jo.get(Constants.SERVER);

            Long serverID         = (Long) server.get(Constants.SERVER_ID);
            String serverName     = (String) server.get(Constants.SERVER_NAME);
            String serverLocation = (String) server.get(Constants.SERVER_LOCATION);
            String serverCountry  = (String) server.get(Constants.SERVER_COUNTRY);
            String serverHost     = (String) server.get(Constants.SERVER_HOST);
            Long serverPort       = (Long) server.get(Constants.SERVER_PORT);
            String serverIP       = (String) server.get(Constants.SERVER_IP);

            // Get the result information
            JSONObject result = (JSONObject) jo.get(Constants.RESULT);

            String resultID  = (String) result.get(Constants.RESULT_ID);
            String resultURL = (String) result.get(Constants.RESULT_URL);

            // Print out the download information
            System.out.println("Download elapsed = " + downloadElapsed);

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        // Read the file

        // Connect to the database

        // Create the insert statement

        // Close the database connection

        // Close the file

        // Done
    }

}
