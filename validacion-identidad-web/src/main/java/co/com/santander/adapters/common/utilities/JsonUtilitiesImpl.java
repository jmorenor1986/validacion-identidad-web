package co.com.santander.adapters.common.utilities;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JsonUtilitiesImpl implements JsonUtilities {

    private static final Logger log = LoggerFactory.getLogger(JsonUtilitiesImpl.class);

    /*
    method that help to get property in any level, example country.direction return
    String json for direction, if not exist return exception
     */
    @Override
    public Optional<String> getObjectWithKey(String nameObject, String jsonString) {
        try {
            String list[] = nameObject.split("\\.");
            String result = jsonString;
            for (String item : list) {
                result = getObjectToString(item, result);
            }
            return Optional.of(result);
        } catch (JSONException ex) {
            return Optional.empty();
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
    public Optional<String> getValueForGivenKey(String nameObject, String nameKey, String jsonString) {
        try {
            String object = getObjectWithKey(nameObject, jsonString).get();
            log.info(object);
            JSONObject jsonObject = getJsonObject(object);
            return Optional.of(String.valueOf(jsonObject.get(nameKey)));
        } catch (JSONException ex) {
            return Optional.empty();
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
