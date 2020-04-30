import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class LogAggregator {

    public List<String> getResultList(Path filePath) throws IOException {
        List<String> resultList = new ArrayList<>();

        String resultRecord = new String(Files.readAllBytes(filePath));
        JSONArray logArray = new JSONArray(resultRecord);
        for (int i = 0; i < logArray.length(); i++) {
            JSONObject jsonObject = logArray.getJSONObject(i);
            resultList.add(jsonObject.getJSONObject("user_profile").getString("real_name"));
        }
        return resultList;
    }

    public List<String> getUsersList(Path userInfoListPath) throws IOException {
        List<String> usersList = new ArrayList<>();

        String usersRecord = new String(Files.readAllBytes(userInfoListPath));
        JSONArray usersArray = new JSONArray(usersRecord);
        for (int i = 0; i < usersArray.length(); i++) {
            JSONObject jsonObject = usersArray.getJSONObject(i);
            usersList.add(jsonObject.getString("real_name"));
        }
        return usersList;
    }

    public List<Info> getInfoList(List<String> resultList, List<String> usersList){
        List<Info> infoList = new ArrayList<>();
        usersList.forEach(user -> {
            Integer count = Math.toIntExact(resultList.stream().filter(user::equals).count());
            Info info = new Info();
            info.setUserName(user);
            info.setCount(count);
            infoList.add(info);
        });
        List<Info> sortedInfoList = infoList.stream().sorted(Comparator.comparing(Info::getCount, Comparator.reverseOrder())).collect(Collectors.toList());
        return sortedInfoList;
    }
}
