package Utility;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import TestData.amazdata;
import java.io.File;

    public class JsonReader {

        public static amazdata getData() {
            amazdata data = null;
            try {
                ObjectMapper mapper = new ObjectMapper();
//                data = mapper.readValue(new File("src/test/java/TestData/amazdata.json"), amazdata.class);
                JsonNode rootNode = mapper.readTree(new File("src/test/java/TestData/amazdata.json"));
                JsonNode amazNode = rootNode.path("amazdata");
                 data = mapper.treeToValue(amazNode, amazdata.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return data;
        }
    }


