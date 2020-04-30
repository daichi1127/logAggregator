import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner stdrIn = new Scanner(System.in);
        String stdrInStr = stdrIn.nextLine();
        stdrIn.close();

        Path resultPath = Paths.get("/Users/earthmurakami/IdeaProjects/logAggregator/log/general/" + stdrInStr + ".json");
        Path userInfoListPath = Paths.get("/Users/earthmurakami/IdeaProjects/logAggregator/log/users.json");
        LogAggregator logAggr = new LogAggregator();
        List<String> resultList = logAggr.getResultList(resultPath);
        List<String> usersList = logAggr.getUsersList(userInfoListPath);
        List<Info> infoList = logAggr.getInfoList(resultList, usersList);

        System.out.println("commnet count: user name");
        infoList.forEach(value -> {
            System.out.print(value.getCount());
            System.out.print(": ");
            System.out.println(value.getUserName());
        });
    }
}
