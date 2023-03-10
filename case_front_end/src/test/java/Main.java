import org.junit.jupiter.api.Test;

public class Main {
    @Test
    public void main() {
        String uri = "/case_front_end_war_exploded/brand/selectAll";
        String[] split = uri.split("/");
        System.out.println(split[2]);
    }
}
