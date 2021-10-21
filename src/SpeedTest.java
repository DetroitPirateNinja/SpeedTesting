/**
 * Class: SpeedTest
 *
 * @author Kevin Hein
 *
 * 2021-10-21   Kevin Hein  Created
 *
 * Description
 * -----------
 * POJO for the results from the Speed test
 */

// Import file handlers
import java.io.FileReader;

// JSON Libraries
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class SpeedTest
{
    // Atributes
    private String jsonFile;
    private String result;
    private String timestamp;
    private Double jitter;
    private Double latency;
    private Long downloadBandwidth;
    private Long downloadBytes;
    private Long downloadElapsed;
    private Long uploadBandwidth;
    private Long uploadBytes;
    private Long uploadElapsed;
    private String ispName;
    private String internalIP;
    private String interfaceName;
    private String macAddr;
    private Boolean isVPN;
    private String externalIP;
    private Long serverID;
    private String serverName;
    private String serverLocation;
    private String serverCountry;
    private String serverHost;
    private Long serverPort;
    private String serverIP;
    private String resultID;
    private String resultURL;

    public SpeedTest(String jsonFileName)
    {
        jsonFile = jsonFileName;
    }

    public boolean parseFile()
    {
        try
        {
            // Parsing the JSON file
            Object obj = new JSONParser().parse(new FileReader(jsonFile));

            // Typecasting obj to JSONObject
            JSONObject jo = ( JSONObject ) obj;

            // Getting info out of the file
            // Get the general info
            result = ( String ) jo.get(Constants.TYPE);
            timestamp = ( String ) jo.get(Constants.TIMESTAMP);

            // Get the Ping test
            JSONObject ping = ( JSONObject ) jo.get(Constants.PING);

            jitter = ( Double ) ping.get(Constants.JITTER);
            latency = ( Double ) ping.get(Constants.LATENCY);

            // Get the download speed
            JSONObject download = ( JSONObject ) jo.get(Constants.DOWNLOAD);

            downloadBandwidth = ( Long ) download.get(Constants.DOWNLOAD_BANDWIDTH);
            downloadBytes = ( Long ) download.get(Constants.DOWNLOAD_BYTES);
            downloadElapsed = ( Long ) download.get(Constants.DOWNLOAD_ELAPSED);

            // Get the upload speed
            JSONObject upload = ( JSONObject ) jo.get(Constants.UPLOAD);

            uploadBandwidth = ( Long ) upload.get(Constants.UPLOAD_BANDWIDTH);
            uploadBytes = ( Long ) upload.get(Constants.UPLOAD_BYTES);
            uploadElapsed = ( Long ) upload.get(Constants.UPLOAD_ELAPSED);

            // Get the ISP
            ispName = ( String ) jo.get(Constants.ISP);

            // Get the computer interface information
            JSONObject networkInterface = ( JSONObject ) jo.get(Constants.INTERFACE);

            internalIP = ( String ) networkInterface.get(Constants.INTERFACE_INTERNAL_IP);
            interfaceName = ( String ) networkInterface.get(Constants.INTERFACE_NAME);
            macAddr = ( String ) networkInterface.get(Constants.INTERFACE_MAC_ADDR);
            isVPN = ( Boolean ) networkInterface.get(Constants.INTERFACE_IS_VPN);
            externalIP = ( String ) networkInterface.get(Constants.INTERFACE_EXTERNAL_IP);

            // Get the server information that provided the responses
            JSONObject server = ( JSONObject ) jo.get(Constants.SERVER);

            serverID = ( Long ) server.get(Constants.SERVER_ID);
            serverName = ( String ) server.get(Constants.SERVER_NAME);
            serverLocation = ( String ) server.get(Constants.SERVER_LOCATION);
            serverCountry = ( String ) server.get(Constants.SERVER_COUNTRY);
            serverHost = ( String ) server.get(Constants.SERVER_HOST);
            serverPort = ( Long ) server.get(Constants.SERVER_PORT);
            serverIP = ( String ) server.get(Constants.SERVER_IP);

            // Get the result information
            JSONObject result = ( JSONObject ) jo.get(Constants.RESULT);

            resultID = ( String ) result.get(Constants.RESULT_ID);
            resultURL = ( String ) result.get(Constants.RESULT_URL);

            return (true);
        }
        catch (Exception e)
        {
            System.err.println("Error loading the file");

            e.printStackTrace();

            return(false);
        }
    }

    // Acessor methods
    public String getFileName()
    {
        return(jsonFile);
    }

    public String getResult()
    {
        return(result);
    }

    public String getTimestamp()
    {
        return(timestamp);
    }

    public Double getJitter()
    {
        return(jitter);
    }

    public Double getLatency()
    {
        return(latency);
    }

    public Long getDownloadBandwidth()
    {
        return(downloadBandwidth);
    }

    public Long getDownloadBytes()
    {
        return(downloadBytes);
    }

    public Long getDownloadElapsed()
    {
        return(downloadElapsed);
    }

    public Long getUploadBandwidth()
    {
        return(uploadBandwidth);
    }

    public Long getUploadBytes()
    {
        return(uploadBytes);
    }

    public Long getUploadElapsed()
    {
        return(uploadElapsed);
    }

    public String getIspName()
    {
        return(ispName);
    }

    public String getInternalIP()
    {
        return(internalIP);
    }

    public String getInterfaceName()
    {
        return(interfaceName);
    }

    public String getMacAddr()
    {
        return(macAddr);
    }

    public Boolean getIsVPN()
    {
        return(isVPN);
    }

    public String getExternalIP()
    {
        return(externalIP);
    }

    public Long getServerID()
    {
        return(serverID);
    }

    public String getServerName()
    {
        return(serverName);
    }

    public String getServerLocation()
    {
        return(serverLocation);
    }

    public String getServerCountry()
    {
        return(serverCountry);
    }

    public String getServerHost()
    {
        return(serverHost);
    }

    public Long getServerPort()
    {
        return(serverPort);
    }

    public String getServerIP()
    {
        return(serverIP);
    }

    public String getResultID()
    {
        return(resultID);
    }

    public String getResultURL()
    {
        return(resultURL);
    }
}