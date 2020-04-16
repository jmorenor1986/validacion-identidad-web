package co.com.santander.adapters.common.utilities;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JsonUtilitiesImpl implements JsonUtilities {

    private static final Logger log = LoggerFactory.getLogger(JsonUtilitiesImpl.class);

    /*
    method that help to get property in any level, example country.direction return
    String json for direction, if not exist return exception
     */
    @Override
    public String getObjectWithKey(String nameObject, String jsonString) {
        try {
            String list[] = nameObject.split("\\.");
            String result = jsonString;
            for (String item : list) {
                result = getObjectToString(item, result);
            }
            return result;
        } catch (JSONException ex) {
            return "";
        }
    }

    @Override
    public Integer getIntWithKey(String nameObject, String jsonString) {
        return null;
    }

    /*
method that help to get property in any level, example country.directions, direction return
String property for direction, if not exist return exception
 */
    @Override
    public String getPropertyObjectWithKey(String nameObject, String property, String jsonString) {
        try {
            String list[] = nameObject.split("\\.");
            String result = jsonString;
            for (String item : list) {
                result = getObjectToString(item, result);
            }
            return getProperty(property, result);
        } catch (JSONException ex) {
            return "";
        }
    }

    @Override
    public List<String> getValuesForGivenKey(String nameObject, String nameArray, String nameKey, String jsonString) {
        return null;
    }

    @Override
    public String getValueForGivenKey(String nameObject, String nameKey, String jsonString) {
        try {
            String object = getObjectWithKey(nameObject, jsonString);
            log.info(object);
            JSONObject jsonObject = getJsonObject(object);
            Object valor = jsonObject.get(nameKey);
            return valor.toString();
        } catch (JSONException ex) {
            return "";
        }
    }

    private String getObjectToString(String name, String jsonString) throws JSONException {
        JSONObject jsonObject = new JSONObject(jsonString);
        return jsonObject.getJSONObject(name).toString();
    }

    private JSONObject getJsonObject(String jsonString) throws JSONException {
        return new JSONObject(jsonString);
    }


    private String getProperty(String name, String jsonString) throws JSONException {
        JSONObject jsonObject = new JSONObject(jsonString);
        return jsonObject.getString(name);
    }
}
