package edu.hw8;

import edu.hw8.Task3.HackPassword;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task3Test {

    @Test
    @DisplayName("Проверка работы HackPassword")
    void hackPasswordTest() throws NoSuchAlgorithmException, UnsupportedEncodingException, InterruptedException {
        Map<String, String> users = new HashMap<>(Map.ofEntries(
            Map.entry("d78b6f30225cdc811adfe8d4e7c9fd34", "user1"),
            Map.entry("4afa19649ae378da31a423bcd78a97c8", "user2")
        ));

        HackPassword hackPassword1 = new HackPassword(users);
        HackPassword hackPassword2 = new HackPassword(users);

        Map<String, String> result1 = hackPassword1.nextPasswordSingleThread();
        Map<String, String> result2 = hackPassword2.nextPasswordMultiThread(8);

        assertThat(result1).isEqualTo(result2);
        assertThat(result1.size()).isEqualTo(2);
        assertThat(result1.containsKey("user1") && result1.containsValue("hack")).isTrue();
        assertThat(result1.containsKey("user2") && result2.containsValue("7443")).isTrue();
    }
}
